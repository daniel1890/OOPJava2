
public class VerkiezingsUitslagMainApp {

	public static void main(String[] args) {
		VerkiezingsUitslag v = new VerkiezingsUitslag();
		v.printVerkiezingen();
		System.out.println("Totaal aantal stemmen: " + v.getTotaalStemmen());
		System.out.println("Kiesdeler: " + v.getKiesdeler());
		System.out.println("Totaal aantal basiszetels: " + v.getTotaalBasiszetels());
		System.out.println("Totaal aantal restzetels: " + v.getTotaalRestzetels());
		System.out.println();
	}

}
