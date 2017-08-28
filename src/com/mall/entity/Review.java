package com.mall.entity;

import java.util.Date;

public class Review {
    private Integer rid;

    private Integer oid;

    private Integer cid;

    private Date rtime;

    private String rtext;
    
    private Double grade;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getRtime() {
        return rtime;
    }

    public void setRtime(Date rtime) {
        this.rtime = rtime;
    }

    public String getRtext() {
        return rtext;
    }

    public void setRtext(String rtext) {
        this.rtext = rtext == null ? null : rtext.trim();
    }

	@Override
	public String toString() {
		return "Review [rid=" + rid + ", oid=" + oid + ", cid=" + cid
				+ ", rtime=" + rtime + ", rtext=" + rtext + "]";
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

}