import processing.core.PApplet;

import java.text.DecimalFormat;

import java.util.ArrayList;

public class KaartjesAutomaat {
    PApplet app;
    public ArrayList<Film> films;
    public ArrayList<Knop> knoppenFilms;
    public ArrayList<Knop> knoppenGeld;
    private ArrayList<Knop> scrollKnoppenFilms;
    public ArrayList<Kaartje> kaartjes;
    private double[] geldBedragen;
    private Knop terugKnop;
    private Knop kaartjeKnop;
    private DecimalFormat format;
    public double dagtotaal;
    private double inworp;
    private double wisselgeld;
    public int indexHuidigeFilm;
    public boolean mouseLock;
    private boolean tekenDrukKnopKaartje;
    private boolean printKaartje;
    public boolean isFilmGekozen;
    public float scrollLock;

    public KaartjesAutomaat(PApplet app) {
        this.app = app;
        this.films = new ArrayList<>();
        this.knoppenFilms = new ArrayList<>();
        this.knoppenGeld = new ArrayList<>();
        this.kaartjes = new ArrayList<>();
        this.scrollKnoppenFilms = new ArrayList<>();
        this.format = new DecimalFormat("##.00");
        double[] geldBedragen = {0.05, 0.10, 0.20, 0.50, 1.00, 2.00, 5.00};
        this.geldBedragen = geldBedragen;
        this.dagtotaal = 0.00;
        this.inworp = 0;
        this.wisselgeld = 0;
        this.scrollLock = 0;
        this.mouseLock = false;
        this.isFilmGekozen = false;
        this.tekenDrukKnopKaartje = false;
        this.printKaartje = false;
    }

    // Onderstaande methodes voeren alle nodige sequenties van het programma uit en wisselt van scherm waar nodig.
    public void tekenSchermenAutomaat() {
        if (!isFilmGekozen && !printKaartje) {
            tekenKiesFilmScherm();
        }
        if (isFilmGekozen && !printKaartje) {
            tekenSchermBetaalFilm();
        } else if (printKaartje){
            tekenKaartjeScherm();
        }
    }

    public void tekenSchermBetaalFilm() {
        resetBackground();
        tekenFilmTitel();
        tekenBetaalKnoppenGeld();
        tekenNogTeBetalenGeld();
        tekenDrukKnopKaartje();
        tekenTerugKnop();
        drukGeldKnop();
        drukTerugKnop();
        drukPrintKaartjeKnop();
    }

    public void tekenKiesFilmScherm() {
        resetBackground();
        tekenKnoppenFilmsPerFilm();
        tekenScrollKnoppenFilms();
        drukFilmKnop();
        drukScrollKnoppenFilms();
    }

    public void tekenKaartjeScherm() {
        resetBackground();
        tekenKaartje();
        tekenTerugKnop();
        drukTerugKnop();
    }

    public void initialiseerAlleKnoppen(){
        voegBetaalKnoppenToe();
        voegTekenKaartjeKnopToe();
        voegTekenTerugKnopToe();
        voegScrollKnoppenFilmsToe();
    }

    // Onderstaande methodes hebben alles te maken met het tekenen van tekst/elementen op het scherm.
    public String tekstMet0(double bedrag) {
        if (bedrag >= 1.00) {
            return format.format(bedrag);
        } else if (bedrag < 1) {
            return "0" + (format.format(bedrag));
        }
        return "";
    }

    public void tekenNogTeBetalenGeld() {
        if (!tekenDrukKnopKaartje) {
            double bedrag = films.get(indexHuidigeFilm).getPrijs() - inworp;
            String tekst = "Nog te betalen bedrag: €" + tekstMet0(bedrag);
            app.fill(255);
            app.textSize(32);
            app.text(tekst, app.width / 2, app.height / 2);
        }
    }

    private void tekenFilmTitel() {
        Film f = films.get(indexHuidigeFilm);
        app.fill(255);
        app.textSize(50);
        app.text(f.getNaam(), app.width / 2, 75);
    }

    public void tekenKaartje() {
        Kaartje k = kaartjes.get(kaartjes.size() - 1);
        float kaartjeX1 = 0;
        float lijnBuffer = 5;
        float kaartjeY1 = app.height / 4;
        float kaartjeBreedte = app.width;
        float kaartjeHoogte = app.height / 2;
        app.fill(255);
        app.textAlign(app.CENTER);
        app.textSize(22);
        app.rect(kaartjeX1, kaartjeY1, kaartjeBreedte, kaartjeHoogte);
        kaartjeArt(300, 200, 400);
        app.strokeWeight(2);
        app.stroke(0, 100);
        app.line(kaartjeX1 + lijnBuffer, kaartjeY1 + lijnBuffer, app.width - lijnBuffer, kaartjeY1 + lijnBuffer);
        app.line(kaartjeX1 + lijnBuffer, kaartjeY1 + (kaartjeHoogte) - (lijnBuffer * 1), app.width - lijnBuffer, kaartjeY1 + (kaartjeHoogte) - (lijnBuffer * 1));
        app.line(kaartjeX1 + lijnBuffer, kaartjeY1 + lijnBuffer, kaartjeX1 + lijnBuffer, kaartjeY1 + kaartjeHoogte - (lijnBuffer * 1));
        app.line(kaartjeX1 + kaartjeBreedte - (lijnBuffer), kaartjeY1 + lijnBuffer, kaartjeX1 + kaartjeBreedte - (lijnBuffer), kaartjeY1 + kaartjeHoogte - (lijnBuffer * 1));
        app.strokeWeight(1);
        app.fill(0);
        app.text(k.printKaartjeConsole(), app.width / 2, kaartjeY1 + (kaartjeHoogte / 2));
    }

    public void kaartjeArt(float x, float y, float d){
        app.stroke(0, 15);
        app.noFill();
        app.ellipse(x, y, d, d);
        if (d > 2){
            kaartjeArt(x + (d / 2 ), y, d / 2);
            kaartjeArt(x - (d / 2 ), y, d / 2);
            kaartjeArt(x, y + (d / 2), d / 2);
        }
    }

    // Onderstaande methods hebben alles te maken met het initialiseren van bepaalde objecten.
    private void voegBetaalKnoppenToe() {
        int nKnoppen = 7;
        for (int i = 0; i < nKnoppen; i++) {
            float knoppenBreedteTotaal = app.width - 200;
            float knopBreedte = knoppenBreedteTotaal / nKnoppen;
            String formatBedrag = tekstMet0(geldBedragen[i]);
            Knop kg = new Knop(app, ((app.width / 2) - knoppenBreedteTotaal / 2) + (i * (knopBreedte)), app.height / 4, knopBreedte, 50, formatBedrag, i, 10);
            knoppenGeld.add(kg);
        }
    }

    public void voegTekenKaartjeKnopToe() {
        float knopBreedte = app.width / 3;
        float knopHoogte = app.height / 9;
        float knopX = (app.width / 2) - (knopBreedte / 2);
        float knopY = (app.height / 2) - (knopHoogte / 2);
        this.kaartjeKnop = new Knop(app, knopX, knopY, knopBreedte, knopHoogte, "Print kaartje.", 0, 26);
    }

    public void voegTekenTerugKnopToe() {
        float knopX = (app.width / 2) + (app.width / 4);
        float knopY = (app.height / 2) + (app.height / 3);
        this.terugKnop = new Knop(app, knopX, knopY, app.width / 6, app.height / 9, "Terug", knoppenGeld.size() + 1, 15);
    }

    public void voegFilmsToe(String naam, double prijs) {
        Film f = new Film(naam, prijs);
        films.add(f);
        float knopHoogte = 100;
        Knop p = new Knop(app, app.width / 4, 10 + (knoppenFilms.size() * 120), app.width / 2, knopHoogte, f.getNaam(), knoppenFilms.size(), 32);
        knoppenFilms.add(p);
    }

    public void voegNieuwKaartjeToe() {
        Film f = films.get(indexHuidigeFilm);
        Kaartje k = new Kaartje(f.getNaam(), f.getPrijs(), this.wisselgeld, this.format);
        kaartjes.add(k);
    }

    public void voegScrollKnoppenFilmsToe() {
        float knopHoogte = app.height / 9;
        float knopBreedte = app.width / 9;
        float knopX = app.width / 2 + (app.width / 4) + (app.width / 15);
        float knopY = app.height / 2 - knopHoogte;
        String[] scrollString = {"▲", "▼"};
        for (int i = 0; i < 2; i++) {
            Knop k = new Knop(app, knopX, knopY + (i * knopHoogte * 2), knopBreedte, knopHoogte, scrollString[i], i, 16);
            scrollKnoppenFilms.add(k);
        }
    }

    // De onderstaande methodes tekenen de knoppen waarmee de gebruiker interactie heeft.
    public void tekenDrukKnopKaartje() {
        if (tekenDrukKnopKaartje) {
            kaartjeKnop.tekenKnop();
        }
    }

    public void tekenScrollKnoppenFilms() {
        for (Knop k : scrollKnoppenFilms) {
            k.tekenKnop();
        }
    }

    private void tekenTerugKnop() {
        terugKnop.tekenKnop();
    }

    private void tekenBetaalKnoppenGeld() {
        for (Knop kg : knoppenGeld){
            kg.tekenKnop();
        }
    }

    public void tekenKnoppenFilmsPerFilm() {
        for (Knop p : knoppenFilms) {
            p.tekenKnop();
        }
    }

    // De onderstaande methodes hebben alles te maken met het herkennen van input door de gebruiker.
    public void drukPrintKaartjeKnop() {
        Knop kn = kaartjeKnop;
        Film f = films.get(indexHuidigeFilm);
        if (app.mousePressed && kn.isMuisOverKnop() && !mouseLock && tekenDrukKnopKaartje){
            setMouseLock(true);
            tekenDrukKnopKaartje = false;
            printKaartje = true;
            voegNieuwKaartjeToe();
            resetInworpEnWisselGeld();
        }
    }

    public void drukTerugKnop() {
        Knop k = terugKnop;
        if (app.mousePressed && k.isMuisOverKnop() && !mouseLock && !printKaartje) {
            annuleerBetaling();
            tekenDrukKnopKaartje = false;
            isFilmGekozen = false;
        } else if (app.mousePressed && k.isMuisOverKnop() && !mouseLock && printKaartje) {
            resetInworpEnWisselGeld();
            resetFilmKnoppenPositie();
            resetScrollLock();
            tekenDrukKnopKaartje = false;
            isFilmGekozen = false;
            printKaartje = false;
        }
    }

    public void drukGeldKnop() {
        for (Knop k : knoppenGeld) {
            if (app.mousePressed && k.isMuisOverKnop() && !mouseLock && !tekenDrukKnopKaartje) {
                werpGeldIn(geldBedragen[k.getKnopNummer()]);
                setMouseLock(true);
            }
        }
    }

    public void drukFilmKnop() {
        for (Knop p : knoppenFilms) {
            if (app.mousePressed && p.isMuisOverKnop() && !mouseLock) {
                this.indexHuidigeFilm = p.getKnopNummer();
                isFilmGekozen = true;
            }
        }
    }

    public void drukScrollKnoppenFilms() {
        float scrollSize = 10;
        float scrollMax = 10;
        if (knoppenFilms.size() > 5) {
            Knop filmknop = knoppenFilms.get(0);
            scrollMax = 10 + ((knoppenFilms.size() - 5) + filmknop.getHoogte() * (knoppenFilms.size() - 5));
        }
        for (Knop k : scrollKnoppenFilms) {
            if (k.isMuisOverKnop() && app.mousePressed && !mouseLock && k.getKnopNummer() == 0 && scrollLock > 0) {
                for (Knop filmKnoppen : knoppenFilms) {
                    this.mouseLock = true;
                    filmKnoppen.scroll(-scrollSize);
                    this.scrollLock -= scrollSize / knoppenFilms.size();
                }
            } else if (k.isMuisOverKnop() && app.mousePressed && !mouseLock && k.getKnopNummer() == 1 && scrollLock < scrollMax) {
                for (Knop filmKnoppen : knoppenFilms) {
                    this.mouseLock = true;
                    filmKnoppen.scroll(scrollSize);
                    this.scrollLock += scrollSize / knoppenFilms.size();
                }
            }
        }
    }

    // Onderstaande methodes setten/resetten de staat van bepaalde variabelen.
    public void resetFilmKnoppenPositie() {
        int i = 0;
        for (Knop k : knoppenFilms) {
            k.setY(10 + (i * 120));
            i++;
        }
    }

    public void resetScrollLock() {
        this.scrollLock = 0;
    }

    public void setMouseLock(boolean mouseLock) {
        this.mouseLock = mouseLock;
    }

    // Onderstaande methods hebben te maken met het berekenen van bepaalde opdrachten.
    public void annuleerBetaling() {
        System.out.println("Betaling gestopt, geld terug: " + this.inworp);
        System.out.println("--------------------------------------------");
        resetInworpEnWisselGeld();
    }

    public void resetInworpEnWisselGeld() {
        this.inworp = 0;
        this.wisselgeld = 0;
    }

    public void accepteerBetaling(Film f) {
        boolean betalingVoldaan = this.inworp >= f.getPrijs();
        if (betalingVoldaan) {
            verhoogDagTotaal();
            this.wisselgeld = berekenWisselgeld();
            tekenDrukKnopKaartje = true;
        }
    }

    public void werpGeldIn(double inworp) {
        Film f = films.get(indexHuidigeFilm);
        this.inworp += inworp;
        accepteerBetaling(f);
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

/* to-do lijst:
- Voeg links van het scherm een knop toe die de gebruiker naar het beheer scherm brengt via een wachtwoord die je input via de console.
- Voeg/verwijder films toe via beheer scherm.
- Bekijk of reset het dagtotaal.
- Bestel meerdere kaartjes in 1 bestelling.
 */
