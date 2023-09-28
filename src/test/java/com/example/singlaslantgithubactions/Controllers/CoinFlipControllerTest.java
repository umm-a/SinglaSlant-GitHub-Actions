package com.example.singlaslantgithubactions.Controllers;

import com.example.singlaslantgithubactions.Model.CoinFlip;
import com.example.singlaslantgithubactions.Services.Game;
import com.example.singlaslantgithubactions.Services.WinRateCalculator;
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
        coinFlipController = new CoinFlipController(new Game(new CoinFlip()),new WinRateCalculator());

    }

    @Test
    public void testPageIsDisplayedCorrectly() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(content().string(containsString("Coin Flip Game")));
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





}