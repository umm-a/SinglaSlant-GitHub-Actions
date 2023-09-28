package com.example.singlaslantgithubactions.Model;

import org.springframework.stereotype.Component;

@Component
public class CoinFlip {
    private int userScore = 0;
    private int computerScore = 0; //needed? is calculated as turns - userScore
    private int turns = 0;

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public void setComputerScore(int computerScore) {
        this.computerScore = computerScore;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }
}
