package com.oldhandgo.common.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import com.oldhandgo.common.exception.BadRequestException;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * File工具类
 *
 * @author dormirr
 */
public class FileUtils extends cn.hutool.core.io.FileUtil {
    /**
     * 定义GB的计算常量
     */
    private static final int GB = 1024 * 1024 * 1024;

    /**
     * 定义MB的计算常量
     */
    private static final int MB = 1024 * 1024;

    /**
     * 定义KB的计算常量
     */
    private static final int KB = 1024;

    /**
     * 格式化小数
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    /**
     * MultipartFile转File
     *
     * @param multipartFile MultipartFile类
     * @return File类
     */
    public static File toFile(MultipartFile multipartFile) {
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = "." + getExtensionName(fileName);
        File file = null;
        try {
            // 用uuid作为文件名，防止生成的临时文件重复
            file = File.createTempFile(IdUtil.simpleUUID(), prefix);
            // MultipartFile to File
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 删除
     *
     * @param files 要删除的文件
     */
    public static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
    /**
     * 获取文件扩展名
     *
     * @param filename 文件名
     * @return 文件扩展名
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * Java文件操作 获取不带扩展名的文件名
     *
     * @param filename 文件名
     * @return 文件名（不包括扩展）
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 文件大小转换
     *
     * @param size 大小
     * @return 把文件大小转换成比特为单位
     */
    public static String getSize(long size) {
        String resultSize;
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = DF.format(size / (float) GB) + "GB   ";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = DF.format(size / (float) MB) + "MB   ";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = DF.format(size / (float) KB) + "KB   ";
        } else {
            resultSize = size + "B   ";
        }
        return resultSize;
    }

    /**
     * inputStream 转 File
     *
     * @param inputStream 输入流
     * @param name        文件名
     * @return File类
     * @throws Exception 异常
     */
    public static File inputStreamToFile(InputStream inputStream, String name) throws Exception {
        File file = new File(System.getProperty("java.io.tmpdir") + name);
        if (file.exists()) {
            return file;
        }
        OutputStream os = new FileOutputStream(file);
        int bytesRead;
        byte[] buffer = new byte[8192];
        while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        inputStream.close();
        return file;
    }

    /**
     * 将文件名解析成文件的上传路径
     *
     * @param file     文件
     * @param filePath 文件路径
     * @return 上传到服务器的文件名
     */
    public static File upload(MultipartFile file, String filePath) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssS");
        String name = getFileNameNoEx(file.getOriginalFilename());
        String suffix = getExtensionName(file.getOriginalFilename());
        String nowStr = "-" + format.format(date);
        try {
            String fileName = name + nowStr + "." + suffix;
            String path = filePath + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                // 新建文件夹
                dest.getParentFile().mkdirs();
            }
            String d = dest.getPath();
            // 文件写入
            file.transferTo(dest);
            return dest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件转换到Base64
     *
     * @param file 文件
     * @return Base64
     * @throws Exception 异常
     */
    public static String fileToBase64(File file) throws Exception {
        FileInputStream inputFile = new FileInputStream(file);
        String base64;
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        base64 = Base64.encode(buffer);
        return base64.replaceAll("[\\s*\t\n\r]", "");
    }

    /**
     * 导出excel
     *
     * @param list 内容
     * @throws IOException 异常
     */
    public static void downloadExcel(List<Map<String, Object>> list, HttpServletResponse response) throws IOException {
        String tempPath = System.getProperty("java.io.tmpdir") + IdUtil.fastSimpleUUID() + ".xlsx";
        File file = new File(tempPath);
        BigExcelWriter writer = ExcelUtil.getBigWriter(file);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition", "attachment;filename=file.xlsx");
        ServletOutputStream out = response.getOutputStream();
        // 终止后删除临时文件
        file.deleteOnExit();
        writer.flush(out, true);
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    /**
     * 获取文件类型
     *
     * @param type 类型
     * @return 类型
     */
    public static String getFileType(String type) {
        String documents = "txt doc pdf ppt pps xlsx xls";
        String music = "mp3 wav wma mpa ram ra aac aif m4a";
        String video = "avi mpg mpe mpeg asf wmv mov qt rm mp4 flv m4v webm ogv ogg";
        String image = "bmp dib pcp dif wmf gif jpg tif eps psd cdr iff tga pcd mpt png jpeg";
        if (image.contains(type)) {
            return "图片";
        } else if (documents.contains(type)) {
            return "文档";
        } else if (music.contains(type)) {
            return "音乐";
        } else if (video.contains(type)) {
            return "视频";
        } else {
            return "其他";
        }
    }

    /**
     * 通过MIME类型获取文件类型
     *
     * @param type 类型
     * @return 类型
     */
    public static String getFileTypeByMimeType(String type) {
        String mimeType = new MimetypesFileTypeMap().getContentType("." + type);
        return mimeType.split("/")[0];
    }

    /**
     * 检查大小
     *
     * @param maxSize 最大大小
     * @param size    文件大小
     */
    public static void checkSize(long maxSize, long size) {
        int unit = 1024;
        if (size > (maxSize * unit * unit)) {
            throw new BadRequestException("文件超出规定大小");
        }
    }
}
