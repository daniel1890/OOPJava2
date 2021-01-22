public class Vorm {
    protected int kleur;
    protected float vx;
    protected float vy;
    protected float ax;
    protected float ay;
    protected float x;
    protected float y;

    public Vorm(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void doeStap() {
        if (!staatStil()) {
            pasVersnellingToe();
            pasSnelheidToe();
        }
    }

    public void setSnelheid(float vx, float vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public void setVersnelling(float ax, float ay) {
        this.ax = ax;
        this.ay = ay;
    }

    public void zetStil() {
        vx = vy = ax = ay = 0;
    }

    public boolean staatStil() {
        return (vx == 0 && vy == 0 && ax == 0 && ay == 0);
    }

    public boolean isOnzichtbaar() {
        return this.kleur == 0;
    }

    public void setOnzichtbaar() {
        if (this.kleur != 0) {
            this.kleur = 0;
        } else {
            this.kleur = 255;
        }
    }

    public void setKleur(int kleur) {
        this.kleur = kleur;
    }

    public float getVx() {
        return vx;
    }

    private void pasVersnellingToe() {
        vx += ax;
        vy += ay;
    }

    private void pasSnelheidToe() {
        x += vx;
        y += vy;
    }
}
