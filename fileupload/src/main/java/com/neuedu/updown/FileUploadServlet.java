package com.neuedu.updown;

import com.neuedu.updown.entity.UploadFile;
import com.neuedu.updown.service.UploadFileService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * Keafmd
 *
 * @ClassName: FileUploadServlet
 * @Description: FileUploadServlet
 * @author: 牛哄哄的柯南
 * @date: 2020-12-18 09:55
 */

@WebServlet(name = "FileUploadServlet", urlPatterns = "/up")
public class FileUploadServlet extends HttpServlet {


    private UploadFileService uploadFileService = new UploadFileService();

    private static String baseDir;
    static {

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);

            baseDir = (String) properties.getProperty("upload.dir");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {

            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // Parse the request
            try {

                //表单域， input【text、file】
                List<FileItem> items = upload.parseRequest(request);
                for (FileItem item : items) {

                    if(item.isFormField()) { // text password checkbox ...
                        String name = item.getFieldName() ;// 控件的名字 input[name= ??? ]
                        String value = item.getString(); // 控件的值 input[value= ??? ]
                        System.out.println(name+"\t"+value);
                    }else{ //   type =  file

//                        String fileName = item.getFieldName(); //原始的文件名
                        String fileName = item.getName(); //原始的文件名


                        String ext = fileName.substring(fileName.lastIndexOf("."));
                        String newFileName = UUID.randomUUID().toString().replace("-","")+ext;
                        File dist = new File(baseDir,newFileName); //使用uuid 生成不重复的 文件名
                        item.write(dist);//转储到 baseDir【D:/upload】


                        String now = "";//new Date();
                        String clientHost = request.getRemoteHost();
                        long size = item.getSize();

                        String info = String.format("host:%s\t time:%s, name:%s\t size:%s",
                                clientHost,
                                new Date().toString(),
                                fileName,
                                String.valueOf(size)
                        );

                        //上传文件的信息
                        System.out.println(info);


                        UploadFile uploadFile = new UploadFile();
                        uploadFile.setIp(clientHost);
                        uploadFile.setOriginName(fileName);
                        uploadFile.setPath(newFileName);
                        uploadFile.setSize((int) size);

                        uploadFileService.save(uploadFile);

                    }


                }



            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {//普通的表单处理
            //            request.getParameter()

        }
        //查询
        response.sendRedirect(request.getContextPath()+"/filelist");

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}