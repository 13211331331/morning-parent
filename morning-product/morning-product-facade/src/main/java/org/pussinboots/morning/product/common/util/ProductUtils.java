package org.pussinboots.morning.product.common.util;

import org.pussinboots.morning.common.util.RandomUtils;

import java.util.Date;

/**
 * 
* 项目名称：morning-product-facade
* 类名称：ProductUtils
* 类描述：ProductUtils工具类：提供一些订单操作的方法
* 创建人：yeungchihang
* 创建时间：2017年5月11日 上午12:12:15   
*
 */
public class ProductUtils {

	/** 订单编号后缀位数 */
	private static final int SUFFIX_NUMBER = 4;

	private ProductUtils() {
		throw new AssertionError();
	}

	/** 获得订单编号 */
	public static Long getProductNuber() {
		String prefixNumber = Long.toString(new Date().getTime());
		String suffixNumber = RandomUtils.number(0);
		String productNumber = prefixNumber + suffixNumber;
		return Long.valueOf(productNumber);
	}

}
