package com.cy.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
/**自定义mybatis类型转换器*/
public class StatusTypeHandler implements TypeHandler<Status>{
	/**发送sql之前执行:为参数赋值pstmt.setXxx(1,xxx)*/
	@Override
	public void setParameter(PreparedStatement ps, int i, Status parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getCode());
		System.out.println("setParameter()执行了");
	}
	/**处理结果时调用:rs.getXxx(columnName)*/
	@Override
	public Status getResult(ResultSet rs, String columnName) throws SQLException {
		int code = rs.getInt(columnName);
		System.out.println("getResult()执行了");
		return Status.valueOf(code);
	}
	/**处理结果时调用:rs.getXxx(columnIndex)*/
	@Override
	public Status getResult(ResultSet rs, int columnIndex) throws SQLException {
		int code = rs.getInt(columnIndex);
		System.out.println("getResult()执行了");
		return Status.valueOf(code);
	}
	/**处理存储过程对应的结果集时被调用*/
	@Override
	public Status getResult(CallableStatement cs, int columnIndex) throws SQLException {
		int code = cs.getInt(columnIndex);
		return Status.valueOf(code);
	}

}
