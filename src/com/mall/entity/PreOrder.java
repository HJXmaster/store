package com.mall.entity;

public class PreOrder {
    private Integer pid;

    private Integer uid;

    private Integer cid;

    private Integer csize;

    private Commodity commodity;
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

	@Override
	public String toString() {
		return "PreOrder [pid=" + pid + ", uid=" + uid + ", cid=" + cid
				+ ", csize=" + csize + ", commodity=" + commodity + "]";
	}
	
	@Override
	public int hashCode() {
	      if(cid!=null&&csize!=null)
	    	  return cid*10+csize;
	      return (int)Math.random()*123249987;
	   }
	@Override
	public boolean equals(Object obj) {
		if (this == obj) // 比较地址
			return true;
		
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreOrder other = (PreOrder) obj;
//		if(this.pid!=null&&this.pid.equals(other.getPid())){
//			return false;
//		}
		if((this.uid==null||this.uid.equals(0))&&(other.getUid()==null||other.getUid().equals(0))){
			if(this.cid.intValue()==other.getCid().intValue())
				return true;
		}
		return false;
	}
	
}