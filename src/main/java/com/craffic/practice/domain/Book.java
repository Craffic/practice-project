package com.craffic.practice.domain;

public class Book {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
