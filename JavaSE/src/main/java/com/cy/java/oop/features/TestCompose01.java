package com.cy.java.oop.features;

class Person{}
class Male extends Person{}//is a 
class Female extends Person{}

class Family{
	private Male male;//has a(组合)
	private Female female;
	
	public Male getMale() {
		return male;
	}
	public void setMale(Male male) {
		this.male = male;
	}
	public Female getFemale() {
		return female;
	}
	public void setFemale(Female female) {
		this.female = female;
	}
	
}

public class TestCompose01 {
	public static void main(String[] args) {
		Family family = new Family();
		family.setMale(new Male());
		family.setFemale(new Female());
		
		
	}
}
