//package br.ufop.sd.UDPServer;	 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Animation {

	public Animation(){
		
	}
    protected LinkedList<String> animation(String name) throws IOException, InterruptedException {
    	int numberLines=0;
    	LinkedList<String> frames = new LinkedList<String>();
    	
    	if(name.equals("stage")){
    		numberLines = 9;
    	}else if(name.equals("fly")){
    		numberLines = 13;
    	}else if(name.equals("train")){
    		numberLines = 11;
    	}else if(name.equals("sw")){
    		numberLines = 13;
    	}
    	InputStream in = null;
	        try {
	            in = new FileInputStream(name+".txt");
	            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "US-ASCII"));
	            String line;
	            String frame="";
	            while ((line = reader.readLine()) != null) {
	            	if(name.equals("sw")){
	            		frame ="";
	            		int quantityFrames = Integer.parseInt(line);
	            		for(int i = 0; i<numberLines;i++){
            				line = reader.readLine();
		            		frame += "\n"+ line;
            			}
	            		for(int k = 0; k<quantityFrames; k++){
	            			frames.add(frame);
			            }
	            	}
		            else{
		            	frame = "";
		            	for(int i = 0; i<numberLines;i++){
		            		frame += "\n"+ line;
		            		line = reader.readLine();
		            		if(line == null){
		            			break;
		            		}
		            	}
		            	frames.add(frame);
		            }
	            	
	            }
	            in.close();
	            reader.close();
	        
    	}catch (Exception e) {
			e.printStackTrace();
		}
	   return frames;
    }
}
