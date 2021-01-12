
public class TestStudieadviesApp {
	public static void main(String[] args) {
		RandomStudentenGenerator rsg = new RandomStudentenGenerator();
		rsg.genereerStudenten(10);
		for (Student s : rsg.getStudentenLijst()) {
			System.out.println(s);
			System.out.println(Studieadviseur.krijgtPositiefStudieadvies(s));
			System.out.println("------------------");
		}
	}
}