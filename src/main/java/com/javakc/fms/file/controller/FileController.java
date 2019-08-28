package com.javakc.fms.file.controller;

import com.javakc.fms.file.entity.FileEntity;
import com.javakc.fms.file.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
//命名空间
@RequestMapping("file")
public class FileController {
    @Autowired
    private FileService fileService;


    @Value("#{properties.filePath}")
    private String filePath;

    int count;
    @RequestMapping("upload")
    public String upload(@RequestParam("loadFile")CommonsMultipartFile loanFile) throws IOException {
        String Filename=loanFile.getOriginalFilename();

        //通过上传时间建立文件夹
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy\\MM\\dd");
        //文件路径
        String path=filePath+sdf.format(date);

        Path p1 = Paths.get(path);
        //检查文件夹是否存在
        if (!Files.exists(p1)) {
            //不存在则创建
                Files.createDirectories(p1);
        }
        //完整路径
        path += "\\" + UUID.randomUUID();

        try {
            //写入文件
            loanFile.transferTo(new File(path));
            //写入数据库
            FileEntity entity = new FileEntity();
            entity.setName(Filename);
            entity.setTotal(loanFile.getSize());
            entity.setPath(path);

            count=fileService.insert(entity);
            System.out.println(count);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return count==0?"error":"redirect:query.do";


    }
    @RequestMapping("query")
    public String query(ModelMap model){
        model.put("list",fileService.queryByPage(null,0,5));
        return "file/list";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id")String id){
        //找到文件的位置 删除磁盘上的文件
       FileEntity entity= fileService.queryId(id);
        Path path=Paths.get(entity.getPath());
        try {
           boolean b= Files.deleteIfExists(path);
            System.out.println(b);
            //删除数据库的东西
            count= fileService.delete(id);
            System.out.println(count);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return count==0?"error":"redirect:/file/query.do";
    }

    @RequestMapping("download/{id}")
    public void download(@PathVariable("id")String id, HttpServletResponse rsp){

        //找到文件的位置 下载磁盘上的文件
        FileEntity entity= fileService.queryId(id);
        String path=entity.getPath();
        //轉變字符集，解決中文亂碼
        String name=entity.getName();
        try {
            name=new String(name.getBytes("utf-8"),"ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        rsp.reset();
        rsp.setContentType("application/octet-stream; charset=utf-8");
        rsp.setHeader("Content-Disposition", "attachment; filename="+name);

        OutputStream out=null;

        try {
            out=rsp.getOutputStream();
            out.write(FileUtils.readFileToByteArray(new File(path)));
            out.flush();
            //下载次数加一
            fileService.downnumCount(id);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(!StringUtils.isEmpty(out)){
                try {

                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
