package com.neuedu.updown.entity;

import com.neuedu.util.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Keafmd
 *
 * @ClassName: UploadFile
 * @Description: UploadFile实体类
 * @author: 牛哄哄的柯南
 * @date: 2020-12-18 10:46
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFile {

    private Integer id;

    private String path;
    private String ip;
    private Integer size;

    @Column("origin_name")
    private String originName;

    @Column("upload_time")
    private String uploadTime;

}