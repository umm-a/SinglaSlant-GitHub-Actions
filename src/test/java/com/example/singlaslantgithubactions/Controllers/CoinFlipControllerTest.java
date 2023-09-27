package com.example.singlaslantgithubactions.Controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoinFlipControllerTest {
    CoinFlipController coinFlipController;
    @BeforeEach
    void setUp(){
         coinFlipController = new CoinFlipController();
    }
    @Test
    void flipCoin() {
    }
    @Test
    void calculateWinRateStartsWith0() {
        assertTrue(coinFlipController.calculateWinRate()==100);
    }
    @Test
    void calculateWinRateAfterRoundIsLessThan100() {
     //   coinFlipController.flipCoin("heads", Model model);
        assertTrue(coinFlipController.calculateWinRate()<0);
    }


}