package com.example.singlaslantgithubactions.Services;

import com.example.singlaslantgithubactions.Model.CoinFlip;
import org.springframework.stereotype.Component;

@Component
public class WinRateCalculator {
    public double calculateWinRate(CoinFlip coinFlip) {
        if (coinFlip.getTurns() == 0) {
            return 0.0;
        }
        return ((double) coinFlip.getUserScore() / coinFlip.getTurns()) * 100;
    }
}

