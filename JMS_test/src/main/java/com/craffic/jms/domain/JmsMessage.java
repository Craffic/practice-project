package com.craffic.jms.domain;

import java.io.Serializable;
import java.util.Date;

public class JmsMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String content;

    private Date date;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "JmsMessage{" +
                "content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
