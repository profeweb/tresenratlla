package step05;

import processing.core.PApplet;

public class Tauler {

    float midaCasella;
    Casella[][] caselles;
    boolean hihaGuanyador;

    // Símbol del guanyador (X o O)
    char guanyador;

    // Partida finalitzada
    boolean finalPartida;

    // Número de tirades
    int numTirades;

    public Tauler(int n, float w){
        caselles = new Casella[n][n];
        this.midaCasella = w / n;
        hihaGuanyador = false;
        guanyador = ' ';
        finalPartida = false;
        numTirades = 0;

        for(int f = 0; f< caselles.length; f++){
            for(int c = 0; c< caselles[f].length; c++){
                caselles[f][c] = new Casella(f, c, midaCasella *c, midaCasella *f, midaCasella);
                caselles[f][c].setValor(Casella.VALOR.BLANC);
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

    public void actualitzaGuanyador(){
        hihaGuanyador = comprovaGuanyador();
        if(hihaGuanyador){
            guanyador = numTirades%2==0 ? 'X' : 'O';
        }
        if(numTirades == 9 || hihaGuanyador){
            finalPartida = true;
        }
    }

    public void casellaPitjada(PApplet p5){
        if(!finalPartida) {
            for (int f = 0; f < caselles.length; f++) {
                for (int c = 0; c < caselles[f].length; c++) {
                    if (caselles[f][c].estaDins(p5.mouseX, p5.mouseY) && caselles[f][c].valor == Casella.VALOR.BLANC) {
                        if (numTirades%2==0) {
                            caselles[f][c].setValor(Casella.VALOR.CERCLE);
                        } else {
                            caselles[f][c].setValor(Casella.VALOR.CREU);
                        }
                        numTirades++;
                        break;
                    }
                }
            }
        }
    }

    public boolean filaIguals(int f){
        if (caselles[f][0].valor == caselles[f][1].valor && caselles[f][1].valor == caselles[f][2].valor && caselles[f][0].valor!= Casella.VALOR.BLANC){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean columnaIguals(int c){
        if (caselles[0][c].valor == caselles[1][c].valor && caselles[1][c].valor == caselles[2][c].valor && caselles[0][c].valor!= Casella.VALOR.BLANC){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean diagonalAscIguals(){
        if (caselles[2][0].valor == caselles[1][1].valor && caselles[1][1].valor == caselles[0][2].valor && caselles[2][0].valor!= Casella.VALOR.BLANC){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean diagonalDescIguals(){
        if (caselles[0][0].valor == caselles[1][1].valor && caselles[1][1].valor == caselles[2][2].valor && caselles[0][0].valor!= Casella.VALOR.BLANC){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean comprovaGuanyador(){

        for(int f = 0; f< caselles.length; f++){
            if(filaIguals(f)== true){
                return true;
            }
        }

        for(int c = 0; c< caselles[0].length; c++){
            if(columnaIguals(c)== true){
                return true;
            }
        }

        return diagonalAscIguals() || diagonalDescIguals();
    }

    public boolean filaIgualsOptim(int f){
        boolean b = true;
        for(int c = 0; c< caselles[0].length-1; c++){
            b = b && (caselles[f][c].valor == caselles[f][c+1].valor);
        }
        return (b && caselles[f][0].valor!= Casella.VALOR.BLANC);
    }

    public boolean columnaIgualsOptim(int c){
        boolean b = true;
        for(int f = 0; f< caselles.length-1; f++){
            b = b && (caselles[f][c].valor== caselles[f+1][c].valor);
        }
        return b && (caselles[0][c].valor!= Casella.VALOR.BLANC);
    }

    public boolean diagonalAscIgualsOptim(){
        boolean b = true;
        int nc = caselles.length-1;
        for(int d=0; d<nc; d++){
            b = b && (caselles[nc-d][d].valor== caselles[nc-d-1][d+1].valor);
        }
        return b && (caselles[nc][0].valor!= Casella.VALOR.BLANC);
    }

    public boolean diagonalDescIgualsOptim(){
        boolean b = true;
        for(int d = 0; d< caselles.length-1; d++) {
            b = b && (caselles[d][d].valor == caselles[d+1][d+1].valor);
        }
        return b && (caselles[0][0].valor!= Casella.VALOR.BLANC);
    }
}
