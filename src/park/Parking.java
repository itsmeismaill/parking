package park;

import java.util.*;

/**
 * Parking
 */
public class Parking {

	int PlacesOccupees;
	int Capacite;
	public HashSet<Voiture> infoVoitures = new HashSet<Voiture>();

	Parking(int size) {
		this.Capacite = size;
	}

	int places() {
		return (this.Capacite - this.PlacesOccupees);
	}

	synchronized boolean accept(Voiture myVoit) {
		if (this.places() > 0) {
			this.PlacesOccupees++;
			infoVoitures.add(myVoit);
			System.out.format("[Parking]: %s acceptée, il reste %d places \n", myVoit.nom, this.places());
			System.out.format("Voiture Garees\n");
			System.out.println(infoVoitures);
			int i;
			for (i = 0; i < ParkingUI.tabParking.length; i++) {
				if (ParkingUI.tabParking[i] == null) {
					ParkingUI.tabParking[i] = myVoit;
					break;
				}
			}
			ParkingUI.draw();
			return true;
		} else {
			System.out.format("Parking: %s refusée, il reste %d places \n", myVoit.nom, this.places());
			return false;
		}
	}

	synchronized void leave(Voiture myVoit) {
		this.PlacesOccupees--;
		infoVoitures.remove(myVoit);
		System.out.format("Parking: %s est sortie, il reste %d places \n", myVoit.nom, this.places());
		for (int i = 0; i < ParkingUI.tabParking.length; i++) {
			if (ParkingUI.tabParking[i].nom == myVoit.nom) {
				ParkingUI.tabParking[i] = null;
				break;
			}
			ParkingUI.leave(myVoit);
		}
	}
}