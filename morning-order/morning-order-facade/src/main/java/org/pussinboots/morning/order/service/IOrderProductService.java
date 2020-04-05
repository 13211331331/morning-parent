package org.pussinboots.morning.order.service;

import com.baomidou.mybatisplus.service.IService;
import org.pussinboots.morning.common.base.BasePageDTO;
import org.pussinboots.morning.common.support.page.PageInfo;
import org.pussinboots.morning.order.entity.OrderProduct;

import java.util.List;

/**
 * 
* 项目名称：morning-order-facade   
* 类名称：IOrderProductService   
* 类描述：OrderProduct / 订单明细表 业务逻辑层接口   
* 创建人：yeungchihang
* 创建时间：2017年5月10日 上午10:30:45   
*
 */
public interface IOrderProductService extends IService<OrderProduct> {
	
	/**
	 * 根据订单ID查找订单详情
	 * @param orderId 订单ID
	 * @return
	 */
	List<OrderProduct> listByOrderId(Long orderId);

	BasePageDTO<OrderProduct> listByPage(Long orderId, PageInfo pageInfo, String search);

    Integer deleteByOrderProductId(Long orderProductId);

    Integer updateOrderProduct(OrderProduct orderProduct);
}
