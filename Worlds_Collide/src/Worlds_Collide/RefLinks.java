package Worlds_Collide;

import Worlds_Collide.Graphics.Camera;
import Worlds_Collide.Input.MouseHandler;

import Worlds_Collide.Input.KeyHandler;
import Worlds_Collide.Items.Player;
import Worlds_Collide.Maps.Map;
import Worlds_Collide.Maps.MapManager;
import Worlds_Collide.States.StateManager;

import javax.swing.*;
import java.awt.*;


public class RefLinks
{
    private static Game game;              /*!< Referinta catre obiectul Game.*/
    private static Map map;                /*!< Referinta catre harta curenta.*/
    private static MapManager mapManager;
    private static Camera cam;
    private static Player player;
    private static StateManager stateManager;


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

    public static void setPlayer(Player player) {
        RefLinks.player = player;
    }

    public static Player getPlayer() {
        return player;
    }

    public static float getPlayerX(){
        return player.getPos().getX()+Camera.getX_edge_left();
    }

    public static float getPlayerY(){
        return player.GetY();
    }

    public static  Rectangle getPlayerBound(){
        Rectangle aux=new Rectangle(player.getBounds());
        aux.x += Camera.getX_edge_left();
        return aux;
    }
    public static  Rectangle getPlayerAttackBound(){
        Rectangle aux=new Rectangle(player.getAttackBounds());
        aux.x += Camera.getX_edge_left();
        return aux;
    }

    public static JFrame getFrame(){
        return game.getFrame();
    }

    public static void setStateManager(StateManager s){
        stateManager=s;
    }

    public static StateManager getStateManager(){
        return stateManager;
    }

    public static MapManager getMapManager() {
        return mapManager;
    }

    public static void setMapManager(MapManager mapManager) {
        RefLinks.mapManager = mapManager;
    }

    public static int getQuality(){
        return stateManager.getQuality();
    }

    public static int getDifficulty(){
        return stateManager.getDifficulty()+1;
    }
}
