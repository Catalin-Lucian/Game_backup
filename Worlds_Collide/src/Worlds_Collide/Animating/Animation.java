package Worlds_Collide.Animating;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] frames; ///< all frames
    private int currentFrame; ///< current displayed frame
    private int nrFrames;   ///< all numbers of frames

    private int count;
    private int delay;  ///< animation speed

    private int timesPlayed; ///<number of repeats

    /// constructor with a vector of images
    public Animation(BufferedImage[] images){
        timesPlayed=0;
        setFrames(images);
    }

    public Animation(){
        timesPlayed=0;
    } ///< constructor with no parameters

    ///<sets a new animation
    public void setFrames(BufferedImage[] iamges){
        this.frames=iamges;
        currentFrame=0;
        count=0;
        timesPlayed=0;
        delay=4;
        nrFrames=iamges.length;
    }

    public void setDelay(int i){ delay=i; }///< sets speed
    public void setFrame(int i){ currentFrame=i;}///< set currents frame to display
    public void setNrFrames(int i){ nrFrames=i; }///< set the numver of frames

    ///<changes the frame if count has reached the delay
    public void update(){
        if (delay==-1) return ;
        count++;
        if(count==delay){
            currentFrame++;
            count=0;
        }

        if (currentFrame == nrFrames){
            currentFrame=0;
            timesPlayed++;
        }
    }

    public int getDelay(){return delay;}
    public int getFrame(){return currentFrame;}
    public int getCount(){return count;}
    public int getNrFrames(){return nrFrames; }
    public BufferedImage getImage() { return frames[currentFrame]; }
    public boolean hasPlayedOnce(){ return timesPlayed >0; }
    public boolean hasPlayed(int i){ return timesPlayed == i;}
    public boolean onLastFrame(){
        return currentFrame==nrFrames-1;
    }///< return true if is on last frame


}
