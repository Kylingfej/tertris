import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private static ArrayList<JPanel> panels = new ArrayList<>();
    //    static Color pen = new Color(255,0,0);
    private static Color panelPen = Color.GRAY;
    private static Color shapePen = Color.BLUE;
    private static final Tuple blockSize = new Tuple(40, 40);
    private static final Tuple frameSize = new Tuple(800, 800);
    private static music music=new music();
//    static Toolkit kt = Toolkit.getDefaultToolkit();
//    Dimension screenSize = kt.getScreenSize();


    public void Constructor(){
        music.load();

        this.initiateFrame("test title", frameSize);



        //Image icon = new ImageIcon("1.jpg").getImage();
        //JPanel panel = initiatePanel(new Tuple(600, 600), panelPen);
        BackgroundPanel panel = new BackgroundPanel();
        ScorePanel sp= new ScorePanel();
        sp.setBounds(150,390,300,200);
        panel.setSize(600,600);


        panel.setLayout(null);
        panel.add(sp);
        //panel.setOpaque(false);



        JPanel panel2 = initiatePanel(new Tuple(600, 600), panelPen);
        panel2.add(new JLabel("This is panel2"));
        GamePanel gamePanel = new GamePanel();
        this.addPanel(panel);

        JLabel label = new JLabel("Start The Game",JLabel.CENTER);
        //JLabel label2 = new JLabel("  Game Setting",JLabel.CENTER);
        JLabel label3 = new JLabel("  Explanation",JLabel.CENTER);
        Font font1 = new Font("Chalkduster", Font.PLAIN,20);
        JButton musicbutton = new JButton("music");
        musicbutton.setBounds(0,0,50,50);




        panel.add(musicbutton);

        label.setFont(font1);
        //label2.setFont(font1);
        label3.setFont(font1);


        Button button1 = new Button();


        button1.add(label,JLabel.CENTER);


        //button1.setFocusPainted(false);
        button1.setVerticalTextPosition(SwingConstants.BOTTOM);
        button1.setHorizontalTextPosition(SwingConstants.CENTER);
        button1.setBounds(40,310,200,70);


        ButtonGroup button2 = new ButtonGroup();/*{
            @Override
            protected void paintComponent(Graphics g){
                int x=0;
                int y=0;
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/button2.png");
                //img.paintIcon(this,g,0,0);
                g.drawImage(img.getImage(), x, y, getSize().width,
                        getSize().height, this);// 图片会自动缩放
//    g.drawImage(icon.getImage(), x, y,this);//图片不会自动缩放
                repaint();

            }
        };*/
        //button2.setOpaque(false);
        //button2.add(label2);
        button2.setBounds(360,310,200,70);
        //button2.setOpaque(false);



        Button button3 = new Button();
        button3.add(label3);
        button3.setBounds(200,380,225,80);
//
        panel.add(button1);
        panel.add(button2);
        //panel.add(button3);
//        Tertris.setIsrunning(false);

        Tertris tertris = new Tertris(panel, music);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                 tertris.init();

                //setPanel((JPanel) button1.getParent(), tertris);
                //tertris.game_begin(tertris);
                //Tertris.setIsrunning(true);
                new Thread() {

                    public void run() {
                        // TODO Auto-generated method stub

                        super.run();
                        tertris.isrunning=true;
                        tertris.game_pause=false;
                        //tertris.resetData();
                        //tertris.time=1000;
                        tertris.game_begin(panel,sp);
                        //System.out.print(tertris.time);
                        tertris.startcount+=1;
                    }
                }.start();

            }
        });



        musicbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // tertris.init();
                tertris.countmusic +=1;
                if (tertris.countmusic%2==0){
                    music.stop();
                }else{
                    music.play();
                }

            }
        });
        button1.addActionListener(event -> this.setPanel((JPanel) button1.getParent(), tertris));
        //tertris.game_begin(panel);
        //button2.addActionListener(event -> mainFrame.setPanel((JPanel) button2.getParent(), panel));
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "GAME OVER","instruction",JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {

        MainFrame mainFrame = new MainFrame();
        mainFrame.Constructor();




        //mainFrame.add(panel,BorderLayout.CENTER);
        //mainFrame.add(panel2,BorderLayout.CENTER);
        //流式布局
        //panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        //panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));



    }

    /**
     * 关于如何合作
     * This method performs...
     *
     * @param title set title of the frame...
     * @param size  set size of the frame...
     * @author yourName
     */

    private void initiateFrame(String title, Tuple size) {
        new JFrame(title);
        this.setTitle("俄罗斯方块");
        //BackgroundPanel backgroundPanel= new BackgroundPanel();
        //this.add(backgroundPanel);
        //窗口标题
        this.setSize(600,850);


        setSize(size.x, size.y);
        //绑定关闭
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //可见
        setVisible(true);
        //add your code here:
        //TODO: add something...
    }

    /**
     * @param color set pen with color
     */
    private static void setPanelPen(Color color) {
        panelPen = color;
    }

    private static void setShapePen(Color color) {
        shapePen = color;
    }

    static Color getPanelPen() {
        return panelPen;
    }

    static Color getShapePen() {
        return shapePen;
    }

    private static JPanel initiatePanel() {
        //TODO: more about initiate
        return new JPanel();
    }

    private void setPanel(JPanel panel1, JPanel panel2) {
        panel1.setVisible(false);
        add(panel2);
        panel2.setVisible(true);
        revalidate();
        repaint();
    }


    private static JPanel initiatePanel(Tuple size, Color color) {
        JPanel panel = new JPanel();
        panel.setSize(size.x, size.y);
        panel.setBackground(color);
        //TODO: panel.setMore...
        return panel;
    }

    private void addPanel(JPanel panel) {
        panels.add(panel);
        add(panel);
        setBounds(panel.getBounds());
    }


    public static Tuple getBlockSize() {
        return blockSize;
    }

    public static Tuple getFrameSize() {
        return frameSize;
    }

    public void paintComponent(Graphics g) {
        int x = 0, y = 0;
        ImageIcon icon = new ImageIcon("1.jpg");// 003.jpg是测试图片在项目的根目录下
        g.drawImage(icon.getImage(), x, y, getSize().width,
                getSize().height, this);// 图片会自动缩放
//    g.drawImage(icon.getImage(), x, y,this);//图片不会自动缩放
        repaint();
    }

    public void setBak(){
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon("1.jpg"); //添加图片
        JLabel background = new  JLabel(img);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }

}

