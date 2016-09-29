package remoteControl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class mainPage extends Socket {
	public static void main(String[] args) 
	        throws Exception, IOException{
	        Socket socket = new Socket("127.0.0.1",1234);
            InputStream dataIn = socket.getInputStream();
	        ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
	        
//	        byte[] bytes = new byte[4];
//	        for(int i=0;i<4;++i){
//	        	dataIn.read(bytes);
//	        }
//	        System.out.println(byte2int(bytes));
	        
	        ClientWindow1 clw = new ClientWindow1(objectOut);
	        
	        byte[] imageByte = readInputStream(dataIn);
	        //System.out.println(imageByte.toString());
	        
	        
//	        byte[] imageByte = new byte[1024]; 
//	        dataIn.read(imageByte,0,1024);
//	        System.out.println(imageByte.toString());
	        clw.repaintImage(imageByte);
	    } 
	
	public static byte[] readInputStream(InputStream inputStream) throws Exception{
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while(((len = inputStream.read(buffer)) != -1) && inputStream.available()>0){
            outputStream.write(buffer, 0, len);
        }
        outputStream.close();
        inputStream.close();
        return outputStream.toByteArray();
    } 
	
	 /** 
     * @方法功能 InputStream 转为 byte 
     * @param InputStream 
     * @return 字节数组 
     * @throws Exception 
     */  
    public static byte[] inputStream2Byte(InputStream inStream)  
            throws Exception {  
        int count = 0;  
        while (count == 0) {  
            count = inStream.available();  
        }  
        byte[] b = new byte[count];  
        inStream.read(b);  
        return b;  
    }

}
