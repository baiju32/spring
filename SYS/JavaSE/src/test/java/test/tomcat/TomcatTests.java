package test.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.junit.Test;

import com.cy.servlet.HelloServlet;

public class TomcatTests {

	@Test
	public void testTomcat() throws Exception {
		//1.构建tomcat对象
		Tomcat tomcat = new Tomcat();
		//2.构建Connector对象(连接器),负责协议配置,端口配置等
		Connector connector = new Connector("HTTP/1.1");
		//3.设置connector对象
		connector.setPort(80);
		tomcat.getService().addConnector(connector);
		//4.启动tomcat
		tomcat.start();
		//5.阻塞当前线程
		tomcat.getServer().await();
	}

	@Test
	public void testServlet() throws Exception {
		//1.构建tomcat对象
		Tomcat tomcat = new Tomcat();
		//2.构建Connector对象(连接器),负责协议配置,端口配置等
		Connector connector = new Connector("HTTP/1.1");
		//3.设置connector对象
		connector.setPort(80);
		tomcat.getService().addConnector(connector);
		//4.1添加servlet
		Context context = tomcat.addContext("/",null);
		Tomcat.addServlet(context,"HelloServlet","com.cy.servlet.HelloServlet");
		context.addServletMappingDecoded("/hello", "HelloServlet");
		//4.启动tomcat
		tomcat.start();
		//5.阻塞当前线程
		tomcat.getServer().await();
	}

}

