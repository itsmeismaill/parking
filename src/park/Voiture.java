package park;

/**
 * Voiture
 */
public class Voiture implements Runnable {

	String nom;
	Parking park;
	int xInit;
	int yInit;
	int xFinal;
	int yFinal;

	public Voiture(String name, Parking park, int x, int y) {
		this.nom = name;
		this.park = park;
		this.xInit = x;
		this.yInit = y;
	}

	public void run() {
		System.out.format("[%s]: Je débute ! \n", this.nom);
		try {
			while (true) {
				Thread.sleep((long) (3000*Math.random()));
				System.out.format("[%s]: Je demande à rentrer \n", this.nom);
				this.rentrer();
				System.out.format("[%s]: Je viens d'entrer \n", this.nom);
				Thread.sleep((long) (3000* Math.random()));
				System.out.format("[%s]: Je demande à sortir \n", this.nom);
				this.park.leave(this);
			}
		} catch (InterruptedException e) {
			System.out.format("[%s]: Je meurs ! \n", this.nom);
		}
	}

	public void rentrer() throws InterruptedException {
		while (!this.park.accept(this)) {
			Thread.sleep((long) (3000));
			System.out.format("[%s]: Je redemande à rentrer \n", this.nom);
		}
	}

	public void setInit(int x, int y) {
		this.xInit = x;
		this.yInit = y;
	}

	public void setFinal(int x, int y) {
		this.xFinal = x;
		this.yFinal = y;
	}

	public int getxInit() {
		return xInit;
	}

	public int getyInit() {
		return yInit;
	}

	public int getxFinal() {
		return xFinal;
	}

	public int getyFinal() {
		return yFinal;
	}

	public int[] getInit() {
		int[] tab = { xInit, yInit };
		return tab;
	}

	public int[] getFinal() {
		int[] tab = { xFinal, yFinal };
		return tab;
	}

	public static void main(String[] args) {
		ParkingUI parking = new ParkingUI();
		Parking park = new Parking(9);
		int nbVoitures = 11;
		Thread[] voitures = new Thread[nbVoitures];
		for (int i = 0; i < nbVoitures; i++) {
			voitures[i] = new Thread(new Voiture("Voiture " + (i+1), park, 0, 0));
			voitures[i].start();
			ParkingUI.draw();
		}
	}

	public void setxInit(int x) {
		this.xInit = x;
	}

	public void setyInit(int y) {
		this.yInit = y;
	}

	@Override
	public String toString() {
		return nom;
	}
}