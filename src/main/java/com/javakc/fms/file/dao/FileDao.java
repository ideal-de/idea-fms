package com.javakc.fms.file.dao;

import com.javakc.fms.file.entity.FileEntity;

import java.util.List;
import java.util.Map;


public interface FileDao {
    public  int insert(FileEntity entity);

    public List<FileEntity> queryByPage(Map data);

    public int delete(String id);

    public FileEntity queryId(String id);

    public void downnumCount(String id);


}
