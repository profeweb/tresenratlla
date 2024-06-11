package step03;

import processing.core.PApplet;
import step02.Casella;

public class TresEnRatlla extends PApplet {

    Tauler t;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step03.TresEnRatlla");
    }

    public void setup(){
        t = new Tauler(3, width);
    }

    public void draw(){
        background(200, 100, 100);
        t.display(this);
    }

    public void mousePressed(){
        t.checkMousePressed(this);
    }
}
