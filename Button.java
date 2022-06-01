import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class Button extends JButton {


    public Button(){
        super();
        this.setBorderPainted(false);
        this.setOpaque(false);
        this.setSize(130,70);
        //this.setIcon("D:/src 3/src/button.png",this);
        this.setMargin(new Insets(0,0,0,0));


        //this.setContentAreaFilled(false);
        //ImageIcon image = new ImageIcon("src/1.jpg");
        // 实例化按钮对象，并且设置按钮上显示图片
        //this.setIcon(image);
        //this.setMargin(new Insets(0,0,0,0));
        //或者
//设置凸起来的按钮，很多其他的swing也可用此方法


    }
    // 实例化一个图标对象
    public void setIcon(String file,JButton com)
    {
        ImageIcon ico=new ImageIcon(file);
        Image temp=ico.getImage().getScaledInstance(com.getWidth()+10,com.getHeight()+10,ico.getImage().SCALE_DEFAULT);
        ico=new ImageIcon(temp);
        com.setIcon(ico);
    }

    public static void setIcon(String file, JMenu com){
        ImageIcon ico=new ImageIcon(file);
        Image temp=ico.getImage().getScaledInstance(com.getWidth()+10,com.getHeight()+10,ico.getImage().SCALE_DEFAULT);
        ico=new ImageIcon(temp);
        com.setIcon(ico);
    }
    /*public void ShowDIalog(){
        setLayout(new FlowLayout());
        add(this);
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                JOptionPane.showMessageDialog(null,"What a fucking day!");
            }


        });
        setVisible(true);
        setSize(100,100);
    }*/









    public static void main(String[] args) {
// 产生一个带‘Jackpot’标签的按钮。
        Button button = new Button();
        button.setSize(20,20);
        ButtonGroup m = new ButtonGroup();

        JMenuBar x = new JMenuBar(){
            protected void paintComponent(Graphics g){
                int x=0;
                int y=0;
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/button.png");
                //img.paintIcon(this,g,0,0);
                g.drawImage(img.getImage(), x, y, getSize().width,
                        getSize().height, this);// 图片会自动缩放
//    g.drawImage(icon.getImage(), x, y,this);//图片不会自动缩放
                repaint();
        };};


        x.setSize(200,70);
        x.setBorderPainted(true);


        JMenu l1 = new JMenu("Game start");


        l1.setSize(200,70);
        l1.setBorderPainted(false);
        l1.setContentAreaFilled(false);

        x.add(l1, BorderLayout.CENTER);
        JMenuItem level1 = new JMenuItem("level 1");
        JMenuItem level2 = new JMenuItem("level 2");
        JMenuItem level3 = new JMenuItem("level 3");
        l1.add(level1);
        l1.add(level2);
        l1.add(level3);

        level1.addActionListener((e)->Tertris.setTime(1000));
        level2.addActionListener((e)->Tertris.setTime(500));
        level3.addActionListener((e)->Tertris.setTime(200));


        //button.ShowDIalog();

        //button.setBorder(BorderFactory.createRaisedBevelBorder());
        //设置凹起来的按钮，很多其他的swing也可用此方法
        //button.setBorder(BorderFactory.createLoweredBevelBorder());
        //设置按钮的前景色和背景色

        //button.setBackground(Color.green);

// 产生一个框架以显示这个按钮。
        JFrame frame = new JFrame();

        frame.getContentPane().add(button,BorderLayout.EAST);
        frame.add(x);

        frame.getContentPane().setLayout(new FlowLayout());
        frame.setSize(150, 150);
        frame.setVisible(true);
    }
}



