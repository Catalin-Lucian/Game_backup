package PaooGame;

import PaooGame.GameWindow.GameWindow;

public class Main
{
    public static void main(String[] args)
    {
        Game paooGame =Game.getInstance("PaooGame", 1280, 720);
        paooGame.StartGame();
    }
}
