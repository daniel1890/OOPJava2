public class Kaartje {
    private String naam;
    private double prijs;
    private static int kaartNummer = 0;

    public Kaartje(String naam, double prijs) {
        this.naam = naam;
        this.prijs = prijs;
        this.kaartNummer++;
    }

    public String printKaartjeConsole() {
        return "Gekozen Film: " + naam + ",  prijs: €" + prijs + " , kaartnummer: " + kaartNummer;
    }
}
