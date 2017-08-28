package com.mall.entity;

import java.util.List;

public class User {
    @Override
	public String toString() {
		return "User [uid=" + uid + ", unickname=" + unickname + ", upassword="
				+ upassword + ", uemail=" + uemail + ", uaddress=" + uaddress
				+ ", uphone=" + uphone + ", uidcard=" + uidcard + ", uname="
				+ uname + ", uquestion=" + uquestion + ", uanswer=" + uanswer
				+ ", upostcode=" + upostcode + "]";
	}

	private Integer uid;

    private String unickname;

    private String upassword;

    private String uemail;

    private String uaddress;

    private String uphone;

    private String uidcard;

    private String uname;

    private String uquestion;

    private String uanswer;

    private String upostcode;

//    private List<PreOrder> cart;
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUnickname() {
        return unickname;
    }

    public void setUnickname(String unickname) {
        this.unickname = unickname == null ? null : unickname.trim();
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword == null ? null : upassword.trim();
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail == null ? null : uemail.trim();
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress == null ? null : uaddress.trim();
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone == null ? null : uphone.trim();
    }

    public String getUidcard() {
        return uidcard;
    }

    public void setUidcard(String uidcard) {
        this.uidcard = uidcard == null ? null : uidcard.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUquestion() {
        return uquestion;
    }

    public void setUquestion(String uquestion) {
        this.uquestion = uquestion == null ? null : uquestion.trim();
    }

    public String getUanswer() {
        return uanswer;
    }

    public void setUanswer(String uanswer) {
        this.uanswer = uanswer == null ? null : uanswer.trim();
    }

    public String getUpostcode() {
        return upostcode;
    }

    public void setUpostcode(String upostcode) {
        this.upostcode = upostcode == null ? null : upostcode.trim();
    }

//	public List<PreOrder> getCart() {
//		return cart;
//	}
//
//	public void setCart(List<PreOrder> cart) {
//		this.cart = cart;
//	}
}