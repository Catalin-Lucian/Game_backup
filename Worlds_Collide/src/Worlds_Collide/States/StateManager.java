package Worlds_Collide.States;


import Worlds_Collide.GUI.BType;
import Worlds_Collide.RefLinks;
import Worlds_Collide.__Utils.DataBase;

import java.awt.*;

public class StateManager {


    public DataBase dataBase;
    protected  State currentState;
    protected  State lastState;

    public  int quality; //
    public  int difficulty;


    public StateManager (){
        currentState=new MenuState(this);
        RefLinks.setStateManager(this);
        dataBase=new DataBase();

        quality=dataBase.getData("QUALITY","SETTINGS");
        difficulty=dataBase.getData("DIFFICULTY","SETTINGS");

    }

    public void update(){
       currentState.Update();
    }

    public void draw(Graphics g){
        currentState.Draw(g);
    }

    public void Return(){
        State aux=lastState;
        lastState=currentState;
        currentState=aux;
    }

    public void selectState(BType type){
        lastState=currentState;
        switch (type){
            case MENU:
                currentState=new MenuState(this);break;
            case PLAY:
                currentState=new PlayState(this);break;
            case LOAD:
                currentState=new PlayState(this,true);break;
            case SETTINGS:
                currentState=new SettingsState(this);break;
            case ABOUT:
                currentState=new AboutState(this);break;
            case PAUSE:
                currentState=new PauseState(this);break;
            case EXIT:
                dataBase.updateSettings(quality,difficulty);
                RefLinks.GetGame().StopGame();


        }
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getQuality() {
        return quality;
    }
}
