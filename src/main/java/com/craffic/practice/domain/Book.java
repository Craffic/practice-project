package com.craffic.practice.domain;

import java.io.Serializable;

/**
 * 序列id，实体类进行序列化时生成的唯一标识
 * IDEA的File->Settings->Editor->Inspections,然后在搜索框输入serialV，出现对应的
 * 选择第二栏和第四栏即可
 * 然后点击需要添加serialVersionUID的实体类名，Alt+Enter，出现Add ‘serialVersionUID’ field，即可给类添加serialVersionUID标识
 *
 */
public class Book implements Serializable {
    private static final long serialVersionUID = -1743613101689183181L;
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
