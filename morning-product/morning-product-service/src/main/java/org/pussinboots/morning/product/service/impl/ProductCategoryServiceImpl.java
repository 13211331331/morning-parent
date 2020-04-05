package org.pussinboots.morning.product.service.impl;

import java.util.Date;
import java.util.List;

import org.pussinboots.morning.common.base.BasePageDTO;
import org.pussinboots.morning.common.enums.StatusEnum;
import org.pussinboots.morning.common.support.page.PageInfo;
import org.pussinboots.morning.product.entity.ProductCategory;
import org.pussinboots.morning.product.mapper.CategoryMapper;
import org.pussinboots.morning.product.mapper.ProductCategoryMapper;
import org.pussinboots.morning.product.pojo.vo.ProductVO;
import org.pussinboots.morning.product.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 *
 * 项目名称：morning-product-service
 * 类名称：ProductCategoryServiceImpl
 * 类描述：ProductCategory / 商品分类关联表 业务逻辑层接口实现
 * 创建人：yeungchihang
 * 创建时间：2017年4月12日 下午4:24:43
 *
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public BasePageDTO<ProductVO> listProducts(Long categoryId, PageInfo pageInfo) {
        // 查询子目录信息ID
        List<Long> categoryIds = categoryMapper.listIds(categoryId, StatusEnum.SHOW.getStatus());

        // 查询该目录ID列表下商品列表
        Page<ProductVO> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
        List<ProductVO> productVOs = productCategoryMapper.listByPage(categoryIds, StatusEnum.SHELVE.getStatus(),
                pageInfo, page);
        pageInfo.setTotal(page.getTotal());

        return new BasePageDTO<ProductVO>(pageInfo, productVOs);
    }

    @Override
    public BasePageDTO<ProductVO> listBySearch(String search, PageInfo pageInfo) {
        Page<ProductVO> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
        List<ProductVO> productVOs = productCategoryMapper.listBySearch(search, StatusEnum.SHELVE.getStatus(), pageInfo,
                page);
        pageInfo.setTotal(page.getTotal());

        return new BasePageDTO<ProductVO>(pageInfo, productVOs);
    }

    @Override
    public ProductCategory getByProductId(Long productId) {
        return productCategoryMapper.getByProductId(productId);
    }

    @Override
    public Integer updateProductCategory(Long productId, Long categoryId) {
        ProductCategory productCategory=productCategoryMapper.getByProductId(productId);
        productCategory.setCategoryId(categoryId);
        return productCategoryMapper.updateById(productCategory);
    }

    @Override
    public Integer insertProductCategory(Long productId, Long categoryId, String userName) {
        ProductCategory productCategory=new ProductCategory();
        productCategory.setProductId(productId);
        productCategory.setCategoryId(categoryId);
        productCategory.setCreateTime(new Date());
        productCategory.setCreateBy(userName);
        return productCategoryMapper.insertProductCategory(productCategory);
    }
}
