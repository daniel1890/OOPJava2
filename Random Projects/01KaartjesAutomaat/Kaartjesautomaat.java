
import processing.core.PApplet;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class KaartjesAutomaat {
    private double[] geldBedragen;
    PApplet app;
    public ArrayList<Film> films;
    public ArrayList<Knop> knoppenFilms;
    public ArrayList<Knop> knoppenGeld;
    public ArrayList<Kaartje> kaartjes;
    public double dagtotaal;
    private double inworp;
    public int indexHuidigeFilm;
    boolean mouseLock;
    public boolean isFilmGekozen;

    public KaartjesAutomaat(PApplet app) {
        this.app = app;
        this.films = new ArrayList<>();
        this.knoppenFilms = new ArrayList<>();
        this.knoppenGeld = new ArrayList<>();
        this.kaartjes = new ArrayList<>();
        double[] geldBedragen = {0.05, 0.10, 0.20, 0.50, 1.00, 2.00, 5.00};
        this.geldBedragen = geldBedragen;
        dagtotaal = 0.00;
        this.inworp = 0;
        this.mouseLock = false;
        this.isFilmGekozen = false;
    }

    public void tekenSchermenAutomaat() {
        if(!isFilmGekozen) {
            kiesFilmScherm();
        }

        if (isFilmGekozen) {
            tekenSchermBetaalFilm();
        }
    }

    public void tekenSchermBetaalFilm() {
        resetBackground();
        tekenFilmTitel();
        voegBetaalKnoppenToe();
        tekenBetaalKnoppenGeld();
        tekenNogTeBetalenGeld();
        drukGeldKnop();
    }

    public void kiesFilmScherm() {
        resetBackground();
        tekenknoppenFilmsPerFilm();
        drukFilmKnop();
    }

    public String tekstMet0(double bedrag, DecimalFormat format){
        if (bedrag >= 1.00) {
            return "Nog te betalen bedrag: €" + (format.format(bedrag));
        } else if (bedrag < 1) {
            return "Nog te betalen bedrag: €0" + (format.format(bedrag));
        }

        return "";
    }

    public void tekenNogTeBetalenGeld() {
        double bedrag = films.get(indexHuidigeFilm).getPrijs() - inworp;
        DecimalFormat f = new DecimalFormat("##.00");
        String tekst = "Nog te betalen bedrag: €" + (f.format(bedrag));
        String testTekst = tekstMet0(bedrag, f);
        app.fill(255);
        app.textSize(32);
        app.text(testTekst, app.width / 2, app.height / 2);
    }

    private void tekenFilmTitel() {
        Film f = films.get(indexHuidigeFilm);
        app.fill(255);
        app.textSize(50);
        app.text(f.getNaam(), app.width / 2, 75);
    }

    private void voegBetaalKnoppenToe() {
        int nKnoppen = 7;
        for (int i = 0; i < nKnoppen; i++) {
            float knoppenBreedteTotaal = app.width - 200;
            float knopBreedte = knoppenBreedteTotaal / nKnoppen;
            String tekst = Double.toString(geldBedragen[i]);
            Knop kg = new Knop(app, ((app.width / 2) - knoppenBreedteTotaal / 2) + (i * (knopBreedte)), app.height / 4, knopBreedte, 50, tekst, i, 10);
            knoppenGeld.add(kg);
        }
    }

    private void tekenBetaalKnoppenGeld() {
        for (Knop kg : knoppenGeld){
            kg.tekenKnop();
        }
    }

    public String printKaartje() {
        Film f = films.get(indexHuidigeFilm);
        return "Film: " + f.getNaam() + ", prijs: " + f.getPrijs();
    }

    public void tekenknoppenFilmsPerFilm() {
        for (Knop p : knoppenFilms){
            p.tekenKnop();
        }
    }

    public void drukGeldKnop() {
        for (Knop k : knoppenGeld){
            if (app.mousePressed && k.isMuisOverKnop() && !mouseLock){
                werpGeldIn(geldBedragen[k.getKnopNummer()]);
                setMouseLock(true);
            }
        }
    }

    public void drukFilmKnop() {
        for (Knop p : knoppenFilms){
            if (app.mousePressed && p.isMuisOverKnop() && !mouseLock){
                this.indexHuidigeFilm = p.getKnopNummer();
                isFilmGekozen = true;
                System.out.println(indexHuidigeFilm);
            }
        }
    }

    public void setMouseLock(boolean mouseLock) {
        this.mouseLock = mouseLock;
    }

    public void kiesFilm(int i) {
        if (this.inworp > 0) {
            annuleerBetaling();
        }
        this.indexHuidigeFilm = i;
        System.out.println("Gekozen " + printKaartje());
    }

    public void voegFilmsToe(String naam, double prijs){
        Film f = new Film(naam, prijs);
        float knopHoogte = 100;
        Knop p = new Knop(app, app.width / 4, 50 + (knoppenFilms.size() * 120), app.width / 2, knopHoogte, f.getNaam(), knoppenFilms.size(), 32);
        films.add(f);
        knoppenFilms.add(p);
    }

    public void annuleerBetaling() {
        System.out.println("Betaling gestopt, geld terug: " + this.inworp);
        System.out.println("--------------------------------------------");
        this.inworp = 0;
    }

    public void werpGeldIn(double inworp) {
        Film f = films.get(indexHuidigeFilm);
        this.inworp += inworp;
        System.out.println("Ingeworpen: " + inworp + " , totaal ingeworpen: " + this.inworp);
        if (this.inworp >= f.getPrijs()) {
            verhoogDagTotaal();
            Kaartje k = new Kaartje(f.getNaam(), f.getPrijs());
            System.out.println(k.printKaartjeConsole());
            double wisselgeld = berekenWisselgeld();
            printTekst("Uw wisselgeld: ", wisselgeld);
            System.out.println("-----------------------------");
            this.inworp = 0;
        } else {
            double rest = f.getPrijs() - this.inworp;
            printTekst("Nog in te werpen: ", rest);

        }
    }


    public void verhoogDagTotaal() {
        Film f = films.get(indexHuidigeFilm);
        this.dagtotaal += f.getPrijs();
    }

    public void resetBackground() {
        app.background(0);
    }

    public void printTekst(String tekst, double waarde) {
        System.out.println(tekst + waarde);
    }

    private double berekenWisselgeld() {
        Film f = films.get(indexHuidigeFilm);
        return this.inworp - f.getPrijs();
    }

    public void leegAutomaat() {
        this.dagtotaal = 0;
    }

    public double getDagtotaal() {
        return dagtotaal;
    }

}
