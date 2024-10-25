package lesson1.mvcTwelveStick;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AI ai = new AI();
        TwelveStickGame model = new TwelveStickGame(ai);
        GameView view = new GameView(model);
        GameController controller = new GameController(model, view);

        controller.playGame();
    }
}

interface GameOverChecker{
    boolean isGameOver();
    String getWinner();
}
interface StickManipulator{
    int getSticks();
    boolean takeSticks(int numSticks);
}
interface PlayerTurn{
    boolean isPlayerOneTurn();
}

interface LanguageSelector{
    void languageSelector();
}

interface AIEnableFunction{
    void aiEnableFunction();
}
interface Game extends GameOverChecker, StickManipulator, PlayerTurn, LanguageSelector, AIEnableFunction {
    void aiEnableFunction();
    int getSticks();
    boolean isPlayerOneTurn();
    boolean isGameOver();
    String getWinner();
    boolean takeSticks(int numSticks);
}

class TwelveStickGame implements Game {
    private final AI ai;
    private int sticks = 12;
    private boolean isPlayerOneTurn = true;
    private boolean aiEnabled = false;

    String [][] dict= {{"Player 1","Player 2","'s turn. How many sticks will you take? (1-3): ","'s turn. How many sticks will you take? (1-3): ","Invalid move! Try again."," wins!"},
                    {"Jugador 1","Jugador 2"," ¿Cuántos palos tomarás? (1-3): "," ¿Cuántos palos tomarás? (1-3): ","¡Movimiento inválido! Inténtalo de nuevo."," gana!"}};

    TwelveStickGame(AI ai) {
        this.ai = ai;
    }

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

    public void languageSelector(){
        Scanner scanner = new Scanner(System.in);
        int language = scanner.nextInt();
        if(language == 2){
            dict[0][0] = dict[1][0];
            dict[0][1] = dict[1][1];
            dict[0][2] = dict[1][2];
            dict[0][3] = dict[1][3];
            dict[0][4] = dict[1][4];
            dict[0][5] = dict[1][5];
        }
    }

    public void aiEnableFunction(){
        ai.enableAI();
    }
}
interface InformationDisplayer{
    void displayLanguageChoice();
    void displayGameState(int sticks);
    void displayTurn(boolean isPlayerOneTurn);
    void displayInvalidMove();
    void displayWinner(String winner);

}
interface InformationGetterForView {
    int getMoveInput();
}

interface GameViewInterface extends InformationDisplayer, InformationGetterForView {
    void displayLanguageChoice();
    void displayGameState(int sticks);
    void displayTurn(boolean isPlayerOneTurn);
    void displayInvalidMove();
    void displayWinner(String winner);
    int getMoveInput();
}
class GameView implements GameViewInterface {
    private final TwelveStickGame model;
    private final Scanner scanner;

    public GameView(TwelveStickGame model) {
        this.model = model;
        scanner = new Scanner(System.in);
    }

    public void displayGameState(int sticks) {
        System.out.println("|".repeat(sticks));
    }

    public void displayTurn(boolean isPlayerOneTurn) {
        String player = isPlayerOneTurn ? model.dict[0][0] : model.dict[0][1];
        System.out.println(player + model.dict[0][2]);
    }

    public void displayInvalidMove() {
        System.out.println(model.dict[0][4]);
    }

    public void displayWinner(String winner) {
        System.out.println(winner + model.dict[0][5]);
    }

    public int getMoveInput() {
        return scanner.nextInt();
    }

    public void displayLanguageChoice() {
        System.out.println("Choose language: 1. English 2. Español");
    }
}
interface GamePlay{
    void playGame();
}

interface LanguageSelectorForController{
    void languageSelectorForController();
}

interface GameControllerInterface extends GamePlay {
    void playGame();
}

class GameController implements GameControllerInterface {
    private final TwelveStickGame model;
    private final GameView view;

    public GameController(TwelveStickGame model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public void playGame() {
        model.aiEnableFunction();
        languageSelectorForController();
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

    public void languageSelectorForController(){
        view.displayLanguageChoice();
        model.languageSelector();
    }
}