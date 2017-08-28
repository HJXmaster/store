package com.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.dao.GoodsPicsMapper;
import com.mall.entity.Pics;
import com.mall.service.GoodsPicsService;
@Service
public class GoodsPicsServiceImpl implements GoodsPicsService {
	
	static String defaultUrl="goodsPic/default.gif";
	@Autowired
	GoodsPicsMapper goodsPicsMapper;
	/* (non-Javadoc)
	 * @see com.mall.service.impl.GoodsPicsService#selectMiniPic(java.lang.Integer)
	 */
	@Override
	public String selectMiniPic(Integer cid){
		String url=goodsPicsMapper.selectMiniPic(cid);
		if(url==null){
			return defaultUrl;
		}
		return url;
	}
	/* (non-Javadoc)
	 * @see com.mall.service.impl.GoodsPicsService#selectPics(java.lang.Integer)
	 */
	@Override
	public Pics selectPics(Integer cid){
		Pics pics=goodsPicsMapper.selectPics(cid);
		if(pics==null){
			pics=new Pics();
			pics.setPic1(defaultUrl);
			pics.setPic2(defaultUrl);
			pics.setPic3(defaultUrl);
		}
		return pics;
	}
}
