package org.pussinboots.morning.product.mapper;

import org.pussinboots.morning.product.entity.ProductParameter;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 *
 * 项目名称：morning-product-service
 * 类名称：ProductParameterMapper
 * 类描述：ProductParameter / 商品参数表 数据访问层接口
 * 创建人：yeungchihang
 * 创建时间：2017年4月14日 上午2:04:57
 *
 */
public interface ProductParameterMapper extends BaseMapper<ProductParameter> {

    Integer deleteByProductId(Long productId);

    Integer insertProductParameters(List<ProductParameter> productParameters);
}