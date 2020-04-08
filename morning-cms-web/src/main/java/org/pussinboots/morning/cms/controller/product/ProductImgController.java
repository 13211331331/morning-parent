package org.pussinboots.morning.cms.controller.product;

import com.alibaba.fastjson.JSON;
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
import org.pussinboots.morning.common.enums.StatusEnum;
import org.pussinboots.morning.common.support.page.PageInfo;
import org.pussinboots.morning.product.entity.*;
import org.pussinboots.morning.product.pojo.vo.ProductCategoryVO;
import org.pussinboots.morning.product.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * 项目名称：morning-cms-web Maven Webapp
 * 类名称：ProductCategoryController
 * 类描述：分类管理表示层控制器
 * 创建人：yeungchihang
 * 创建时间：2017年5月20日 下午3:08:25
 *
 */
@Controller
@RequestMapping(value = "/product/image")
@Api(value = "产品管理", description = "产品管理")
public class ProductImgController extends BaseController {


    @Autowired
    private IProductImageService productImageService;



    /**
     * GET 分类管理页面
     * @return
     */
    @ApiOperation(value = "产品图片", notes = "产品图片管理页面")
    @GetMapping(value = "/{productId}/view")
    public String detailView(Model model,@PathVariable("productId") Long productId) {
        model.addAttribute("productId",productId);
        return "/modules/product/product_images";
    }


    @ApiOperation(value = "获取产品列表", notes = "根据分页信息/搜索内容获取产品列表")
    @RequiresPermissions("product:list:view")
    @GetMapping(value = "/{productId}/data/view")
    @ResponseBody
    public Object listProductImages(PageInfo pageInfo, @PathVariable("productId") Long productId,
                                    @RequestParam(required = false, value = "search") String search){
        BasePageDTO<ProductImage> basePageDTO = productImageService.listByPage(pageInfo, search,productId);
        return new CmsPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }





    /**
     * @return
     */
    @RequiresPermissions("product:list:add")
    @GetMapping(value = "/{productId}/create")
    public String getImageCreatePage(@PathVariable("productId") Long productId,Model model) {
        model.addAttribute("productId",productId);
        return "/modules/product/product_image_create";
    }




    /**
     * @return
     */
    @ApiOperation(value = "创建商品页面", notes = "创建商品页面")
    @RequiresPermissions("product:list:add")
    @PostMapping(value = "/create")
    @ResponseBody
    public Object createInsert(ProductImage productImage) {
        AuthorizingUser authorizingUser=SingletonLoginUtils.getUser();
        if (authorizingUser!=null){
             productImageService.insert(productImage);
            return new CmsResult(CommonReturnCode.SUCCESS,1);
        }else {
            return new CmsResult(CommonReturnCode.UNAUTHORIZED);
        }
    }




    /**
     * GET 更新商品页面
     * @return
     */
    @ApiOperation(value = "更新商品页面", notes = "更新商品页面")
    @GetMapping(value = "/{picImgId}/edit")
    public String getUpdateImagePage(Model model, @PathVariable("picImgId") Long picImgId) {
        ProductImage productImage = productImageService.selectById(picImgId);
        model.addAttribute("productImage", productImage);
        return "/modules/product/product_image_update";
    }

    /**
     * PUT 更新商品
     * @return
     */
    @ApiOperation(value = "更新商品页面", notes = "更新商品页面")
    @RequiresPermissions("product:list:edit")
    @PostMapping(value = "/{picImgId}/edit")
    @ResponseBody
    public Object updateImage(@PathVariable("picImgId") Long picImgId, ProductImage productImage) {
        AuthorizingUser authorizingUser=SingletonLoginUtils.getUser();
        if (authorizingUser!=null){
            productImage.setPicImgId(picImgId);
             productImageService.updateById(productImage);
            return new CmsResult(CommonReturnCode.SUCCESS,1);
        }else {
            return new CmsResult(CommonReturnCode.UNAUTHORIZED);
        }
    }


    /**
     * PUT 更新商品
     * @return
     */
    @ApiOperation(value = "更新商品页面", notes = "更新商品页面")
    @PostMapping(value = "/{picImgId}/del")
    @ResponseBody
    public Object delImage(@PathVariable("picImgId") Long picImgId) {
        AuthorizingUser authorizingUser=SingletonLoginUtils.getUser();
        if (authorizingUser!=null){
            productImageService.deleteById(picImgId);
            return new CmsResult(CommonReturnCode.SUCCESS,1);
        }else {
            return new CmsResult(CommonReturnCode.UNAUTHORIZED);
        }
    }


    /**
     * PUT 启用/冻结产品
     * @return
     */
    @ApiOperation(value = "启用/冻结产品", notes = "根据url产品ID启动/冻结产品")
    @RequiresPermissions("product:list:audit")
    @PostMapping(value = "/{picImgId}/audit")
    @ResponseBody
    public Object audit(@PathVariable("picImgId") Long picImgId) {
        AuthorizingUser authorizingUser = SingletonLoginUtils.getUser();
        if (authorizingUser != null) {
            ProductImage p = productImageService.selectById(picImgId);
            Integer a = p.getStatus();
            if(0 == a){
                p.setStatus(1);
            }
            if(1 == a){
                p.setStatus(0);
            }
            productImageService.updateById(p);
            return new CmsResult(CommonReturnCode.SUCCESS, 1);
        } else {
            return new CmsResult(CommonReturnCode.UNAUTHORIZED);
        }
    }


}
