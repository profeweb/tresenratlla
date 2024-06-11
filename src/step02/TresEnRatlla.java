package step02;

import processing.core.PApplet;

public class TresEnRatlla extends PApplet {

    Casella c1, c2, c3;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step02.TresEnRatlla");
    }

    public void setup(){

        c1 = new Casella(0, 0, 100, 100, 200);
        c1.setValor(Casella.VALOR.CERCLE);

        c2 = new Casella(1, 2, 400, 100, 200);
        c2.setValor(Casella.VALOR.CREU);

        c3 = new Casella(2, 0, 100, 400, 200);
    }

    public void draw(){
        background(200, 100, 100);
        c1.display(this);
        c2.display(this);
        c3.display(this);
    }
}
