package PaooGame.GameWindow;

import javax.swing.*;
import java.awt.*;


public class GameWindow
{
    private JFrame  wndFrame;           /*!< fereastra principala a jocului*/
    private String  wndTitle;           /*!< titlul ferestrei*/
    private int     wndWidth;           /*!< latimea ferestrei in pixeli*/
    private int     wndHeight;          /*!< inaltimea ferestrei in pixeli*/

    private Canvas  canvas;             /*!< "panza/tablou" in care se poate desena*/


    public GameWindow(String title, int width, int height){
        wndTitle    = title;
        wndWidth    = width;
        wndHeight   = height;
        wndFrame    = null;
    }

    public void BuildGameWindow()
    {

        if(wndFrame != null)
        {
            return;
        }

        wndFrame = new JFrame(wndTitle);
        wndFrame.setTitle("The_Island");
        wndFrame.setSize(wndWidth, wndHeight);
        wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wndFrame.setResizable(false);
        wndFrame.setLocationRelativeTo(null);



        /* ------------------------- set the canvas----------------*/
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(wndWidth, wndHeight));
        canvas.setMaximumSize(new Dimension(wndWidth, wndHeight));
        canvas.setMinimumSize(new Dimension(wndWidth, wndHeight));
        wndFrame.add(canvas);
        canvas.setFocusable(true);



        wndFrame.pack();
        wndFrame.setVisible(true);

    }

    public int GetWndWidth()
    {
        return wndWidth;
    }

    public int GetWndHeight()
    {
        return wndHeight;
    }

    public Canvas GetCanvas()
    {
        return canvas;
    }

    public JFrame GetWndFrame()
    {
        return wndFrame;
    }

}
