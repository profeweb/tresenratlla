package step08;

import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;

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

    // Imatges del símbols del joc
    PImage imgCreu, imgCercle;

    // Variable de classe SoundFile
    SoundFile soClick;

    Casella[] casellesBuides;

    Casella[] rootValues;
    int numRootValues = 0;

    enum PLAYER { ORDINADOR, HUMA};

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

        rootValues = new Casella[n*n];
        numRootValues = 0;
    }

    public void display(PApplet p5){
        for(int f = 0; f< caselles.length; f++){
            for(int c = 0; c< caselles[f].length; c++){
                caselles[f][c].display(p5, imgCreu, imgCercle);
            }
        }
    }

    public void actualitzaGuanyador(){
        hihaGuanyador = comprovaGuanyador();
        if(hihaGuanyador){
            guanyador = numTirades%2==0 ? 'X' : 'O';
        }
        if(numTirades == caselles.length*caselles.length || hihaGuanyador){
            finalPartida = true;
        }
    }

    public void casellaPitjada(PApplet p5){
        if(!finalPartida) {
            for (int f = 0; f < caselles.length; f++) {
                for (int c = 0; c < caselles[f].length; c++) {
                    if (caselles[f][c].estaDins(p5.mouseX, p5.mouseY) && caselles[f][c].valor == Casella.VALOR.BLANC) {
                        soClick.play();
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

    public boolean filaIguals(int f){
        boolean b = true;
        for(int c = 0; c< caselles[0].length-1; c++){
            b = b && (caselles[f][c].valor == caselles[f][c+1].valor);
        }
        return (b && caselles[f][0].valor!= Casella.VALOR.BLANC);
    }

    public boolean columnaIguals(int c){
        boolean b = true;
        for(int f = 0; f< caselles.length-1; f++){
            b = b && (caselles[f][c].valor== caselles[f+1][c].valor);
        }
        return b && (caselles[0][c].valor!= Casella.VALOR.BLANC);
    }

    public boolean diagonalAscIguals(){
        boolean b = true;
        int nc = caselles.length-1;
        for(int d=0; d<nc; d++){
            b = b && (caselles[nc-d][d].valor== caselles[nc-d-1][d+1].valor);
        }
        return b && (caselles[nc][0].valor!= Casella.VALOR.BLANC);
    }

    public boolean diagonalDescIguals(){
        boolean b = true;
        for(int d = 0; d< caselles.length-1; d++) {
            b = b && (caselles[d][d].valor == caselles[d+1][d+1].valor);
        }
        return b && (caselles[0][0].valor!= Casella.VALOR.BLANC);
    }

    // Setter per definir les imatges del joc del Tres en ratlla
    public void setImatges(PApplet p5){
        this.imgCreu   = p5.loadImage("creu.png");
        this.imgCercle = p5.loadImage("cercle.png");
    }

    // Setter per definir el so del click en el tauler del joc
    public void setSoClick(PApplet p5){
        this.soClick = new SoundFile(p5, "click.wav");
    }

    int numCaselles(){
        return caselles.length*caselles.length;
    }

    boolean enJoc() {
        if (guanyaOrdinador() || guanyaJugador()){
            return false;
        }
        else if (estaPlena()){
            return false;
        }
        else {
            return true;
        }
    }

    boolean guanyaOrdinador(){
        hihaGuanyador = comprovaGuanyador();
        return  hihaGuanyador && numTirades%2==0;
    }

    boolean guanyaJugador(){
        hihaGuanyador = comprovaGuanyador();
        return  hihaGuanyador && numTirades%2==1;
    }

    boolean estaPlena(){
        return numTirades == caselles.length*caselles.length;
    }

    Casella[] getCasellesBuides() {

        casellesBuides = new Casella[numCaselles() - numTirades];

        int numBuida = 0;
        for (int f = 0; f < caselles.length; f++) {
            for (int c = 0; c < caselles[f].length; c++) {
                if (caselles[f][c].valor == Casella.VALOR.BLANC){
                    casellesBuides[numBuida] = caselles[f][c];
                    numBuida++;
                }
            }
        }
        return casellesBuides;
    }

    public int returnMin(int[] list) {

        int min = Integer.MAX_VALUE;
        int index = Integer.MIN_VALUE;

        for (int i = 0; i < list.length; ++i) {
            if (list[i] < min) {
                min = list[i];
                index = i;
            }
        }
        return list[index];
    }

    int returnMax(int[] list) {
        int max = Integer.MIN_VALUE;
        int index = Integer.MIN_VALUE;
        for (int i = 0; i < list.length; ++i) {
            if (list[i] > max) {
                max = list[i];
                index = i;
            }
        }

        return list[index];
    }

    public void mou(Casella c, PLAYER player) {
        if(player==PLAYER.HUMA) {
            caselles[c.fila][c.columna].valor = Casella.VALOR.CERCLE;
        }
        else if(player==PLAYER.ORDINADOR) {
            caselles[c.fila][c.columna].valor = Casella.VALOR.CREU;
        }
    }

    int minimax(int depth, PLAYER player) {

        if (guanyaOrdinador()){ return +1; }
        if (guanyaJugador()){return -1; }

        Casella[] casellesBuides = getCasellesBuides();

        if (casellesBuides.length==0){ return 0; }

        int[] marcadors = new int[casellesBuides.length];
        int numMarcadors = 0;

        for (int i = 0; i < casellesBuides.length; i++) {

            Casella c = casellesBuides[i];

            if(c!=null) {

                if (player == PLAYER.ORDINADOR) { //X's turn select the highest from below minimax() call
                    mou(c, PLAYER.ORDINADOR);
                    int currentScore = minimax(depth + 1, PLAYER.HUMA);
                    marcadors[numMarcadors] = currentScore;
                    numMarcadors++;

                    if (depth == 0) {
                        c.setValorMiniMax(currentScore);
                        rootValues[numRootValues] = c;
                        numRootValues++;
                    }

                } else if (player == PLAYER.HUMA) {//O's turn select the lowest from below minimax() call
                    mou(c, PLAYER.HUMA);
                    marcadors[numMarcadors] = minimax(depth + 1, PLAYER.ORDINADOR);
                    numMarcadors++;
                }

                caselles[c.fila][c.columna].valor = Casella.VALOR.BLANC; //Reset this point
            }
        }

        if( player == PLAYER.ORDINADOR ){
            return returnMax(marcadors);
        }

        return returnMin(marcadors);
    }

    Casella getMillorMoviment() {

        int max = Integer.MIN_VALUE;
        int best = Integer.MIN_VALUE;

        for (int i = 0; i < numRootValues; ++i) {
            if (max < rootValues[i].valorMiniMax) {
                max = rootValues[i].valorMiniMax;
                best = i;
            }
        }

        return rootValues[best];
    }

    void callMinimax(int depth, PLAYER player){
        rootValues = new Casella[caselles.length*caselles.length];
        numRootValues = 0;
        minimax(depth, player);
    }
}
