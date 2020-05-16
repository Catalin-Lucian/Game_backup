package PaooGame.States;

import java.awt.*;

import PaooGame.Input.KeyHandler;
import PaooGame.Input.MouseHandler;
import PaooGame.RefLinks;


public abstract class State {

    private StateManager stateManager;


    public State( StateManager stateManager) {
        this.stateManager = stateManager;
    }

    /*public static void SetState(State state)
    {
        previousState = currentState;
        currentState = state;
    }

    public static State GetState()
    {
        return currentState;
    }*/

    public abstract void Update();

    public abstract void Draw(Graphics g);
}

