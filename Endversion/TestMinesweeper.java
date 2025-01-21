class TestMinesweeper {
	public static void main(String[] args) {
		mineInCornerTest();
		mineInCenterTest();
		multipleMinesTest();
		minesAroundAFieldTest();
		automaticMineLayingTest();
		leftclickTest();
	}
	
	static void annahmeGleich (int h, int b, int ist, int soll) {
		if (ist == soll) {
			System.out.println("correct: h" + h + " and: b" + b + " as expected: " + soll);
		}
		else {
			System.out.println("error at: h" + h + " and: b" + b + " is: " + ist + ", but expected:  " + soll);
		}
	}
	
	static boolean annahmeWertVergleich(int ist, int soll) {
		if (ist == soll) {
			return true;
		}
		return false;
	}
	
	static void mineInCornerTest() {
		System.out.println("mine in corner at h:0, w:0 test");
		int[][] mineCoords = new int[2][1];
		mineCoords[0][0] = 0;
		mineCoords[1][0] = 0;
		MinesweeperMap testMap = new MinesweeperMap(9, 9, 0);
		testMap.minenVerleger(mineCoords);
		annahmeGleich(0, 1, testMap.manuallyCheckNearbyMines(0, 1), 1);
		annahmeGleich(1, 1, testMap.manuallyCheckNearbyMines(1, 1), 1);
		annahmeGleich(0, 0, testMap.manuallyCheckNearbyMines(0, 0), 0);
		annahmeGleich(5, 5, testMap.manuallyCheckNearbyMines(5, 5), 0);
		System.out.println("");

	}

	static void mineInCenterTest() {
		System.out.println("mine in center at h:4, w:4 test");
		int[][] mineCoords = new int[2][1];
		mineCoords[0][0] = 4;
		mineCoords[1][0] = 4;
		MinesweeperMap testMap = new MinesweeperMap(9, 9, 0);
		testMap.minenVerleger(mineCoords);
		annahmeGleich(3, 4, testMap.manuallyCheckNearbyMines(3, 4), 1);
		annahmeGleich(4, 5, testMap.manuallyCheckNearbyMines(4, 5), 1);
		annahmeGleich(4, 4, testMap.manuallyCheckNearbyMines(4, 4), 0);
		annahmeGleich(0, 0, testMap.manuallyCheckNearbyMines(0, 0), 0);
		System.out.println("");
	}

	static void multipleMinesTest() {
		System.out.println("multiple mines in center at h:3, w:4 and h:4, w:3 and h:4, w:5 and h:5, w:4 and test");
		int[][] mineCoords = new int[2][5];
		mineCoords[0][0] = 3;
		mineCoords[1][0] = 4;
		mineCoords[0][1] = 4;
		mineCoords[1][1] = 3;
		mineCoords[0][2] = 4;
		mineCoords[1][2] = 5;
		mineCoords[0][3] = 5;
		mineCoords[1][3] = 4;
		mineCoords[0][4] = 4;
		mineCoords[1][4] = 6;
		MinesweeperMap testMap = new MinesweeperMap(9, 9, 0);
		testMap.minenVerleger(mineCoords);
		annahmeGleich(2, 4, testMap.manuallyCheckNearbyMines(2, 4), 1);
		annahmeGleich(3, 4, testMap.manuallyCheckNearbyMines(3, 4), 2);
		annahmeGleich(4, 4, testMap.manuallyCheckNearbyMines(4, 4), 4);
		annahmeGleich(5, 4, testMap.manuallyCheckNearbyMines(5, 4), 2);
		annahmeGleich(5, 5, testMap.manuallyCheckNearbyMines(5, 5), 3);
		annahmeGleich(0, 0, testMap.manuallyCheckNearbyMines(0, 0), 0);
		System.out.println("");

	}

	static void minesAroundAFieldTest() {
		System.out.println("mines all around the field h:4, w:4 test");
		int[][] mineCoords = new int[2][8];
		mineCoords[0][0] = 3;
		mineCoords[1][0] = 3;
		mineCoords[0][1] = 3;
		mineCoords[1][1] = 4;
		mineCoords[0][2] = 3;
		mineCoords[1][2] = 5;
		mineCoords[0][3] = 4;
		mineCoords[1][3] = 3;
		mineCoords[0][4] = 4;
		mineCoords[1][4] = 5;
		mineCoords[0][5] = 5;
		mineCoords[1][5] = 3;
		mineCoords[0][6] = 5;
		mineCoords[1][6] = 4;
		mineCoords[0][7] = 5;
		mineCoords[1][7] = 5;
		MinesweeperMap testMap = new MinesweeperMap(9, 9, 0);
		testMap.minenVerleger(mineCoords);
		annahmeGleich(4, 4, testMap.manuallyCheckNearbyMines(4, 4), 8);
		annahmeGleich(3, 3, testMap.manuallyCheckNearbyMines(3, 3), 2);
		annahmeGleich(2, 4, testMap.manuallyCheckNearbyMines(2, 4), 3);
		annahmeGleich(0, 0, testMap.manuallyCheckNearbyMines(0, 0), 0);
		System.out.println("");

	}

	static void automaticMineLayingTest() {
		System.out.println("automatic mine laying test");
		MinesweeperMap testMap = new MinesweeperMap(5, 5, 25);
		if(annahmeWertVergleich(testMap.getNumberOfMines(), 0)) {
			System.out.println("all 25 mines have been laid automatically");
		}
		else  {
			System.out.println("incorrect minelaying: " + testMap.getNumberOfMines() + " are left after distributing");
		}
		MinesweeperMap testMap2 = new MinesweeperMap(5, 5, 0);
		if(annahmeWertVergleich(testMap2.getNumberOfMines(), 0)) {
			System.out.println("no mine has been laid automatically");
		}
		else  {
			System.out.println("incorrect minelaying: " + testMap2.getNumberOfMines() + " are left after distributing");
		}
		MinesweeperMap testMap3 = new MinesweeperMap(5, 5, -25);
		if(annahmeWertVergleich(testMap3.getNumberOfMines(), 0)) {
			System.out.println("all -25 mines have been converted to 25 and been laid automatically");
		}
		else  {
			System.out.println("incorrect minelaying: " + testMap3.getNumberOfMines() + " are left after distributing");
		}
		MinesweeperMap testMap4 = new MinesweeperMap(5, 5, 30);
		if(annahmeWertVergleich(testMap4.getNumberOfMines(), 5)) {
			System.out.println("trying to lay 30 mines in a map with 25 fields resulted in 5 mines not being laid");
		}
		else  {
			System.out.println("incorrect minelaying: " + testMap4.getNumberOfMines() + " are left after distributing");
		}
		System.out.println("");
	}

	static void leftclickTest() {
		System.out.println("left click test");
		MinesweeperMap testMap = new MinesweeperMap(5, 5, 0);
		int[][] mineCoords = new int[2][5];
		mineCoords[0][0] = 2;
		mineCoords[1][0] = 0;
		mineCoords[0][1] = 2;
		mineCoords[1][1] = 1;
		mineCoords[0][2] = 2;
		mineCoords[1][2] = 2;
		mineCoords[0][3] = 2;
		mineCoords[1][3] = 3;
		mineCoords[0][4] = 2;
		mineCoords[1][4] = 4;
		testMap.minenVerleger(mineCoords);
		testMap.leftClickOnField(0, 0);
		System.out.println("left click on h:0, w:0");
		annahmeGleich(0, 0, testMap.manuallyCheckNearbyMines(0, 0), 0);
		annahmeGleich(2, 2, testMap.manuallyCheckNearbyMines(2, 2), 2);
		annahmeGleich(1, 2, testMap.manuallyCheckNearbyMines(1, 2), 3);
		if(testMap.getFeldIsOpen(0, 0)) {
			System.out.println("correct: field h:0, w:0 is open");
		}
		else {
			System.out.println("incorrect: field h:0, w:0 is not open");
		}
		if(testMap.getFeldIsOpen(0, 1)) {
			System.out.println("correct: field h:0, w:1 is open");
		}
		else {
			System.out.println("incorrect: field h:0, w:1 is not open");
		}
		if(testMap.getFeldIsOpen(1, 4)) {
			System.out.println("correct: field h:1, w:4 is open");
		}
		else {
			System.out.println("incorrect: field h:1, w:4 is not open");
		}
		if(!testMap.getFeldIsOpen(2, 2)) {
			System.out.println("correct: field h:2, w:2 is closed");
		}
		else {
			System.out.println("incorrect: field h:2, w:2 is open");
		}
		if(!testMap.getFeldIsOpen(4, 4)) {
			System.out.println("correct: field h:4, w:4 is closed");
		}
		else {
			System.out.println("incorrect: field h:4, w:4 is open");
		}

		System.out.println("");
		System.out.println("left click on bomb h:2, w:0 test");
		testMap.leftClickOnField(2, 0);
		if(testMap.getFeldIsOpen(2, 0)) {
			System.out.println("correct: field h:2, w:0 is open");
		}
		else {
			System.out.println("incorrect: field h:2, w:0 is not open");
		}
		if(testMap.getGameIsLost()) {
			System.out.println("correct: game status has changed to: lost");
		}
		else {
			System.out.println("error: game status does not show: lost");
		}

		System.out.println("");
		System.out.println("left click on field h:4, w:4 after game is lost test");
		testMap.leftClickOnField(4, 4);
		if(!testMap.getFeldIsOpen(4, 4)) {
			System.out.println("correct: field h:4, w:4 remains closed");
		}
		else {
			System.out.println("incorrect: field h:4, w:4 is open");
		}
	}
}