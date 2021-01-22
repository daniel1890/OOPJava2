package App;

public class Studieadviseur {
	public static boolean krijgtPositiefStudieadvies(Student s) {
		int cijfers[] = s.getCijfers();
		int getallenGroterOfGelijkAanZes = 0;
		for (int cijfer : cijfers) {
			if (cijfer >= 6) {
				getallenGroterOfGelijkAanZes++;
			}
		}
		if (getallenGroterOfGelijkAanZes >= 4) {
			return true;
		} else {
			return false;
		}
	}
}