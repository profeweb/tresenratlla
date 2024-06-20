package step04;

import processing.core.PApplet;

public class TresEnRatlla extends PApplet {

    Tauler t;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step04.TresEnRatlla");
    }

    public void setup(){
        t = new Tauler(3, width);
    }

    public void draw(){
        background(200, 100, 100);
        t.display(this);
        if(t.hihaGuanyador){
            textAlign(CENTER); textSize(24); fill(0);
            text("GUANYADOR", width/2, height/2);
        }
    }

    public void mousePressed(){
        t.casellaPitjada(this);
        t.actualitzaGuanyador();

    }

    public void keyPressed(){
        if(key=='r' || key=='R'){
            t = new Tauler(3, width);
        }
    }
}
