package com.example.singlaslantgithubactions.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CoinFlipControllerTest {
    CoinFlipController coinFlipController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        coinFlipController = new CoinFlipController();

    }

    @Test
    public void testPageIsDisplayedCorrectly() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(content().string(containsString("Coin Flip Game")));
    }

    @Test
    void flipCoinShouldReturnHeads() {
        String result = coinFlipController.flipCoin(0.6);
        assertEquals(result, "heads");
    }

    @Test
    void flipCoinShouldReturnTails() {
        String result = coinFlipController.flipCoin(0.3);
        assertEquals(result, "tails");
    }


/*
    @Test
    public void testPublishFlip() { //test som ska kunna se vad som returneras av endpointen
        // Create a mock Model object using Mockito
        Model model = Mockito.mock(Model.class);

        // Call the controller method with the mock Model
        String result = coinFlipController.publishFlip("heads", model);

        // Perform assertions as needed
        // You can verify interactions with the mock Model if necessary
        Mockito.verify(model).addAttribute("rounds", Mockito.anyList());
        Mockito.verify(model).addAttribute("WINNER", Mockito.anyString());

        // Assert the return value (e.g., the redirect URL)
        assertEquals("redirect:/", result);
    }


 */


    @Test
    void calculateWinRateStartsAt0() {
        coinFlipController.coinFlip.setTurns(0);
        assertEquals(coinFlipController.calculateWinRate(), 0);
    }

    @Test
    void whenUserHasWonAllWinRateIs100() {
        coinFlipController.coinFlip.setTurns(1);
        coinFlipController.coinFlip.setUserScore(1);
        coinFlipController.coinFlip.setComputerScore(0);

        //assertTrue(coinFlipController.calculateWinRate()<0);
        assertEquals(coinFlipController.calculateWinRate(), 100);
    }

    @Test
    void whenUserHasLostAllWinRateIsZero() {
        coinFlipController.coinFlip.setTurns(1);
        coinFlipController.coinFlip.setUserScore(0);
        //coinFlipController.coinFlip.setComputerScore(1);

        //assertTrue(coinFlipController.calculateWinRate()<0);
        assertEquals(coinFlipController.calculateWinRate(), 0);
    }

    @Test
    void whenUserHasWonHalfWinRateIs50() {
        coinFlipController.coinFlip.setTurns(2);
        coinFlipController.coinFlip.setUserScore(1);
        //coinFlipController.coinFlip.setComputerScore(1);

        //assertTrue(coinFlipController.calculateWinRate()<0);
        assertEquals(coinFlipController.calculateWinRate(), 50);
    }

    @Test
    void choosingHeadMeansComputerGetsTails() {
        String computerChoice = coinFlipController.calculateComputerChoice("heads");
        assertEquals(computerChoice, "tails");
    }

    @Test
    void choosingTailsMeansComputerGetsHeads() {
        String computerChoice = coinFlipController.calculateComputerChoice("tails");
        assertEquals(computerChoice, "heads");
    }

    @Test
    void whenChoiceAndResultIsHeadsUserWins() {
        String winner = coinFlipController.calculateWinner("heads", "heads");
        assertEquals(winner, "User");
    }

    @Test
    void whenChoiceAndResultIsTailsUserWins() {
        String winner = coinFlipController.calculateWinner("tails", "tails");
        assertEquals(winner, "User");
    }

    @Test
    void whenChoiceEqualsHeadsAndResultEqualsTailsComputerWins() {
        String winner = coinFlipController.calculateWinner("heads", "tails");
        assertEquals(winner, "Computer");
    }

    @Test
    void whenChoiceEqualsTailsAndResultEqualsHeadsComputerWins() {
        String winner = coinFlipController.calculateWinner("tails", "heads");
        assertEquals(winner, "Computer");
    }
}