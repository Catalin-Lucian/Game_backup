package PaooGame.Animating;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] frames;
    private int currentFrame;
    private int nrFrames;

    private int count;
    private int delay;

    private int timesPlayed;

    public Animation(BufferedImage[] images){
        timesPlayed=0;
        setFrames(images);
    }

    public Animation(){
        timesPlayed=0;
    }

    public void setFrames(BufferedImage[] iamges){
        this.frames=iamges;
        currentFrame=0;
        count=0;
        timesPlayed=0;
        delay=4;
        nrFrames=iamges.length;
    }

    public void setDelay(int i){ delay=i; }
    public void setFrame(int i){ currentFrame=i;}
    public void setNrFrames(int i){ nrFrames=i; }

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
    }


}
