package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.WebFileService;
import com.gangster.cms.common.pojo.WebFile;
import com.gangster.cms.common.pojo.WebFileExample;
import com.gangster.cms.dao.mapper.WebFileMapper;
import org.springframework.stereotype.Service;

/**
 * @author Yoke
 * Created on 2018/4/18
 */
@Service
public class WebFileServiceImpl extends BaseServiceImpl<WebFileMapper, WebFile, WebFileExample> implements WebFileService {
}
