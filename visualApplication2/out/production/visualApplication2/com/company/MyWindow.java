package com.company;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class MyWindow extends JPanel implements KeyListener {

    private final int DIMX;
    private final int DIMY;
    private Car player;
    private String parola;
    private char currentChar;
    private int indexOfchar;
    private long frameNumber;

    public MyWindow()
    {
        this.DIMX = 640;
        this.DIMY = 360;
        this.player = new Car();
        this.parola = WordsDictionary.getSingleWord();
        this.indexOfchar = 0;
        this.currentChar = parola.charAt(indexOfchar);
        this.frameNumber = 0;
        finestradigioco();
    }

    /*
    static class MKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {

            char ch = event.getKeyChar();

            if (ch ==  ) {

                System.out.println(event.getKeyChar());

            }

            if (event.getKeyCode() == KeyEvent.VK_HOME) {

                System.out.println("Key codes: " + event.getKeyCode());

            }
        }
    }
    */

    public void paint(Graphics g) {
        super.paint(g);

        Lettera x = new Lettera(currentChar);
        x.loadImage();

        String speed = Double.toString(player.getSpeed());
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(Background.getImage(),0,-30,this);

        g2d.drawString(speed,10,10);

        g2d.drawImage(x.getImage(),x.getX(),x.getY()+20, this);
        g2d.drawImage(Strada.getImage(),Strada.getX(),Strada.getY(),this);
        g2d.drawImage(player.getImage(),player.getX(),player.getY(),this);
        g2d.drawImage(Nuvole.getImage(), Nuvole.getX(),Nuvole.getY(),this);
        printWord(g);
    }

    public void actions()
    {
        if(frameNumber %5==0)
            player.slowDown(1);
        frameNumber++;
        Strada.incX((int) player.getSpeed());
        if(player.getSpeed()<10 && player.getSpeed()>0)
            Nuvole.incX(1);
        else Nuvole.incX((int) player.getSpeed()/10);
    }

    private void finestradigioco()
    {
        JFrame frame = new JFrame("Need For Speedball");
        frame.add(this);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        frame.setSize(DIMX, DIMY);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(DIMX,DIMY);
        frame.setResizable(false);
        Sound.mainwrapper();
        /*
        try {
            setBackground(frame);
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
        while (true) {
            actions();
            this.repaint();
            try {
                Thread.sleep(32);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public char getCurrentChar() {
        return currentChar;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("caco");
        System.out.println((char)(e.getKeyCode()+32));

        if((char)(e.getKeyCode()+32) == currentChar)
        {
            System.out.println("entro");
            if(indexOfchar < parola.length()-1)
            {
                indexOfchar++;
                currentChar = parola.charAt(indexOfchar);
                player.accelerate(3);
            }
            else
            {
                indexOfchar = 0;
                parola = WordsDictionary.getSingleWord();
                currentChar = parola.charAt(indexOfchar);
            }
        }
    }

    private void printWord(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i < parola.length();i++)
        {
            Lettera x = new Lettera(parola.charAt(i));
            x.loadImage();
            g2d.drawImage(x.getImage(),x.getX()+(i*15),x.getY(),this);
        }
    }

}
