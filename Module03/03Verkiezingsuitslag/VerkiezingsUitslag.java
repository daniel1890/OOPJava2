import java.util.ArrayList;

public class VerkiezingsUitslag {
	public ArrayList<PartijUitslag> PartijUitslagen;
	private int totaalStemmen;
	private int TKTOTAAL;
	private int kiesdeler;
	private int totaalBasiszetels;
	private int totaalRestzetels;

	public VerkiezingsUitslag() {
		this.TKTOTAAL = 150;
		maakTK2003();
		verhoogTotaalStemmen();
		verdeelKiesdeler();
		verhoogPartijZetels();
		bepaalTotaalBasiszetels();
		this.totaalRestzetels = TKTOTAAL - this.totaalBasiszetels;
		bepaalTotaalAantalRestzetels();

	}

	public void printVerkiezingen() {
		for (PartijUitslag uitslag : PartijUitslagen) {
			System.out.println(uitslag.getResultaat());
		}
	}

	public void bepaalTotaalAantalRestzetels() {
		for (int i = 0; i < totaalRestzetels; i++) {
			int max = 0;
			for (PartijUitslag uitslag : PartijUitslagen) {
				if (uitslag.getStemmenPerPluszetel() >= max) {
					max = uitslag.getStemmenPerPluszetel();
				}
			}
			
			for (PartijUitslag uitslag : PartijUitslagen) {
				if (uitslag.getStemmenPerPluszetel() == max) {
					uitslag.ontvangRestzetel();
					System.out.println("Restzetel voor de partij: " + uitslag.getNaam() + max);
				}
			}
		}
	}

	public void bepaalTotaalBasiszetels() {
		for (PartijUitslag uitslag : PartijUitslagen) {
			totaalBasiszetels += uitslag.getBasisZetels();
		}
	}

	public void verhoogPartijZetels() {
		for (PartijUitslag uitslag : PartijUitslagen) {
			uitslag.berekenBasiszetels(kiesdeler);
		}
	}

	public void verdeelKiesdeler() {
		kiesdeler = totaalStemmen / TKTOTAAL;
	}

	public void verhoogTotaalStemmen() {
		for (PartijUitslag uitslag : PartijUitslagen) {
			totaalStemmen += uitslag.getStemmen();
		}
	}

	public void maakTK2010() {
		PartijUitslagen = new ArrayList<>();
		PartijUitslagen.add(new PartijUitslag("CDA", 1281886));
		PartijUitslagen.add(new PartijUitslag("ChristenUnie", 305094));
		PartijUitslagen.add(new PartijUitslag("D66", 654167));
		PartijUitslagen.add(new PartijUitslag("EPN", 924));
		PartijUitslagen.add(new PartijUitslag("GroenLinks", 628096));
		PartijUitslagen.add(new PartijUitslag("Heel NL", 1255));
		PartijUitslagen.add(new PartijUitslag("Lijst 17", 7456));
		PartijUitslagen.add(new PartijUitslag("Mens en Spirit", 26196));
		PartijUitslagen.add(new PartijUitslag("Nieuw NL", 2010));
		PartijUitslagen.add(new PartijUitslag("Partij een", 2042));
		PartijUitslagen.add(new PartijUitslag("Piratenpartij", 10471));
		PartijUitslagen.add(new PartijUitslag("PvdA", 1848805));
		PartijUitslagen.add(new PartijUitslag("PvdD", 122317));
		PartijUitslagen.add(new PartijUitslag("PVV", 1454493));
		PartijUitslagen.add(new PartijUitslag("SGP", 163581));
		PartijUitslagen.add(new PartijUitslag("SP", 924696));
		PartijUitslagen.add(new PartijUitslag("Trots op NL", 52937));
		PartijUitslagen.add(new PartijUitslag("VVD", 1929575));
	}

	public void maakTK2006() {
		PartijUitslagen = new ArrayList<>();
		PartijUitslagen.add(new PartijUitslag("CDA", 2608573));
		PartijUitslagen.add(new PartijUitslag("PvdA", 2085077));
		PartijUitslagen.add(new PartijUitslag("SP", 1630803));
		PartijUitslagen.add(new PartijUitslag("VVD", 1443312));
		PartijUitslagen.add(new PartijUitslag("PVV", 579490));
		PartijUitslagen.add(new PartijUitslag("GL", 453054));
		PartijUitslagen.add(new PartijUitslag("CU", 390969));
		PartijUitslagen.add(new PartijUitslag("D66", 193232));
		PartijUitslagen.add(new PartijUitslag("PvdD", 179988));
		PartijUitslagen.add(new PartijUitslag("SGP", 153266));
		PartijUitslagen.add(new PartijUitslag("EenNL", 62829));
		PartijUitslagen.add(new PartijUitslag("Fortuyn", 20956));
		PartijUitslagen.add(new PartijUitslag("Verenigde Senioren Partij", 12522));
		PartijUitslagen.add(new PartijUitslag("Ad Bos Collectief", 5149));
		PartijUitslagen.add(new PartijUitslag("Partij voor Nederland", 5010));
		PartijUitslagen.add(new PartijUitslag("Lijst Potmis", 4339));
		PartijUitslagen.add(new PartijUitslag("Nederland Transparant", 2318));
		PartijUitslagen.add(new PartijUitslag("Groen Vrij Internet Partij", 2297));
		PartijUitslagen.add(new PartijUitslag("Liberaal Democratische Partij", 2276));
		PartijUitslagen.add(new PartijUitslag("Lijst Poortman", 2181));
		PartijUitslagen.add(new PartijUitslag("Continue Directe Democratie Partij", 559));
		PartijUitslagen.add(new PartijUitslag("LRVP - het Zeteltje", 185));
		PartijUitslagen.add(new PartijUitslag("Solide Multiculturele Partij", 184));
		PartijUitslagen.add(new PartijUitslag("Tamara's Open Partij", 114));
	}

	public void maakTK2003() {
		PartijUitslagen = new ArrayList<PartijUitslag>();
		PartijUitslagen.add(new PartijUitslag("CDA", 2763480));
		PartijUitslagen.add(new PartijUitslag("PvdA", 2631363));
		PartijUitslagen.add(new PartijUitslag("VVD", 1728707));
		PartijUitslagen.add(new PartijUitslag("SP", 609723));
		PartijUitslagen.add(new PartijUitslag("LPF", 549975));
		PartijUitslagen.add(new PartijUitslag("GL", 495802));
		PartijUitslagen.add(new PartijUitslag("D66", 393333));
		PartijUitslagen.add(new PartijUitslag("CU", 204694));
		PartijUitslagen.add(new PartijUitslag("SGP", 150305));
		PartijUitslagen.add(new PartijUitslag("PvdD", 47754));
		PartijUitslagen.add(new PartijUitslag("LN", 38894));
		PartijUitslagen.add(new PartijUitslag("Partij van de Toekomst", 13845));
		PartijUitslagen.add(new PartijUitslag("Lijst Ratelband", 9045));
		PartijUitslagen.add(new PartijUitslag("Duurzaam Nederland", 7271));
		PartijUitslagen.add(new PartijUitslag("Nieuwe Communistische Partij", 4854));
		PartijUitslagen.add(new PartijUitslag("De Conservatieven.nl", 2521));
		PartijUitslagen.add(new PartijUitslag("VIP Vooruitstrevende Integratie Partij", 1623));
		PartijUitslagen.add(new PartijUitslag("Alliantie Vernieuwing en Democratie", 990));
		PartijUitslagen.add(new PartijUitslag("Lijst Veldhoen", 296));
	}

	public void maakTK2002() {
		PartijUitslagen = new ArrayList<PartijUitslag>();
		PartijUitslagen.add(new PartijUitslag("CDA", 2653723));
		PartijUitslagen.add(new PartijUitslag("LPF", 1614801));
		PartijUitslagen.add(new PartijUitslag("VVD", 1466722));
		PartijUitslagen.add(new PartijUitslag("PvdA", 1436023));
		PartijUitslagen.add(new PartijUitslag("GL", 660692));
		PartijUitslagen.add(new PartijUitslag("SP", 560447));
		PartijUitslagen.add(new PartijUitslag("D66", 484317));
		PartijUitslagen.add(new PartijUitslag("CU", 240953));
		PartijUitslagen.add(new PartijUitslag("SGP", 163562));
		PartijUitslagen.add(new PartijUitslag("LN", 153055));
		PartijUitslagen.add(new PartijUitslag("Verenigde Senioren Partij", 39005));
		PartijUitslagen.add(new PartijUitslag("Vrije Indische Partij & OuderenUnie", 10033));
		PartijUitslagen.add(new PartijUitslag("Duurzaam Nederland", 9058));
		PartijUitslagen.add(new PartijUitslag("Partij van de Toekomst", 6393));
		PartijUitslagen.add(new PartijUitslag("Nieuwe Midden Partij", 2305));
		PartijUitslagen.add(new PartijUitslag("Republikeinse Volkspartij", 63));
	}

	public void maakTK1998() {
		PartijUitslagen = new ArrayList<PartijUitslag>();
		PartijUitslagen.add(new PartijUitslag("PvdA", 2494555));
		PartijUitslagen.add(new PartijUitslag("VVD", 2124971));
		PartijUitslagen.add(new PartijUitslag("CDA", 1581053));
		PartijUitslagen.add(new PartijUitslag("D66", 773497));
		PartijUitslagen.add(new PartijUitslag("GL", 625968));
		PartijUitslagen.add(new PartijUitslag("SP", 303703));
		PartijUitslagen.add(new PartijUitslag("RPF", 174593));
		PartijUitslagen.add(new PartijUitslag("SGP", 153583));
		PartijUitslagen.add(new PartijUitslag("GPV", 108724));
		PartijUitslagen.add(new PartijUitslag("CD", 52226));
		PartijUitslagen.add(new PartijUitslag("AOV/Unie55+", 45994));
		PartijUitslagen.add(new PartijUitslag("Nederland Mobiel", 45219));
		PartijUitslagen.add(new PartijUitslag("Senioren 2000", 36157));
		PartijUitslagen.add(new PartijUitslag("Nederlandse Middenstands Partij", 23512));
		PartijUitslagen.add(new PartijUitslag("De Groenen", 16585));
		PartijUitslagen.add(new PartijUitslag("Natuurwetpartij", 15746));
		PartijUitslagen.add(new PartijUitslag("Katholiek Politieke Partij", 8233));
		PartijUitslagen.add(new PartijUitslag("Vrije Indische Partij", 7225));
		PartijUitslagen.add(new PartijUitslag("Nieuw Solidair Ouderen Verbond", 6455));
		PartijUitslagen.add(new PartijUitslag("Nieuwe Communistische Partij", 5620));
		PartijUitslagen.add(new PartijUitslag("Idealisten/Jij", 2500));
		PartijUitslagen.add(new PartijUitslag("Het Kiezers Collectief", 1668));
	}

	public int getTotaalBasiszetels() {
		return totaalBasiszetels;
	}

	public int getTotaalRestzetels() {
		return totaalRestzetels;
	}

	public int getTotaalStemmen() {
		return totaalStemmen;
	}

	public int getKiesdeler() {
		return kiesdeler;
	}
}
