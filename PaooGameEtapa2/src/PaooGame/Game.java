package PaooGame;

import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Input.KeyHandler;
import PaooGame.Input.MouseHandler;
import PaooGame.States.*;
import PaooGame.__Utils.Singleton;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game extends Singleton implements Runnable
{
    private GameWindow      wnd;
    private boolean         runState;
    private Thread          gameThread;
    private BufferStrategy  bs;

    private Graphics        g;

    private StateManager stateManager;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    private RefLinks refLink;




    private Game(String title, int width, int height)
    {
        wnd = new GameWindow(title, width, height);
        runState = false;
        keyHandler = new KeyHandler();
        mouseHandler=new MouseHandler();
    }

    public static Game getInstance(String title, int width, int height){
        if(instance == null)
            instance=new Game(title,width,height);
        return (Game) instance;
    }

    private void InitGame()
    {
        wnd.BuildGameWindow();
        wnd.GetCanvas().addKeyListener(keyHandler);
        wnd.GetCanvas().addMouseListener(mouseHandler);
        wnd.GetCanvas().addMouseMotionListener(mouseHandler);

        Assets.Init();


        refLink = new RefLinks(this);
        stateManager=new StateManager();


    }


    public void run()
    {
        InitGame();
        long oldTime = System.nanoTime();
        long currentTime;

        final int framesPerSecond   = 60;
        final double timeFrame      = 1000000000 / framesPerSecond;


        while (runState)
        {

            currentTime = System.nanoTime();


            if((currentTime - oldTime) > timeFrame)
            {
                Update();
                Draw();


                oldTime = currentTime;
            }
        }

    }


    public synchronized void StartGame()
    {
        if(!runState)
        {
            runState = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

    }


    public synchronized void StopGame()
    {
        if(runState)
        {
            runState = false;
            try
            {
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }

    }


    private void Update()
    {

        stateManager.update();
    }


    private void Draw()
    {
        bs = wnd.GetCanvas().getBufferStrategy();
        if(bs == null)
        {
            try
            {
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        stateManager.draw(g);

        bs.show();
        g.dispose();
    }

    public int GetWidth()
    {
        return wnd.GetWndWidth();
    }

    public int GetHeight()
    {
        return wnd.GetWndHeight();
    }

    public KeyHandler getKeyHandler(){
        return keyHandler;
    }

    public MouseHandler getMouseHandler(){
        return mouseHandler;
    }

    public JFrame getFrame(){
        return wnd.GetWndFrame();
    }
}

