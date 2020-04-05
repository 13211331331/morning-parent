package org.pussinboots.morning.cms.controller.order;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.pussinboots.morning.cms.common.result.CmsPageResult;
import org.pussinboots.morning.cms.common.result.CmsResult;
import org.pussinboots.morning.cms.common.security.AuthorizingUser;
import org.pussinboots.morning.cms.common.util.SingletonLoginUtils;
import org.pussinboots.morning.common.base.BasePageDTO;
import org.pussinboots.morning.common.constant.CommonReturnCode;
import org.pussinboots.morning.common.support.page.PageInfo;
import org.pussinboots.morning.order.entity.Order;
import org.pussinboots.morning.order.entity.OrderShipment;
import org.pussinboots.morning.order.pojo.vo.OrderVO;
import org.pussinboots.morning.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 项目名称：morning-cms-web Maven Webapp
 * 类名称：OrderController
 * 类描述：订单管理表示层控制器
 * 创建人：yeungchihang
 * 创建时间：2017年4月10日 下午2:05:50
 *
 */
@Controller
@RequestMapping(value = "/system/order/list")
@Api(value = "订单管理", description = "订单管理")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ApiOperation(value = "订单列表页面", notes = "订单列表页面")
    @RequiresPermissions("order:list:view")
    @GetMapping(value = "/view")
    public String getOrderPage(Model model){
        return "/modules/order/order_list";
    }

    @ApiOperation(value = "获取订单列表", notes = "根据分页信息/搜索内容获取订单列表")
    @RequiresPermissions("order:list:view")
    @GetMapping(value = "/")
    @ResponseBody
    public Object listOrder(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search){
        BasePageDTO<Order> basePageDTO = orderService.listByPage(pageInfo, search);
        return new CmsPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }

    @ApiOperation(value = "手动取消订单", notes = "根据url订单ID取消订单")
    @RequiresPermissions("order:list:delete")
    @DeleteMapping(value = "/delete/{orderId}")
    @ResponseBody
    public Object delete(@PathVariable("orderId") Long orderId) {
        AuthorizingUser authorizingUser = SingletonLoginUtils.getUser();
        if (authorizingUser != null) {
            Integer count = orderService.updateCancelOrder(orderId,authorizingUser.getUserId().toString());
            return new CmsResult(CommonReturnCode.SUCCESS, count);
        } else {
            return new CmsResult(CommonReturnCode.UNAUTHORIZED);
        }
    }

    /**
     * GET 更新订单页面
     * @return
     */
    @ApiOperation(value = "更新订单页面", notes = "更新订单页面")
    @RequiresPermissions("order:list:edit")
    @GetMapping(value = "/{orderId}/edit")
    public String getUpdatePage(Model model, @PathVariable("orderId") Long orderId) {

        OrderVO order = orderService.getOrderById(orderId);
        model.addAttribute("order", order);

        return "/modules/order/order_update";
    }

    /**
     * PUT 更新订单信息
     * @return
     */
    @ApiOperation(value = "更新订单信息", notes = "根据url订单ID来指定更新对象,并根据传过来的订单信息来更新订单信息")
    @RequiresPermissions("order:list:edit")
    @PutMapping(value = "/{orderId}")
    @ResponseBody
    public Object update(Order order, OrderShipment orderShipment, @PathVariable("orderId") Long orderId) {

        AuthorizingUser authorizingUser = SingletonLoginUtils.getUser();
        if (authorizingUser != null) {
            // 更新订单记录
            Integer count = orderService.updateOrder(order, orderShipment, authorizingUser.getUserName());
            return new CmsResult(CommonReturnCode.SUCCESS, count);
        } else {
            return new CmsResult(CommonReturnCode.UNAUTHORIZED);
        }
    }
}
