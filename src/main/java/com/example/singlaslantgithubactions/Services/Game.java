package com.example.singlaslantgithubactions.Services;

import com.example.singlaslantgithubactions.Model.CoinFlip;
import com.example.singlaslantgithubactions.Model.RoundResult;
import org.springframework.stereotype.Component;

@Component
public class Game {
    private final CoinFlip coinFlip;

    //@Autowired
    public Game(CoinFlip coinFlip) {
        this.coinFlip = coinFlip;
    }

    public RoundResult playGame(String choice, double resultAsDouble) {
        String result = flipCoin(resultAsDouble);
        String computerChoice = calculateComputerChoice(choice);
        String winner = calculateWinner(choice, result);

        updateCoinFlip(winner);

        return new RoundResult(choice, computerChoice, winner);
    }

    protected String flipCoin(double randomNumber) {
        return randomNumber > 0.5 ? "heads" : "tails";
    }

    protected String calculateComputerChoice(String choice) {
        return choice.equals("heads") ? "tails" : "heads";
    }

    protected String calculateWinner(String choice, String result) {
        if (choice.equals(result)) {

            return "User";
        } else {
            return "Computer";
        }
    }

    protected void updateCoinFlip(String winner) {
        switch (winner) {
            case "User":
                coinFlip.setUserScore(coinFlip.getUserScore() + 1);
                break;
            case "Computer":
                coinFlip.setComputerScore(coinFlip.getComputerScore() + 1);
                break;
            default:
                return;
        }
        coinFlip.setTurns(coinFlip.getTurns() + 1);
    }

    public CoinFlip getCoinFlip() {
        return coinFlip;
    }

}
