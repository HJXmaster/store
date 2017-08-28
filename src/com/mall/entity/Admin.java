package com.mall.entity;

public class Admin {
    private Integer aid;

    private String aname;

    private String apassword;

    private String atype;

    @Override
	public String toString() {
		return "Admin [aid=" + aid + ", aname=" + aname + ", apassword="
				+ apassword + ", atype=" + atype + "]";
	}

	public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname == null ? null : aname.trim();
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword == null ? null : apassword.trim();
    }

    public String getAtype() {
        return atype;
    }

    public void setAtype(String atype) {
        this.atype = atype == null ? null : atype.trim();
    }
}