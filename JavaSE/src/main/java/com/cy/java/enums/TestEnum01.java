package com.cy.java.enums;
/**枚举类型*/
enum Gender{
	MALE,FEMALE,NONE;//对象实例(类加载时创建)
	//默认私有构造方法
	private Gender() {
	}
}

enum Sex{
	MALE("男"),FEMALE("女");//对象实例(类加载时创建)
	//默认私有构造方法
	private String name;
	private Sex(String name) {
		this.name=name;
	}
}


class Product{
	Gender gender=Gender.NONE;
}

public class TestEnum01 {
	
}
