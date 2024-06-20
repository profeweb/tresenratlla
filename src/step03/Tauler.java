package step03;

import processing.core.PApplet;

public class Tauler {

    // Dimensions de les caselles del tauler
    float midaCasella;

    // Array bidimensional de caselles
    Casella[][] caselles;

    // Constructor
    public Tauler(int n, float w){
        caselles = new Casella[n][n];
        this.midaCasella = w / n;
        for(int f = 0; f< caselles.length; f++){
            for(int c = 0; c< caselles[f].length; c++){
                caselles[f][c] = new Casella(f, c, midaCasella *c, midaCasella *f, midaCasella);
            }
        }
    }

    // Dibuixa el tauler del joc
    public void display(PApplet p5){
        for(int f = 0; f< caselles.length; f++){
            for(int c = 0; c< caselles[f].length; c++){
                caselles[f][c].display(p5);
            }
        }
    }

    // Comprova si clicam sobre alguna casella del tauler
    public void casellaPitjada(PApplet p5){
        for(int f = 0; f< caselles.length; f++){
            for(int c = 0; c< caselles[f].length; c++){
                if(caselles[f][c].estaDins(p5.mouseX, p5.mouseY)){
                    caselles[f][c].setValor(Casella.VALOR.CERCLE);
                    break;
                }
            }
        }
    }
}
