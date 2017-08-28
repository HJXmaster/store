package com.mall.entity;

public class Commodity {
    private Integer cid;

    private String cname;

    private Integer btid;

    private Integer stid;

    private Double cprice;

    private Integer cremain;
    
    private String miniPic="goodsPic/default.gif";
    
    private Double grade;

    @Override
	public String toString() {
		return "Commodity [cid=" + cid + ", cname=" + cname + ", btid=" + btid
				+ ", stid=" + stid + ", cprice=" + cprice + ", cremain="
				+ cremain + "]";
	}

	public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Integer getBtid() {
        return btid;
    }

    public void setBtid(Integer btid) {
        this.btid = btid;
    }

    public Integer getStid() {
        return stid;
    }

    public void setStid(Integer stid) {
        this.stid = stid;
    }

    public Double getCprice() {
        return cprice;
    }

    public void setCprice(Double cprice) {
        this.cprice = cprice;
    }

    public Integer getCremain() {
        return cremain;
    }

    public void setCremain(Integer cremain) {
        this.cremain = cremain;
    }

	public String getMiniPic() {
		return miniPic;
	}

	public void setMiniPic(String miniPic) {
		this.miniPic = miniPic;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

}