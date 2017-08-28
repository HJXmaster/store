package com.mall.entity;

public class OrderInfo {
    private Integer iid;

    private Integer oid;

    private Integer cid;

    private Integer csize;
    
    private Commodity commodity;

    @Override
	public String toString() {
		return "OrderInfo [iid=" + iid + ", oid=" + oid + ", cid=" + cid
				+ ", csize=" + csize + "]";
	}

	public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
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

    public Integer getCsize() {
        return csize;
    }

    public void setCsize(Integer csize) {
        this.csize = csize;
    }

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

}