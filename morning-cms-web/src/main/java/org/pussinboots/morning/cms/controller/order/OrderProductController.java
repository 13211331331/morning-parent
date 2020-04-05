package org.pussinboots.morning.cms.controller.order;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.pussinboots.morning.cms.common.result.CmsPageResult;
import org.pussinboots.morning.cms.common.result.CmsResult;
import org.pussinboots.morning.cms.common.security.AuthorizingUser;
import org.pussinboots.morning.cms.common.util.SingletonLoginUtils;
import org.pussinboots.morning.common.base.BaseController;
import org.pussinboots.morning.common.base.BasePageDTO;
import org.pussinboots.morning.common.constant.CommonReturnCode;
import org.pussinboots.morning.common.support.page.PageInfo;
import org.pussinboots.morning.order.entity.Order;
import org.pussinboots.morning.order.entity.OrderProduct;
import org.pussinboots.morning.order.pojo.vo.OrderVO;
import org.pussinboots.morning.order.service.IOrderProductService;
import org.pussinboots.morning.order.service.IOrderService;
import org.pussinboots.morning.product.entity.ProductSpecification;
import org.pussinboots.morning.product.pojo.dto.ProductSpecificationDTO;
import org.pussinboots.morning.product.service.IProductSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 *
 * 项目名称：morning-cms-web Maven Webapp
 * 类名称：OrderProductController
 * 类描述：订单商品管理表示层控制器
 * 创建人：yeungchihang
 * 创建时间：2017年4月10日 下午3:05:23
 *
 */
@Controller
@RequestMapping(value = "/system/order/list/{orderId}/product")
@Api(value = "订单商品管理", description = "订单商品管理")
public class OrderProductController extends BaseController {

    @Autowired
    private IOrderProductService orderProductService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IProductSpecificationService productSpecificationService;

    /**
     * GET 订单商品列表页面
     * @param model
     * @return
     */
    @ApiOperation(value = "订单商品列表页面", notes = "订单商品列表页面")
    @RequiresPermissions("order:list:view")
    @GetMapping(value = "/view")
    public String getOrderProductPage(Model model, @PathVariable("orderId") Long orderId) {
        Order order=orderService.selectById(orderId);
        model.addAttribute("order", order);
        return "/modules/order/order_detail_list";
    }

    /**
     * GET 订单商品列表
     * @param pageInfo
     * @param search
     * @return
     */
    @ApiOperation(value = "获取订单商品列表", notes = "根据分页信息/搜索内容获取订单商品列表")
    @RequiresPermissions("order:list:view")
    @GetMapping(value = "/")
    @ResponseBody
    public Object listProduct(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search,
                                 @PathVariable("orderId") Long orderId) {
        BasePageDTO<OrderProduct> basePageDTO = orderProductService.listByPage(orderId, pageInfo, search);
        return new CmsPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }

    /**
     * DELETE 删除订单商品详情
     * @param orderProductId 订单商品详情ID
     * @return
     */
    @ApiOperation(value = "删除订单商品详情", notes = "根据url订单商品详情ID删除订单商品详情")
    @RequiresPermissions("order:list:delete")
    @DeleteMapping(value = "/{orderProductId}")
    @ResponseBody
    public Object delete(@PathVariable("orderId") Long orderId,@PathVariable("orderProductId") Long orderProductId) {
        AuthorizingUser authorizingUser = SingletonLoginUtils.getUser();
        if (authorizingUser != null) {
            OrderVO orderVO=orderService.getOrderById(orderId);
            int productSize = orderVO.getOrderProducts().size();
            if (productSize > 1){
                Integer count = orderProductService.deleteByOrderProductId(orderProductId);
                Integer size = orderService.updateOrderAmountScoreNumber(orderId);
                return new CmsResult(CommonReturnCode.SUCCESS, count+size);
            }else {
                return new CmsResult(0,"不能删除订单唯一商品！请返回上级手动取消订单");
            }
        } else {
            return new CmsResult(CommonReturnCode.UNAUTHORIZED);
        }
    }

    /**
     * GET 更新订单商品详情页面
     * @return
     */
    @ApiOperation(value = "更新订单商品详情页面", notes = "更新订单商品详情页面")
    @RequiresPermissions("order:list:edit")
    @GetMapping(value = "/{orderProductId}/edit")
    public String getUpdatePage(Model model, @PathVariable("orderId") Long orderId,
                                @PathVariable("orderProductId") Long orderProductId) {

        // 订单信息
        Order order = orderService.selectById(orderId);
        model.addAttribute("order", order);

        // 订单商品信息
        OrderProduct orderProduct = orderProductService.selectById(orderProductId);
        model.addAttribute("orderProduct", orderProduct);

        //商品规格信息
        ProductSpecificationDTO productSpecDTO = productSpecificationService.getByProductNumber(orderProduct.getProductNumber());
        model.addAttribute("productSpecDTO", productSpecDTO);

        Map<String, Object> productSpecifications=productSpecDTO.getProductSpecifications();
        Set<String> keySet=productSpecifications.keySet();
        String spec=null;
        for (String key: keySet) {
            ProductSpecification productSpec= (ProductSpecification) productSpecifications.get(key);
            if (productSpec.getProductSpecNumber().equals(orderProduct.getProductSpecNumber())){
                spec=productSpec.getSpec();
            }
        }
        model.addAttribute("spec",spec);

        return "/modules/order/order_detail_update";
    }

    /**
     * PUT 更新订单商品详情信息
     * @return
     */
    @ApiOperation(value = "更新订单商品详情信息", notes = "根据url订单商品详情ID来指定更新对象,并根据传过来的订单商品详情信息来更新订单商品详情信息")
    @RequiresPermissions("order:list:edit")
    @PutMapping(value = "/{orderProductId}")
    @ResponseBody
    public Object update(OrderProduct orderProduct, @PathVariable("orderProductId") Long orderId, @RequestParam("kind") String kind,
                         HttpServletRequest request) {

        AuthorizingUser authorizingUser = SingletonLoginUtils.getUser();
        if (authorizingUser != null) {
            //商品规格信息
            ProductSpecificationDTO productSpecDTO = productSpecificationService.getByProductNumber(orderProduct.getProductNumber());

            //处理规格信息
            if (!kind.isEmpty()){
                String[] kinds=kind.split(",");
                String spec=request.getParameter(kinds[0]).split("-")[0];
                String spec_ch=request.getParameter(kinds[kinds.length-1]).split("-")[1];
                for (int i = 1; i < kinds.length; i++) {
                    spec += ("," + request.getParameter(kinds[i]).split("-")[0]);
                }
                for (int i = kinds.length - 2; i >= 0; i--) {
                    spec_ch += (" " + request.getParameter(kinds[i]).split("-")[1]);
                }
                Map<String,Object> productSpecs=productSpecDTO.getProductSpecifications();
                if (productSpecs.containsKey(spec)){
                    ProductSpecification productSpecification= (ProductSpecification) productSpecs.get(spec);
                    orderProduct.setProductSpecNumber(productSpecification.getProductSpecNumber());
                    orderProduct.setProductSpecName(spec_ch);
                    orderProduct.setPrice(productSpecification.getPrice());
                    orderProduct.setScore(productSpecification.getScore());
                    int buyNumber=orderProduct.getBuyNumber();
                    orderProduct.setProductAmount(productSpecification.getPrice().multiply(BigDecimal.valueOf(buyNumber)));
                    orderProduct.setProductScore(productSpecification.getScore() * buyNumber);
                }
            }

            // 更新订单商品详情记录
            Integer count = orderProductService.updateOrderProduct(orderProduct);
            return new CmsResult(CommonReturnCode.SUCCESS, count);
        } else {
            return new CmsResult(CommonReturnCode.UNAUTHORIZED);
        }
    }

}

