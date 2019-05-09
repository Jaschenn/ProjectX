import com.sun.deploy.net.HttpRequest;
import data.DATAENTY;
import util.Image2aviUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.*;

public class Main {


    public static void main(String[] agrs){
    //todo:处理逻辑关系，控制什么时候才可以调用util中的方法对视频进行处理
        Timer timer = new Timer(true);
        TimerTask checkConvert = new TimerTask() {
            @Override
            public void run() {
                if (DATAENTY.Writable){
                    Image2aviUtil.convert("1.avi");//todo:根据文件的名字或者是其他的方法对这个进行处理，保证逻辑正确
                }
            }
        };
        TimerTask mergeAvi = new TimerTask() {
            @Override
            public void run() {
                //todo:code to merge avi
            }
        };
        timer.schedule(checkConvert,10*1000);

        timer.schedule(mergeAvi,4*60*60*1000);


    }
}
