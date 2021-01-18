import processing.core.PApplet;

public class MainApp extends PApplet {

    public static void main(String[] args) {
        PApplet.main(new String[] {"MainApp"} );
    }

    public void settings() {
        this.size(600, 600);
    }

    KaartjesAutomaat k = new KaartjesAutomaat(this);

    public void setup() {

        k.voegFilmsToe("Finding Nemo", 10.00);
        k.voegFilmsToe("Parasite", 10.00);
        k.voegFilmsToe("Forest Gump", 15.00);
        k.voegFilmsToe("Avengers", 50.00);
    }

    public void draw() {
        k.tekenSchermenAutomaat();
    }

    public void mouseReleased() {
        k.setMouseLock(false);
    }

}
