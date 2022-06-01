

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

import java.net.URL;
public class Tertris extends JPanel implements KeyListener{
    //游戏的行数26,列数12
    public static final int game_x = 26;
    public static final int game_y = 12;
    JButton start;
    //文本域数组
    JTextArea[][] text;
    //二维数组
    int[][] data;
    //显示游戏状态的标签
    JLabel label1;
    //显示游戏分数的标签
    JLabel label;
    //用于判断游戏是否结束
    static public boolean isrunning;
    //用于存储所有的方块的数组
    int[] allRect;
    //用于存储当前方块的变量
    static int rect;
    //线程的休眠时间
    static int time = 1000;
    //表示方块坐标
    int x, y;
    //该变量用于计算得分
    int score=0;
    //定义一个标志变量,用于判断游戏是否暂停
    boolean game_pause = false;
    //定义一个变量用于记录按下暂停键的次数
    int pause_times = 0;
    JButton button4 = new JButton();
    public static int startcount=1;
    public static int countmusic = 0;
     int stroecount = 1;
     int stroex = 0;
     int stroey = 0;
    int[] Falldown;
    Color[][] recordColor;
    Color first = new Color(255,183,209);
    Color second = new Color(131,238,255);
    Color third = new Color(108,255,138);
    Color forth = new Color(255,243,149);
    Color fifth = new Color(241,189,255);
    Color sixth = new Color(255,148,137);
    Color[] list={first,second,third,forth,fifth,sixth};
    Color present;
    static public void setTime(int t){
        time = t;
    }

    static public void setIsrunning(boolean type){
        isrunning = type;
    }

    //初始化游戏界面
    public void initGamePanel(JPanel game_main) {

        game_main.setLayout(new GridLayout(game_x,game_y,1,1));

        //初始化面板

        for (int i = 0 ; i < text.length ; i++) {
            for (int j = 0 ; j < text[i].length ;j++) {
                //设置文本域的行列数
                text[i][j] = new JTextArea(game_x,game_y);
                //设置文本域的背景颜色
                text[i][j].setBackground(Color.WHITE);
                //添加键盘监听事件
                text[i][j].addKeyListener(this);
                //初始化游戏边界
                if (j == 0 || j == text[i].length-1 || i == text.length-1) {
                    text[i][j].setBackground(Color.BLACK);
                    data[i][j] = 1;
                }
                //设置文本区域不可编辑
                text[i][j].setEditable(false);
                //文本区域添加到主面板上
                game_main.add(text[i][j]);
            }
        }
        //添加到窗口中
        this.setLayout(new BorderLayout());
        this.add(game_main,BorderLayout.CENTER);
    }

    //初始化游戏的说明面板
    public void initExplainPanel(JPanel panel,music music) {
//        //创建游戏的左说明面板
//        JPanel explain_left = new JPanel();
//        //创建游戏的右说明面板
//        JPanel explain_right = new JPanel();
//        // JPanel explain_top = new JPanel();
//        explain_left.setLayout(new GridLayout(4,1));
//        explain_right.setLayout(new GridLayout(2,1));
//        // explain_top.setLayout(new GridLayout(1,1));
//
//        //初始化左说明面板
//
//        //在左说明面板,添加说明文字
//        explain_left.add(new JLabel("按空格键,方块变形"));
//        explain_left.add(new JLabel("按左箭头,方块左移"));
//        explain_left.add(new JLabel("按右箭头,方块右移"));
//        explain_left.add(new JLabel("按下箭头,方块下落"));
//        //设置标签的内容为红色字体
//        label1.setForeground(Color.RED);
//        //把游戏状态标签,游戏分数标签,添加到右说明面板
//        explain_right.add(label);
//        explain_right.add(label1);
//        //将左说明面板添加到窗口的左侧
//        this.add(explain_left,BorderLayout.WEST);
//        //将右说明面板添加到窗口的右侧
//        this.add(explain_right,BorderLayout.EAST);
//        initPausePanel(panel,music);
        JPanel explain_right = new JPanel(){
            protected void paintComponent(Graphics g){
                int x=0;
                int y=0;
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/x.jpg");
                //img.paintIcon(this,g,0,0);
                g.drawImage(img.getImage(), x, y, getSize().width,
                        getSize().height, this);// 图片会自动缩放
//    g.drawImage(icon.getImage(), x, y,this);//图片不会自动缩放


            }
        };
        explain_right.setLayout(new GridLayout(2,5));

        Font type = new Font("楷体",Font.BOLD,20);
        label1.setForeground(Color.WHITE);
        label.setFont(type);
        label.setForeground(Color.white);
        label1.setFont(type);
        //把游戏状态标签,游戏分数标签,添加到右说明面板
        explain_right.add(label);
        explain_right.add(label1);
        //将左说明面板添加到窗口的左侧
        //this.add(explain_left,BorderLayout.WEST);
        //将右说明面板添加到窗口的右侧
        this.add(explain_right,BorderLayout.EAST);
        initPausePanel(panel,music);

    }

    //设置暂停界面
    public void initPausePanel(JPanel panel,music music){
//        JPanel explain_top = new JPanel();

        JPanel explain_top = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                int x=0;
                int y=0;
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/1.jpg");
                //img.paintIcon(this,g,0,0);
                g.drawImage(img.getImage(), x, y, getSize().width,
                        getSize().height, this);// 图片会自动缩放
//    g.drawImage(icon.getImage(), x, y,this);//图片不会自动缩放

            }
        };
        JPanel panel1 =new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                int x=0;
                int y=0;
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("src/dark.jpg");
                //img.paintIcon(this,g,0,0);
                g.drawImage(icon.getImage(), x, y, getSize().width,
                        getSize().height, this);// 图片会自动缩放
//    g.drawImage(icon.getImage(), x, y,this);//图片不会自动缩放

            }
        };

        //设置背景
        JLabel label = new JLabel(); // 创建一个标签组件对象


//        label.setIcon(icon); // 设置标签组件要显示的图标
//        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // 设置组件的显示位置及大小

//        JButton musicb = new JButton("music");
        JLabel condition = new JLabel("                             Music");
        JButton musicb = new JButton();

        musicb.add(condition);
        condition.setForeground(Color.BLUE);

        musicb.setOpaque(false);
        musicb.setBorderPainted(false);
        JLabel condition2 = new JLabel("                    Pause");
        condition2.setForeground(Color.BLUE);
        button4.add(condition2);
        button4.setOpaque(false);
        button4.setBorderPainted(false);


        musicb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // tertris.init();
                countmusic +=1;
                if (countmusic%2==0){
                    music.stop();
                }else{
                    music.play();
                }

            }
        });
        explain_top.add(musicb);

        explain_top.setLayout(new GridLayout(1,1));
        //设置上面的面板
        // JButton button4 = new JButton("暂停游戏");
        explain_top.add(button4);
        //explain_top.add(start);

        this.add(explain_top,BorderLayout.SOUTH);
        JButton button5 = new JButton("Resume");
        JButton button6 = new JButton("Store");
        JButton button7 = new JButton("How to play");
        JButton button8 = new JButton("Return");


        JFrame Pauseframe = new JFrame("Pause");
        //设置在屏幕的位置
        Pauseframe.setLocation(100,50);
        //	窗体大小
        Pauseframe.setSize(300,300);
        //显示窗体
        Pauseframe.getLayeredPane().add(label,new Integer(Integer.MAX_VALUE));
        JPanel j=(JPanel)Pauseframe.getContentPane();
        j.setOpaque(false);

        panel.setLayout(null);


        //改变字体
        Font f=new Font("Arial",Font.BOLD,20);

        button5.setBounds(150,30,100,30);
        button6.setBounds(150,110,100,30);
        button7.setBounds(150,190,100,30);
        button8.setBounds(150,270,100,30);
        j.add(button5);
        j.add(button6);

        j.add(button7);

        j.add(button8);

        /*button5.setPreferredSize(new Dimension(200, 50));
        button6.setPreferredSize(new Dimension(200, 50));
        button7.setPreferredSize(new Dimension(200, 50));
        button8.setPreferredSize(new Dimension(200, 50));
        button5.setFont(f);
        button6.setFont(f);
        button7.setFont(f);
        button8.setFont(f);

        //箱式布局
        Box vBox = Box.createVerticalBox();
        vBox.add(button5);
        vBox.add(Box.createVerticalStrut(30));//两个方向都可以拉伸的间隔
        vBox.add(button6);
        vBox.add(Box.createVerticalStrut(30));//两个方向都可以拉伸的间隔
        vBox.add(button7);
        vBox.add(Box.createVerticalStrut(30));//两个方向都可以拉伸的间隔
        vBox.add(button8);
        vBox.add(Box.createVerticalStrut(30));//两个方向都可以拉伸的间隔
        panel1.add(vBox);*/

        panel1.setOpaque(false);
        Pauseframe.add(panel1);
        // Pauseframe.setSize(icon.getIconWidth(),icon.getIconHeight());
        Pauseframe.setSize(400,400);
        // Pauseframe.pack();
        button4.addActionListener(new ActionListener(){
            //单击按钮执行的方法
            public void actionPerformed(ActionEvent e) {
                game_pause = true;
                //创建新的窗口
                Pauseframe.setVisible(true);
                label1.setText("游戏状态: 游戏暂停!");
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pauseframe.setVisible(false);
                game_pause = false;
                label1.setText("游戏状态: 游戏进行!");

            }});
        button6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                game_pause=true;     
                            
                SaveGmaeData("D:/src 3/src/data.dat","data");
                SaveGmaeData("D:/src 3/src/rect.dat","rect");
                SaveGmaeData("D:/src 3/src/score.dat","score");
                SaveGmaeData("D:/src 3/src/x.dat","x");
                SaveGmaeData("D:/src 3/src/y.dat","y");
                SaveGmaeData("D:/src 3/src/recordColor.dat","recordColor");
                
                    
//                game_pause=false;
                isrunning = false;
                game_clear(panel);
                
                Pauseframe.setVisible(false);
                panel.setVisible(true);

    
        }});

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String explain = "> 向右移动" +
                        "\n\n<   向左移动"+"\n\n ^ 向上移动"+"\n\n v 向下移动"+"\n\n space   变换形状";
                JOptionPane.showMessageDialog(null, explain,"游戏说明",JOptionPane.INFORMATION_MESSAGE);

            }});

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                isrunning = false;
                game_pause=true;
                game_clear(panel);

                    Pauseframe.setVisible(false);
                    // game_main.setVisible(false);


                    //    button4.setVisible(false);
                    panel.setVisible(true);

                // JOptionPane.showConfirmDialog(null, "game over","游戏结束",JOptionPane.INFORMATION_MESSAGE);
                int n=JOptionPane.showConfirmDialog(null,"Your score is: "+score,"GAMEOVER",JOptionPane.DEFAULT_OPTION);
            }});

    }

    //新加的
    public void SaveGmaeData(String string,String name){
        try {
            File file = new File(string);
            FileOutputStream fileStream = new FileOutputStream(file,false);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            if(name=="data"){
                objectStream.writeObject(data);
            }if(name == "rect"){
                objectStream.writeObject(rect);
            }if(name == "score"){
                objectStream.writeObject(score);
            }if(name == "x"){
                objectStream.writeObject(stroex);
            }if(name == "y"){
                objectStream.writeObject(stroey);
            }if (name=="recordColor"){
                objectStream.writeObject(recordColor);
            }
            
            
            objectStream.close();
            fileStream.close();
            // label1.setText("1");
            // System.out.print(2);
            

        }
        catch (Exception e) {
//            System.out.print(1);
        }
        
    }

    public Tertris(JPanel panel,music music) {
        start=new JButton("start");
       /* start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isrunning=true;
                Tertris tertris = new Tertris();
                //game_begin(panel);
            }
        });*/
        this.add(start,BorderLayout.NORTH);
        text = new JTextArea[game_x][game_y];
        data = new int[game_x][game_y];
        //初始化表示游戏状态的标签
        label1 = new JLabel(" 游戏状态: 游戏进行!");
        //初始化表示游戏分数的标签
        label = new JLabel(" 游戏得分为:"+score);
        JPanel game_main = new JPanel();
        initGamePanel(game_main);
        initExplainPanel(panel,music);


        //初始化开始游戏的标志
        isrunning = true;
        //初始化存放方块的数组
        Falldown= new int[]{0x00cc,0x8888,0x0c44,0x0c88,0x04c8,0x08c8,0x08c4};//7个图形
        allRect = new int[]{0x00cc,//正方形
                0x8888, 0x000f,//直线
                0x0c44, 0x002e, 0x088c, 0x00e8,//L
                0x0c88, 0x00e2, 0x044c, 0x008e,//倒L
                0x08c4, 0x006c, //S
                0x04c8, 0x00c6, //倒S
                0x08c8, 0x004e, 0x04c4, 0x00e4//T形
        };
        recordColor= new Color[game_x][game_y];

    }

//    public void game_begin() {
//        while (true){
//            //判断游戏是否结束
//            if (!isrunning) {
//                // System.out.print(1);
//                break;
//            }
//
//            //进行游戏
//            game_run();
//        }
//        //在标签位置显示"游戏结束"
//        label1.setText("游戏状态: 游戏结束!");
//        this.setVisible(false);
//        //GameoverFrame gameoverFrame = new GameoverFrame(this);
//        //gameoverFrame.setVisible(true);
//        game_clear(this);
//
//
//    }

    //开始游戏的方法
    public void game_begin(JPanel panel,ScorePanel scorePanel) {
//        while(isStart == false);
        isrunning=true;
        //game_clear(panel);
        int flag = 0;
            while (true) {
                //判断游戏是否结束
                
                if (!isrunning) {
//                     Thread.currentThread().interrupt();
//                    System.out.println(" 1");
                    break;
                }

                int value = game_run();

                //进行游戏
                if(value == 0 && !isrunning)
                {
//                    System.out.println(" 2");
                    flag = 1;
                    break;
                }
            }

            //在标签位置显示"游戏结束"
            label1.setText("游戏状态: 游戏结束!");

            this.setVisible(false);
            if(flag == 0) {
                GameoverFrame gameoverFrame = new GameoverFrame(panel,score,this);
                gameoverFrame.setVisible(true);

            }
        //scorePanel.setList(score);
        //scorePanel.display();
        scorePanel.changeFirst(score);
            game_clear(this);
            //game_begin(panel);

    }

    //清除所有方块
    public void game_clear(JPanel panel){
        for (int i = game_x - 2;i >= 1;i--) {
            for (int j = 1;j <= (game_y-2);j++) {
                data[i][j] = 0;
                text[i][j].setBackground(Color.WHITE);
                recordColor[i][j]=Color.WHITE;
            }
        }
        score = 0;

        label1.setText("游戏状态: 游戏进行!");
        label.setText("游戏得分为: " + score);
        Tertris.setTime(1000);
    }


    //随机生成下落方块形状的方法
    public void ranRect() {
        Random random = new Random();

        rect = Falldown[random.nextInt(7)];
    }

    //游戏运行的方法
    
    public int game_run() {
        ranRect();
        present=list[(int)(Math.random()*6)];
        if (stroecount!=0){
            ranRect();}else{
            stroecount=1;
            x=stroex;
            y=stroey;

        }

        //方块下落位置
        x = 0;
        y = 5;
        int flag = 0;
        for (int i = 0; i < game_x; i++) {
            try {
                
                Thread.sleep(time);
                {
                    // if(!isrunning){
                    //     break;
                    // }

                    if(!isrunning){
                        Thread.currentThread().interrupt();
                        break;
                    }
                    if (!game_pause) {
                        //新加的
                        stroex=x;
                        stroey=y;

                        //判断方块是否可以下落
                        if (!canFall(x, y)) {
                            //将data置为1,表示有方块占用
                            changData(x, y);
                            //新加的
                            stroex=x;
                            stroey=y;
                            //循环遍历4层,看是否有行可以消除
                            for (int j = x; j < x + 4; j++) {
                                int sum = 0;

                                for (int k = 1; k <= (game_y - 2); k++) {
                                    if (data[j][k] == 1) {
                                        sum++;
                                    }
                                }

                                //判断是否有一行可以被消除
                                if (sum == (game_y - 2)) {
                                    //消除j这一行
                                    removeRow(j);
                                }
                            }
                            //判断游戏是否失败
                            for (int j = 1; j <= (game_y - 2); j++) {
                                if (data[3][j] == 1) {
                                    isrunning = false;
                                    return 1;
                                }
                            }
                            break;

                        } else {
                            //层数+1
                            x++;
                            //新加的
                            stroex=x;
                            stroey=y;
                            //方块下落一行
                            fall(x, y);
                            //新加的
                            stroex=x;
                            stroey=y;
                            
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    //判断方块是否可以继续下落的方法
    public boolean canFall(int m,int n) {
        //定义一个变量
        int temp = 0x8000;
        //遍历4 * 4方格
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                if ((temp & rect) != 0) {
                    //判断该位置的下一行是否有方块
                    if (data[m+1][n] == 1) {
                        return false;
                    }
                }
                n++;
                temp >>= 1;
            }
            m++;
            n = n - 4;
        }
        //可以下落
        return true;
    }

    //改变不可下降的方块对应的区域的值的方法
    public void changData(int m,int n) {
        //定义一个变量
        int temp = 0x8000;
        //遍历整个4 * 4的方块
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                if ((temp & rect) != 0) {
                    data[m][n] = 1;
                }
                n++;
                temp >>= 1;
            }
            m++;
            n = n - 4;
        }
    }

    //移除某一行的所有方块,令以上方块掉落的方法
    public void removeRow(int row) {

        int temp = 100;
        for (int i = row;i >= 1;i--) {
            for (int j = 1;j <= (game_y-2);j++) {
                //进行覆盖
                data[i][j] = data[i-1][j];
            }
        }
        //刷新游戏区域
        //if(!game_pause)
          reflesh(row);

        //方块加速
        if (time > temp) {
            time -= temp;
        }

        score += temp;

        //显示变化后的分数
        label.setText("游戏得分为: " + score);
    }

    //刷新移除某一行后的游戏界面的方法
    public void reflesh(int row) {
        //遍历row行以上的游戏区域
        for (int i = row;i >= 1;i--) {
            for (int j = 1;j <= (game_y-2);j++) {
                if (data[i][j] == 1) {
                    text[i][j].setBackground(recordColor[i][j]);
                }else {
                    text[i][j].setBackground(Color.WHITE);
                    recordColor[i][j]=Color.WHITE;
                }
            }
        }
    }

    //方块向下掉落一层的方法
    public void fall(int m,int n) {
        if (m > 0) {
            //清除上一层方块
            clear(m-1,n);
        }
        //重新绘制方块
        draw(m,n);
    }

    //清除方块掉落后,上一层有颜色的地方的方法
    public void clear(int m,int n) {
        //定义变量
        int temp = 0x8000;
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                if ((temp & rect) != 0) {
                    text[m][n].setBackground(Color.WHITE);
                    recordColor[i][j]=Color.WHITE;
                }
                n++;
                temp >>= 1;
            }
            m++;
            n = n - 4;
        }
    }

    //重新绘制掉落后方块的方法
    public void draw(int m,int n) {
        //定义变量
        int temp = 0x8000;
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                if ((temp & rect) != 0) {
                    text[m][n].setBackground(present);
                    recordColor[m][n]=present;
                }
                n++;
                temp >>= 1;
            }
            m++;
            n = n - 4;
        }
    }


    
    
    @Override

    public void keyTyped(KeyEvent e) {
        //控制游戏暂停
        if (e.getKeyChar() == 'p') {
            //判断游戏是否结束
            if (!isrunning) {
                return;

            }

            pause_times++;

            //判断按下一次,暂停游戏
            if (game_pause = true) {

                label1.setText("游戏状态: 游戏暂停!");

            }
            //判断按下两次,继续游戏
            if (game_pause = false) {


                label1.setText("游戏状态: 游戏进行!");
            }
        }

        //控制方块进行变形
        if (e.getKeyChar() == KeyEvent.VK_SPACE) {
            //判断游戏是否结束
            if (!isrunning) {
                return;
            }

            //判断游戏是否暂停
            if (game_pause) {
                return;
            }

            //定义变量,存储目前方块的索引
            int old;
            for (old = 0;old < allRect.length;old++) {
                //判断是否是当前方块
                if (rect == allRect[old]) {
                    break;
                }
            }

            //定义变量,存储变形后方块
            int next;

            //清除当前方块
            clear(x,y);

            if(old==0)
                return;

            if (old == 1 || old == 2) {
                next = allRect[old == 1 ? 2 : 1];

                if (canTurn(next,x,y)) {
                    rect = next;
                }
            }

            if (old >= 3 && old <= 6) {
                next = allRect[old + 1 > 6 ? 3 : old + 1];

                if (canTurn(next,x,y)) {
                    rect = next;
                }
            }

            if (old >= 7 && old <= 10) {
                next = allRect[old + 1 > 10 ? 7 : old + 1];

                if (canTurn(next,x,y)) {
                    rect = next;
                }
            }

            if (old == 11 || old == 12) {
                next = allRect[old == 11 ? 12 : 11];

                if (canTurn(next,x,y)) {
                    rect = next;
                }
            }


            if (old == 13 || old == 14) {
                next = allRect[old == 13 ? 14 : 13];

                if (canTurn(next,x,y)) {
                    rect = next;
                }
            }



            if (old >= 15 && old <= 18) {
                next = allRect[old + 1 > 18 ? 15 : old + 1];

                if (canTurn(next,x,y)) {
                    rect = next;
                }
            }


            //重新绘制变形后方块
            draw(x,y);
        }
    }

    //判断方块此时是否可以变形的方法
    public boolean canTurn(int a,int m,int n) {
        //创建变量
        int temp = 0x8000;
        //遍历整个方块
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 4;j++) {
                if ((a & temp) != 0) {
                    if (data[m][n] == 1) {
                        return false;
                    }
                }
                n++;
                temp >>= 1;
            }
            m++;
            n = n -4;
        }
        //可以变形
        return true;
    }

    public void resetData() {
        //定义一个变量

        //遍历整个4 * 4的方块
        for (int i = 1;i < game_x-1;i++) {
            for (int j = 1;j < game_y-2;j++) {

                data[i][j] = 0;
                text[i][j].setBackground(Color.WHITE);


            }

        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        //方块进行左移
        if (e.getKeyCode() == 37) {
            //判断游戏是否结束
            if (!isrunning) {
                return;
            }

            //判断游戏是否暂停
            if (game_pause) {
                return;
            }

            //方块是否碰到左墙壁
            if (y <= 1) {
                return;
            }

            //定义一个变量
            int temp = 0x8000;

            for (int i = x;i < x + 4;i++) {
                for (int j = y;j < y + 4;j++) {
                    if ((temp & rect) != 0) {
                        if (data[i][j-1] == 1) {
                            return;
                        }
                    }
                    temp >>= 1;
                }
            }

            //首先清除目前方块
            clear(x,y);

            y--;

            draw(x,y);
        }

        //方块进行右移
        if (e.getKeyCode() == 39) {
            //判断游戏是否结束
            if (!isrunning) {
                return;
            }

            //判断游戏是否暂停
            if (game_pause) {
                return;
            }

            //定义变量
            int temp = 0x8000;
            int m = x;
            int n = y;

            //存储最右边的坐标值
            int num = 1;

            for (int i = 0;i < 4;i++) {
                for (int j = 0;j < 4;j++) {
                    if ((temp & rect) != 0) {
                        if (n > num) {
                            num = n;
                        }
                    }
                    n++;
                    temp >>= 1;
                }
                m++;
                n = n - 4;
            }

            //判断是否碰到右墙壁
            if (num >= (game_y-2)) {
                return;
            }

            //方块右移途中是否碰到别的方块
            temp = 0x8000;
            for (int i = x;i < x + 4;i++) {
                for (int j = y;j < y + 4;j++) {
                    if ((temp & rect) != 0) {
                        if (data[i][j+1] == 1) {
                            return;
                        }
                    }
                    temp >>= 1;
                }
            }

            //清除当前方块
            clear(x,y);

            y++;

            draw(x,y);
        }

        //方块进行下落
        if (e.getKeyCode() == 40) {
            //判断游戏是否结束
            if (!isrunning) {
                return;
            }

            //判断游戏是否暂停
            if (game_pause) {
                return;
            }

            //判断方块是否可以下落
            if (!canFall(x,y)) {
                return;
            }

            clear(x,y);

            //改变方块的坐标
            x++;

            draw(x,y);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
