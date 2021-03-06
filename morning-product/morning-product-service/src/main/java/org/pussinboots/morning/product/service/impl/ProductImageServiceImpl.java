package org.pussinboots.morning.product.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import org.pussinboots.morning.common.base.BasePageDTO;
import org.pussinboots.morning.common.support.page.PageInfo;
import org.pussinboots.morning.product.entity.ProductImage;
import org.pussinboots.morning.product.mapper.ProductImageMapper;
import org.pussinboots.morning.product.service.IProductImageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
* 项目名称：morning-product-service   
* 类名称：ProductImageServiceImpl   
* 类描述：ProductImage / 商品图片表 业务逻辑层接口实现        
* 创建人：陈星星   
* 创建时间：2017年4月14日 上午2:07:20   
*
 */
@Service
public class ProductImageServiceImpl extends ServiceImpl<ProductImageMapper, ProductImage> implements IProductImageService {

	@Autowired
	private ProductImageMapper productImageMapper;
	
	@Override
	public List<ProductImage> listByProductId(Long productId, Integer type,Integer showNumber, Integer status) {
		return productImageMapper.listByProductId(productId,type, showNumber, status);
	}

	@Override
	public BasePageDTO<ProductImage> listByPage(PageInfo pageInfo, String search, Long productId) {
		Page<ProductImage> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
		List<ProductImage> products = productImageMapper.listByPage(pageInfo, search,productId, page);
		pageInfo.setTotal(page.getTotal());
		return new BasePageDTO<ProductImage>(pageInfo, products);
	}

}
