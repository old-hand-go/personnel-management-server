package com.oldhandgo.system.modules.security.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
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

        Color color = getRandomColor(200, 250);
        // 设置背景色
        graphics.setColor(color);
        /*
         * 填充指定的矩形
         *
         * public abstract void fillRect​(int x, int y, int width, int height)
         * 从左到右 x 到 x + width - 1
         * 从上到下 y 到 y + height - 1
         */
        graphics.fillRect(0, 2, width, height - 4);

        // 绘制干扰线
        Random random = new Random();
        // 设置线条的颜色
        graphics.setColor(getRandomColor(160, 200));
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            /*
             * 在此图形上下文的坐标系中使用当前颜色在(x1, y1)以及(x2, y2)这些点之间绘制一条线
             *
             * public abstract void drawLine​(int x1, int y1, int x2, int y2)
             * x1-第一点的x坐标
             * y1-第一点的y坐标
             * x2-第二点的x坐标
             * y2-第二点的y坐标
             */
            graphics.drawLine(x, y, x + xl + 40, y + yl + 20);
        }

        // 添加噪点
        // 噪点率
        float noiseRate = 0.05f;
        int area = (int) (noiseRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int rgb = getRandomIntColor();
            /*
             * 将此像素设置为BufferedImage指定的RGB值
             * 假定像素处于默认RGB颜色模型TYPE_INT_ARGB和默认sRGB颜色空间中
             * 对于具有IndexColorModel的图像，将选择最接近的颜色的索引
             *
             * public void setRGB​(int x, int y, int rgb)
             * x 要设置的像素的X坐标
             * y 要设置的像素的Y坐标
             * rgb RGB值
             */
            image.setRGB(x, y, rgb);
        }

        // 使图片扭曲
        shear(graphics, width, height, color);

        graphics.setColor(getRandomColor(100, 160));
        int fontSize = height - 4;
        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        graphics.setFont(font);
        char[] chars = code.toCharArray();
        for (int i = 0; i < verifySize; i++) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (width / verifySize) * i + fontSize / 2, height / 2);
            graphics.setTransform(affine);
            graphics.drawChars(chars, i, 1, ((width - 10) / verifySize) * i + 5, height / 2 + fontSize / 2 - 10);
        }

        graphics.dispose();
        ImageIO.write(image, "jpg", os);
    }

    /**
     * 随机获取一种颜色
     * @param fc
     * @param bc
     * @return Color类型的颜色
     */
    private static Color getRandomColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    /**
     * 随机获取R、G、B三个值
     * @return 保存R、G、B三个值的数组
     */
    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }
    }
}