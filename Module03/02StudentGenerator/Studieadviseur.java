public class Studieadviseur {
	public static boolean krijgtPositiefStudieadvies(Student s) {
		int cijfers[] = s.getCijfers();
		int getallenGroterOfGelijkAanZes = controlleerOfGetallenGroterOfGelijkAanZesZijn(cijfers);
		if (controlleerOfAantalGetallenHogerDanInputIs(getallenGroterOfGelijkAanZes)) {
			return true;
		}
		else return false;

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
		if (getal >= 4) {
			return true;
		} else {
			return false;
		}
	}
}