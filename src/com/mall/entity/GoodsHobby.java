package com.mall.entity;

public class GoodsHobby {

	private Integer curCid;
	private Integer introCid;
	private Double rank;
	private Integer sales;
	private Commodity commodity;
	
	public Double getRank() {
		return rank;
	}
	public void setRank(Double rank) {
		this.rank = rank;
	}
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public Integer getCurCid() {
		return curCid;
	}
	public void setCurCid(Integer curCid) {
		this.curCid = curCid;
	}
	public Integer getIntroCid() {
		return introCid;
	}
	public void setIntroCid(Integer introCid) {
		this.introCid = introCid;
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}
}
