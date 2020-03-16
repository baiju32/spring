package com.cy.java.serializable;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public final class KryoUtil {
	
	private KryoUtil() {	}
	
	private static final ThreadLocal<Kryo> local=new ThreadLocal<Kryo>() {
		protected Kryo initialValue() {
			Kryo kryo=new Kryo();
			kryo.setRegistrationRequired(false);
			System.out.println("initialValue()");
			return kryo;
		};
	};

	public static byte[] serialize(Object object) throws IOException {
		Kryo kryo=local.get();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Output output = new Output(bos);
		kryo.writeClassAndObject(output, object);
		bos.close();
		output.close();
		return bos.toByteArray();
	}


	public static Object deserialize(byte[] array) throws IOException {
		Kryo kryo=local.get();
		ByteArrayInputStream bis = new ByteArrayInputStream(array);
		Input input = new Input(bis);
		Object object = kryo.readClassAndObject(input);
		bis.close();
		input.close();
		return object;
	}
}
