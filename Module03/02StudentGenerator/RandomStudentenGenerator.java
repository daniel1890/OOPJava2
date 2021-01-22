import java.util.Random;


public class RandomStudentenGenerator {
	private Student[] studentenLijst;

	public void genereerStudenten(int aantal) {
		Random generator = new Random();
		studentenLijst = new Student[aantal];
		for (int i = 0; i < aantal; i++) {
			studentenLijst[i] = new Student("Persoon_" + (i + 1));
			for (int j = 0; j < 8; j++) {
				studentenLijst[i].setCijfer(j, generator.nextInt(10) + 1);
			}
		}
	}

	public Student[] getStudentenLijst() {
		return studentenLijst;
	}
}