package lesson1.mvcTwelveStick;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TwelveStickGame model = new TwelveStickGame();
        GameView view = new GameView();
        GameController controller = new GameController(model, view);

        controller.playGame();
    }
}


class TwelveStickGame {
    private int sticks = 12;
    private boolean isPlayerOneTurn = true;

    public int getSticks() {
        return sticks;
    }

    public boolean isPlayerOneTurn() {
        return isPlayerOneTurn;
    }

    public boolean isGameOver() {
        return sticks <= 0;
    }

    public String getWinner() {
        return isPlayerOneTurn ? "Player 1" : "Player 2";
    }

    public boolean takeSticks(int numSticks) {
        if (numSticks < 1 || numSticks > 3 || numSticks > sticks) {
            return false; // Invalid move
        }
        sticks -= numSticks;
        isPlayerOneTurn = !isPlayerOneTurn; // Switch turns
        return true;
    }
}


class GameView {
    private Scanner scanner;

    public GameView() {
        scanner = new Scanner(System.in);
    }

    public void displayGameState(int sticks) {
        System.out.println("|".repeat(sticks));
    }

    public void displayTurn(boolean isPlayerOneTurn) {
        String player = isPlayerOneTurn ? "Player 1" : "Player 2";
        System.out.println(player + "'s turn. How many sticks will you take? (1-3): ");
    }

    public void displayInvalidMove() {
        System.out.println("Invalid move! Try again.");
    }

    public void displayWinner(String winner) {
        System.out.println(winner + " wins!");
    }

    public int getMoveInput() {
        return scanner.nextInt();
    }
}

class GameController {
    private TwelveStickGame model;
    private GameView view;

    public GameController(TwelveStickGame model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public void playGame() {
        while (!model.isGameOver()) {
            view.displayGameState(model.getSticks());
            view.displayTurn(model.isPlayerOneTurn());

            boolean validMove = false;
            while (!validMove) {
                int sticksToTake = view.getMoveInput();
                validMove = model.takeSticks(sticksToTake);
                if (!validMove) {
                    view.displayInvalidMove();
                }
            }
        }
        view.displayWinner(model.getWinner());
    }
}