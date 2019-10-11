package com.oldhandgo.system.modules.security.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
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

        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            verifyCode.append(sources.charAt(random.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }

    /**
     * 输出指定验证码图片流
     *
     * @param width        创建图像的宽度
     * @param height       创建图像的高度
     * @param outputStream 输出流
     * @param code         验证码
     * @throws IOException 是由失败或中断的I/O操作产生的异常。
     */
    public static void outputImage(int width, int height, OutputStream outputStream, String code) throws IOException {
        int verifySize = code.length();

        /*
         * TYPE_INT_RGB表示将8位RGB颜色分量打包为整数像素的图像
         * 构造一种BufferedImage预定义图像类型
         */
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

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

        // 白，青，灰，浅灰，洋红，橙，粉，黄
        Color[] colorSpaces = new Color[]{Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW};

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

        // 设置背景色
        Color color = getRandomColor(200, 250);
        graphics.setColor(color);
        /*
         * 填充指定的矩形
         *
         * public abstract void fillRect​(int x, int y, int width, int height)
         * 从左到右 x 到 x + width - 1
         * 从上到下 y 到 y + height - 1
         */
        graphics.fillRect(0, 2, width, height - 4);

        // 绘制干扰线 设置线条的颜色
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

        //设置字体颜色
        graphics.setColor(getRandomColor(100, 160));

        //设置字体大小
        int fontSize = height - 4;

        /*
         * 设置图形的字体
         *
         * public Font​(String name, int style, int size)
         * name 字体名称
         * style 字体样式
         * size 字体大小
         *
         * PLAIN 普通样式
         * ITALIC 斜体样式
         */
        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        graphics.setFont(font);

        char[] chars = code.toCharArray();
        for (int i = 0; i < verifySize; i++) {
            // 表示2D仿射变换，可以使用平移，缩放，翻转，旋转和剪切的序列来构造仿射变换
            AffineTransform affineTransform = new AffineTransform();
            /*
             * 将此变换设置为平移旋转变换
             *
             * public void setToRotation​(double theta, double anchorx, double anchory)
             * theta -旋转角度（以弧度为单位）
             * anchorx -旋转锚点的X坐标
             * anchory -旋转锚点的Y坐标
             *
             * 此操作等效于平移坐标，使锚点位于原点（S1），然后将其绕新原点旋转（S2），最后平移，以便将中间原点恢复到原始锚点的坐标（S3）
             * 此操作等效于以下调用序列：
             * setToTranslation(anchorx，anchory); S3：最终平移
             * rotate(theta); S2：围绕锚点
             * translate(-anchorx，-anchory); S1：将锚点转换为原点
             *
             * 表示此矩阵变为：
             * [   cos(theta)    -sin(theta)    x-x*cos+y*sin  ]
             * [   sin(theta)     cos(theta)    y-x*sin-y*cos  ]
             * [       0              0               1        ]
             */
            affineTransform.setToRotation(Math.PI / 4 * random.nextDouble() * (random.nextBoolean() ? 1 : -1), ((float) width / verifySize) * i + ((float) fontSize / 2), ((float) height / 2));
            graphics.setTransform(affineTransform);

            /*
             * 使用此图形的当前字体和颜色绘制由指定字符数组给定的文本
             * 第一个字符的基线在此图形上下文的坐标系中的位置（x，y）
             *
             * public void drawChars​(char[] data, int offset, int length, int x, int y)
             * data 要绘制的字符数组
             * offset 数据中的起始偏移量
             * length 要绘制的字符数
             * x 文本基线的x坐标
             * y 文本基线的y坐标
             *
             */
            graphics.drawChars(chars, i, 1, ((width - 10) / verifySize) * i + 5, height / 2 + fontSize / 2 - 10);
        }
        // 处置此图形并释放它正在使用的所有系统资源
        graphics.dispose();
        /*
         * 使用ImageWriter支持给定格式的任意图像将图像写入OutputStream。
         * 在写操作完成后，此方法不会关闭提供OutputStream的内容。如果需要，调用者有责任关闭流。
         * 来自getUseCache和getCacheDirectory的当前缓存设置将用于控制缓存。
         *
         * public static boolean write​(RenderedImage im, String formatName, OutputStream output) throws IOException
         * im 一个要写的RenderedImage
         * formatName String包含格式的非正式名称
         * output 要写入的OutputStream
         */
        ImageIO.write(image, "jpg", outputStream);
    }

    /**
     * 随机获取一种颜色
     *
     * @param fc R、G、B的最小值
     * @param bc R、G、B的最大值
     * @return Color类型的颜色
     */
    private static Color getRandomColor(int fc, int bc) {
        if (fc > bc) {
            fc = fc ^ bc;
            bc = fc ^ bc;
            fc = fc ^ bc;
        }
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

    /**
     * 随机获取一种颜色
     *
     * @return int型颜色
     */
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
     *
     * @return 保存R、G、B三个值的数组
     */
    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    private static void shear(Graphics graphics, int width, int height, Color color) {
        shearAbscissa(graphics, width, height, color);
        shearOrdinate(graphics, width, height, color);
    }

    private static void shearAbscissa(Graphics graphics, int width, int height, Color color) {
        int period = random.nextInt(2);
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < height; i++) {
            double dx = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            /*
             * 按dx和dy指定的距离复制组件的区域
             * 此方法会从指定的点x和点y向下和向右复制
             *
             * public abstract void copyArea​(int x, int y, int width, int height, int dx, int dy)
             * x 源矩形的x坐标
             * y 源矩形的y坐标
             * width 源矩形的宽度
             * height 源矩形的高度
             * dx 复制像素的水平距离
             * dy 复制像素的垂直距离
             */
            graphics.copyArea(0, i, width, 1, (int) dx, 0);
            graphics.setColor(color);
            /*
             *使用当前颜色在此图形的坐标系中，在(x1, y1)以及(x2, y2)这些点之间绘制一条线
             *
             * public abstract void drawLine​(int x1, int y1, int x2, int y2)
             * x1 第一点的x坐标
             * y1 第一点的y坐标
             * x2 第二点的x坐标
             * y2 第二点的y坐标
             */
            graphics.drawLine((int) dx, i, 0, i);
            graphics.drawLine((int) dx + width, i, width, i);
        }

    }

    private static void shearOrdinate(Graphics graphics, int width, int height, Color color) {
        // 最大50
        int period = random.nextInt(40) + 10;
        int frames = 20;
        int phase = 7;

        for (int i = 0; i < width; i++) {
            double dy = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            /*
             * 按dx和dy指定的距离复制组件的区域
             * 此方法会从指定的点x和点y向下和向右复制
             *
             * public abstract void copyArea​(int x, int y, int width, int height, int dx, int dy)
             * x 源矩形的x坐标
             * y 源矩形的y坐标
             * width 源矩形的宽度
             * height 源矩形的高度
             * dx 复制像素的水平距离
             * dy 复制像素的垂直距离
             */
            graphics.copyArea(i, 0, 1, height, 0, (int) dy);
            graphics.setColor(color);
            /*
             *使用当前颜色在此图形的坐标系中，在(x1, y1)以及(x2, y2)这些点之间绘制一条线
             *
             * public abstract void drawLine​(int x1, int y1, int x2, int y2)
             * x1 第一点的x坐标
             * y1 第一点的y坐标
             * x2 第二点的x坐标
             * y2 第二点的y坐标
             */
            graphics.drawLine(i, (int) dy, i, 0);
            graphics.drawLine(i, (int) dy + height, i, height);
        }
    }
}