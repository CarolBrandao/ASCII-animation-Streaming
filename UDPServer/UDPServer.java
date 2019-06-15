//package br.ufop.sd.UDPServer;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
public class UDPServer{
    public static void main(String args[]) throws InterruptedException{ 
    	DatagramSocket aSocket = null;
    	TreatConnection treatconnection = null;
		try{
			System.out.println("Server running..");
	    	aSocket = new DatagramSocket(6789);
			// create socket at agreed port
			byte[] buffer = new byte[1000];
 			while(true){
 				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
 				aSocket.receive(request);  
 				treatconnection = new TreatConnection(aSocket, request);
 				Thread treat_connection = new Thread(treatconnection);
 				treat_connection.start();
    		}
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e) {System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
    }
}
