import java.util.ArrayList;
import java.util.Random;

public class TurkeyFarm {

	public static void main(String[] args) {
		ArrayList<Turkey> farm = new ArrayList<Turkey>();
		Random gen = new Random(System.currentTimeMillis());

		int startTurkeys = /* gen.nextInt(100) + */ 50;

		// Add the number of turkeys
		for (int i = 0; i < startTurkeys; i++) {
			farm.add(new Turkey());
		}

		int daysMax = 110;
		// int daysMax = 365;
		int days = 0;
		while (days <= daysMax) {// Loop for each day
			System.out.println("Day:" + days);
			for (Turkey t1 : farm) {
				// check if adult{
				if (t1.isAdult()) {
					// if paired & not pregnant call mate
					if (!t1.isPregnant() && t1.isPaired()) {
						for (Turkey t2 : farm) {
							// find a turkey's pair and mate
							// The turkey is only paired with the opposite
							// gender
							if (t1.getID() == t2.getPairID()) {
								Turkey.mate(t1, t2);
							}
						}
					}
					// if not paired pair up with a free turkey of the opposite
					// gender
					if (!t1.isPaired())
						for (Turkey t2 : farm) {// find a turkey to pair up with
							if (!t2.isPaired() && t1.getGender() != t2.getGender() && t2.isAdult()) {
								Turkey.pairTurkeys(t1, t2);
								// break;
							}
						}
				}
			}
			for (int j = farm.size() - 1; j >= 0; j--) {
				Turkey t1 = farm.get(j);
				// if pregnant give birth to new turkeys?
				if (t1.isPregnant() && t1.isPregnancyDue()) {
					int x = gen.nextInt(3) + 1;
					for (int i = 0; i <= x; i++)
						farm.add(Turkey.birth(t1));
				}

			} // end check if adult

			for (int i = farm.size() - 1; i >= 0; i--) {
				Turkey t1 = farm.get(i);
				// is too old? Time to die
				if (t1.istimeToDie()) {
					// Unpair other turkey if need be
					for (Turkey t2 : farm) {
						if (t2.getPairID() == t1.getID()) {
							t1.unPair();
							t2.unPair();
						}
					}
					Turkey.slaughter();
					farm.remove(t1);
				}

				// PassTime Method
				t1.passTime();

			} // end for

			// Status of the Farm. These should be static method calls
			System.out.println("Total Number of Turkeys: " + Turkey.getTotalTurkeys());
			System.out.println("Total Number of Adult Turkeys: " + Turkey.getTotalAdultTurkeys());
			System.out.println("Total Number of Child Turkeys: " + Turkey.getTotalChildTurkeys());
			System.out.println("TotalNumber of Paired Turkeys: " + Turkey.getTotalPairedTurkeys());
			System.out.println("Total Number of Unpaired Turkeys: " + Turkey.getTotalUnpairedTurkeys());
			System.out.println("Total Number of Slaughtered Turkeys: " + Turkey.getKilledTurkeys());
		

			days++;
		} // end While loop

		// Display all the IDs of the turkeys left
		for (Turkey t1 : farm) {
			System.out.println(t1);
		}

	}
}