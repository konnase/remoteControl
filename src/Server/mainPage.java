package Server;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class mainPage extends Socket {
	public static void main(String[] args) throws Exception {
        //ʵ��������˼��� �� 1234�˿�
        ServerSocket sever = new ServerSocket(1234);
        System.out.println("�������Ѿ�����...");
        Socket socket = sever.accept(); //�ȴ���������
        System.out.println("�пͻ��Ѿ�����");
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
