package servlet;

import data.DATAENTY;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(name = "ImageServlet")
public class ImageServlet extends HttpServlet {
    /**
     * 传输类型：File类型
     * 原始图片的颜色模式：RGB
     * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 设计思路：
         * 采集端传过来的都是图片，因此需要去拼接视频。然后去保存这些视频到文件夹中。
         * 每天一个文件夹，每个小时一个视频 1h = 60m * 60 s = 3600s
         * 图片名字 前六位表示日期 190509
         *          然后是一个大写字母R N        N正常，直接拼接   R表示三秒钟的视频，需要重复这个视频
         *
         *
         * 图片需要先进行缓存，加入队列[内存队列]中，每10分钟（照片数量最大为10*30 = 600张）将内存中的图片写入到硬盘中，等每个文件夹中的内容够6个之后合并到硬盘中的一个视频。
         * */

        //todo：将接收到图片保存到队列中，剩下的交由Java程序去处理。

        String name = request.getParameter("name");//todo:得到上传的图片的名字
        BufferedImage bufferedImage = ImageIO.read(request.getInputStream());
        if (name.charAt(6)=='R'){
            //需要重复。 时间是3s，也就是3s*30 = 90 张图片
            for (int i = 0 ;i < 30;i++){
                DATAENTY.imageQueue.add(bufferedImage);
            }
        }else {
            DATAENTY.imageQueue.add(bufferedImage);
        }
        DATAENTY.Writable = true;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
