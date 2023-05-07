package com.seiferson.hxtask.model;

import java.util.Date;

public class Update {
    private Date date;
    private String text;
    private String note;
    private String author;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
