package com.ceit.util;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import static java.awt.Font.SANS_SERIF;

/**
 * Created by samsung on 2017/6/22.
 */
public class CaptchaUtil {
    private CaptchaUtil(){}
//    随机字符字典

    private static final char[] chars={ '0','1','2', '3', '4', '5', '6', '7', '8',
            '9'};
    //随机数

    private static Random random=new Random();
    //获取4位随机数
    private static String getRandomString()
    {
        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < 4; i++)
        {
            buffer.append(chars[random.nextInt(chars.length)]);
        }
        return buffer.toString();
    }
    /*
    * 获取随机数颜色
    */
    private static Color getRandomColor(){
        return  new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    }
    /*
   * 返回某颜色的反色
   */
    private static Color getReverseColor(Color c)
    {
        return new Color(255 - c.getRed(), 255 - c.getGreen(),
                255 - c.getBlue());
    }
    public static void outputCaptcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        response.setContentType("image/jpeg");

        String randomString = getRandomString();
        request.getSession(true).setAttribute("randomString", randomString);

        int width = 70;
        int height = 40;

        Color color = getRandomColor();
        Color reverse = getReverseColor(color);

        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setFont(new Font(SANS_SERIF, Font.BOLD, 25));
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.setColor(reverse);
        String[] arr=new String[randomString.length()];
        for(int i=0,a=9;i<randomString.length();i++){
            arr[i]=randomString.substring(i,i+1);
            g.drawString(arr[i], a, 30);
            a=a+13;
        }
//        g.drawString(randomString, 18, 20);
        for (int i = 0, n = random.nextInt(100); i < n; i++)
        {
            g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
        }

        // 转成JPEG格式
        ServletOutputStream out;
        out = response.getOutputStream();

        //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        //encoder.encode(bi);
        ImageIO.write(bi, "jpg", out);

        out.flush();

    }

}
