package com.mall.entity;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	private List<PreOrder> items=new ArrayList<PreOrder>();
	
	
	public List<PreOrder> getItems() {
		return items;
	}


	public void setItems(List<PreOrder> items) {
		this.items = items;
	}


	public boolean addItem(PreOrder item){
		if(items.contains(item)){
			for(PreOrder po:items){
				if(po.equals(item)){
					if(item.getCommodity().getCremain()<po.getCsize()+item.getCsize()){
						return false;
					}
					po.setCsize(po.getCsize()+item.getCsize());
					po.setCid(item.getCid());
				}
			}
		}else {
			items.add(item);
		}
		return true;
	}
	
	public void updateItem(PreOrder item){
		if(items.contains(item)){
			for(PreOrder po:items){
				if(po.equals(item)){
					po.setCsize(item.getCsize());
					po.setCid(item.getCid());
				}
			}
		}else {
			items.add(item);
		}
	}
	
	public void setUser(Integer uid){
		for(PreOrder po:items){
			po.setUid(uid);
		}
	}
	public void delete(Integer cid){
		for(PreOrder po:items){
			if(po.getCid().equals(cid)){
				items.remove(po);
				break;
			}
		}
	}
}
