import data.DATAENTY;
import org.junit.jupiter.api.Test;
import util.Image2aviUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Author: jas chen
 * Date: 2019-05-21
 * Time: 19:45
 * Blog: http://www.rukia.cc
 */
public class ALL_TEST {
    //本类用来对应用的全部功能进行测试
    public static void main(String[] args)throws Exception{
        //testUtil();//单线程没有任何的问题，访问的数据均正确，并且可以正常生成视频。
        //testUtil2();//证明确实压缩成一个视频之后还可以对队列进行操作，然后在进行压缩。但是这样无法测试同时进行压缩和添加图片操作
        //testUtil3();//证明使用计时器也可以正常的完成工作。可以对队列中的多组图片进行压缩
        //todo：那么剩下来的什么会影响呢？一下没有想清楚。究竟是哪里出了问题呢。
        /**
         *
         * 现在系统启动逻辑：
         * 首先第一次接收到图片的时候（或者是系统初始化的时候），计时器开始监听图片的数量，满足条件的时候就会进行转换
         *
         * 此时，系统还在不间断的接收新传过来的图片。队列的长度依然会增加。
         *
         * */


        /**
         *
         * 已经找到问题的关键所在：
         *  计时器没有问题，问题出现在removeImage上，每一次调用removeImage之后 队列的数量确实会减少一个。
         *  但是无法获取到对象。 log显示为：获取到的图片--->null。
         *  证明他确实是减少了，获取不到对象。
         *  在上面的测试中是没有问题的。
         * */


    }

    private static void testUtil()throws Exception{
        DATAENTY.MAX_POOL = 1800;
        FileInputStream fileInputStream = new FileInputStream("/Users/jaschen/Desktop/1.jpg");
        BufferedImage bufferedImage = ImageIO.read(fileInputStream);
        for (int i = 0;i <1800;i++){
            DATAENTY.addImage(bufferedImage);
        }
        System.out.println("添加成功");
        Image2aviUtil.convert("1.avi");
    }

    private static void testUtil2()throws Exception{
        DATAENTY.MAX_POOL = 1800;
        FileInputStream fileInputStream = new FileInputStream("/Users/jaschen/Desktop/1.jpg");
        BufferedImage bufferedImage = ImageIO.read(fileInputStream);
        for (int i = 0;i <1800;i++){
            DATAENTY.addImage(bufferedImage);
        }
        System.out.println("添加成功");
        Image2aviUtil.convert("2.avi");
        System.out.println("视频压缩完成了");
        //用来测试是否可以在压缩视频的同时对队列进行操作
        for (int i = 0;i <1800;i++){
            DATAENTY.addImage(bufferedImage);
        }
        Image2aviUtil.convert("3.avi");
    }


    /**
     *
     *  测试 使用定时器是否可以成功的调试程序
     * */
    private static void testUtil3()throws Exception{
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Image2aviUtil.convert(Math.random()+"__4.avi");
                System.out.println("视频压缩完成了");
            }
        },0,2000);
        DATAENTY.MAX_POOL = 1800;
        FileInputStream fileInputStream = new FileInputStream("/Users/jaschen/Desktop/1.jpg");
        BufferedImage bufferedImage = ImageIO.read(fileInputStream);
        for (int i = 0;i <1800;i++){
            DATAENTY.addImage(bufferedImage);
        }
        System.out.println("添加第一组成功");
        for (int i = 0;i <1800;i++){
            DATAENTY.addImage(bufferedImage);
        }
        System.out.println("添加第二组成功");


    }


    private static void test5(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Image2aviUtil.convert(Math.random()+"__4.avi");
                System.out.println("视频压缩完成了");
            }
        },0,2000);


    }



}
