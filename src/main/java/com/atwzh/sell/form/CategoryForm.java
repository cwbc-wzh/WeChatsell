package com.atwzh.sell.form;

import lombok.Data;

@Data
public class CategoryForm {

    private Integer categoryId;
    /** 栏目名称 */
    private String categoryName;
    /** 栏目编号 */
    private Integer categoryType;
}
