package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.exception.UserNotFoundException;
import com.gangster.cms.admin.service.GroupService;
import com.gangster.cms.admin.service.UserService;
import com.gangster.cms.common.pojo.*;
import com.gangster.cms.dao.mapper.UserGroupMapper;
import com.gangster.cms.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, UserExample> implements UserService {
    @Autowired
    private
    GroupService groupService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private
    UserGroupMapper userGroupMapper;

    private static final String ADMIN = "admin";

    @Override
    public void deleteUser(Integer userId) throws UserNotFoundException {
        //delete user's own group
        Group group = groupService.findUserOwnGroup(userId);
        groupService.deleteGroup(group.getGroupId());
        //delete user-group map
        UserGroupExample userGroupExample = new UserGroupExample();
        userGroupExample.or().andUserIdEqualTo(userId);
        userGroupMapper.deleteByExample(userGroupExample);

        //delete user
        deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(User user) {
        return createUser(user);
    }

    @Override
    public int createUser(User user) {
        user.setUserCreateTime(new Date());
        int ret = super.insert(user);

        Group group = new Group();
        group.setGroupName(user.getUserName());
        groupService.insert(group);

        UserGroup userGroup = new UserGroup(user.getUserId(), group.getGroupId());
        userGroupMapper.insert(userGroup);
        return ret;
    }
}