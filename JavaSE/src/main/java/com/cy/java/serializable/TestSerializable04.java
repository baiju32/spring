package com.cy.java.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

//基于Kryo序列化框架完成对象的序列化操作
//最大优势:速度比较快
public class TestSerializable04 {

	public static void main(String[] args) throws Exception{
		Email email1 = new Email(1,"使用kryo框架完成序列化");
		
		//kryoSerialize(email1);
		byte[] array = KryoUtil.serialize(email1);
		System.out.println(Arrays.toString(array));
		Object obj = KryoUtil.deserialize(array);
		System.out.println(obj);
	}
	
	
	public static void kryoSerialize(Email email1) throws Exception {
		Kryo kryo=new Kryo();
		kryo.register(Email.class);
		Output output = new Output(new FileOutputStream("kryo.txt"));
		kryo.writeObject(output, email1);
		output.close();
		System.out.println("序列化完成");
		
		Input input = new Input(new FileInputStream("kryo.txt"));
		Email email2 = kryo.readObject(input, Email.class);
		input.close();
		System.out.println(email2);
	}
}

class Email{
	private int id;
	private String mgs;
	public Email() {
		// TODO Auto-generated constructor stub
	}
	
	public Email(int id, String mgs) {
		super();
		this.id = id;
		this.mgs = mgs;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMgs() {
		return mgs;
	}
	public void setMgs(String mgs) {
		this.mgs = mgs;
	}
	@Override
	public String toString() {
		return "Email [id=" + id + ", mgs=" + mgs + "]";
	}
	
}