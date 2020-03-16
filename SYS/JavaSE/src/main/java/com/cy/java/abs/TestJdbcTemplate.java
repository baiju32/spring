package com.cy.java.abs;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

class JdbcTemplate{
	//DataSource为java中连接池规范
	private DataSource dataSource;
	public JdbcTemplate(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
}
/**此对象创建连接池对象*/
interface DataSourceFactory{
	
	static DataSource newHiKariCP() {
		HikariDataSource hds = new HikariDataSource();
		hds.setJdbcUrl("jdbc:mysql:///jtdb?serverTimezone=GMT");
		hds.setUsername("root");
		hds.setPassword("root");
		return hds;
	}
	
	
}

public class TestJdbcTemplate {
	public static void main(String[] args) throws SQLException {
		DataSource dataSource=DataSourceFactory.newHiKariCP();
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		Connection conn = jt.getConnection();
		System.out.println(conn);
		
	}
}
