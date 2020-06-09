package Worlds_Collide.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/// opens an image
public class ImageLoader
{
    /// path to image
    public static BufferedImage LoadImage(String path)
    {
        try
        {
            return ImageIO.read(ImageLoader.class.getResource(path));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

