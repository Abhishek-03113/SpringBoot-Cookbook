package com.abhishek.todo.controller;

public class Todo {
    private long id;
    private String title;
    private Status status;

    public Todo(){}

    public Todo(long id, String title, Status status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
