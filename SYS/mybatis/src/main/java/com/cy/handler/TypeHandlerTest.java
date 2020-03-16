package com.cy.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


public class TypeHandlerTest {
	
	private SqlSessionFactory sessionFactory;
	
	public TypeHandlerTest() {
		InputStream in;
		try {
			in = Resources.getResourceAsStream("mybatis-config.xml");
			this.sessionFactory = new SqlSessionFactoryBuilder().build(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFindAll() {
		SqlSession sqlSession = sessionFactory.openSession();
		GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
		List<Goods> list = goodsMapper.findAll();
		System.out.println(list);
		sqlSession.close();
	}
	
	@Test
	public void testInsertGoods() {
		SqlSession sqlSession = sessionFactory.openSession();
		GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
		int rows = goodsMapper.insertGoods(new Goods(null, "javaEE", Status.PUBLISHED));
		System.out.println(rows);
		sqlSession.commit();
		sqlSession.close();
	}
	
	
}
