package com.neuedu.updown.service;
import com.neuedu.updown.dao.UploadFileDao;
import com.neuedu.updown.entity.UploadFile;

import java.util.List;


/**
 * Keafmd
 *
 * @ClassName: UploadFileService
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2020-12-18 10:49
 */

public class UploadFileService {

    private UploadFileDao uploadFileDao = new UploadFileDao();

    /**
     * 查集合
     * @return
     */
    public List<UploadFile> list(){
        return uploadFileDao.selectList();
    }

    /**
     * 查询一个
     * @param id
     * @return
     */
    public UploadFile  getById(Integer id){
        return  uploadFileDao.getOne(id);
    }


    /**
     * 保存信息
     * @param uploadFile
     * @return
     */
    public boolean  save(UploadFile uploadFile){
        return  uploadFileDao.insert(uploadFile);
    }

}
