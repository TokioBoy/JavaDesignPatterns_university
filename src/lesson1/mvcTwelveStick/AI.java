package lesson1.mvcTwelveStick;

import java.util.Objects;
import java.util.Scanner;

interface InterfaceAI {
    int decreaseSticksFromGame();
}
public class AI implements InterfaceAI {
    private int sticks = 12;
    private boolean isPlayerOneTurn = true;

    public void randomAI() {
        if(sticks == 3){
            sticks -= 2;
        } else if(sticks == 2){
            sticks -= 1;
        } else {
            int numSticks = (int) (Math.random() * 3) + 1;
            sticks -= numSticks;
        }
        isPlayerOneTurn = !isPlayerOneTurn;
    }
    public int decreaseSticksFromGame(){
        randomAI();
        return sticks;
    }

    public void aiPLay() {
        decreaseSticksFromGame();
    }

    public void enableAI(){
        System.out.println("Wanna enable AI? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String aiScanner = scanner.next();
        if (Objects.equals(aiScanner, "y")) {
            aiPLay();
        } else if (Objects.equals(aiScanner, "n")) {
            System.out.println("AI is disabled");
        } else {
            System.out.println("Invalid input, ai is disabled");
        }
    }
}
