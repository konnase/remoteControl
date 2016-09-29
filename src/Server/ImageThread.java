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

/*创建一个子线程，用来将本地图片发送到客户端去*/
public class ImageThread extends Thread{
    private DataOutputStream dataOut;
    public ImageThread (DataOutputStream dataOut){
        this.dataOut = dataOut;
    }
    @Override
    public void run() {
        try {
            //Robot robot = new Robot();
            //截取整个屏幕
            //使用Toolkit工具包获取当前操作系统的一些信息
            Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
            //Rectangle rect = new Rectangle(dm);
            BufferedImage img ;
            byte[] imageByte;
            while(true){
                img = (new Robot())  
                        .createScreenCapture(new Rectangle(0, 0, (int) dm 
                                .getWidth(), (int) dm.getHeight()));
                //存储到本地
                File file = new File("D:/screen.jpg");  
                ImageIO.write(img, "jpg", file);  
                
                //压缩处理以及转换成byte数组
                imageByte = getByteImage(img);
                //将图片的长度告诉客户端  客户端必须要用byte数组接受
                //dataOut.writeInt(imageByte.length);
                System.out.println(imageByte.length);
                
                //将图片传输到客户端
                dataOut.write(imageByte);
                
                System.out.println("图片已传送");
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