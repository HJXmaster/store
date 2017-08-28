package com.mall.controller.mk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.entity.Btype;
import com.mall.entity.Stype;
import com.mall.service.BtypeService;
import com.mall.service.StypeService;

/**
 * 大小标签管理
 * @author MK
 *
 */
@Controller
public class TypeManagerController {

	@Autowired
	private BtypeService btypeService;
	@Autowired
	private StypeService stypeService;

	/**
	 * 添加大标签
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addBtype.do")
	public Map<String, Object> addBtype(String name) {
        Btype btype=new Btype();
        btype.setBtname(name);
		boolean value=btypeService.insert(btype);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("status", value);
		if(!value){
			map.put("msg", "昵称已经存在");
		}
		return map;

	}
	
	@ResponseBody
	@RequestMapping("updateBtype.do")
	public Map<String, Object> updateBtype(Btype btype) {

		boolean value=btypeService.update(btype);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("status", value);
		if(!value)
		map.put("msg", "更新失败");
		return map;

	}
    /**
     * 查找所有大标签
     * @return
     */
	@ResponseBody
	@RequestMapping("queryBtype.do")
	public Map<String, Object> queryBtype() {

		List<Btype> list=btypeService.selectAll();
		Map<String,Object> map=new HashMap<String, Object>();
 		map.put("btypeList", list);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("deleteBtype.do")
	public Map<String, Object> deleteBtype(Integer id) {
      
		boolean value=btypeService.delete(id);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("status", value);
		return map;

	}
	
	
	/**
	 * 添加小标签
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addStype.do")
	public Map<String, Object> addStype(String name,Integer id) {
        Stype stype=new Stype();
        stype.setStname(name);
        stype.setBtid(id);
		boolean value=stypeService.insert(stype);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("status", value);
		if(!value){
			map.put("msg", "小类别名称重复或大类别为空");
		}
		return map;

	}
	/**
     * 查找所有小标签
     * @return
     */
	@ResponseBody
	@RequestMapping("queryStype.do")
	public Map<String, Object> queryStype(Integer btid) {

		List<Stype> list=stypeService.selectByBtype(btid);
		Map<String,Object> map=new HashMap<String, Object>();
 		map.put("stypeList", list);
		return map;
	}
	
	/**
     * 查找所有小标签
     * @return
     */
	@ResponseBody
	@RequestMapping("queryAllStype.do")
	public Map<String, Object> queryAllStype() {
		Map<String,Object> map=new HashMap<String, Object>();
		List<Map<String,Object>> stypeList=new ArrayList<Map<String,Object>>();
		List<Stype> list=stypeService.selectAll();
		for (Stype stype : list) {
			Map<String,Object> stypeB=new HashMap<String, Object>();
			stypeB.put("stid", stype.getStid());
			stypeB.put("btid", stype.getBtid());
			stypeB.put("stname", stype.getStname());
			Btype btype=btypeService.select(stype.getBtid());
			if(btype!=null)
			  stypeB.put("btname", btype.getBtname());
            stypeList.add(stypeB);
		}
		
 		map.put("stypeList", stypeList);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("updateStype.do")
	public Map<String, Object> updateStype(Stype stype) {

		boolean value=stypeService.update(stype);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("status", value);
		if(!value)
		map.put("msg", "更新失败");
		return map;

	}
	@ResponseBody
	@RequestMapping("deleteStype.do")
	public Map<String, Object> deleteStype(Integer id) {
      
		boolean value=stypeService.delete(id);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("status", value);
		return map;

	}
}
