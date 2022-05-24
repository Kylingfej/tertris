

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.*;
public class music {
    private static  Clip bgm;//背景乐
    private static Clip hit;//音效
    private static AudioInputStream ais;
    
    public static void play(){
       
        bgm.start();//开始播放
       bgm.loop(Clip.LOOP_CONTINUOUSLY);//循环播放
  
    }
    public static void load(){
        try {
            bgm=AudioSystem.getClip();
           
            ais = AudioSystem.getAudioInputStream(new File("src/music.wav"));
            
            bgm.open(ais);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        
    }
    public static void stop()
    {
        if(ais!=null)
        //想每次都重新开始就这个
            // bgm.close();
            //想每次开始继续从上次那个地方继续听就这个
            bgm.stop();
    }
}