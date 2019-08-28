package com.javakc.fms.file.entity;


import java.sql.Timestamp;

public class FileEntity {
    private String id;
    private String name;
    private Timestamp ctime;
    private String  path;
    private long  total;
    private int  preview;
    private int  downnum;
    private String suffix;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPreview() {
        return preview;
    }

    public void setPreview(int preview) {
        this.preview = preview;
    }

    public int getDownnum() {
        return downnum;
    }

    public void setDownnum(int downnum) {
        this.downnum = downnum;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ctime=" + ctime +
                ", path='" + path + '\'' +
                ", total=" + total +
                ", preview=" + preview +
                ", downnum=" + downnum +
                ", suffix='" + suffix + '\'' +
                '}';
    }
}
