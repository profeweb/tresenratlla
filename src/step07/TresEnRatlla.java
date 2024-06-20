package step07;

import processing.core.PApplet;

public class TresEnRatlla extends PApplet {

    // Tauler del joc
    Tauler t;

    // Dimensió del tauler
    final int N = 3;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step07.TresEnRatlla");
    }

    public void setup(){
        t = new Tauler(N, width);
        t.setImatges(this);
        t.setSoClick(this);
    }

    public void draw(){
        background(200, 100, 100);
        t.display(this);
        if(t.hihaGuanyador){
            textAlign(CENTER); textSize(24); fill(0);
            text("GUANYADOR " + t.guanyador, width/2, height/2 - 20);
        }
        if(t.finalPartida){
            textAlign(CENTER); textSize(24); fill(0);
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
            t = new Tauler(N, width);
        }
    }
}
