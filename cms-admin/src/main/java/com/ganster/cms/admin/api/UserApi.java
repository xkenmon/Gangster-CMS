package com.ganster.cms.admin.api;

import com.ganster.cms.admin.dto.MessageDto;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * @author Yoke
 * Created on 2018/4/15
 */
@RestController
@RequestMapping("/user/api")
public class UserApi {


    @GetMapping("/judge")
    public MessageDto judge(@SessionAttribute(CmsConst.CURRENT_USER) User user) {
        if (user.getUserIsAdmin()) {
            return new CmsResultUtil<>().setData(true);
        }
        return new CmsResultUtil<>().setData(false);
    }

}
