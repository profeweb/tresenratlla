package step03;

import processing.core.PApplet;

public class Casella {

    public enum VALOR { BLANC, CREU, CERCLE };

    int fila, columna;
    VALOR valor;

    float x, y, w;

    public Casella(int f, int c, float x, float y, float w){
        this.fila = f;
        this.columna = c;
        this.valor = VALOR.BLANC;
        this.x = x;
        this.y = y;
        this.w = w;
    }

    public void setValor(VALOR v){
        this.valor = v;
    }

    public void display(PApplet p5){
        p5.pushStyle();
        p5.rectMode(p5.CORNER);
        p5.fill(255);
        if(estaDins(p5.mouseX, p5.mouseY)){
            p5.fill(200);
        }
        p5.rect(x, y, w, w);
        if(valor== VALOR.CREU){
            p5.line(x, y, x+w, y+w);
            p5.line(x, y+w, x+w, y);
        }
        else if(valor== VALOR.CERCLE){
            p5.ellipse(x + w/2, y + w/2, w/2, w/2);
        }
        p5.fill(0);
        p5.textAlign(p5.CENTER); p5.textSize(18);
        p5.text(this.fila+", "+this.columna, x+this.w/2, y + 20);
        p5.popStyle();
    }

    public boolean estaDins(float x, float y){
        return (this.x<=x && x<=this.x+w && this.y<=y && y<=this.y+w);
    }

}