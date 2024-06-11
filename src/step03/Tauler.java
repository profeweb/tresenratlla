package step03;

import processing.core.PApplet;

public class Tauler {

    float wc;
    Casella[][] tauler;

    public Tauler(int n, float w){
        tauler = new Casella[n][n];
        this.wc = w / n;
        for(int f=0; f<tauler.length; f++){
            for(int c=0; c<tauler[f].length; c++){
                tauler[f][c] = new Casella(f, c, wc*c, wc*f, wc);
            }
        }
    }

    public void display(PApplet p5){
        for(int f=0; f<tauler.length; f++){
            for(int c=0; c<tauler[f].length; c++){
                tauler[f][c].display(p5);
            }
        }
    }

    public void checkMousePressed(PApplet p5){
        for(int f=0; f<tauler.length; f++){
            for(int c=0; c<tauler[f].length; c++){
                if(tauler[f][c].estaDins(p5.mouseX, p5.mouseY)){
                    tauler[f][c].setValor(Casella.VALOR.CERCLE);
                    break;
                }
            }
        }
    }
}
