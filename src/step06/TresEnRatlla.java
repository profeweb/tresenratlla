package step06;

import processing.core.PApplet;
import processing.core.PFont;

public class TresEnRatlla extends PApplet {

    Tauler t;

    // Fonts per mostrar textos
    PFont font1, font2;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step06.TresEnRatlla");
    }

    public void setup(){
        t = new Tauler(3, width);
        t.setImatges(this);
        t.setSoClick(this);

        // Carregar fonts
        font1 = createFont("Dimitri.ttf", 48);
        font2 = createFont("Platinum.ttf", 24);
    }

    public void draw(){
        background(200, 100, 100);
        t.display(this);
        if(t.hihaGuanyador){
            textAlign(CENTER); textSize(24); fill(0);
            textFont(font1);
            text("GUANYADOR " + t.guanyador, width/2, height/2 - 20);
        }
        if(t.finalPartida){
            textAlign(CENTER); textSize(24); fill(0);
            textFont(font2);
            text("FINAL PARTIDA", width/2, height/2 + 20);
        }
    }

    public void mousePressed(){
        t.casellaPitjada(this);
        t.actualitzaGuanyador();
        println("NUM TIRADES: "+ t.numTirades);
    }

    public void keyPressed(){
        if(key=='r' || key=='R'){
            t = new Tauler(3, width);
        }
    }
}
