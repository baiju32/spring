package com.cy.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class MybatisTest01 {

	@Test
	public void testMybatis01() throws IOException {
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

		SqlSession sqlSession = sqlSessionFactory.openSession();
		String statement="com.cy.mybatis.UserMapper.findAll";
		List<User> list = sqlSession.selectList(statement);
		System.out.println(list);

	}
	@Test
	public void testMybatis02() throws IOException {
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = mapper.findAll();
		System.out.println(list);
	}
}
