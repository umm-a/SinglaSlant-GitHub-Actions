package com.example.singlaslantgithubactions.Controllers;

import com.example.singlaslantgithubactions.Model.RoundResult;
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
    private int userScore = 0;
    private int computerScore = 0;
    private int turns = 0;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userScore", userScore);
        model.addAttribute("computerScore", computerScore);
        model.addAttribute("turns", turns);
        model.addAttribute("winRate", calculateWinRate());
        return "index";
    }

    protected double calculateWinRate() {
        if (turns == 0) return 0.0;
        return ((double) userScore / turns) * 100;
    }

    @PostMapping("/flip")
    public String flipCoin(@RequestParam String choice, Model model) {
        String result = Math.random() > 0.5 ? "heads" : "tails";
        String winner;
        String computerChoice;

        if(choice.equals("heads")){
            computerChoice = "tails";
        } else {
            computerChoice = "heads";
        }

        if (choice.equals(result)) {
            userScore++;
            winner = "User";
        } else {
            computerScore++;
            winner = "Computer";
        }

        RoundResult roundResult = new RoundResult(choice, computerChoice, winner);
        rounds.add(roundResult);
        turns++;
        model.addAttribute("rounds", rounds);
        model.addAttribute("WINNER", winner + " WINS!!!!!");

        return "redirect:/";
    }
}
