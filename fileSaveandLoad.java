import java.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
public class fileSaveandLoad {



    public void loadGameData(Tertris tertris,String string,String name){
        try {
            //选择要加载的数据的组件
            int[][] saveddata = new int[tertris.game_x][tertris.game_y];
            JTextArea[][] savedtext = new JTextArea[tertris.game_x][tertris.game_y];
            JLabel savedlabel = new JLabel();
            boolean savedisrunning = true;
            int savedx;
            int savedy;
            // int savedrect;
            int savedscore;
            boolean savedgame_pause = false;
            int savedrect;
            Color[][] saverecordColor=new Color[tertris.game_x][tertris.game_y];
            // File file = new File("D:/src 3/src/store.txt");
            File file = new File(string);
            FileInputStream fileStream = new FileInputStream(file);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);

            //将数据存储进来
            if (name =="x"){
                savedx= (int) objectStream.readObject();

                tertris.stroex = savedx;
            }else if(name == "data"){
                saveddata = (int[][]) objectStream.readObject();
                if (saveddata != null){
                    tertris.data = saveddata;}
            }else if (name == "rect"){
                savedrect = (int) objectStream.readObject();

                tertris.rect = savedrect;
            }
            else if (name == "score"){
                savedscore = (int) objectStream.readObject();

                tertris.score = savedscore;
            }else if (name == "y"){
                savedy = (int) objectStream.readObject();

                tertris.stroey = savedy;
            }
            else if (name=="recordColor"){
                saverecordColor = (Color[][]) objectStream.readObject();
                if (saveddata != null){
                    tertris.recordColor = saverecordColor;}

            }

            objectStream.close();
            fileStream.close();





        }catch (Exception e) {
            System.out.print(4);
        }
    }
    public  JTextArea[][] test(JTextArea[][] text,String string,String name){
        try {
            //选择要加载的数据的组件

            JTextArea[][] savedtext = new JTextArea[26][12];
            int[][] saveddata = new int[26][12];
            JLabel savedlabel = new JLabel();
            boolean savedisrunning = true;
            // int savedrect;
            int savedscore;
            int savedrect;
            boolean savedgame_pause = false;

            // File file = new File("D:/src 3/src/store.txt");
            File file = new File(string);
            FileInputStream fileStream = new FileInputStream(file);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            //将数据存储进来
            // if (name =="text"){
            //     savedtext= (JTextArea[][]) objectStream.readObject();
            //     if (savedtext != null){
            //     text = savedtext;}
            // }
            if (name =="text"){
                savedtext = (JTextArea[][]) objectStream.readObject();

                text = savedtext;
                // System.out.println(rect);
            }
            // saveddata = (int[][]) objectStream.readObject();
            // savedlabel = (JLabel) objectStream.readObject();
            // // savedrect = (int) objectStream.readObject();

            // savedscore = (int) objectStream.readObject();

            objectStream.close();
            fileStream.close();





        }catch (Exception e) {
            System.out.print(4);
        }
        return text;

    }
    public void load(Tertris tertris,String string,String name){
        try {
            //选择要加载的数据的组件
            int[][] saveddata = new int[tertris.game_x][tertris.game_y];
            JTextArea[][] savedtext = new JTextArea[tertris.game_x][tertris.game_y];
            JLabel savedlabel = new JLabel();
            boolean savedisrunning = true;
            // int savedrect;
            int savedscore;
            boolean savedgame_pause = false;
            int savedrect;
            // File file = new File("D:/src 3/src/store.txt");
            File file = new File(string);
            Reader r =new FileReader(file);
            BufferedReader br = new BufferedReader(r);

            //将数据存储进来
            if (name =="data"){
                int flag = 0;
                String s;
                while((s = br.readLine())!=null) {
                    String[] split = s.split("\t");
                    for(int i = 0;i<split.length;i++) {
                        tertris.data[flag][i] = Integer.parseInt(split[i]);
                    }
                    flag++;
                }
            }else if (name=="recordColor"){
                int flag1=0;
                String s;
                while((s = br.readLine())!=null) {
                    String[] split = s.split("\t");

                    for(int i = 0;i<split.length;i++) {

                        tertris.recordColor[flag1][i] = Color.decode(split[i]);
                    }
                    flag1++;}

            }
            else if (name == "rect"){
                String s;
                while((s = br.readLine())!=null) {
                    tertris.rect = Integer.parseInt(s);
                }
            }
            else if (name == "score"){
                String s;
                while((s = br.readLine())!=null) {
                    tertris.score = Integer.parseInt(s);
                    tertris.label.setText("游戏得分为: " + s);
                }
            }
            br.close();
        }catch (Exception e) {
            System.out.print(4);
        }

    }
}











// SaveGmaeData("D:/src 3/src/text.dat","text");
// SaveGmaeData("D:/src 3/src/text.txt","text");
// fileSaveandLoad filel= new fileSaveandLoad();
// JTextArea[][] data1 = new JTextArea[26][12];
// data1=filel.test(data1, "D:/src 3/src/text.dat", "text");
// for (int i = game_x - 2;i >= 1;i--) {
//     for (int j = 1;j <= (game_y-2);j++) {
//         if(data1[i][j].getCaretColor().equals(Color.blue)){
//             System.out.print("diff");
//             // text[i][j].getCaretColor();
//         }
//     }}
//     System.out.print("sus");