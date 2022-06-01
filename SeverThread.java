//import java.io.IOException;
//import java.net.*;
//public class SeverThread extends Thread{
//    public volatile boolean exit = false;
//
//        @Override
//        public void run(Tertris tertris,Jpanel panel,Jpanel sp) {
//            tertris.stroecount=0;
//                        fileSaveandLoad fileload = new fileSaveandLoad();
//                        tertris.isrunning=true;
//                        tertris.game_pause=false;
//
//                        fileload.loadGameData(tertris,"D:/src 3/src/data.dat","data");
//
//                        fileload.loadGameData(tertris,"D:/src 3/src/rect.dat","rect");
//                        fileload.loadGameData(tertris,"D:/src 3/src/score.dat","score");
//
//                        fileload.loadGameData(tertris,"D:/src 3/src/x.dat","x");
//                        fileload.loadGameData(tertris,"D:/src 3/src/y.dat","y");
//                        // fileload.loadGameData(tertris,"D:/src 3/src/text.txt","text");
//                        for (int i = 24;i >= 1;i--) {
//                                for (int j = 1;j <= (10);j++) {
//                                    if(tertris.data[i][j]==1){
//                                        tertris.text[i][j].setBackground(Color.BLUE);
//                                        // System.out.print("blue");
//                                    }
//                                }}
//                        //tertris.resetData();
//                        tertris.time=1000;
//
//                        tertris.game_begin(panel,sp);
//
//                        tertris.startcount+=1;
//}}
