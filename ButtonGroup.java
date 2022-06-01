import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonGroup extends JMenuBar {

    public ButtonGroup(){
        super();
        //JMenuBar x = new JMenuBar();
        this.setSize(130,70);
        this.setBorderPainted(false);
        this.setOpaque(false);



        //Font t = new Font("Bangla MN", Font.BOLD,25);
        JMenu l1 = new JMenu("                                       ");
        l1.setOpaque(false);
        l1.setBorderPainted(false);

        //1.setFont(t);
//        ImageIcon imageIcon = new ImageIcon("src/level.png");
//        l1.setIcon(imageIcon);
        //setIcon("src/level.png",l1);
        l1.setOpaque(false);

        l1.setBounds(0,0,130,70);
        //l1.setBorderPainted(false);
        //l1.setContentAreaFilled(false);

        this.add(l1);


        //JMenu l2= new JMenu("l2");
        JMenuItem level1 = new JMenuItem("level 1");
        JMenuItem level2 = new JMenuItem("level 2");
        JMenuItem level3 = new JMenuItem("level 3");
        l1.add(level1);
        l1.add(level2);
        l1.add(level3);
        level1.addActionListener((e)->Tertris.setTime(1000));
        level2.addActionListener((e)->Tertris.setTime(500));
        level3.addActionListener((e)->Tertris.setTime(200));



        //this.add(x,BorderLayout.CENTER);

        level1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You have clicked the paste！");
            }
        });

    }
    public void setIcon(String file,JMenu com) {
        ImageIcon ico=new ImageIcon(file);
        Image temp=ico.getImage().getScaledInstance(com.getWidth()+10,com.getHeight()+10,ico.getImage().SCALE_DEFAULT);
        ico=new ImageIcon(temp);
        com.setIcon(ico);
    }


}
