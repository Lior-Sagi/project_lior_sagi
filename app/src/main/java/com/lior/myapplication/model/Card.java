package com.lior.myapplication.model;

public class Card {
    int half1;
    int half2;
    int cardValue;

    public Card(int half1, int half2) {
        this.half1 = half1;
        this.half2 = half2;
        this.cardValue=half1+half2;
    }

    public int getHalf1() {
        return half1;
    }

    public void setHalf1(int half1) {
        this.half1 = half1;
    }

    public int getHalf2() {
        return half2;
    }

    public void setHalf2(int half2) {
        this.half2 = half2;
    }

    public int getCardValue() {
        return cardValue;
    }

    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

}
