package com.example.singlaslantgithubactions.Controllers;

import com.example.singlaslantgithubactions.Model.CoinFlip;
import com.example.singlaslantgithubactions.Model.RoundResult;
import com.example.singlaslantgithubactions.Services.Game;
import com.example.singlaslantgithubactions.Services.WinRateCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CoinFlipController {

    private List<RoundResult> rounds = new ArrayList<>();
   // protected CoinFlip coinFlip = new CoinFlip();
    private final Game game;
    private final WinRateCalculator winRateCalculator;


    @Autowired
    public CoinFlipController(Game game, WinRateCalculator winRateCalculator) {
        this.game = game;
        this.winRateCalculator = winRateCalculator;
    }

    @GetMapping("/")
    public String index(Model model) {
        CoinFlip coinFlip = game.getCoinFlip();
        double winRate = winRateCalculator.calculateWinRate(coinFlip);

        model.addAttribute("userScore", coinFlip.getUserScore());
        model.addAttribute("computerScore", coinFlip.getComputerScore());
        model.addAttribute("turns", coinFlip.getTurns());
        model.addAttribute("winRate", winRate);
        return "index";
    }

    @PostMapping("/flip")
    public String publishFlip(@RequestParam String choice, Model model) {
        double resultAsDouble = Math.random();
        RoundResult roundResult = game.playGame(choice, resultAsDouble);
        double winRate = winRateCalculator.calculateWinRate(game.getCoinFlip());
        rounds.add(roundResult);

        model.addAttribute("rounds", rounds);
        model.addAttribute("WINNER", roundResult.getWinner() + " WINS!!!!!");

        model.addAttribute("userScore", game.getCoinFlip().getUserScore());
        model.addAttribute("computerScore", game.getCoinFlip().getComputerScore());
        model.addAttribute("turns", game.getCoinFlip().getTurns());
        model.addAttribute("winRate", winRate);

        //return "redirect:/";
        return "index";
    }
/*

    @PostMapping("/flip")
    public String publishFlip(@RequestParam String choice, Model model) {
        RoundResult roundResult = playGame(choice);

        rounds.add(roundResult);

        model.addAttribute("rounds", rounds);
        model.addAttribute("WINNER", roundResult.getWinner() + " WINS!!!!!");

        model.addAttribute("userScore", coinFlip.getUserScore());
        model.addAttribute("computerScore", coinFlip.getComputerScore());
        model.addAttribute("turns", coinFlip.getTurns());
        model.addAttribute("winRate", calculateWinRate());

        //return "redirect:/";
        return "index";
    }

    protected RoundResult playGame(String choice) {
        String result = flipCoin(Math.random());
        String computerChoice = calculateComputerChoice(choice);
        String winner = calculateWinner(choice, result);

        coinFlip.setTurns(coinFlip.getTurns() + 1);
        return new RoundResult(choice, computerChoice, winner);
    }

    protected String calculateComputerChoice(String choice) {
        if (choice.equals("heads")) {
            return  "tails";
        } else {
            return "heads";
        }
    }

    protected String calculateWinner(String choice, String result) {
        if (choice.equals(result)) {
            coinFlip.setUserScore(coinFlip.getUserScore() + 1);
            return  "User";
        } else {
            coinFlip.setComputerScore(coinFlip.getComputerScore() + 1);
            return  "Computer";
        }
    }

    protected String flipCoin(double randomNumber) {
        return randomNumber > 0.5 ? "heads" : "tails";
    }


 */
}
