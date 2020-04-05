package org.pussinboots.morning.product.pojo.vo;

import java.io.Serializable;

/**
 * Created by Hins on 2018/2/27.
 */
public class ProductCategoryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long categoryId;

    private Long parentId;

    private String name;

    /** 是否选中 */
    private boolean checked;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
