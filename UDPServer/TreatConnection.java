//package br.ufop.sd.UDPServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.LinkedList;

public class TreatConnection implements Runnable{
	private DatagramSocket aSocket = null;
	private DatagramPacket request = null;
	
	public TreatConnection(DatagramSocket aSocket, DatagramPacket req) {
		this.aSocket = aSocket;
		this.request = req;
	}

	@Override
	public void run() {
		try{
			System.out.println("Thread started!");
			DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), 
    				request.getAddress(), request.getPort());
    			String command = new String (request.getData(), request.getOffset(), request.getLength()).trim();
    			System.out.println("Received: " + command);
    			if(command.equals("CATALOG")){
    				reply.setData("\n[fly],[stage],[sw],[train]".getBytes());
    				aSocket.send(reply);
    			}
    			if(command.contains("WATCH")){
    				command = command.replace("WATCH ", "");
    				Animation a = new Animation();
        			LinkedList<String> frames = a.animation(command);
        			String t = frames.size()+"";
        			reply.setData(t.getBytes());
        			for(String frame : frames){
        				reply.setData(frame.getBytes());
        				aSocket.send(reply);
        			}
        			for(int i=0;i<20;i++){
        				reply.setData("EOF".getBytes());
        				aSocket.send(reply);
        			}
    			}else{
    				reply.setData("Non-existent command. Try again.".getBytes());
    				aSocket.send(reply);
    			}
			
		}catch(IOException | InterruptedException e){
			e.printStackTrace();
		}
		
	}

}
