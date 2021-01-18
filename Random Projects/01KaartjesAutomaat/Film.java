public class Film {
    String naam;
    double prijs;
    public static int aantalFilms;

    public Film(String naam, double prijs) {
        this.naam = naam;
        if (prijs < 5.00) {
            this.prijs = 10.00;
        } else if (prijs > 50.00) {
            this.prijs = 50.00;
        } else {
            this.prijs = prijs;
        }

        aantalFilms++;
    }

    public String getNaam() {
        return naam;
    }

    public double getPrijs() {
        return prijs;
    }


}
