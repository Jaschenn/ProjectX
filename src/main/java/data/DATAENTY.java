package data;

import util.Image2aviUtil;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Author: jas chen
 * Date: 2019-05-09
 * Time: 17:24
 * Blog: http://www.rukia.cc
 */
public class DATAENTY {
    private static Queue<BufferedImage> imageQueue = new LinkedList<>();
    public static boolean Writable;
    public static int MAX_POOL;


    /**
     * @param bufferedImage 传入的是一个图像，由servlet调用
     * @return 返回当前是否可以将结果写入到硬盘中
     * */
    static public  boolean addImage(BufferedImage bufferedImage){
        DATAENTY.imageQueue.add(bufferedImage);
        return imageQueue.size() >= MAX_POOL;//todo：做一个抽象类，用来实现配置文件的功能
    }



    /**
     * @return 返回的是当前队列中的一个图像
     * */
    public static BufferedImage removeImage(){
        System.out.println(" 开始移除-->当前的size:"+DATAENTY.imageQueue.size());
        BufferedImage bufferedImage = DATAENTY.imageQueue.remove();
        System.out.println("获取到的图片---->"+bufferedImage);
        return bufferedImage;
    }
    public static int getSize(){
        return imageQueue.size();
    }

}
