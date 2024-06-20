package step03;

import processing.core.PApplet;

public class TresEnRatlla extends PApplet {

    // Variable de classe Tauler
    Tauler t;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step03.TresEnRatlla");
    }

    public void setup(){
        // Creació d'un Tauler de 3x3 de tota l'amplada de la finestra
        t = new Tauler(3, width);
    }

    public void draw(){
        background(200, 100, 100);

        // Dibuixa el tauler (i totes les seves caselles)
        t.display(this);
    }

    public void mousePressed(){
        t.casellaPitjada(this);
    }
}
