package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException{
		
		// 소켓 객체 생성
		Socket socket = new Socket();
		
		System.out.println("<클라이언트 시작>");
		System.out.println("=============================================");
		System.out.println("[서버에 연결을 요청합니다.]");

		// connect
		
		// InetSocketAddress aaa = new InetSocketAddress("192.168.0.14" , 10001));
		// aaa 대신 new ~ 뒷부분을 아래에 대입
		socket.connect(new InetSocketAddress("192.168.0.43", 10001));
		
		System.out.println("[서버에 연결 되었습니다.]");
		
		// 메세지 보내기 Stream Out
		// Client 에서 서버로 내용을 보내야 하기 때문에 OutputStream 사용
		// OutputStream out = new FileOutputStream("파일경로"); // 파일을 읽을 때 쓰던 방법
		OutputStream os = socket.getOutputStream(); // Client 와 server 의 socket 끼리 연결 되었기 떄문에 사용중인 outputStream 을 get으로 받아온다
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8"); // os 주소값을 보낼때 UTF-8을 사용한다
		BufferedWriter bw = new BufferedWriter(osw); // Buffered 는 일정량을 모아서 보낸다. 모아진 양이 적으면 null 이 뜰수도있음.
		
		// 메세지 받기 Stream in
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		// 스캐너 입력
		Scanner sc = new Scanner(System.in); 
		/*
		InputStream sc = System.in; // 스캐너도 InputStream 을 부모로 두고 있다. 스캐너대신 InputStream 으로 작성 가능.
		InputStreamReader scIsr = new InputStreamReader(sc); // 스캐너를 대신해서 쓸때는 UTF-8을 사용하지 않는다. 스캐너의 기능이 작동되는듯?
		BufferedReader scBr = new BufferedReader(scIsr);
		*/
		
		
		// 반복문 while 입력 - 내용을 반복해서 주고받기 위해서 작성 
		// /q 가 입력면 종료되는 탈출조건 작성
		while(true) {
			String str = sc.nextLine();
			//String str = scBr.readLine(); // 스캐너대신 InputStream sc 로 작성했을때

			if ("/q".equals(str)) { // str.equals("/q") 를 작성하면 null 일때 작동을 안할수도 있다. 따라서 "/q".equals(str) 을 작성.
				break;
			}
			// 메세지 보내기
			bw.write(str);
			bw.newLine(); // 줄바꿈을 해주지 않으면 어디까지가 한줄인지 인식하지 못함.
			bw.flush(); // 일정량이 모이지 않아도 BufferedWriter가 값을 보낸다.
			
			// 메세지 받기
			String reMsg = br.readLine();
			System.out.println("[ server: " + reMsg + " ]" );
		
		}
		
		System.out.println("===================================");
		System.out.println("<클라이언트 종료>");
		
		////////////////////////////////////////////
		// println 만들기
        	
		OutputStream pos = System.out; // System.out.println() 대신 작성하는법.
		OutputStreamWriter posw = new OutputStreamWriter(pos); // (pos, "UTF-8") 작성시 한글이 적용안됨? UTF-8 대신 (pos) 만 작성
		BufferedWriter pbw = new BufferedWriter(posw);
		
		pbw.write("pritnln 테스트");
		pbw.newLine();
		pbw.flush();
		
		
		//닫기 
		sc.close();
		bw.close();
		br.close();
		socket.close();
		
	}

}
