package com.spring.config;

import java.io.File;
import java.net.URLDecoder;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import ognl.DefaultMemberAccess;
import ognl.Ognl;
import ognl.OgnlContext;

public class Configure {
	
	private static Map<String, Map> map;
	
	//用来加载配置,把yml配置数据解析,生成一个map集合
	@SuppressWarnings("unchecked")
	public static void load() throws Exception {
		//获取文件的完整路径
		String path = Configure.class.getResource("/application.yml").getPath();
		path=URLDecoder.decode(path,"UTF-8");
		//使用jackson+yml插件,来解析处理yml格式数据
		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		//读取配置文件,把数据封装成一个map
		map= objectMapper.readValue(new File(path), Map.class);
		
	}
	
	/*"${com.mysql.jdbc.Driver}"*/
	public static String get(String exp) throws Exception {
		//将字符串中的空格去掉,截取${}
		exp = exp.replace("\\s+", "").substring(2,exp.length()-1);
		//用OGNL工具,使用属性表达式从map提取数据
		OgnlContext context = new OgnlContext(null,null,new DefaultMemberAccess(true));
		String value = (String) Ognl.getValue(exp,context, map);
		return value;
	}
	
	
	public static void main(String[] args) throws Exception {
		Configure.load();
		System.out.println(map);
		System.out.println(Configure.get("${spring.datasource.driver}"));
	}
}
