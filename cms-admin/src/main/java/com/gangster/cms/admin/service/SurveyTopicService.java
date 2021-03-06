package com.gangster.cms.admin.service;

import com.gangster.cms.admin.base.BaseService;
import com.gangster.cms.common.pojo.SurveyTopic;
import com.gangster.cms.common.pojo.SurveyTopicExample;

import java.util.List;

/**
 * @author Yoke
 * Created on 2018/4/27
 */
public interface SurveyTopicService extends BaseService<SurveyTopic, SurveyTopicExample> {

    boolean deleteSurveyTopicWithOptions(Integer surveyTopicId);

    boolean deleteSurveyTopicsWithOptions(List<Integer> surveyTopicIds);
}
