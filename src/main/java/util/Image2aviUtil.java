package util;


import com.jbrown.image2avi.core.AVIOutputStream;
import com.jbrown.image2avi.core.VideoFormat;
import data.DATAENTY;

import java.io.File;

/**
 * Author: jas chen
 * Date: 2019-05-09
 * Time: 17:15
 * Blog: http://www.rukia.cc
 */
//todo:考虑这里的steps和Main中的扫描周期是否可以同步
public class Image2aviUtil {
    /**
     * @param steps 描述生成视频的步长，单位是分钟，默认为10分钟 10分钟 = 10*60*30 = 18000张照片
     * @param path 生成的视频的路径和名字
     * @param quality 生成的视频的质量，用来控制文件的大小
     * */
    public static boolean convert(float steps,String path,float quality){
        /**
         * 取队列中的前steps * 60 * 30 张照片,然后写入到文件中，并且文件名字为需要向后延续，以便于保存在今天的视频
         * 和便于下一次的合并视频
         *
         *
         * */
        AVIOutputStream out = null;
        if (DATAENTY.imageQueue.size() > steps * 60 * 1000){
            DATAENTY.Writable = true;
        }//保证写的时候不会产生错误
        if (DATAENTY.Writable){
            try {
                out = new AVIOutputStream(new File(path), VideoFormat.JPG);
                out.setVideoCompressionQuality(quality);
                out.setTimeScale(1);
                out.setFrameRate(30);
                float nums = steps * 60 * 30;
                while (nums>0){
                    out.writeFrame(DATAENTY.imageQueue.remove());
                    nums --;
                }

                out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        DATAENTY.Writable = false;//直到下一轮扫描的时候，如果长度大于步长才允许进行remove操作。
        return true;
    }


    /**
     * 提供一个参数最少的方法，其他的隐藏值都采用默认的
     * @param path 视频的路径
     * */
    public static boolean convert(String path){
        convert(10,path,1f);
        return true;
    }

}
