package com.Entity;

public class Student {
    private int id;
    private String name;
    private int calledTimes;
    private int answeredTimes;
    private int state;

    public Student(int id, String name, int calledTimes, int answeredTimes,int state) {
        this.id = id;
        this.name = name;
        this.calledTimes = calledTimes;
        this.answeredTimes = answeredTimes;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getState() {
        return state;
    }

    public void setState(int state) {
    	this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalledTimes() {
        return calledTimes;
    }

    public void setCalledTimes(int calledTimes) {
        this.calledTimes = calledTimes;
    }

    public int getAnsweredTimes() {
        return answeredTimes;
    }

    public void setAnsweredTimes(int answeredTimes) {
        this.answeredTimes = answeredTimes;
    }
}
