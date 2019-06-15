//package br.ufop.sd.UDPClient;

import java.net.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;
public class UDPClient{
	static DatagramSocket aSocket = null;
    public static void main(String args[]) throws InterruptedException{ 
    	LinkedList<String> frames = new LinkedList<String>(); 
		Scanner scanner = new Scanner(System.in);
		try {
			aSocket = new DatagramSocket();    
			InetAddress aHost = InetAddress.getByName("localhost");
			int serverPort = 6789;	
			String m = "";
			DatagramPacket request =
				 	new DatagramPacket(m.getBytes(),  m.length(), aHost, serverPort);
			while(true){
				System.out.println("");
				System.out.println("[CATALOG] Show movie list.");
				System.out.println("[WATCH  movieTitle] Run movie.");
				m = scanner.nextLine();
					request.setData(m.getBytes());
					aSocket.send(request);			                        
					byte[] buffer = new byte[1000];
					DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
					if(m.contains("WATCH")){
						System.out.println("Loading..");
						String isEof="";
						while(true){
							aSocket.setSoTimeout(5000);
							aSocket.receive(reply);
							isEof = new String (reply.getData(), reply.getOffset(), reply.getLength()).trim();
							if(isEof.equals("EOF")){
								break;
							}
							frames.add(new String(reply.getData(), reply.getOffset(), reply.getLength()));
						}
						showFrame(frames);
						break;
					}
					else if(m.equals("CATALOG")){
						aSocket.setSoTimeout(3000);
						aSocket.receive(reply);
						System.out.println(new String(reply.getData()));
					}else{
						aSocket.setSoTimeout(3000);
						aSocket.receive(reply);
						System.out.println(new String(reply.getData()));
					}
			}
			
		}catch (SocketException e){
			System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){
			System.out.println("IO: " + e.getMessage());
		}finally {
			if(aSocket != null) 
				aSocket.close();
			}
	}	
    private static void showFrame(LinkedList<String> frames) throws InterruptedException{
    	for(String frame: frames){
    		System.out.print("\033[H\033[2J");
    		System.out.println(new String(frame));
    		Thread.sleep(66);
    	}
    	aSocket.close();
    }
}
