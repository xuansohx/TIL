package tcp1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/*class ServerThread extends Thread {
	Socket socket;
	
	// server는 socket만 만들고, socket으로 stream 만듦
	OutputStream out;
	DataOutputStream dout;
	InputStream in;
	DataInputStream din;

	public ServerThread(Socket socket) throws IOException {
		this.socket = socket;
		out = socket.getOutputStream();
		dout = new DataOutputStream(out); // Android의 AsynTask 역할
		in = socket.getInputStream();
		din = new DataInputStream(in); // Client에게서 오는 data 받음
	}

	public void run() {
		try {
			String str = null;
			str = din.readUTF();
			System.out.println(socket.getInetAddress()+" : "+str);
			dout.writeUTF("안녕");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(din != null) {
				try {
					din.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(dout != null) {
				try {
					dout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}*/

public class Server {

	boolean flag = true; // accept를 위한 것으로 while문 안에서 사용
	boolean rflag = true;
	Map<String,DataOutputStream> map = new HashMap<>(); // map에 data 넣어놓고 sender..?
	
	Socket socket;
	ServerSocket serverSocket;
	
	public Server() {

	}

	public Server(int port) throws IOException {
		// port를 주면 server가 준비를 함 (여기부터 만듦?)
		// Network 준비 → 포트 중복되는 문제와 방화벽 주의
		serverSocket = new ServerSocket(port); // port
		System.out.println("Server Start..");
		Runnable r = new Runnable() {

			@Override
			public void run() {
				try {
					while(flag) {
						System.out.println("ServerReady");
						socket=serverSocket.accept();
						new Receiver(socket).start();
						System.out.println(socket.getInetAddress());
					}
					System.out.println("Server END");
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(r).start();
	}

	public void start() throws IOException {
		/*
		 * while (flag) { System.out.println("Server Ready.."); Socket socket =
		 * serverSocket.accept(); new Receiver(socket).start();
		 * System.out.println(socket.getInetAddress()); // 첫번째 사람이 들어와서 socket이 만들어졌는데
		 * // 다른 사람이 들어오면 socket의 변수가 바뀜 // 따라서 위에서 socket을 선언하지 않고, 매번 새로 만듦
		 * 
		 * //new ServerThread(socket).start(); // 여기에 정의해버리면 시간이 오래 걸려서 // 다음 사람이 들어오는데
		 * 시간이 많이 소요됨 // ∴ 위에서 Thread class 만들어서 정의 }
		 */
	//	System.out.println("Server End..");
		
	    // Server에서도 메시지를 보냄
		Scanner sc = new Scanner(System.in);
		boolean sflag = true;
		while (sflag) {
			System.out.println("Input Msg.");
			String str = sc.next();
			sendMsg(str); // message를 보냄 - sendMsg 함수와 send라는 Thread?
			// Start가 되면 socket이 생성되고, Thread를 통해 주고 받음
			if (str.contentEquals("q")) {
				break;
			}
		}
		sc.close();
		
	}
	
	public void sendMsg(String msg) {
		Sender sender = new Sender();
		sender.setMsg(msg);
		sender.start();
	}
	
	class Sender extends Thread{

		String msg;

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public void run() {
			Collection<DataOutputStream> col = map.values();
			// .values 하면 map에 있는 key 값 무시하고 value 값 가져옴
			Iterator<DataOutputStream> it = col.iterator();
			while(it.hasNext()) {
				try {
					it.next().writeUTF(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class Receiver extends Thread{
		Socket socket;

		boolean rflag = true;
		
		InputStream in;
		DataInputStream din;
		OutputStream out;
		DataOutputStream dout;
		
		String ip;

		public Receiver(Socket socket) throws IOException {
			this.socket = socket;
			in = socket.getInputStream();
			din = new DataInputStream(in);
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
			ip = socket.getInetAddress().toString();
			map.put(ip,dout);
			System.out.println("접속자수 : "+map.size());	
		}

		public void run() {
			// 받는 역할만 수행
			try {
				while (rflag) {
					String str = din.readUTF();
					if(str.equals("q")) {
						map.remove(ip);
						System.out.println("접속자수 : "+map.size());
						break;
					}
					System.out.println(str);
					sendMsg(str);
				}
			} catch (Exception e) {
				//e.printStackTrace();
				System.out.println("비정상 아웃:"+ip);
				map.remove(ip);
				System.out.println("접숙자수 : "+map.size());
				if(din != null) {
				try {
					din.close();
				}catch(IOException e1) {
					e1.printStackTrace();
					}
				}
			}finally {
				if(socket != null) {
					try {
						socket.close();
					}catch(IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		Server server = null;
		try {
			server = new Server(8888);
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
