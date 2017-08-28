package com.mall.entity;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.mall.util.DateSerializer;

public class Orders {
    private Integer oid;

    private Integer uid;

    private String oaddress;

    private Double ototalprice;
    @JsonSerialize(using=DateSerializer.class)
    private Date ostime;
    @JsonSerialize(using=DateSerializer.class)
    private Date oetime;

    private String ostate;
    
    private String oname;
    private String ophone;
    private String opostcode;

    public String getOname() {
		return oname;
	}

	public void setOname(String oname) {
		this.oname = oname;
	}

	public String getOphone() {
		return ophone;
	}

	public void setOphone(String ophone) {
		this.ophone = ophone;
	}

	public String getOpostcode() {
		return opostcode;
	}

	public void setOpostcode(String opostcode) {
		this.opostcode = opostcode;
	}

	private User user;
    private List<OrderInfo> infos;
    


	@Override
	public String toString() {
		return "Orders [oid=" + oid + ", uid=" + uid + ", oaddress=" + oaddress
				+ ", ototalprice=" + ototalprice + ", ostime=" + ostime
				+ ", oetime=" + oetime + ", ostate=" + ostate + ", oname="
				+ oname + ", ophone=" + ophone + ", opostcode=" + opostcode
				+ ", user=" + user + ", infos=" + infos + "]";
	}

	public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getOaddress() {
        return oaddress;
    }

    public void setOaddress(String oaddress) {
        this.oaddress = oaddress == null ? null : oaddress.trim();
    }

    public Double getOtotalprice() {
        return ototalprice;
    }

    public void setOtotalprice(Double ototalprice) {
        this.ototalprice = ototalprice;
    }

    public Date getOstime() {
        return ostime;
    }

    public void setOstime(Date ostime) {
        this.ostime = ostime;
    }

    public Date getOetime() {
        return oetime;
    }

    public void setOetime(Date oetime) {
        this.oetime = oetime;
    }

    public String getOstate() {
        return ostate;
    }

    public void setOstate(String ostate) {
        this.ostate = ostate == null ? null : ostate.trim();
    }

	public List<OrderInfo> getInfos() {
		return infos;
	}

	public void setInfos(List<OrderInfo> infos) {
		this.infos = infos;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}