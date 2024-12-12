

class TestSpielfeld {
	public static void main(String[] args) {
		mineInCornerTest();
		mineInCenterTest();
		moreMinesInCenterTest();
		minesAroundACenterTest();
		//visualTest();
		//runTimeTest();
		automaticMineLayingTest();
		leftClickOnFeldTest();
	}
	static void annahmeGleich(int h, int b, int ist, int soll) {
		if (ist == soll) {
			System.out.println("Annahme gleich bei: h" + h + " und: b" + b + " ists: " + soll);
		}
		else {
			System.out.println("Annahme ungleich, bei: h" + h + " und: b" + b + " ist: " + ist + ", aber soll:  " + soll);
		}
	}

	static void mineInCornerTest() {
		System.out.println("");
		System.out.println("MineInCornerTest / manuell Mine in Ecke gelegt / manuell nearby Mine Werte abgefragt");
		int[][] mineCoords = new int[2][1];
		mineCoords[0][0] = 0;
		mineCoords[1][0] = 0;
		Spielfeld tutut1 = new Spielfeld(10, 10, 0);
		tutut1.minenVerleger(mineCoords);
		int nearbyMines = tutut1.checkNearbyMines(0, 1);
		annahmeGleich(0, 1, nearbyMines, 1);
		int nearbyMines1 = tutut1.checkNearbyMines(1, 1);
		annahmeGleich(1, 1,nearbyMines1, 1);
		int nearbyMines2 = tutut1.checkNearbyMines(1, 0);
		annahmeGleich(1, 0,nearbyMines2, 1);
		int nearbyMines3 = tutut1.checkNearbyMines(2, 1);
		annahmeGleich(2, 1,nearbyMines3, 0);
		tutut1.commandlineAllOpenOutput();
	}
	static void mineInCenterTest() {
		System.out.println("");
		System.out.println("MineInCenterTest / manuell Mine in Mitte gelegt / manuell nearby Mine Werte abgefragt");
		int[][] mineCoords = new int[2][1];
		mineCoords[0][0] = 5;
		mineCoords[1][0] = 5;
		Spielfeld blablab = new Spielfeld(10, 10, 0);
		blablab.minenVerleger(mineCoords);
		int nearbyMines = blablab.checkNearbyMines(4, 4);
		annahmeGleich(4, 4,nearbyMines, 1);
		int nearbyMines1 = blablab.checkNearbyMines(4, 5);
		annahmeGleich(4, 5,nearbyMines1, 1);
		int nearbyMines2 = blablab.checkNearbyMines(4, 6);
		annahmeGleich(4, 6,nearbyMines2, 1);
		int nearbyMines3 = blablab.checkNearbyMines(5, 4);
		annahmeGleich(5, 4,nearbyMines3, 1);
		int nearbyMines4 = blablab.checkNearbyMines(5, 6);
		annahmeGleich(5, 6,nearbyMines4, 1);
		int nearbyMines5 = blablab.checkNearbyMines(6, 4);
		annahmeGleich(6, 4,nearbyMines5, 1);
		int nearbyMines6 = blablab.checkNearbyMines(6, 5);
		annahmeGleich(6, 5,nearbyMines6, 1);
		int nearbyMines7 = blablab.checkNearbyMines(6, 6);
		annahmeGleich(6, 6,nearbyMines7, 1);
		int nearbyMines8 = blablab.checkNearbyMines(5, 5);
		annahmeGleich(5, 5,nearbyMines8, 0);
		blablab.commandlineAllOpenOutput();
	}
	static void moreMinesInCenterTest() {
		System.out.println("");
		System.out.println("MoreMinesInCenterTest");
		int[][] mineCoords = new int[2][4];
		mineCoords[0][0] = 5;
		mineCoords[1][0] = 5;
		mineCoords[0][1] = 2;
		mineCoords[1][1] = 5;
		mineCoords[0][2] = 5;
		mineCoords[1][2] = 6;
		mineCoords[0][3] = 4;
		mineCoords[1][3] = 5;
		Spielfeld hekmek = new Spielfeld(10, 10, 0);
		hekmek.minenVerleger(mineCoords);
		hekmek.commandlineAllOpenOutput();
	}

	static void minesAroundACenterTest() {
		System.out.println("");
		System.out.println("MinesAroundACenterTest");
		int[][] mineCoords = new int[2][8];
		mineCoords[0][0] = 4;
		mineCoords[1][0] = 4;
		mineCoords[0][1] = 4;
		mineCoords[1][1] = 5;
		mineCoords[0][2] = 4;
		mineCoords[1][2] = 6;
		mineCoords[0][3] = 5;
		mineCoords[1][3] = 4;
		mineCoords[0][4] = 5;
		mineCoords[1][4] = 6;
		mineCoords[0][5] = 6;
		mineCoords[1][5] = 4;
		mineCoords[0][6] = 6;
		mineCoords[1][6] = 5;
		mineCoords[0][7] = 6;
		mineCoords[1][7] = 6;
		Spielfeld grgrgr = new Spielfeld(10, 10, 0);
		grgrgr.minenVerleger(mineCoords);
		grgrgr.commandlineAllOpenOutput();
	}

	static void visualTest() {
		System.out.println("");
		System.out.println("VisualTest (random generated Game) + various minor tests");
		long startTime = System.nanoTime();
		Spielfeld zatzat = new Spielfeld(20, 20, 50);
		long endTime = System.nanoTime();
		long totalTime = (endTime - startTime) / 1000000;
		zatzat.commandlineAllOpenOutput();
		zatzat.commandLineCurrentStateOutput();
		zatzat.leftClickOnFeld(0, 0);
		zatzat.commandLineCurrentStateOutput();
		System.out.println("so viele Minen wurden nicht automatisch gelegt: " + zatzat.getAnzM());
		System.out.println("time to build Spielfeld: " + totalTime + "ms");
	}

	static void runTimeTest() {
		System.out.println("");
		System.out.println("RunTimeTest");
		long startTime = System.nanoTime();
		Spielfeld[] z = new Spielfeld[1_000];
		for(int i = 0; i < 1_000; i++) {
			z[i] = new Spielfeld(50, 50, 50);
		}
		long endTime = System.nanoTime();
		float totalTime = (endTime - startTime) / 1000000;
		System.out.println("" + totalTime + "ms");
		System.out.println("and: " + (totalTime / 1_000) + " ms on average per Spielfeld");
	}

	static void automaticMineLayingTest() {
		System.out.println("");
		System.out.println("AutomaticMineLayingTest");
		int heightIn = 10;
		int widthIn = 10;
		int minesIn = 0;
		int minesOut = 0;
		int nearbyMinesNumber = 0;
		Spielfeld paouisdnaisn = new Spielfeld(heightIn, widthIn, minesIn);
		for(int i = 0; i < widthIn; i++) {
			for(int j = 0; j < heightIn; j++) {
				if(paouisdnaisn.checkIsFeldMine(i, j))
					minesOut++;
				if(paouisdnaisn.checkNearbyMines(i, j) > 0)
					nearbyMinesNumber++;
			}
		}
		System.out.println("sind so viele Minen drin, wie erwartet?");
		annahmeGleich(0, 0, minesOut, minesIn);
		System.out.println("checken die Felder, ob eine Mine außen rum ist?");
		annahmeGleich(0, 0, nearbyMinesNumber, 0);

		minesIn = 100;
		minesOut = 0;
		nearbyMinesNumber = 0;
		Spielfeld paouisdnaisn1 = new Spielfeld(heightIn, widthIn, minesIn);
		for(int i = 0; i < widthIn; i++) {
			for(int j = 0; j < heightIn; j++) {
				if(paouisdnaisn1.checkIsFeldMine(i, j))
					minesOut++;
				if(paouisdnaisn1.checkNearbyMines(i, j) > 0)
					nearbyMinesNumber++;
			}
		}
		System.out.println("sind so viele Minen drin, wie erwartet?");
		annahmeGleich(0, 0, minesOut, minesIn);
		System.out.println("checken die Felder, ob eine Mine außen rum ist?");
		annahmeGleich(0, 0, nearbyMinesNumber, 100);

		minesIn = 66;
		minesOut = 0;
		Spielfeld paouisdnaisn11 = new Spielfeld(heightIn, widthIn, minesIn);
		for(int i = 0; i < widthIn; i++) {
			for(int j = 0; j < heightIn; j++) {
				if(paouisdnaisn11.checkIsFeldMine(i, j))
					minesOut++;
				if(paouisdnaisn11.checkNearbyMines(i, j) > 0)
					nearbyMinesNumber++;
			}
		}
		System.out.println("sind so viele Minen drin, wie erwartet?");
		annahmeGleich(0, 0, minesOut, minesIn);

		minesIn = 50;
		minesOut = 0;
		Spielfeld paouisdnaisn111 = new Spielfeld(heightIn, widthIn, minesIn);
		for(int i = 0; i < widthIn; i++) {
			for(int j = 0; j < heightIn; j++) {
				if(paouisdnaisn111.checkIsFeldMine(i, j))
					minesOut++;
				if(paouisdnaisn111.checkNearbyMines(i, j) > 0)
					nearbyMinesNumber++;
			}
		}
		System.out.println("sind so viele Minen drin, wie erwartet?");
		annahmeGleich(0, 0, minesOut, minesIn);

		minesIn = -50;
		minesOut = 0;
		Spielfeld paouisdnaisn1111 = new Spielfeld(heightIn, widthIn, minesIn);
		for(int i = 0; i < widthIn; i++) {
			for(int j = 0; j < heightIn; j++) {
				if(paouisdnaisn1111.checkIsFeldMine(i, j))
					minesOut++;
				if(paouisdnaisn1111.checkNearbyMines(i, j) > 0)
					nearbyMinesNumber++;
			}
		}
		System.out.println("sind so viele Minen drin, wie erwartet?");
		annahmeGleich(0, 0, minesOut, 50);


	}
}