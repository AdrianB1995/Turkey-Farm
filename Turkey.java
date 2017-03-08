import java.util.Random;

public class Turkey {
	// Instance Vairables
	private final int MAX_AGE = 100; // days
	private final int PREGNANCY_DURATION = 7; // turkey born 7 days after being
												// pregnant
	private final int ADULT_AGE = 20; // Age at which a turkey is an adult
	private int gender; // 1 or 2, 1 is FEMALE
	private int id; // unique ID
	private int age; // in days, 0 initially
	private int pairedWithID; // 0 if not paired.
	private int daysPregnant; // 0 initially
	private boolean isPregnant; // initially false
	private boolean isAdult; // initially false
	static Random gen = new Random(System.currentTimeMillis());

	// Static Variables
	private static int nextID = 1; // This holds the value of the next free ID
	private static int turkeysSlaughtered = 0;
	private static int totalTurkeys = 0;
	private static int totalAdultTurkeys = 0;
	private static int totalChildTurkeys = 0;
	private static int pairedTurkeys = 0;
	private static int unpairedTurkeys = 0;

	// Constructor, creates a turkey of a random gender.
	public Turkey() {
		gender = gen.nextInt(2) + 1;
		id = nextID++;
		age = 0;
		pairedWithID = 0;
		daysPregnant = 0;
		isPregnant = false;
		isAdult = false;
		++totalTurkeys;
		++totalChildTurkeys;
		++unpairedTurkeys;
	}

	// New turkey is born, parent turkey no longer pregnant
	public static Turkey birth(Turkey t1) {
		t1.isPregnant = false;
		t1.daysPregnant = 0;
		return new Turkey();
	}

	public boolean isAdult() {
		// returns true if age is over ADULT_AGE
		if (this.age > ADULT_AGE) {
			if (!this.isAdult) {
				this.isAdult = true;
				totalAdultTurkeys++;
				totalChildTurkeys--;
			}
			return isAdult;
		} else
			return isAdult;
	}

	public boolean isPregnant() {
		if (this.isPregnant)
			return true;
		else
			return false;
	}

	public boolean istimeToDie() {
		// returns true if age is > max_Age
		if (this.age > MAX_AGE)
			return true;
		else
			return false;
	}

	public int getID() {
		// returns the value of the ID of the current Turkey
		return this.id;
	}

	public int getPairID() {
		// this method returns the pairID, the ID of the turkey this one is pair
		// with.
		return this.pairedWithID;
	}

	public void unPair() {
		// This method removes the value for pairID and sets it to zero
		this.pairedWithID = 0;
		pairedTurkeys--;
		unpairedTurkeys++;
	}

	public void passTime() {
		// add one to each of the day values. This includes the age and
		// daysPregnant.
		this.age = this.age + 1;
		if (this.isPregnant)
			this.daysPregnant++;
	}

	public static void slaughter() {
		// increments a counter that keeps track of how many turkeys have been
		// killed.
		turkeysSlaughtered++;
		totalTurkeys--;
		totalAdultTurkeys--;
		unpairedTurkeys--;

	}

	public boolean isPaired() {
		// return true if the turkey pairID is != 0;
		if (this.pairedWithID != 0)
			return true;
		else
			return false;
	}

	public static void mate(Turkey t1, Turkey t2) {
		// Always set female to pregnant
		// Determine which of the two Turkey's is female and make pregnant and
		// set days pregnant to zero
		if (t1.gender == 1 && !t1.isPregnant) // 1 will be FEMALE
		{
			t1.isPregnant = true;
			t1.daysPregnant = 0;
		} else if (t2.gender == 1 && !t2.isPregnant) {
			t2.isPregnant = true;
			t2.daysPregnant = 0;
		}
	}

	private void setDaysPregnant(int days) {
		this.daysPregnant = days;
	}

	public static void pairTurkeys(Turkey t1, Turkey t2) {
		// This method will set pair ID of each other
		// System.out.println("Before Pairing!");
		// System.out.println(t1);
		// System.out.println(t2);
		if(!t1.isPaired() && !t2.isPaired()){
			
			t1.pairedWithID = t2.id;
			t2.pairedWithID = t1.id;
			pairedTurkeys = pairedTurkeys + 2;
			unpairedTurkeys = unpairedTurkeys - 2;
		}
		// System.out.println("After Pairing!");
		// System.out.println(t1);
		// System.out.println(t2);
	}

	private void setPairIDTo(int id) {
		this.pairedWithID = id;
	}

	public int getGender() {
		return this.gender;
	}

	public boolean isPregnancyDue() {
		// This method will return true if the daysPregnant is greater than
		// PREGNANCYDURATION
		if (this.daysPregnant > PREGNANCY_DURATION)
			return true;
		else
			return false;
	}

	public void setIsPregnant(boolean p) {
		this.isPregnant = p;
	}

	// toString method that returns important information about a Turkey
	public String toString() {
		String turkeyInfo = "";
		String genderString;
		if (gender == 1)
			genderString = "female";
		else
			genderString = "male";

		// if pregnant, must be an adult
		if (this.isPregnant) {
			turkeyInfo = "Adult " + genderString + ": " + " ID: " + id + " Age: " + age + " days pregnant: "
					+ daysPregnant;
		} else {
			if (this.isAdult)
				turkeyInfo = "Adult " + genderString + ": " + " ID: " + id + " Age: " + age;
			else
				turkeyInfo = "Child " + genderString + ": " + " ID: " + id + " Age: " + age;
		}
		if (this.isPaired())
			turkeyInfo = turkeyInfo + " Paired With: " + pairedWithID;
		else
			turkeyInfo = turkeyInfo + " Unpaired.";

		return turkeyInfo;
	}

	// Static method to return total number of turkeys
	public static int getTotalTurkeys() {
		return totalTurkeys;
	}

	// Static method to return total number of adult turkeys
	public static int getTotalAdultTurkeys() {
		return totalAdultTurkeys;
	}

	// Static method to return total number of child turkeys
	public static int getTotalChildTurkeys() {
		return totalChildTurkeys;
	}

	// Static method to return total number of paired turkeys
	public static int getTotalPairedTurkeys() {
		return pairedTurkeys;
	}

	// Static method to return total number of unpaired turkeys
	public static int getTotalUnpairedTurkeys() {
		return unpairedTurkeys;
	}

	// Static method to return number of killed turkeys
	public static int getKilledTurkeys() {
		return turkeysSlaughtered;
	}

}
