package com.xddal.moon.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 多图片上传
 *
 * @author xuedong
 */
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
    /**
     *
     * @param request HttpServletRequest
     * @param folder String
     * @return  JSONObject
     * @throws IOException
     * @throws Exception
     */
    public static Map<String,String> uploadManyFile(HttpServletRequest request, String folder) throws IOException, Exception {
        //创建一个数组
        Map<String,String> map = new HashMap<String,String>();
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            logger.info(iter.toString());
            while (iter.hasNext()) {
                logger.info("开始上传");
                //记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                //取得上传文件
                List<MultipartFile> files = multiRequest.getFiles(iter.next());
                if (files != null) {
                    for (MultipartFile file : files) {
                        //取得当前上传文件的文件名称
                        String myFileName = file.getOriginalFilename();
                        //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if (myFileName.trim() != "") {
                            logger.info(myFileName);
                            //取得前缀
                            String name = null;
                            name = myFileName.substring(0, myFileName.indexOf("."));
                            logger.info(name);
                            // 取得后缀
                            String postfix = null;
                            postfix = myFileName.substring(myFileName.indexOf(".")).toUpperCase();
                            String fileName = null;
                            String str = UUID.randomUUID().toString();

                            //获取根目录
                           /* String root = request.getSession().getServletContext().getRealPath("/upload/" + folder)
                                    + "/";*/
                            String root = "E:\\demo\\upload"+"/";
                            //重命名上传后的文件名
                            fileName = str + postfix;
                            String savePath = root + fileName;
                            logger.info(savePath);
                            File localFile = new File(savePath);
                            file.transferTo(localFile);
                            String imgurl = ("E:/demo/upload" + "/" + str)
                                    .replace("//", "/");
                            map.put(name, imgurl + postfix);
                        }
                    }
                }
                //记录上传该文件后的时间
                int finaltime = (int) System.currentTimeMillis();
                System.out.println(finaltime - pre);
            }

        }

        return map;
    }

    /**
     * 文件下载相关代码
     * @param request
     * @param response
     * @return
     */
    public String downloadFile(HttpServletRequest request, HttpServletResponse response){
        String fileName = "FileUtil.java";
        if (fileName != null) {
            /*当前是从该工程的WEB-INF/File/下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录*/
            String realPath = request.getServletContext().getRealPath(
                    "//WEB-INF//");
            File file = new File(realPath, fileName);
            if (file.exists()) {
                /*设置强制下载不打开*/
                response.setContentType("application/force-download");
                /*设置文件名*/
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" +  fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    logger.info("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }


}
