package com.mall.entity;

import java.util.List;

public class Btype {
    private Integer btid;

    private String btname;

    private List<Stype> stypes;
    @Override
	public String toString() {
		return "Btype [btid=" + btid + ", btname=" + btname + "]";
	}

	public Integer getBtid() {
        return btid;
    }

    public void setBtid(Integer btid) {
        this.btid = btid;
    }

    public String getBtname() {
        return btname;
    }

    public void setBtname(String btname) {
        this.btname = btname == null ? null : btname.trim();
    }

	public List<Stype> getStypes() {
		return stypes;
	}

	public void setStypes(List<Stype> stypes) {
		this.stypes = stypes;
	}
}