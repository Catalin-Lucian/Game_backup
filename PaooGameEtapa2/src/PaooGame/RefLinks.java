package PaooGame;

import PaooGame.Graphics.Camera;
import PaooGame.Input.MouseHandler;

import PaooGame.Input.KeyHandler;
import PaooGame.Maps.Map;


public class RefLinks
{
    private static Game game;              /*!< Referinta catre obiectul Game.*/
    private static Map map;                /*!< Referinta catre harta curenta.*/
    private static Camera cam;


    public RefLinks(Game game)
    {
        RefLinks.game = game;
    }


    public static KeyHandler GetKeyHandler()
    {
        return game.getKeyHandler();
    }

    public static  MouseHandler GetMouseHandler(){
        return game.getMouseHandler();
    }

    public  static int GetWidth()
    {
        return game.GetWidth();
    }

    public  static int GetHeight()
    {
        return game.GetHeight();
    }

    public static   Game GetGame()
    {
        return game;
    }

    public  static void SetGame(Game game)
    {
        RefLinks.game = game;
    }

    public static Map GetMap()
    {
        return map;
    }

    public static void SetMap(Map map)
    {
        RefLinks.map = map;
    }

    public static Camera GetCam() {
        return cam;
    }

    public static void SetCam(Camera cam) {
        RefLinks.cam = cam;
    }
}
