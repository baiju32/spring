package com.cy.java.serializable;

import java.io.Serializable;


class Problems implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String	title;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Problems [id=" + id + ", title=" + title + "]";
	}
	
}

public class TestSerializable02 {
	public static void main(String[] args) throws Exception{
		
	}
}
