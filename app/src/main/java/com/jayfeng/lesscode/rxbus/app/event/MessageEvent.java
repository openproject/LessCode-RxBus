package com.jayfeng.lesscode.rxbus.app.event;

public class MessageEvent {
    private int randomNum;

    public MessageEvent(int randomNum) {
        this.randomNum = randomNum;
    }

    public int getRandomNum() {
        return randomNum;
    }

    public void setRandomNum(int randomNum) {
        this.randomNum = randomNum;
    }
}
