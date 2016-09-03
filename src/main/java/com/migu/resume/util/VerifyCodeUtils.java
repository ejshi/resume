package com.migu.resume.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
/**
 *生在验证码
 *@author zhangpanfu
 */
public class VerifyCodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeUtils.class);
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    private static Random random = new Random();

    public static final String SESSION_KEY_SECURITY_CODE = "SecurityCode";
    private static int strWidth = 60;
    private static int strHeight = 20;
    private static int strLength = 4;
    private static int fontWeight = 3;
    private static int fontSize = 13;

    private static String fontName = "Verdana";

    boolean refresh = true;

    // session的存放key
    String key = null;


    // 随机码类型（type,1－字母，2－数字，3－简单汉字，4－随机汉字，允许多种同时使用，如下即表示字母与数字混合）
    int mode[] = new int[] { 2 };

    public static String generateVerifyCode(int verifySize){
        String verifyCode = generateVerifyCode(verifySize, VERIFY_CODES);
        logger.info("生成的验证码为[{}]",verifyCode);
        return   verifyCode;
    }

    public static String generateVerifyCode(int verifySize, String sources){
        if(sources == null || sources.length() == 0){
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for(int i = 0; i < verifySize; i++){
            verifyCode.append(sources.charAt(rand.nextInt(codesLen-1)));
        }
        return verifyCode.toString();
    }

    /**
     * 按照传入参数，生成一个验证码图片
     * @param width
     * @param height
     * @param length
     * @param background_color
     * @return
     */
    public static BufferedImage BuildImage(int width,int height,int length,Color background_color,final String verifyCode )
    {


        int size = (width - width / 10) / length;
        int vlign = (height + size) / 2;

        // 创建内存图像
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 创建随机类的实例
        Random random = new Random();

        // 设定图像背景色(因为是做背景，所以偏淡)
        g.setColor(background_color);
        g.fillRect(0, 0, width, height);

        // 备选字体
        String[] fontTypes = { "\u5b8b\u4f53", "\u65b0\u5b8b\u4f53",
                "\u9ed1\u4f53", "\u6977\u4f53", "\u96b6\u4e66" };
        int fontTypesLength = fontTypes.length;





        String sRand = verifyCode;
        for (int i = 0; i < length; i++) {
            // 设置字体的颜色
            g.setColor(getRandColor(random, 10, 150));
            // 设置字体
            Font f = new Font(
                    fontTypes[random.nextInt(fontTypesLength)],
                    Font.BOLD, 22);
            g.setFont(f);
            // 将此汉字画到图片上
            g.drawString(sRand.charAt(i) + "", size * i + size / 8
                    * (random.nextInt(3) + 2), vlign);
        }

        return image;
    }

    private static Color getRandColor(Random random, int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public static  void main(String[] args)
    {
        System.out.println(generateVerifyCode(5));
    }
}
