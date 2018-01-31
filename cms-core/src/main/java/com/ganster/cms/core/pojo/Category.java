package com.ganster.cms.core.pojo;

import java.io.Serializable;
import java.util.Date;

public class Category implements Serializable {
    private Integer categoryId;

    private String categoryTitle;

    private Date categoryCreateTime;

    private Date categoryUpdateTime;

    private Integer categoryParentId;

    private Integer categoryLevel;

    private Integer categorySiteId;

    private Integer categoryStatus;

    private String categoryDesc;

    private Integer categoryOrder;

    private static final long serialVersionUID = 1L;

    public Category(Integer categoryId, String categoryTitle, Date categoryCreateTime, Date categoryUpdateTime, Integer categoryParentId, Integer categoryLevel, Integer categorySiteId, Integer categoryStatus, String categoryDesc, Integer categoryOrder) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.categoryCreateTime = categoryCreateTime;
        this.categoryUpdateTime = categoryUpdateTime;
        this.categoryParentId = categoryParentId;
        this.categoryLevel = categoryLevel;
        this.categorySiteId = categorySiteId;
        this.categoryStatus = categoryStatus;
        this.categoryDesc = categoryDesc;
        this.categoryOrder = categoryOrder;
    }

    public Category() {
        super();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle == null ? null : categoryTitle.trim();
    }

    public Date getCategoryCreateTime() {
        return categoryCreateTime;
    }

    public void setCategoryCreateTime(Date categoryCreateTime) {
        this.categoryCreateTime = categoryCreateTime;
    }

    public Date getCategoryUpdateTime() {
        return categoryUpdateTime;
    }

    public void setCategoryUpdateTime(Date categoryUpdateTime) {
        this.categoryUpdateTime = categoryUpdateTime;
    }

    public Integer getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(Integer categoryParentId) {
        this.categoryParentId = categoryParentId;
    }

    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public Integer getCategorySiteId() {
        return categorySiteId;
    }

    public void setCategorySiteId(Integer categorySiteId) {
        this.categorySiteId = categorySiteId;
    }

    public Integer getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Integer categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc == null ? null : categoryDesc.trim();
    }

    public Integer getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(Integer categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Category other = (Category) that;
        return (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getCategoryTitle() == null ? other.getCategoryTitle() == null : this.getCategoryTitle().equals(other.getCategoryTitle()))
            && (this.getCategoryCreateTime() == null ? other.getCategoryCreateTime() == null : this.getCategoryCreateTime().equals(other.getCategoryCreateTime()))
            && (this.getCategoryUpdateTime() == null ? other.getCategoryUpdateTime() == null : this.getCategoryUpdateTime().equals(other.getCategoryUpdateTime()))
            && (this.getCategoryParentId() == null ? other.getCategoryParentId() == null : this.getCategoryParentId().equals(other.getCategoryParentId()))
            && (this.getCategoryLevel() == null ? other.getCategoryLevel() == null : this.getCategoryLevel().equals(other.getCategoryLevel()))
            && (this.getCategorySiteId() == null ? other.getCategorySiteId() == null : this.getCategorySiteId().equals(other.getCategorySiteId()))
            && (this.getCategoryStatus() == null ? other.getCategoryStatus() == null : this.getCategoryStatus().equals(other.getCategoryStatus()))
            && (this.getCategoryDesc() == null ? other.getCategoryDesc() == null : this.getCategoryDesc().equals(other.getCategoryDesc()))
            && (this.getCategoryOrder() == null ? other.getCategoryOrder() == null : this.getCategoryOrder().equals(other.getCategoryOrder()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getCategoryTitle() == null) ? 0 : getCategoryTitle().hashCode());
        result = prime * result + ((getCategoryCreateTime() == null) ? 0 : getCategoryCreateTime().hashCode());
        result = prime * result + ((getCategoryUpdateTime() == null) ? 0 : getCategoryUpdateTime().hashCode());
        result = prime * result + ((getCategoryParentId() == null) ? 0 : getCategoryParentId().hashCode());
        result = prime * result + ((getCategoryLevel() == null) ? 0 : getCategoryLevel().hashCode());
        result = prime * result + ((getCategorySiteId() == null) ? 0 : getCategorySiteId().hashCode());
        result = prime * result + ((getCategoryStatus() == null) ? 0 : getCategoryStatus().hashCode());
        result = prime * result + ((getCategoryDesc() == null) ? 0 : getCategoryDesc().hashCode());
        result = prime * result + ((getCategoryOrder() == null) ? 0 : getCategoryOrder().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", categoryId=").append(categoryId);
        sb.append(", categoryTitle=").append(categoryTitle);
        sb.append(", categoryCreateTime=").append(categoryCreateTime);
        sb.append(", categoryUpdateTime=").append(categoryUpdateTime);
        sb.append(", categoryParentId=").append(categoryParentId);
        sb.append(", categoryLevel=").append(categoryLevel);
        sb.append(", categorySiteId=").append(categorySiteId);
        sb.append(", categoryStatus=").append(categoryStatus);
        sb.append(", categoryDesc=").append(categoryDesc);
        sb.append(", categoryOrder=").append(categoryOrder);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}