package com.javakc.fms.file.service.impl;

import com.javakc.fms.file.dao.FileDao;
import com.javakc.fms.file.entity.FileEntity;
import com.javakc.fms.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    //注入依赖
    @Autowired
    private FileDao fileDao;
    @Override
    public int insert(FileEntity entity) {
        //主键
        entity.setId(UUID.randomUUID().toString().replace("-",""));
        //日期
        entity.setCtime(new Timestamp(System.currentTimeMillis()));
        //获取后缀
        String name=entity.getName();
        String suffix=name.substring(name.lastIndexOf(".")+1);
        entity.setSuffix(suffix);
        return fileDao.insert(entity);
    }

    @Override
    public int delete(String id) {

        return fileDao.delete(id);
    }

    @Override
    public int update(FileEntity entity) {
        return 0;
    }

    @Override
    public List<FileEntity> queryByPage(FileEntity entity, int start, int end) {
        Map data=new HashMap();
        data.put("start",start);
        data.put("end",end);

        return fileDao.queryByPage(data);
    }

    @Override
    public FileEntity queryId(String id) {
        return fileDao.queryId(id);
    }

    @Override
    public void downnumCount(String id) {
        fileDao.downnumCount(id);
    }
}
