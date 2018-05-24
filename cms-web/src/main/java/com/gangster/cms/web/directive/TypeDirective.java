package com.gangster.cms.web.directive;

import com.gangster.cms.common.pojo.ArticleExample;
import com.gangster.cms.common.pojo.CategoryExample;
import com.gangster.cms.common.pojo.Site;
import com.gangster.cms.dao.mapper.ArticleMapper;
import com.gangster.cms.dao.mapper.CategoryMapper;
import com.github.pagehelper.PageHelper;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 获取指定类型的栏目或文章，参数用法同{@link ContentListDirective}
 */
@Component
public class TypeDirective implements TemplateDirectiveModel {
    private static final String PARAM_CATEGORY_TYPE = "cate_type";
    private static final String PARAM_ARTICLE_TYPE = "article_type";
    private static final String PARAM_SIZE = "size";    //default 5
    private static final String PARAM_PAGE = "page";    //default 0
    private static final String PARAM_SORT = "sort";
    private static final String DEFAULT_ARTICLE_SORT = "article_create_time";
    private static final String DEFAULT_CATE_SORT = "category_create_time";
    private static final String PARAM_RET = "ret";

    private final ArticleMapper articleMapper;
    private final CategoryMapper categoryMapper;

    @Autowired
    public TypeDirective(ArticleMapper articleMapper, CategoryMapper categoryMapper) {
        this.articleMapper = articleMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String cateType = DirectiveUtil.getString(PARAM_CATEGORY_TYPE, params);
        String articleType = DirectiveUtil.getString(PARAM_ARTICLE_TYPE, params);
        String sort = DirectiveUtil.getString(PARAM_SORT, params);
        Integer size = DirectiveUtil.getInteger(PARAM_SIZE, params);
        Integer page = DirectiveUtil.getInteger(PARAM_PAGE, params);
        Site site = DirectiveUtil.getSite(env);

        if (site == null) {
            throw new TemplateException("site can't found", env);
        }

        //异或非运算 有且仅有一个不为null
        if ((cateType == null) == (articleType == null)) {
            throw new TemplateException(PARAM_ARTICLE_TYPE + " and " + PARAM_CATEGORY_TYPE + " only one must be specified.", env);
        }

        if (size == null) {
            size = 5;
        }
        if (page == null) {
            page = 0;
        }
        if (sort == null) {
            if (cateType != null) sort = DEFAULT_CATE_SORT;
            if (articleType != null) sort = DEFAULT_ARTICLE_SORT;
        }

        List retList;
        if (cateType != null) {
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.or().andCategoryTypeEqualTo(cateType).andCategorySiteIdEqualTo(site.getSiteId());
            categoryExample.setOrderByClause(sort);
            retList = PageHelper.startPage(page, size).doSelectPage(() -> categoryMapper.selectByExample(categoryExample));
        } else {
            ArticleExample articleExample = new ArticleExample();
            articleExample.or().andArticleTypeEqualTo(articleType).andArticleSiteIdEqualTo(site.getSiteId());
            articleExample.setOrderByClause(sort);
            retList = PageHelper.startPage(page, size).doSelectPage(() -> articleMapper.selectByExample(articleExample));
        }

        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.getVersion());
        env.setVariable(DirectiveUtil.getRetName(PARAM_RET, params), builder.build().wrap(retList));

        body.render(env.getOut());
    }
}
