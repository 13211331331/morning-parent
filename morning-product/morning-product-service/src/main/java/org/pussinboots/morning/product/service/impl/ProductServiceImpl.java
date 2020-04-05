package org.pussinboots.morning.product.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import org.pussinboots.morning.common.base.BasePageDTO;
import org.pussinboots.morning.common.support.page.PageInfo;
import org.pussinboots.morning.product.common.util.ProductUtils;
import org.pussinboots.morning.product.entity.Product;
import org.pussinboots.morning.product.mapper.ProductMapper;
import org.pussinboots.morning.product.pojo.vo.ProductVO;
import org.pussinboots.morning.product.service.IProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 * 项目名称：morning-product-service
 * 类名称：ProductServiceImpl
 * 类描述：Product / 商品表 业务逻辑层接口实现
 * 创建人：yeungchihang
 * 创建时间：2017年4月11日 下午3:17:31
 *
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductVO getByNumber(Long productNumber, Integer status) {
        return productMapper.getByNumber(productNumber, status);
    }

    @Override
    public BasePageDTO<Product> listByPage(PageInfo pageInfo, String search) {
        Page<Product> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
        List<Product> products = productMapper.listByPage(pageInfo, search, page);
        pageInfo.setTotal(page.getTotal());
        return new BasePageDTO<Product>(pageInfo, products);
    }

    @Override
    public Integer updateStatus(Long productId,String adminName) {
        Product query=new Product();
        query.setProductId(productId);
        Product product=productMapper.selectOne(query);

        if (product!=null){
            int showInShelve = product.getShowInShelve();
            if (showInShelve == 0){
                product.setShowInShelve(1);
            }else if (showInShelve == 1){
                product.setShowInShelve(0);
            }
            product.setShelveTime(new Date());
            product.setShelveBy(adminName);

            return productMapper.updateById(product);
        }
        return null;
    }

    @Override
    public Integer deleteProduct(Long productId) {
        return productMapper.deleteById(productId);
    }

    @Override
    public Product getByProductId(Long productId) {
        return productMapper.getByProductId(productId);
    }

    @Override
    public Integer updateProduct(Product product, String userName) {
        product.setUpdateTime(new Date());
        product.setUpdateBy(userName);
        return productMapper.updateById(product);
    }

    @Override
    public Integer insertProduct(Product product, String userName) {
        product.setProductNumber(ProductUtils.getProductNuber());
        product.setCreateBy(userName);
        product.setCreateTime(new Date());
        return productMapper.insertProduct(product);
    }

    @Override
    public Long getIdbyNumber(Long productNumber) {
        return productMapper.getIdByNumber(productNumber);
    }


}
