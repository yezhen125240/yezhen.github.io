/*

 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shop.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.shop.Filter;
import net.shop.Order;
import net.shop.Page;
import net.shop.Pageable;
import net.shop.entity.Attribute;
import net.shop.entity.Brand;
import net.shop.entity.Goods;
import net.shop.entity.Member;
import net.shop.entity.ProductCategory;
import net.shop.entity.Promotion;
import net.shop.entity.Tag;

/**
 * Dao - 货品
 * 
 * @author SHOP++ Team
 * @version 4.0
 */
public interface GoodsDao extends BaseDao<Goods, Long> {

	/**
	 * 判断编号是否存在
	 * 
	 * @param sn
	 *            编号(忽略大小写)
	 * @return 编号是否存在
	 */
	boolean snExists(String sn);

	/**
	 * 根据编号查找货品
	 * 
	 * @param sn
	 *            编号(忽略大小写)
	 * @return 货品，若不存在则返回null
	 */
	Goods findBySn(String sn);

	/**
	 * 查找货品
	 * 
	 * @param type
	 *            类型
	 * @param productCategory
	 *            商品分类
	 * @param brand
	 *            品牌
	 * @param promotion
	 *            促销
	 * @param tag
	 *            标签
	 * @param attributeValueMap
	 *            属性值Map
	 * @param startPrice
	 *            最低价格
	 * @param endPrice
	 *            最高价格
	 * @param isMarketable
	 *            是否上架
	 * @param isList
	 *            是否列出
	 * @param isTop
	 *            是否置顶
	 * @param isOutOfStock
	 *            是否缺货
	 * @param isStockAlert
	 *            是否库存警告
	 * @param hasPromotion
	 *            是否存在促销
	 * @param orderType
	 *            排序类型
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @return 货品
	 */
	List<Goods> findList(Goods.Type type, ProductCategory productCategory, Brand brand, Promotion promotion, Tag tag, Map<Attribute, String> attributeValueMap, BigDecimal startPrice, BigDecimal endPrice, Boolean isMarketable, Boolean isList, Boolean isTop, Boolean isOutOfStock,
			Boolean isStockAlert, Boolean hasPromotion, Goods.OrderType orderType, Integer count, List<Filter> filters, List<Order> orders);

	/**
	 * 查找货品
	 * 
	 * @param productCategory
	 *            商品分类
	 * @param isMarketable
	 *            是否上架
	 * @param generateMethod
	 *            静态生成方式
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @param first
	 *            起始记录
	 * @param count
	 *            数量
	 * @return 货品
	 */
	List<Goods> findList(ProductCategory productCategory, Boolean isMarketable, Goods.GenerateMethod generateMethod, Date beginDate, Date endDate, Integer first, Integer count);

	/**
	 * 查找货品分页
	 * 
	 * @param type
	 *            类型
	 * @param productCategory
	 *            商品分类
	 * @param brand
	 *            品牌
	 * @param promotion
	 *            促销
	 * @param tag
	 *            标签
	 * @param attributeValueMap
	 *            属性值Map
	 * @param startPrice
	 *            最低价格
	 * @param endPrice
	 *            最高价格
	 * @param isMarketable
	 *            是否上架
	 * @param isList
	 *            是否列出
	 * @param isTop
	 *            是否置顶
	 * @param isOutOfStock
	 *            是否缺货
	 * @param isStockAlert
	 *            是否库存警告
	 * @param hasPromotion
	 *            是否存在促销
	 * @param orderType
	 *            排序类型
	 * @param pageable
	 *            分页信息
	 * @return 货品分页
	 */
	Page<Goods> findPage(Goods.Type type, ProductCategory productCategory, Brand brand, Promotion promotion, Tag tag, Map<Attribute, String> attributeValueMap, BigDecimal startPrice, BigDecimal endPrice, Boolean isMarketable, Boolean isList, Boolean isTop, Boolean isOutOfStock,
			Boolean isStockAlert, Boolean hasPromotion, Goods.OrderType orderType, Pageable pageable);

	/**
	 * 查找货品分页
	 * 
	 * @param rankingType
	 *            排名类型
	 * @param pageable
	 *            分页信息
	 * @return 货品分页
	 */
	Page<Goods> findPage(Goods.RankingType rankingType, Pageable pageable);

	/**
	 * 查找收藏货品分页
	 * 
	 * @param member
	 *            会员
	 * @param pageable
	 *            分页信息
	 * @return 收藏货品分页
	 */
	Page<Goods> findPage(Member member, Pageable pageable);

	/**
	 * 查询货品数量
	 * 
	 * @param type
	 *            类型
	 * @param favoriteMember
	 *            收藏会员
	 * @param isMarketable
	 *            是否上架
	 * @param isList
	 *            是否列出
	 * @param isTop
	 *            是否置顶
	 * @param isOutOfStock
	 *            是否缺货
	 * @param isStockAlert
	 *            是否库存警告
	 * @return 货品数量
	 */
	Long count(Goods.Type type, Member favoriteMember, Boolean isMarketable, Boolean isList, Boolean isTop, Boolean isOutOfStock, Boolean isStockAlert);

	/**
	 * 清空货品属性值
	 * 
	 * @param attribute
	 *            属性
	 */
	void clearAttributeValue(Attribute attribute);

}