package remoteControl;

import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

//定义一个窗体
public class  ClientWindow1 extends JFrame{
  private JLabel backGround;
  private ObjectOutputStream objectOut;
  public void repaintImage(byte[] image){
      backGround.setIcon( new ImageIcon(image));
      this.repaint();
  }
  public ClientWindow1(ObjectOutputStream objectOut){
      this.objectOut = objectOut;
      this.setTitle("远程协助工具");
      this.setSize(1024, 768);
      backGround = new JLabel();
      JPanel jp = new JPanel();
      jp.add(backGround);
      JScrollPane scrollPanel = new JScrollPane(jp);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.add(scrollPanel);
      this.setVisible(true);
  }
}