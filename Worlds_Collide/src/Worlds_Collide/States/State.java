package Worlds_Collide.States;

import java.awt.*;



public abstract class State {

    protected StateManager stateManager;



    public State( StateManager stateManager) {
        this.stateManager = stateManager;
    }


    public abstract void Update();

    public abstract void Draw(Graphics g);
}

