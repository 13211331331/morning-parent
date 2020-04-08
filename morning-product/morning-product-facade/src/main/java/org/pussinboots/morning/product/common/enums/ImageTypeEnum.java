package org.pussinboots.morning.product.common.enums;

/**
 * 
* 项目名称：morning-os-pojo   
* 类名称：CommentTypeEnum   
* 类描述：CommentTypeEnum 评论类型枚举表述常量数据字段   
* 创建人：陈星星   
* 创建时间：2017年2月24日 下午5:01:16   
*
 */
public enum ImageTypeEnum {

	/** 优质评论 */
	IMG_PRODUCT_MAIN(1, "产品主要图片"),

	/** 普通评论 */
	IMG_PRODUCT_DETAIL(2, "产品详情图片");

	private Integer type;

	private String typeInfo;

	private ImageTypeEnum(Integer type, String typeInfo) {
		this.type = type;
		this.typeInfo = typeInfo;
	}

	public Integer getType() {
		return type;
	}

	public String getTypeInfo() {
		return typeInfo;
	}

	public static ImageTypeEnum stateOf(int index) {
		for (ImageTypeEnum typeEnum : values()) {
			if (typeEnum.getType() == index) {
				return typeEnum;
			}
		}
		return null;
	}
}
