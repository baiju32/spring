package com.cy.java.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


class Message implements Serializable{
	
	private static final long serialVersionUID = 7770654353954140625L;
	private int id;
	private String	content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + "]";
	}
	
}

public class TestSerializable01 {
	public static void main(String[] args) throws Exception{
		Message message = new Message();
		message.setId(10);
		message.setContent("对象序列化");
		//序列化对象
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("f1.txt"));
		oos.writeObject(message);
		oos.close();
		System.out.println("序列化成功");
		//反序列化对象
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream("f1.txt"));
		Object message1 = ois.readObject();
		ois.close();
		System.out.println("反序列化成功"+message1);
		System.out.println(message1==message);
		
	}
}
