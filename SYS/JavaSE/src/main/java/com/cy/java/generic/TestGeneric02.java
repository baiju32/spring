package com.cy.java.generic;

interface Task<Param,Result>{
	Result execute(Param param);
}

class IntegerConvertTask implements Task<String,Integer>{
	@Override
	public Integer execute(String param) {
		return Integer.parseInt(param);
	}
}

public class TestGeneric02 {
	public static void main(String[] args) {
		
	}
}
