import processing.core.PApplet;

public class Knop {
    private final PApplet app;
    private float x;
    private float y;
    private float breedte;
    private float hoogte;
    private int knopNummer;
    private int tekstGrootte;
    private String tekst;

    public Knop(PApplet app, float x, float y, float breedte, float hoogte, String tekst, int knopNummer, int tekstGrootte) {
        this.app = app;
        this.x = x;
        this.y = y;
        this.breedte = breedte;
        this.hoogte = hoogte;
        this.tekst = tekst;
        this.knopNummer = knopNummer;
        this.tekstGrootte = tekstGrootte;
    }

    public void tekenKnop() {
        app.fill(255);
        app.rect(this.x, this.y, this.breedte, this.hoogte);
        app.textSize(tekstGrootte);
        app.textAlign(app.CENTER);
        app.fill(0);
        app.text(tekst, this.x + (this.breedte / 2), this.y + (this.hoogte / 2) + (this.hoogte / 10));
    }

    public boolean isMuisOverKnop() {
        if (app.mouseX > x && app.mouseX < x + breedte &&
                app.mouseY > y && app.mouseY < y + hoogte) {
            return true;
        }

        return false;
    }

    public void scroll(float getal) {
        this.y += -getal;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getBreedte() {
        return breedte;
    }

    public float getHoogte() {
        return hoogte;
    }

    public int getKnopNummer() {
        return knopNummer;
    }
}
