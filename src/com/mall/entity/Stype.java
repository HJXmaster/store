package com.mall.entity;

public class Stype {
    private Integer stid;

    private String stname;

    private Integer btid;

    public Integer getStid() {
        return stid;
    }

    public void setStid(Integer stid) {
        this.stid = stid;
    }

    public String getStname() {
        return stname;
    }

    public void setStname(String stname) {
        this.stname = stname == null ? null : stname.trim();
    }

    public Integer getBtid() {
        return btid;
    }

    public void setBtid(Integer btid) {
        this.btid = btid;
    }

	@Override
	public String toString() {
		return "Stype [stid=" + stid + ", stname=" + stname + ", btid=" + btid
				+ "]";
	}
}