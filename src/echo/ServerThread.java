package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	
	//필드
	
	private Socket socket;
	
	// 생성자
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			System.out.println("[클라이언트가 연결 되었습니다.]");
			
			// 로직
			
			// 메세지 받기용 스트림 in
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			// 메세지 보내기용 스트림 out
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			// 받은메세지들을 계속 보내주기 위해 반복문 작성
			while(true) {
				// 메세지 받기 read
				String msg = br.readLine();
				if(msg == null) { // msg 와 null 은 주소 끼리의 비교이므로 equals가 아닌 == 사용
					break;
				}
				System.out.println("[ 받은 메세지: " + msg + " ]");
				
				// 메세지 보내기 write
				bw.write(msg); // 받은걸 그대로 보내주는 프로그램, 따라서 "내용" 가 아닌 받은 메세지 msg를 작성해준다.
				bw.newLine(); // 줄바꿈 작성한 것은 읽어들이지 않고 인식만함. 
				bw.flush(); // BufferedWriter 가 일정량 모이지 않아도 메세지를 보냄
				} 
		} catch(IOException e) {
			System.out.println(e.toString());
			}
		
	}

}



