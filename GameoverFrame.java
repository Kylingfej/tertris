import javax.swing.*;
import java.awt.*;

public class GameoverFrame extends JFrame {

    private static music music;
    ScorePanel display = new ScorePanel();


    public GameoverFrame(JPanel panel,int score,JPanel panel1){

        JButton re= new JButton("Return");
        JButton begin= new JButton("Restart");

        re.addActionListener(e->{

            this.setVisible(false);
            // game_main.setVisible(false);

            //    button4.setVisible(false);
            panel.setVisible(true);
        });

        //Tertris tertris = new Tertris(panel,music);
        //panel.add(tertris);
        //panel.add(tertris);
        Tertris tertris = new Tertris(panel,music);
        begin.addActionListener(e -> {
                    this.setVisible(false);
                    panel.add(tertris);
                    panel.setVisible(false);
                    //panel.setVisible(true);
                    tertris.setVisible(true);
                    //tertris.game_begin();

                }

        );






            //tertris.setVisible(true);




            //panel.setVisible(true);



        ImageIcon img = new ImageIcon("src/giphy2.gif");

        /*JLabel bac = new JLabel(img);
        bac.setBounds(0,0,400,600);
        this.add(bac);*/

        JPanel back = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                int x=0;
                int y=0;
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/1.gif");
                //img.paintIcon(this,g,0,0);
                g.drawImage(img.getImage(), x, y, getSize().width,
                        getSize().height, this);// 图片会自动缩放
//    g.drawImage(icon.getImage(), x, y,this);//图片不会自动缩放
                repaint();

            }
        };
        back.setSize(400,600);
        back.setLayout(null);
        this.add(back);


        JButton gameover = new JButton();
        gameover.setBorderPainted(false);
        setIcon("src/giphy1.gif",gameover);
        gameover.setBounds(0,0,400,150);

        this.setLayout(null);
        display.setBounds(50,160,300,300);
        back.add(display);
        back.add(gameover);


        re.setBounds(60,470,80,80);
        begin.setBounds(260,470,80,80);

         back.add(re);
         back.add(begin);

         this.setSize(400,600);
//        this.setTitle("俄罗斯方块");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        display.addScore(score);

    }

//    public static void main(String[] args) {
//        GameoverFrame gameoverFrame= new GameoverFrame();
//
//    }

    public static void setIcon(String file,JButton com)
    {
        ImageIcon ico=new ImageIcon(file);
        Image temp=ico.getImage().getScaledInstance(450,350,ico.getImage().SCALE_DEFAULT);
        ico=new ImageIcon(temp);
        com.setIcon(ico);
    }



}
