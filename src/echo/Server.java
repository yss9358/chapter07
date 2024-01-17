package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		
		// 서버 소켓 생성
		ServerSocket serverSocket = new ServerSocket();

		//InetSocketAddress aaa = new InetSocketAddress("192.168.0.14", 10001);
		// bind - aaa 변수를 생성하는대신 아래 바인드() 안에 ("ip주소", 포트번호)를 적는다
		serverSocket.bind(new InetSocketAddress("192.168.0.14", 10001));
		
		// 서버시작
		System.out.println("<서버 시작>");
		System.out.println("============================================");

		// 반복 - 
		while(true) {
			System.out.println("[연결을 기다리고 있습니다.]");
			
			// 클라이언트가 접속을 하면 accept()가 실행됨
			Socket socket = serverSocket.accept();
			
			// 출장 thread.star()
			Thread thread = new ServerThread(socket);
			thread.start();
			
		}
		
		/*
		System.out.println("===============================");
		System.out.println("<서버를 종료합니다.>");
		
		// 소켓 닫기
		bw.close();
		br.close();		
		socket.close();
		serverSocket.close();
		*/
	}

}
