package org.pussinboots.morning.product.service;

import java.util.List;

import org.pussinboots.morning.common.base.BasePageDTO;
import org.pussinboots.morning.common.support.page.PageInfo;
import org.pussinboots.morning.product.entity.Category;
import org.pussinboots.morning.product.pojo.vo.CategoryVO;

import com.baomidou.mybatisplus.service.IService;
import org.pussinboots.morning.product.pojo.vo.ProductCategoryVO;

/**
 *
 * 项目名称：morning-product-facade
 * 类名称：ICategoryService
 * 类描述：Category / 分类表 业务逻辑层接口
 * 创建人：yeungchihang
 * 创建时间：2017年4月12日 下午4:21:11
 *
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 创建广告
     * @param category 广告详情
     * @param userName 操作人
     * @return
     */
    Integer insertAdvert(Category category, String userName);

    /**
     * 根据商品显示数量/广告数量查找显示置顶分类列表
     * @param showNumber 商品显示数量
     * @param advertNumber 广告数量
     * @return
     */
    List<CategoryVO> listTop(Integer showNumber, Integer advertNumber);

    /**
     * 根据商品显示数量/广告数量查找显示热门分类列表
     * @param showNumber 商品显示数量
     * @param advertNumber 广告数量
     * @param type 评论类型
     * @return
     */
    List<CategoryVO> listHot(Integer showNumber, Integer advertNumber, Integer type);

    /**
     * 根据商品显示数量/分类显示数量查找显示导航分类列表
     * @param showNumber 商品显示数量
     * @param navNumber 分类显示数量
     * @return
     */
    List<CategoryVO> listNav(Integer showNumber, Integer navNumber);

    /**
     * 根据类目ID查找当前类目信息
     * @param categoryId 类目ID
     * @param status 类目状态
     * @return
     */
    Category getById(Long categoryId, Integer status);

    /**
     * 根据类目ID查找子类目列表（如果沒有则返回当前目录列表）
     * @param categoryId 类目ID
     * @param status 子类目状态
     * @return
     */
    List<Category> listLowerCategories(Long categoryId, Integer status);

    /**
     * 根据类目ID查找父类目列表（如果沒有则返回当前目录列表）
     * @param categoryId 类目ID
     * @param status 父类目状态
     * @return
     */
    List<Category> listUpperCategories(Long categoryId, Integer status);

    /**
     * 根据商品ID查找父类目列表
     * @param productId 商品ID
     * @param status 父类目状态
     * @return
     */
    List<Category> listUpperByProductId(Long productId, Integer status);

    /**
     * 根据分页信息/搜索内容查找一级分类列表
     * @param pageInfo 分页信息
     * @param search 搜索内容
     * @param parentId 父类目ID
     * @return
     */
    BasePageDTO<Category> listParentByPage(PageInfo pageInfo, String search, Long parentId);

    /**
     * 更新类目状态
     * @param categoryId 类目ID
     * @return
     */
    Integer updateStatus(Long categoryId);

    /**
     * 更新类目
     * @param category 类目信息
     * @param userName 操作人
     * @return
     */
    Integer updateCategory(Category category, String userName);

    List<Category> listByStatus(Integer status);

    List<ProductCategoryVO> listTreeByStatus(Integer status);
}
