package com.cy.java.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;


class Replay implements Serializable{
	private static final long serialVersionUID = 5886547255174886723L;
	/**transient关键字在描述的属性不进行序列化*/
	private transient int id;
	private String context;
	/**当我们调用对象流的writeObjcet方法时,
	 * 系统底层会调用此类中的writeObjcet方法,
	 * 但此方法的格式必须为
	 * private void writeObjcet(ObjectOutputStream oos)
	 */
	private void writeObject(ObjectOutputStream oos)throws IOException{
		//1.对数据加密
		//获取加密对象
		Encoder en = Base64.getEncoder();
		//执行加密操作
		byte[] encode = en.encode(context.getBytes());
		context=new String(encode);
		System.out.println(context);
		//2.对数据进行默认序列化
		oos.defaultWriteObject();
	}

	private void readObject(ObjectInputStream in)throws IOException, ClassNotFoundException{
		//1.对内容进行反序列化
		in.defaultReadObject();
		//2.对内容进行解密操作
		//获取解密对象
		Decoder decoder = Base64.getDecoder();
		//执行解密操作
		byte[] decode = decoder.decode(context.getBytes());
		context=new String(decode);
	}
	
	public Replay(int id, String context) {
		super();
		this.id = id;
		this.context = context;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	@Override
	public String toString() {
		return "Replay [id=" + id + ", context=" + context + "]";
	}

}

public class TestSerializable03 {

	public static void Server() throws Exception {//服务端
		//1.构建server对象,并在9999端口进行监听
		ServerSocket server = new ServerSocket(9999);
		//2.等待客户端的连接
		Socket socket = server .accept();
		System.out.println("客户端已连接");
		//3.获取对象输入流对象
		ObjectInputStream ois = 
				new ObjectInputStream(socket.getInputStream());
		//4.读取流中对象
		Object object = ois.readObject();
		System.out.println(object);
		//5.释放资源
		ois.close();
		socket.close();
		server.close();
	}

	public static void Client() throws Exception{//客户端
		//1.构建客户端对象
		Socket socket = new Socket("127.0.0.1",9999);
		//2.获取输出流对象
		ObjectOutputStream oos = 
				new ObjectOutputStream(socket.getOutputStream());
		//3.将对象写到服务端
		Replay replay = new Replay(1, "你好");
		oos.writeObject(replay);
		//4.释放资源
		oos.close();
		socket.close();
	}

	public static void main(String[] args){
		new Thread() {
			@Override
			public void run() {
				try {
					Server();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				try {
					Client();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
