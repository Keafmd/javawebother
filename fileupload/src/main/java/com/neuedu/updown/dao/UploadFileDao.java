package com.neuedu.updown.dao;

import com.neuedu.updown.entity.UploadFile;
import com.neuedu.util.JDBCUtil;

import java.util.List;

/**
 * Keafmd
 *
 * @ClassName: UploadFileDao
 * @Description: UploadFileDao
 * @author: 牛哄哄的柯南
 * @date: 2020-12-18 10:48
 */

public class UploadFileDao {

    /**
     * 查询列表
     * @return
     */
    public List<UploadFile> selectList(){
        String sql = " select * from  upload_files ";
        return JDBCUtil.executeQuery(sql,UploadFile.class);
    }

    /**
     * 根据主键查询信息
     * @param id
     * @return
     */
    public UploadFile  getOne(Integer id){
        String sql = " select * from upload_files where id = ? ";
        return JDBCUtil.getOne(sql,UploadFile.class,id);
    }


    /**
     * 保存信息
     * @param uploadFile
     * @return
     */
    public boolean insert(UploadFile uploadFile){
        String sql = "insert into upload_files( origin_name,  path, size, ip ) values(?,?,?,?) ";
        return JDBCUtil.executeUpdate(sql,
                uploadFile.getOriginName(),
                uploadFile.getPath(),
                uploadFile.getSize(),
                uploadFile.getIp()
        );
    }
}
