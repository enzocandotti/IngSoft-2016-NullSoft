package model;

public class BulletAdapter implements BeatModelInterface {
	BulletModelInterface bullet;

	public BulletAdapter(BulletModelInterface bullet) {
		this.bullet = bullet;
	}

	public void initialize() {
	}

	public void on() {
	}

	public void off() {
	}

	public int getBPM() {
		return bullet.getX();
	}

	public void setBPM(int bpm) {
	}

	public void registerObserver(BeatObserver o) {
		bullet.registerObserver(o);
	}

	public void removeObserver(BeatObserver o) {
		bullet.removeObserver(o);
	}

	public void registerObserver(BPMObserver o) {
		bullet.registerObserver(o);
	}

	public void removeObserver(BPMObserver o) {
		bullet.removeObserver(o);
	}

	public void registerObserver(BulletObserver o) {
		bullet.registerObserver(o);

	}

	public void removeObserver(BulletObserver o) {
		bullet.removeObserver(o);
	}

	public String getPos() {
		return bullet.getX() + " ," + bullet.getY() + " ," + bullet.getDIR();
	}
}