
public class MainApp {

	public static void main(String[] args) {
		Film[] f = new Film[2];
		f[0] = new Film("Forest Gump", 10.00);
		f[1] = new Film("Parasite", 5.00);
		
		Kaartjesautomaat k = new Kaartjesautomaat(f);
		k.kiesFilm(0);
		k.werpGeldIn(5);
		k.werpGeldIn(2);
		k.werpGeldIn(3);
		k.kiesFilm(1);
		k.werpGeldIn(10);
		k.kiesFilm(0);
		k.werpGeldIn(2);
		k.kiesFilm(1);
		k.werpGeldIn(5);
		System.out.println(k.getDagtotaal());
	}

}
