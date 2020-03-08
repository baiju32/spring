package com.cy.java.oop.features;
/**
 * aop ,ocp原则,对源代码封闭,对扩展开放
 * @author lll
 *
 */
interface MailService{
	public void send(String mag);
}

class DefaultMailService implements MailService{

	@Override
	public void send(String mag) {
		System.out.println("send:"+mag);
	}
}

class LogMailService implements MailService{
	
	private MailService mailService;
	public LogMailService(MailService mailService) {
		this.mailService=mailService;
	}
	@Override
	public void send(String mag) {
		System.out.println("send:"+System.currentTimeMillis());
		mailService.send(mag);
		System.out.println("send:"+System.currentTimeMillis());
	}
}

public class TestCompose02 {
	public static void main(String[] args) {
		MailService mailService=new LogMailService(
								new DefaultMailService());
		mailService.send("简单aop案例");
		
	}
}
