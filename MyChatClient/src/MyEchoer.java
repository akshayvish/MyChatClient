import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class MyEchoer {
	public static String tcpServerPort;
	static Scanner scanInput = new Scanner(System.in);
	
	public static void main(String args[]){
		String ipAddr = "";
		String portNum = "";
		
		if(args!=null)
		{
			tcpServerPort = args[0];
		}
		
		while(scanInput.hasNext()){
			String inputCmd = scanInput.next();
			if(inputCmd.startsWith("Connect")){
				
				String splitInput[] = inputCmd.split(" ");
				
				if(splitInput.length>=2){
					ipAddr = splitInput[1];
					portNum = splitInput[2];
				}
				
				System.out.println("IP Address ::: " + ipAddr + " Port Number ::: " + portNum);
			}
			
		}
	}
}

class Server{
	ServerSocket serverSock;
	Server(ServerSocket sock){
		serverSock = sock;
		while(true){
			Socket newSock;
			try {
				newSock = serverSock.accept();
				client_Handler c_Handler = new client_Handler(newSock);
				c_Handler.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
class client_Handler extends Thread{
	Socket sock;
	client_Handler(Socket newSock){
		sock = newSock;
	}
	public void run(){
		System.out.println("I got request from this Socket :: " + sock);
	}
}