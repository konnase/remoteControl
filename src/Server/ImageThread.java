package Server;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

import remoteControl.ClientWindow1;

//import com.sun.image.codec.jpeg.ImageFormatException;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

/*����һ�����̣߳�����������ͼƬ���͵��ͻ���ȥ*/
public class ImageThread extends Thread{
    private DataOutputStream dataOut;
    public ImageThread (DataOutputStream dataOut){
        this.dataOut = dataOut;
    }
    @Override
    public void run() {
        try {
            //Robot robot = new Robot();
            //��ȡ������Ļ
            //ʹ��Toolkit���߰���ȡ��ǰ����ϵͳ��һЩ��Ϣ
            Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
            //Rectangle rect = new Rectangle(dm);
            BufferedImage img ;
            byte[] imageByte;
            while(true){
                img = (new Robot())  
                        .createScreenCapture(new Rectangle(0, 0, (int) dm 
                                .getWidth(), (int) dm.getHeight()));
                //�洢������
                File file = new File("D:/screen.jpg");  
                ImageIO.write(img, "jpg", file);  
                
                //ѹ�������Լ�ת����byte����
                imageByte = getByteImage(img);
                //��ͼƬ�ĳ��ȸ��߿ͻ���  �ͻ��˱���Ҫ��byte�������
                //dataOut.writeInt(imageByte.length);
                System.out.println(imageByte.length);
                
                //��ͼƬ���䵽�ͻ���
                dataOut.write(imageByte);
                
                System.out.println("ͼƬ�Ѵ���");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private byte[] getByteImage(BufferedImage image) throws IOException{
    	
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        boolean flag = ImageIO.write(image, "jpg", out);  
        byte[] b = out.toByteArray();  
    	return b;
//        ByteArrayOutputStream bout = new ByteArrayOutputStream();
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bout);
//        encoder.encode(image);
//        return bout.toByteArray();
    }
}