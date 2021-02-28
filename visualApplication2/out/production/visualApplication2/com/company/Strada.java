package com.company;

import javax.swing.*;
import java.awt.Image;

public class Strada {
    private static Image image;
    private static int x = 0;
    private static int y = -30;

    public static void loadImage() {
        ImageIcon ii = new ImageIcon("src/com/company/strada.png");
        image = ii.getImage();
    }
    public static Image getImage() {
        return image;
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static void incX(int amount)
    {
        x-=amount;
    }

}
