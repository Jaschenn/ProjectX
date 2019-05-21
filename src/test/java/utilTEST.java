import data.DATAENTY;
import util.Image2aviUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

/**
 * Author: jas chen
 * Date: 2019-05-16
 * Time: 15:01
 * Blog: http://www.rukia.cc
 */
public class utilTEST {
    public static void main(String[] args)throws Exception{
        FileInputStream fileInputStream = new FileInputStream("/Users/jaschen/Desktop/1.jpg");
        DATAENTY.MAX_POOL = 1800;
        BufferedImage bufferedImage = ImageIO.read(fileInputStream);
        for (int i = 0;i <18001;i++){
            DATAENTY.addImage(bufferedImage);
        }
        System.out.println("添加成功");
        Image2aviUtil.convert("1.avi");

    }
}
