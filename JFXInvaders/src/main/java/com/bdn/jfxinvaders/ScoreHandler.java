package com.bdn.jfxinvaders;

public class ScoreHandler {
    // handles the score
    private int score = 0;

    public void addScore(int points){
        score += points;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
