package com.neuedu.updown;

import com.neuedu.updown.entity.UploadFile;
import com.neuedu.updown.service.UploadFileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Keafmd
 *
 * @ClassName: FileListServlet
 * @Description: FileListServlet
 * @author: 牛哄哄的柯南
 * @date: 2020-12-18 11:32
 */

@WebServlet(name = "FileListServlet",urlPatterns = "/filelist")
public class FileListServlet extends HttpServlet {
    UploadFileService uploadFileService = new UploadFileService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //跳转到 列表页面

        List<UploadFile> list = uploadFileService.list();

        request.setAttribute("list",list);
        request.getRequestDispatcher("file_list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}