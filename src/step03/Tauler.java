package step03;

import processing.core.PApplet;

public class Tauler {

    float midaCasella;
    Casella[][] caselles;

    public Tauler(int n, float w){
        caselles = new Casella[n][n];
        this.midaCasella = w / n;
        for(int f = 0; f< caselles.length; f++){
            for(int c = 0; c< caselles[f].length; c++){
                caselles[f][c] = new Casella(f, c, midaCasella *c, midaCasella *f, midaCasella);
            }
        }
    }

    public void display(PApplet p5){
        for(int f = 0; f< caselles.length; f++){
            for(int c = 0; c< caselles[f].length; c++){
                caselles[f][c].display(p5);
            }
        }
    }

    public void checkMousePressed(PApplet p5){
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
