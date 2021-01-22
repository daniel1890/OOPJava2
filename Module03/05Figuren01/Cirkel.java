import processing.core.PApplet;

public class Cirkel extends Vorm {
    private float diameter;

    public Cirkel(float x, float y, float diameter) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        zetStil();
    }

    public void tekenCirkel(PApplet p) {
        p.noStroke();
        p.fill(kleur);
        p.ellipse(x, y, diameter, diameter);
    }

}