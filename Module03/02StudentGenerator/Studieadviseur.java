import App.Student;

public class Studieadviseur {
	public static boolean krijgtPositiefStudieadvies(Student s) {
		int cijfers[] = s.getCijfers();
		int getallenGroterOfGelijkAanZes = controlleerOfGetallenGroterOfGelijkAanZesZijn(cijfers);
		return controlleerOfAantalGetallenHogerDanInputIs(getallenGroterOfGelijkAanZes);

	}

	static int controlleerOfGetallenGroterOfGelijkAanZesZijn(int[] cijfers) {
		int getallenGroterAantalIngevoerd = 0;
		for (int cijfer : cijfers) {
			if (cijfer >= 6) {
				getallenGroterAantalIngevoerd++;
			}
		}
		return getallenGroterAantalIngevoerd;
	}
	
	static boolean controlleerOfAantalGetallenHogerDanInputIs(int getal) {
		return getal >= 4;
	}
}