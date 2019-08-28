package com.javakc.fms.file.service;

import com.javakc.fms.file.entity.FileEntity;

import java.util.List;

public interface FileService {
    //添加
    public int insert(FileEntity  entity);

    //删除
    public int delete(String id);

    //改
    public int update(FileEntity entity);

    //查 (分页，条件)
    public List<FileEntity> queryByPage(FileEntity entity,int start,int end);

    //根据主键查
    public FileEntity queryId(String Id);
    //下载次数加一
    public void downnumCount(String id);
}
