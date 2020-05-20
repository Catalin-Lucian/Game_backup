package PaooGame;

import PaooGame.Graphics.Camera;
import PaooGame.Input.MouseHandler;

import PaooGame.Input.KeyHandler;
import PaooGame.Items.Hero;
import PaooGame.Maps.Map;

import java.awt.*;


public class RefLinks
{
    private static Game game;              /*!< Referinta catre obiectul Game.*/
    private static Map map;                /*!< Referinta catre harta curenta.*/
    private static Camera cam;
    private static Hero hero;


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

    public static void setHero(Hero hero) {
        RefLinks.hero = hero;
    }

    public static Hero getHero() {
        return hero;
    }

    public static float getHeroX(){
        return hero.getPos().getX()+Camera.getX_edge_left();
    }

    public static float getHeroY(){
        return hero.GetY();
    }

    public static  Rectangle getHeroBound(){
        Rectangle aux=new Rectangle(hero.getBounds());
        aux.x += Camera.getX_edge_left();
        return aux;
    }
    public static  Rectangle getHeroAttackBound(){
        Rectangle aux=new Rectangle(hero.getAttackBounds());
        aux.x += Camera.getX_edge_left();
        return aux;
    }


}
