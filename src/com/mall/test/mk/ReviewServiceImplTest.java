package com.mall.test.mk;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mall.entity.Review;
import com.mall.service.ReviewService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml",
		"file:WebRoot/WEB-INF/springmvc-servlet.xml" })
public class ReviewServiceImplTest {

	@Autowired
	ReviewService service;
	@Test
	public void testDelete() {
		boolean b=service.delete(3);
		System.out.println(b);
	}

	@Test
	public void testInsert() {
		Review review=new Review();
		review.setCid(1);
		boolean b=service.insert(review);
		System.out.println(b);
	}

	@Test
	public void testSelectOne() {
		Review review=service.selectOne(3);
		System.out.println(review);
	}

	@Test
	public void testGetReviewsByCid() {
		List<Review> reviews=service.getReviewsByCid(3);
		System.out.println(reviews);
	}

}
