
public class Kaartjesautomaat {
		private Film[] films;
		public double dagtotaal;	
		private double inworp;
		private int indexHuidigeFilm;
		
		public Kaartjesautomaat(Film[] films) {
			this.films = films;
			dagtotaal = 0.00;
			this.inworp = 0;
		}
		
		public String printKaartje() {
			return "Film: " + films[indexHuidigeFilm].naam + ", prijs: " + films[indexHuidigeFilm].prijs;
		}
		
		public void kiesFilm(int i) {
			if (this.inworp > 0) {
				annuleerBetaling();
			}
			this.indexHuidigeFilm = i;
			System.out.println("Gekozen " + printKaartje());
		}
		
		public void annuleerBetaling() {
			System.out.println("Betaling gestopt, geld terug: " + this.inworp);
			System.out.println("--------------------------------------------");
			this.inworp = 0;
		}
		
		public void werpGeldIn(double inworp) {
			this.inworp += inworp;
			System.out.println("Ingeworpen: " + inworp + " , totaal ingeworpen: " + this.inworp);
			if (this.inworp >= films[indexHuidigeFilm].getPrijs()) {
				verhoogDagTotaal(inworp);
				System.out.println(printKaartje());
				double wisselgeld = berekenWisselgeld();
				printTekst("Uw wisselgeld: ", wisselgeld);
				System.out.println("-----------------------------");
				this.inworp = 0;
			} else {
				double rest = films[indexHuidigeFilm].getPrijs() - this.inworp;
				printTekst("Nog in te werpen: ", rest);

			}
		}

		
		public void verhoogDagTotaal(double inworp) {
			this.dagtotaal += films[indexHuidigeFilm].getPrijs();
		}
		
	 	public void printTekst(String tekst, double waarde) {
			System.out.println(tekst + waarde);
		}

		private double berekenWisselgeld() {
			double wisselgeld = this.inworp - films[indexHuidigeFilm].getPrijs();
			return wisselgeld;
		}
		
		public void leegAutomaat() {
			this.dagtotaal = 0;
		}

		public double getDagtotaal() {
			return dagtotaal;
		}
		
}
