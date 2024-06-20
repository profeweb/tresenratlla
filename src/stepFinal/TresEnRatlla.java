package stepFinal;

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
        PApplet.main("stepFinal.TresEnRatlla");
    }

    public void setup(){
        t = new Tauler(N, width);
        t.setImatges(this);
        t.setSoClick(this);
    }

    public void draw(){

        background(200, 100, 100);
        t.display(this);

        if(t.enJoc()){

            if(t.numTirades%2==1) {
                t.cridadaMinimax(0, Tauler.PLAYER.ORDINADOR);
                t.mou(t.getMillorMoviment(), Tauler.PLAYER.ORDINADOR);
                t.numTirades++;
            }
        }
        else {
            if (t.hihaGuanyador) {
                textAlign(CENTER);
                textSize(24);
                fill(0);
                text("GUANYADOR " + t.guanyador, width / 2, height / 2 - 20);
            }
            if (t.finalPartida) {
                textAlign(CENTER);
                textSize(24);
                fill(0);
                text("FINAL PARTIDA", width / 2, height / 2 + 20);
            }
        }
    }

    public void mousePressed(){
        if(t.enJoc() && t.numTirades%2==0) {
            t.casellaPitjada(this);
            t.actualitzaGuanyador();
        }
    }

    public void keyPressed(){
        if(key=='r' || key=='R'){
            t = new Tauler(N, width);
            t.setImatges(this);
            t.setSoClick(this);
        }
    }
}
