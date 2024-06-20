package step02;

import processing.core.PApplet;

public class Casella {

    // Enumerat amb els valors que es podran guardar en les caselles del tauler
    public enum VALOR { BLANC, CREU, CERCLE };
    // Valor guardat de la casella
    VALOR valor;

    // Números de fila i columna que ocupa la casella en el tauler
    int fila, columna;


    // Coordenades x,y i mida de la casella.
    int x, y, w;

    // Constructor
    public Casella(int f, int c, int x, int y, int w){
        this.fila = f;
        this.columna = c;
        this.valor = VALOR.BLANC;
        this.x = x;
        this.y = y;
        this.w = w;
    }

    // Setter per a la propietat valor
    public void setValor(VALOR v){
        this.valor = v;
    }

    // Mètode per dibuixar la casella (emprant processing)
    public void display(PApplet p5){
        p5.pushStyle();
        p5.rectMode(p5.CORNER);
        p5.fill(255);
        if(estaDins(p5.mouseX, p5.mouseY)){
            p5.fill(200);
        }
        p5.rect(x, y, w, w);
        if(valor==VALOR.CREU){
            p5.line(x, y, x+w, y+w);
            p5.line(x, y+w, x+w, y);
        }
        else if(valor==VALOR.CERCLE){
            p5.ellipse(x + w/2, y + w/2, w/2, w/2);
        }
        p5.fill(0);
        p5.textAlign(p5.CENTER); p5.textSize(18);
        p5.text(this.fila+", "+this.columna, x+this.w/2, y + 20);
        p5.popStyle();
    }

    // Mètode que indica si el punt (x, y) es troba dins la casella (quadrada)
    public boolean estaDins(float x, float y){
        return (this.x<=x && x<=this.x+w && this.y<=y && y<=this.y+w);
    }

}