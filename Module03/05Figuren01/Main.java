import processing.core.PApplet;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main(new String[] {"Main"} );
    }

    public void settings() {
        this.size(600, 600);
    }

    Cirkel cirkel = new Cirkel(100, 200, 100);
    Rechthoek rechthoek = new Rechthoek(0, 400, 100, 100);

    public void setup() {
        cirkel.setKleur(255);
        cirkel.setSnelheid(5, 0);
        cirkel.setVersnelling(1, 0);

        rechthoek.setKleur(255);
        rechthoek.setSnelheid(5, 0);
        rechthoek.setVersnelling(1, 0);
    }

    public void draw() {
        background(0);

        cirkel.tekenCirkel(this);
        rechthoek.tekenRechthoek(this);
    }

    public void mouseClicked() {
        cirkel.doeStap();
        cirkel.setOnzichtbaar();
        System.out.println(cirkel.isOnzichtbaar());

        rechthoek.doeStap();
        rechthoek.setOnzichtbaar();
        System.out.println(rechthoek.isOnzichtbaar());
    }
}