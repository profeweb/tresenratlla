package step04;

import processing.core.PApplet;

public class Tauler {

    float wc;
    Casella[][] tauler;
    boolean guanyador;

    public Tauler(int n, float w){
        tauler = new Casella[n][n];
        this.wc = w / n;
        guanyador = false;
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

    public void actualitzaGuanyador(){
        guanyador = hihaGuanyador();
    }

    public void checkMousePressed(PApplet p5){
        for(int f=0; f<tauler.length; f++){
            for(int c=0; c<tauler[f].length; c++){
                if(tauler[f][c].estaDins(p5.mouseX, p5.mouseY)){
                    if(p5.mouseButton == p5.LEFT) {
                        tauler[f][c].setValor(Casella.VALOR.CERCLE);
                    }
                    else if(p5.mouseButton == p5.RIGHT) {
                        tauler[f][c].setValor(Casella.VALOR.CREU);
                    }

                    break;
                }
            }
        }
    }

    public boolean filaIguals(int f){
        if (tauler[f][0].valor == tauler[f][1].valor && tauler[f][1].valor == tauler[f][2].valor && tauler[f][0].valor!= Casella.VALOR.BLANC){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean columnaIguals(int c){
        if (tauler[0][c].valor == tauler[1][c].valor && tauler[1][c].valor == tauler[2][c].valor && tauler[0][c].valor!= Casella.VALOR.BLANC){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean diagonalAscIguals(){
        if (tauler[2][0].valor == tauler[1][1].valor && tauler[1][1].valor == tauler[0][2].valor && tauler[2][0].valor!= Casella.VALOR.BLANC){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean diagonalDescIguals(){
        if (tauler[0][0].valor == tauler[1][1].valor && tauler[1][1].valor == tauler[2][2].valor && tauler[0][0].valor!= Casella.VALOR.BLANC){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hihaGuanyador(){

        for(int f=0; f<tauler.length; f++){
            if(filaIguals(f)== true){
                return true;
            }
        }

        for(int c=0; c<tauler[0].length; c++){
            if(columnaIguals(c)== true){
                return true;
            }
        }

        return diagonalAscIguals() || diagonalDescIguals();
    }

    public boolean filaIgualsOptim(int f){
        boolean b = true;
        for(int c=0; c<tauler[0].length-1; c++){
            b = b && (tauler[f][c].valor == tauler[f][c+1].valor);
        }
        return (b && tauler[f][0].valor!= Casella.VALOR.BLANC);
    }

    public boolean columnaIgualsOptim(int c){
        boolean b = true;
        for(int f=0; f<tauler.length-1; f++){
            b = b && (tauler[f][c].valor==tauler[f+1][c].valor);
        }
        return b && (tauler[0][c].valor!=Casella.VALOR.BLANC);
    }

    public boolean diagonalAscIgualsOptim(){
        boolean b = true;
        int nc = tauler.length-1;
        for(int d=0; d<nc; d++){
            b = b && (tauler[nc-d][d].valor==tauler[nc-d-1][d+1].valor);
        }
        return b && (tauler[nc][0].valor!=Casella.VALOR.BLANC);
    }

    public boolean diagonalDescIgualsOptim(){
        boolean b = true;
        for(int d=0; d<tauler.length-1; d++) {
            b = b && (tauler[d][d].valor == tauler[d+1][d+1].valor);
        }
        return b && (tauler[0][0].valor!=Casella.VALOR.BLANC);
    }
}
