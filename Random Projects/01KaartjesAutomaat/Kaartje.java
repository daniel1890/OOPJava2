import java.text.DecimalFormat;

public class Kaartje {
    private String naam;
    private double prijs;
    private DecimalFormat format;
    private static int kaartNummer = 0;
    private double wisselgeld;

    public Kaartje(String naam, double prijs, double wisselgeld, DecimalFormat format) {
        this.naam = naam;
        this.prijs = prijs;
        this.wisselgeld = wisselgeld;
        this.format = format;
        this.kaartNummer++;
    }

    public String printKaartjeConsole() {
        String lijn1 = "Gekozen Film: " + naam + ",  prijs: €" + format.format(prijs);
        String lijn2 = "";
        if (wisselgeld >= 1.00) {
            lijn2 += "Kaartnummer: " + kaartNummer + ", Uw wisselgeld: €" + format.format(wisselgeld);
        } else if (wisselgeld < 1.00) {
            lijn2 += "Kaartnummer: " + kaartNummer + ", Uw wisselgeld: €0" + format.format(wisselgeld);
        }
        return lijn1 + System.lineSeparator() + lijn2;
    }
    
}
