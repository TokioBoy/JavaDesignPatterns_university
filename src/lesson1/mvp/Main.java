package lesson1.mvp;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Presenter presenter = new Presenter();
        presenter.start();
    }
}

class View {
    Presenter presenter;

    View(Presenter presenter) {
        this.presenter = presenter;
    }

    void showSticks() {
        System.out.println("Current number of sticks:");
        System.out.println("|".repeat(presenter.getNumberOfRemainingSticks()));
    }

    void promptPlayer(int player) {
        System.out.println("Player " + player + ", how many sticks do you want to remove (1, 2 or 3)?");
    }

    void displayWinner(String winner) {
        System.out.println("Game over! The winner is: " + winner);
    }
}

class Presenter {
    Model model = new Model();
    View view = new View(this);
    Scanner scanner = new Scanner(System.in);

    void listen() {
        int sticks;
        while (true) {
            sticks = scanner.nextInt();
            if (sticks >= 1 && sticks <= 3 && sticks <= model.getNumOfRemainingSticks()) {
                break;
            }
            System.out.println("Invalid input. Please enter a number between 1 and 3, and less than or equal to the remaining sticks.");
        }
        model.removeSticks(sticks);
        model.switchPlayer();
    }

    void start() {
        while (!model.isGameOver()) {
            view.showSticks();
            view.promptPlayer(model.getCurrentPlayer());
            listen();
        }
        view.displayWinner(model.getWinner());
    }

    int getNumberOfRemainingSticks() {
        return model.getNumOfRemainingSticks();
    }
}

class Model {
    private int numOfSticks = 12;
    private int currentPlayer = 1;

    int getNumOfRemainingSticks() {
        return numOfSticks;
    }

    boolean isGameOver() {
        return numOfSticks == 0;
    }

    String getWinner() {
        return currentPlayer == 1 ? "Player 1" : "Player 2";
    }

    void removeSticks(int sticks) {
        numOfSticks -= sticks;
    }

    void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    int getCurrentPlayer() {
        return currentPlayer;
    }
}
