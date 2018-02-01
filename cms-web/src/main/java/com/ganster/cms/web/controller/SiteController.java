package com.ganster.cms.web.controller;

import com.ganster.cms.core.CmsConst;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.service.SiteService;
import com.ganster.cms.web.dto.CategoryWithArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SiteController {
    private final
    SiteService siteService;

    private final
    ArticleService articleService;

    private final
    CategoryService categoryService;

    @Autowired
    public SiteController(SiteService siteService, ArticleService articleService, CategoryService categoryService) {
        this.siteService = siteService;
        this.articleService = articleService;
        this.categoryService = categoryService;
    }


    @RequestMapping("/{siteUrl}")
    public String show(@PathVariable("siteUrl") String siteUrl, Model model) {
        SiteExample siteExample = new SiteExample();
        siteExample.or().andSiteUrlEqualTo(siteUrl);
        List<Site> siteList = siteService.selectByExample(siteExample);
        if (siteList == null || siteList.size() != 1) {
            return null;
        }
        Site site = siteList.get(0);

        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategorySiteIdEqualTo(site.getSiteId()).andCategoryLevelEqualTo(0);
        List<Category> categoryList = categoryService.selectByExample(categoryExample);

        List<CategoryTree> categoryTreeList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryTreeList.add(categoryService.toTree(category));
        }

        categoryExample.clear();
        categoryExample.or().andCategoryTypeEqualTo(CmsConst.INDEX_CATEGORY_TYPE);
        List<Category> indexCategoryList = categoryService.selectByExample(categoryExample);
        List<CategoryWithArticle> categoryWithArticleList = new ArrayList<>();
        for (Category category : indexCategoryList) {
            ArticleExample example = new ArticleExample();
            example.or().andArticleCategoryIdEqualTo(category.getCategoryId());
            categoryWithArticleList.add(new CategoryWithArticle(category, articleService.selectByExample(example)));
        }

        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleTypeEqualTo(CmsConst.INDEX_ARTICLE_TYPE);
        List<Article> articleList = articleService.selectByExample(articleExample);

        articleExample.clear();
        articleExample.or().andArticleTypeEqualTo(CmsConst.CAROUSEL_ARTICLE_TYPE);
        List<Article> carouselList = articleService.selectByExample(articleExample);

        model.addAttribute("site", site);
        model.addAttribute("categoryTreeList", categoryTreeList);
        model.addAttribute("categoryWithArticleList", categoryWithArticleList);
        model.addAttribute("articleList", articleList);
        model.addAttribute("carouselList",carouselList);

        return site.getSiteSkin() + CmsConst.SITE_SKIN_SUFFIX;
    }
}
