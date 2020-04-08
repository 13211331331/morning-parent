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
import org.pussinboots.morning.product.pojo.vo.CategoryVO;
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
@RequestMapping(value = "/product/list")
@Api(value = "产品管理", description = "产品管理")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;
    @Autowired
    private ILabelService labelService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductCategoryService productCategoryService;
    @Autowired
    private IProductParameterService productParameterService;
    @Autowired
    private IProductImageService productImageService;


    /**
     * GET 分类管理页面
     * @return
     */
    @ApiOperation(value = "产品管理页面", notes = "产品管理页面")
    @RequiresPermissions("product:list:view")
    @GetMapping(value = "/view")
    public String getProductPage(Model model) {
        return "/modules/product/product_list";
    }



    /**
     * GET 分类管理页面
     * @return
     */
    @ApiOperation(value = "产品图片", notes = "产品图片管理页面")
    @RequiresPermissions("product:list:view")
    @GetMapping(value = "/{productId}/detail/view")
    public String detailView(Model model,@PathVariable("productId") Long productId) {
        model.addAttribute("productId",productId);
        return "/modules/product/product_images";
    }


    @ApiOperation(value = "获取产品列表", notes = "根据分页信息/搜索内容获取产品列表")
    @RequiresPermissions("product:list:view")
    @GetMapping(value = "/{productId}/detail/view/images")
    @ResponseBody
    public Object listProductImages(PageInfo pageInfo, @PathVariable("productId") Long productId,
                                    @RequestParam(required = false, value = "search") String search){
        BasePageDTO<ProductImage> basePageDTO = productImageService.listByPage(pageInfo, search,productId);
        return new CmsPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }




    @ApiOperation(value = "获取产品列表", notes = "根据分页信息/搜索内容获取产品列表")
    @RequiresPermissions("product:list:view")
    @GetMapping(value = "/")
    @ResponseBody
    public Object listProduct(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search){
        BasePageDTO<Product> basePageDTO = productService.listByPage(pageInfo, search);
        return new CmsPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
    }

    /**
     * PUT 启用/冻结产品
     * @return
     */
    @ApiOperation(value = "启用/冻结产品", notes = "根据url产品ID启动/冻结产品")
    @RequiresPermissions("product:list:audit")
    @PostMapping(value = "/{productId}/audit")
    @ResponseBody
    public Object audit(@PathVariable("productId") Long productId) {
        AuthorizingUser authorizingUser = SingletonLoginUtils.getUser();
        if (authorizingUser != null) {
            Integer count = productService.updateStatus(productId,authorizingUser.getUserName());
            return new CmsResult(CommonReturnCode.SUCCESS, count);
        } else {
            return new CmsResult(CommonReturnCode.UNAUTHORIZED);
        }
    }

    /**
     * GET 更新商品页面
     * @return
     */
    @ApiOperation(value = "更新商品页面", notes = "更新商品页面")
    @RequiresPermissions("product:list:edit")
    @GetMapping(value = "/{productId}/edit")
    public String getUpdatePage(Model model, @PathVariable("productId") Long productId) {

        Product product = productService.getByProductId(productId);
        model.addAttribute("product", product);

        List<Label> labels =labelService.list();
        model.addAttribute("labels",labels);

        ProductCategory productCategory=productCategoryService.getByProductId(productId);
        List<ProductCategoryVO> categories=categoryService.listTreeByStatus(StatusEnum.SHOW.getStatus());
        for (ProductCategoryVO category:categories){
            if (category.getCategoryId() == productCategory.getCategoryId()){
                category.setChecked(true);
            }
        }
        model.addAttribute("categories", JSON.toJSON(categories));

        List<ProductParameter> productParameters=productParameterService.listByProductId(productId,StatusEnum.NORMAL.getStatus());
        model.addAttribute("productParameters",productParameters);

        return "/modules/product/product_update";
    }

    /**
     * PUT 更新商品
     * @return
     */
    @ApiOperation(value = "更新商品页面", notes = "更新商品页面")
    @RequiresPermissions("product:list:edit")
    @PostMapping(value = "/{productId}/edit")
    @ResponseBody
    public Object update(@PathVariable("productId") Long productId, Product product,Long categoryId,String paramNames,String paramValues) {
        AuthorizingUser authorizingUser=SingletonLoginUtils.getUser();
        if (authorizingUser!=null){
            Integer count=productService.updateProduct(product,authorizingUser.getUserName());
            //更新种类
            Integer size=productCategoryService.updateProductCategory(productId,categoryId);
            //更新参数
            Integer length=productParameterService.updateProductParameter(productId,paramNames,paramValues,authorizingUser.getUserName());
            return new CmsResult(CommonReturnCode.SUCCESS,count+size+length);
        }else {
            return new CmsResult(CommonReturnCode.UNAUTHORIZED);
        }
    }

    /**
     * GET 创建商品页面
     * @return
     */
    @ApiOperation(value = "创建商品页面", notes = "创建商品页面")
    @RequiresPermissions("product:list:add")
    @GetMapping(value = "/create")
    public String getCreatePage(Model model) {

        Product product = new Product();
        model.addAttribute("product", product);

        List<Label> labels =labelService.list();
        model.addAttribute("labels",labels);

        List<ProductCategoryVO> categories=categoryService.listTreeByStatus(StatusEnum.SHOW.getStatus());
        categories.get(0).setChecked(true);
        model.addAttribute("categories", JSON.toJSON(categories));

        return "/modules/product/product_create";
    }

    /**
     * PUT 创建商品
     * @return
     */
    @ApiOperation(value = "创建商品页面", notes = "创建商品页面")
    @RequiresPermissions("product:list:add")
    @PostMapping(value = "/create")
    @ResponseBody
    public Object update(Product product,Long categoryId,String paramNames,String paramValues) {
        AuthorizingUser authorizingUser=SingletonLoginUtils.getUser();
        if (authorizingUser!=null){
            Integer count=productService.insertProduct(product,authorizingUser.getUserName());
            //更新种类
            Integer size=productCategoryService.insertProductCategory(product.getProductId(),categoryId,authorizingUser.getUserName());
            //更新参数
            Integer length=productParameterService.insertProductParameter(product.getProductId(),paramNames,paramValues,authorizingUser.getUserName());
            return new CmsResult(CommonReturnCode.SUCCESS,count+size+length);
        }else {
            return new CmsResult(CommonReturnCode.UNAUTHORIZED);
        }
    }
}
