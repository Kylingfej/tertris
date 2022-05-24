import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class ScorePanel extends JTextPane {
ArrayList<Integer> list;
    JLabel first;
    JLabel second;
    JLabel third;
    JLabel fourth;
    JLabel fifth;
    JLabel[] num ={first,second,third,fourth,fifth};


    public ScorePanel(){


        list= new ArrayList<>();
        this.setSize(200,300);
        this.setOpaque(false);

        JLabel title = new JLabel("HIGH SCORES");
        title.setForeground(Color.white);
        Font t = new Font("Bangla MN",Font.BOLD,16);
        Font t2 = new Font("Bangla MN",Font.BOLD,14);
        title.setFont(t);
        title.setBounds(80,0,150,50);
        this.add(title);

        int x=130;int y=35;
        for(int i=0;i<5;i++) {
            num[i] = new JLabel("");
            num[i].setBounds(x, y, 180, 30);
            num[i].setFont(t2);
            num[i].setForeground(Color.pink);
            this.add(num[i],JLabel.CENTER);
            y=y+30;
        }



        }
    public void changeFirst(int score){
        setList(score);

        for(int i=0;i<5&&i<list.size();i++) {
            String num = Integer.toString(getList(i));
            getNum(i).setText(num);
        }
    }

    public void setList(int score) {
        list.add(score);
        Collections.sort(list);
        Collections.reverse(list);
    }

    public Integer getList(int i) {
        return list.get(i);

    }

    public void addScore(int score){
        int x=10;int y=50;
        String num =Integer.toString(score);
        getNum(0).setText(num);
        Font m = new Font("Bangla MN",Font.BOLD,60);
        getNum(0).setFont(m);
        getNum(0).setBounds(130,35,200,200);
        setList(score);


    }
    public void display(){
        int x=10;int y=50;

        for(int j=0;j<list.size()&&j<5;j++){

            JLabel order= new JLabel(getList(j).toString());
            order.setBackground(Color.gray);
            order.setBounds(x,y,180,50);
            this.add(order);
            y=y+50;

        }
    }
    public JLabel getNum(int i){
        return num[i];
    }


}
