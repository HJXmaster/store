package com.mall.controller.mk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.entity.Commodity;
import com.mall.entity.OrderInfo;
import com.mall.entity.Pics;
import com.mall.service.BtypeService;
import com.mall.service.CommodityService;
import com.mall.service.GoodsPicsService;
import com.mall.service.OrdersService;
import com.mall.service.StypeService;
import com.mall.util.UObjects;

/**
 * 商品操作
 * @author MK
 *
 */
@Controller
public class GoodsManagerController {
	
	@Autowired
	private CommodityService commodityService;
	@Autowired
	private BtypeService btypeService;
	@Autowired
	private StypeService stypeService;
	@Autowired
	private OrdersService ordersService;
	@Autowired	
	private GoodsPicsService goodsPicsService;
	/**
	 * 添加商品
	 * @param name   
	 * @param price
	 * @param btype
	 * @param stype
	 * @param size
	 * @return
	 */
    @ResponseBody
	@RequestMapping("addGoods.do")
	public Map<String,Object> add(String name,Double price,Integer btype,Integer stype,Integer size){
		
    	Commodity commodity=new Commodity();
    	
		commodity.setCname(name);
		commodity.setCprice(price);
		commodity.setBtid(btype);
    	commodity.setStid(stype);
    	commodity.setCremain(size);
    	
    	boolean value=commodityService.insert(commodity);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("status", value);
		return map;
	}
    
    /**
     * 修改商品
     * @param name
     * @param price
     * @param btype
     * @param stype
     * @param size
     * @return
     */
    @ResponseBody
 	@RequestMapping("updateGoods.do")
 	public Map<String,Object> update(Integer id,String name,Double price,Integer btype,Integer stype,Integer size){
 		
     	Commodity commodity=commodityService.select(id);
     	boolean value=false;
     	if(commodity!=null){
     		commodity.setCname(name);
     		commodity.setCprice(price);
     		commodity.setBtid(btype);
         	commodity.setStid(stype);
         	commodity.setCremain(size);
         	value =commodityService.update(commodity);
     	}
	
 		Map<String,Object> map=new HashMap<String, Object>();
 		map.put("status", value);
 		return map;
 	}
    /**
     * 删除商品
     * @param id
     * @return
     */
    @ResponseBody
 	@RequestMapping("deleteGoods.do")
 	public Map<String,Object> delete(Integer id){
 		
    	boolean value=commodityService.delete(id);
 		Map<String,Object> map=new HashMap<String, Object>();
 		map.put("status", value);
 		return map;
 	}
    
    @ResponseBody
 	@RequestMapping("fuzzySearch.do")
 	public List<Commodity> fuzzySearch(String condition){
 		System.out.println("条件："+condition);
    	return  commodityService.fuzzySearch(condition);
 	}
    
    @ResponseBody
 	@RequestMapping("search.do")
 	public List<Object> search(Integer btid,Integer stid,String condition,String currentPage,String pageSize){
 		System.out.println(currentPage+" "+pageSize+" "+condition);
    	List<Object> result=new ArrayList<Object>();
    	if(UObjects.isNonNullEmpty(condition))condition=condition.trim();
 		result.addAll(commodityService.search(btid,stid,condition,currentPage,pageSize));
 		Map<String, Integer> countPage=new HashMap<String, Integer>();
 		countPage.put("totalCount", commodityService.searchCount(btid, stid, condition));
 		result.add(countPage);
    	return  result;
 	}
    
    @ResponseBody
 	@RequestMapping("getGoodsByCid.do")
 	public Map<String,Object> getGoodsByCid(Integer cid){
    	Commodity commodity=commodityService.select(cid);
    	Map<String,Object> map=new HashMap<String, Object>();
    	
    	if(commodity!=null){
    		map.put("cid", commodity.getCid());
        	map.put("cname", commodity.getCname());
        	map.put("btid", commodity.getBtid());
    		map.put("btype", btypeService.select(commodity.getBtid()).getBtname());
    		map.put("stid", commodity.getStid());
    		map.put("stype", stypeService.select(commodity.getStid()).getStname());
    		map.put("cprice", commodity.getCprice());
    		map.put("cremain", commodity.getCremain());
    		map.put("miniPic", commodity.getMiniPic());
    		Pics pics=goodsPicsService.selectPics(cid);
    		map.put("pic1", pics.getPic1());
    		map.put("pic2", pics.getPic2());
    		map.put("pic3", pics.getPic3());
    		if(commodity.getGrade()==null||commodity.getGrade().equals(0.0)){
    			map.put("grade", commodity.getGrade());
    		}else{
    			map.put("grade", String.format("%.1f", commodity.getGrade()));
    		}
    		
    		map.put("status", true);
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    		String endTime=sdf.format(new Date());
    		Calendar calendar=Calendar.getInstance();
    		calendar.setTime(new Date());
    		calendar.add(Calendar.MONTH, -1);
    		String startTime=sdf.format(calendar.getTime());
    		System.out.println(startTime + " "+ endTime);
    		List<OrderInfo> list = null;
    		int count = 0;
    		try {
    			list = ordersService.getOrderInfosByTimeAndCid(sdf.parse(startTime), sdf.parse(endTime), cid);
    			for (OrderInfo info : list) {
    				count += info.getCsize();
    			}
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

    		map.put("monthSale", count);
    	}else {
    		map.put("status", false);
    		map.put("msg", "查无商品");
    	}
    	
    	return  map;
 	}
}
