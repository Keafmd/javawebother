package com.neuedu.updown;

import com.neuedu.updown.entity.UploadFile;
import com.neuedu.updown.service.UploadFileService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * Keafmd
 *
 * @ClassName: FileDownLoadServlet
 * @Description: FileDownLoadServlet
 * @author: 牛哄哄的柯南
 * @date: 2020-12-18 11:35
 */

@WebServlet(name = "FileDownLoadServlet",urlPatterns = "/download")
public class FileDownLoadServlet extends HttpServlet {

    UploadFileService uploadFileService = new UploadFileService();


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

        //设置header信息 ，通知浏览器，输出文件的类型

        String id = request.getParameter("id");

        UploadFile uploadFile = uploadFileService.getById(Integer.parseInt(id));



        response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(uploadFile.getOriginName(),"utf-8") +"");
        ServletOutputStream out = response.getOutputStream();

        String fileName = uploadFile.getPath();

        File downloadFile = new File(baseDir,fileName);

        FileInputStream fis = new FileInputStream(downloadFile);
        byte[] buffer = new byte[1024];

        int len = -1;
        while ( (len = fis.read(buffer)) != -1){
            out.write(buffer,0,len);
            out.flush();

        }

        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
