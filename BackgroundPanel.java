import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g){
        int x=0;
        int y=0;
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("src/2.jpeg");
        //img.paintIcon(this,g,0,0);
        g.drawImage(img.getImage(), x, y, getSize().width,
                getSize().height, this);// 图片会自动缩放
//    g.drawImage(icon.getImage(), x, y,this);//图片不会自动缩放
        repaint();

    }




}