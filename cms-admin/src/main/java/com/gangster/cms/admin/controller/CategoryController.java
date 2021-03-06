package com.gangster.cms.admin.controller;


import com.gangster.cms.admin.annotation.CmsPermission;
import com.gangster.cms.admin.annotation.SiteId;
import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.web.ContentWebService;
import com.gangster.cms.common.dto.CategoryTree;
import com.gangster.cms.common.pojo.Category;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create by Yoke on 2018/1/30
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final ContentWebService contentWebService;
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    public CategoryController(ContentWebService contentWebService) {
        this.contentWebService = contentWebService;
    }

    @SystemControllerLog(description = "列出所有的栏目")
    @CmsPermission(moduleName = "栏目管理")
    @GetMapping("/{siteId}")
    public AjaxData list(
            @SiteId @PathVariable Integer siteId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<Category> pageInfo = contentWebService.listCategory(siteId, page, limit);
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @SystemControllerLog(description = "列出待审核的栏目")
    @CmsPermission(moduleName = "栏目管理")
    @GetMapping("/check/{siteId}")
    public AjaxData listCheck(
            @SiteId @PathVariable Integer siteId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {

        PageInfo<Category> pageInfo = contentWebService.listCheckCategory(siteId, page, limit);
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @SystemControllerLog(description = "选择栏目")
    @CmsPermission(moduleName = "栏目管理")
    @GetMapping("/select/{siteId}")
    public List<CategoryTree> select(@SiteId @PathVariable("siteId") Integer siteId) {
        return contentWebService.select(siteId);
    }

    @SystemControllerLog(description = "删除栏目")
    @CmsPermission(moduleName = "栏目管理")
    @DeleteMapping("/{siteId}/{cId}")
    public MessageDto delete(@SiteId @PathVariable("siteId") Integer siteId, @PathVariable Integer cId) {
        if (!contentWebService.deleteCategory(cId)) {
            return MessageDto.fail(1, "删除栏目失败");
        } else {
            return MessageDto.success(null);
        }
    }

    @SystemControllerLog(description = "更新单个栏目")
    @CmsPermission(moduleName = "栏目管理")
    @PutMapping("/{siteId}/{cId}")
    public MessageDto update(
            @SiteId @PathVariable("siteId") Integer siteId,
            @PathVariable("cId") Integer cId,
            @RequestBody Category category) {
        if (!contentWebService.updateCategory(cId, category)) {
            return MessageDto.fail(1, "更新栏目失败");
        } else {
            return MessageDto.success(null);
        }
    }

    @SystemControllerLog(description = "查看单个栏目信息")
    @CmsPermission(moduleName = "栏目管理")
    @GetMapping("/details/{siteId}/{cId}")
    public MessageDto details(@SiteId @PathVariable("siteId") Integer siteId, @PathVariable("cId") Integer id) {
        return MessageDto.success(contentWebService.detailsCategory(id));
    }

    @SystemControllerLog(description = "添加栏目")
    @CmsPermission(moduleName = "栏目管理")
    @PostMapping("/{siteId}")
    public MessageDto add(@SiteId @PathVariable("siteId") Integer siteId, @RequestBody Category category) {
        if (!contentWebService.addCategory(category)) {
            return MessageDto.fail(1, "添加栏目失败");
        }
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "批量删除")
    @CmsPermission(moduleName = "栏目管理")
    @PostMapping("/batch/{siteId}")
    public MessageDto batchDelete(@SiteId @PathVariable("siteId") Integer siteId, String ids) {
        if (!contentWebService.deleteCategories(ids)) {
            return MessageDto.fail(1, "批量删除栏目失败");
        }
        LOGGER.info("批量删除id组：{}成功", ids);
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "审核栏目")
    @CmsPermission(moduleName = "栏目管理")
    @PostMapping("/check/{siteId}/{cId}")
    public MessageDto checkCategory(
            @SiteId @PathVariable("siteId") Integer siteId,
            @PathVariable Integer cId,
            @RequestParam Integer judge) {
        if (!contentWebService.checkCategory(cId, judge)) {
            return MessageDto.fail(1, "审核栏目失败");
        }
        return MessageDto.success(null);
    }
}