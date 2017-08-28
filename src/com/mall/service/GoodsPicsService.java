package com.mall.service;

import com.mall.entity.Pics;

public interface GoodsPicsService {

	public abstract String selectMiniPic(Integer cid);

	public abstract Pics selectPics(Integer cid);

}