package Server;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class mainPage extends Socket {
	public static void main(String[] args) throws Exception {
        //实例化服务端监听 在 1234端口
        ServerSocket sever = new ServerSocket(1234);
        System.out.println("服务器已经启动...");
        Socket socket = sever.accept(); //等待接受请求
        System.out.println("有客户已经连接");
        DataOutputStream dataOut = 
        new DataOutputStream(socket.getOutputStream());
        
//        dataOut.write("woshishei".getBytes());
//        dataOut.close();
//        sever.close();
        
        ImageThread th1 = new ImageThread(dataOut);
        Thread t1 = new Thread(th1);
        t1.start();
        
    }
}
