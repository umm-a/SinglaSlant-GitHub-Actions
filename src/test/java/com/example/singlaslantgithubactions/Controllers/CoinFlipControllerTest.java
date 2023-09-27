package com.example.singlaslantgithubactions.Controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

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
        assertTrue(coinFlipController.calculateWinRate()==0);
    }

    @Test
    public void testFlipCoin() {
        // Create a mock Model object using Mockito
        Model model = Mockito.mock(Model.class);

        // Call the controller method with the mock Model
        String result = coinFlipController.flipCoin("heads", model);

        // Perform assertions as needed
        // You can verify interactions with the mock Model if necessary
        Mockito.verify(model).addAttribute("rounds", Mockito.anyList());
        Mockito.verify(model).addAttribute("WINNER", Mockito.anyString());

        // Assert the return value (e.g., the redirect URL)
        assertEquals("redirect:/", result);
    }

    @Test
    void calculateWinRateAfterRoundIsLessThan100() {
        assertTrue(coinFlipController.calculateWinRate()<0);
        //   coinFlipController.flipCoin("heads", Model model);
        //ska fela tills vidare
    }


}