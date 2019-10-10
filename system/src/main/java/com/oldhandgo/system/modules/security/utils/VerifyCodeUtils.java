package com.oldhandgo.system.modules.security.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

/**
 * @author dormir
 */
public class VerifyCodeUtils {
    /**
     * 验证码字符源
     * 去掉0，1，I，L，O，容易混淆
     */
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKMNPQRSTUVWXYZ";
    private static Random random = new Random();

    /**
     * @param verifySize 验证码长度
     * @return 验证码
     */
    public static String generateVerifyCode(int verifySize) {
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }

    /**
     * @param verifySize 验证码长度
     * @param sources    验证码字符源
     * @return 验证码
     */
    public static String generateVerifyCode(int verifySize, String sources) {
        if (sources == null || sources.length() == 0) {
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();

        // 取当前毫秒数作为随机数种子
        Random rand = new Random(System.currentTimeMillis());

        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }

    /**
     * 输出指定验证码图片流
     *
     * @param width  创建图像的宽度
     * @param height 创建图像的高度
     * @param os
     * @param code   验证码
     * @throws IOException
     */
    public static void outputImage(int width, int height, OutputStream os, String code) throws IOException {
        int verifySize = code.length();

        /*
         * TYPE_INT_RGB表示将8位RGB颜色分量打包为整数像素的图像
         * 构造一种BufferedImage预定义图像类型
         */
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Random rand = new Random();

        /*
         * 创建一个Graphics2D，可用于绘制为此BufferedImage
         * Graphics类在几何形状提供更复杂的控制，坐标转换，颜色管理和文本布局
         */
        Graphics2D graphics = image.createGraphics();

        /*
         * 设置渲染算法的单个首选项的值
         * KEY_ANTIALIASING表示抗锯齿值
         * VALUE_ANTIALIAS_ON 使用抗锯齿
         * VALUE_ANTIALIAS_OFF 不使用抗锯齿
         * VALUE_ANTIALIAS_DEFAULT 使用默认抗锯齿模式
         */
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color[] colors = new Color[5];

        // 白，青，灰，浅灰，洋红，橙，粉，黄
        Color[] colorSpaces = new Color[]{Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW};

        float[] fractions = new float[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
            fractions[i] = rand.nextFloat();
        }
        Arrays.sort(fractions);

        // 设置边框色
        graphics.setColor(Color.GRAY);

        /*
         * 填充指定的矩形
         *
         * public abstract void fillRect​(int x, int y, int width, int height)
         * 从左到右 x 到 x + width - 1
         * 从上到下 y 到 y + height - 1
         */
        graphics.fillRect(0, 0, width, height);

        //TODO 接下来该写背景色、干扰线、线条色、噪点、以及扭曲实现。
    }
}