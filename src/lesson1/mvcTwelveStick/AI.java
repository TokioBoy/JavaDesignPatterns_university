package lesson1.mvcTwelveStick;

interface InterfaceAI {
    int getSticks();
    boolean takeSticks(int numSticks);
}
public class AI implements InterfaceAI {
    private int sticks = 12;
    private boolean isPlayerOneTurn = true;

    public int getSticks() {
        return sticks;
    }
    public void randomAI() {
        int numSticks = (int) (Math.random() * 3) + 1;
        sticks -= numSticks;
        isPlayerOneTurn = !isPlayerOneTurn;
    }

    public boolean takeSticks(int numSticks) {
        if (numSticks < 1 || numSticks > 3 || numSticks > sticks) {
            return false;
        }
        sticks -= numSticks;
        isPlayerOneTurn = !isPlayerOneTurn;
        return true;
    }
}
