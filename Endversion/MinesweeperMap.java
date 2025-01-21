class MinesweeperMap {
	Feld[][] mapOfFields;
	int numberOfMines;
	int numberOfMinesConst;
	boolean gameIsLost = false;
	boolean gameIsWon = false;
	
	MinesweeperMap(int h, int b, int inputMines) {
		if(inputMines < 0) {
			numberOfMines = inputMines * (-1);
		}
		else {
			numberOfMines = inputMines;
		}
		numberOfMinesConst = numberOfMines;
		mapOfFields = new Feld[h][b];
		autoFillMinesIntoMap();
	}
	
	//lay mines automatically
	void autoFillMinesIntoMap() {
		double numberOfFields = mapOfFields.length * mapOfFields[0].length;
	
		for(int i = 0; i < mapOfFields.length; i++) {
			for(int j = 0; j < mapOfFields[0].length; j++) {
				if (numberOfMines > 0 && Math.random() <= (numberOfMines / numberOfFields) || numberOfMines == numberOfFields){
					mapOfFields[i][j] = new Feld(true);
					numberOfMines--;
				}
				else {
					mapOfFields[i][j] = new Feld(false);
				}
				numberOfFields--;
			}
		}
		autoCheckNearbyMines();
	}

	//adjust nearbyMinesCount for each field
	void autoCheckNearbyMines() {
		for(int i = 0; i < mapOfFields.length; i++) {
			for(int j = 0; j < mapOfFields[i].length; j++) {
				if(mapOfFields[i][j].getIsMine()) {
					if((j+1)< mapOfFields[i].length) {
						mapOfFields[i][(j + 1)].setNearbyMines();
						if((i - 1) > -1) {
							mapOfFields[i-1][j + 1].setNearbyMines();
						}
						if((i+1) < mapOfFields.length) {
							mapOfFields[i+1][j + 1].setNearbyMines();
						}
					}
					if((j-1) >= 0) {
						mapOfFields[i][(j - 1)].setNearbyMines();
						if((i - 1) > -1) {
							mapOfFields[i-1][j - 1].setNearbyMines();
						}
						if((i+1) < mapOfFields.length) {
							mapOfFields[i+1][j-1].setNearbyMines();
						}
					}
					if((i-1) > -1) {
						mapOfFields[i-1][j].setNearbyMines();
					}
					if((i+1) < mapOfFields.length) {
						mapOfFields[i+1][j].setNearbyMines();
					}
				}
			}
		}
	}
	
	//manual mine laying
	void minenVerleger(int[][] testMinePosition) {
		for(int i = 0; i < testMinePosition[0].length; i++) {
			mapOfFields[testMinePosition[0][i]][(testMinePosition[1][i])].setIsMine(true);
		}
		autoCheckNearbyMines();
	}

	int manuallyCheckNearbyMines(int height, int width) {
		return (mapOfFields[height][width].getNearbyMines());
	}
	
	boolean checkIsFeldMine(int height, int width) {
		return (mapOfFields[height][width].getIsMine());
	}

	boolean getGameIsLost() {
		return gameIsLost;
	}

	boolean getGameIsWon() {
		return gameIsWon;
	}
	
	int getNumberOfMines() {
		return numberOfMines;
	}
	boolean getFeldIsOpen(int h, int b) {
		return mapOfFields[h][b].getIsOpen();
	}
	boolean getFieldIsMarked(int h, int b) {
		return mapOfFields[h][b].getIsMarked();
	}

	void leftClickOnField(int h, int b) {
		if(h > (mapOfFields.length-1) || h < 0 || b < 0 || b > (mapOfFields[0].length-1) || gameIsLost || gameIsWon) {

		}
		else{
			if (!mapOfFields[h][b].getIsOpen()) {
				mapOfFields[h][b].setIsOpen();
				if (mapOfFields[h][b].getIsMine()) {
					gameIsLost = true;
				}
				else {
					if (mapOfFields[h][b].getNearbyMines() == 0) {
						if ((b + 1) < mapOfFields[0].length) {
							leftClickOnField(h, b + 1);
							if ((h - 1) > -1) {
								leftClickOnField(h - 1, b + 1);
							}
							if ((h + 1) < mapOfFields.length) {
								leftClickOnField(h + 1, b + 1);
							}
						}
						if ((b - 1) >= 0) {
							leftClickOnField(h, b - 1);
							if ((h - 1) > -1) {
								leftClickOnField(h - 1, b - 1);
							}
							if ((h + 1) < mapOfFields.length) {
								leftClickOnField(h + 1, b - 1);
							}
						}
						if ((h - 1) > -1) {
							leftClickOnField(h - 1, b);
						}
						if ((h + 1) < mapOfFields.length) {
							leftClickOnField(h + 1, b);
						}
						//alle nebenliegenden Felder öffnen
					}
				}
			}
		}
	}
		
	void checkIfGameIsWon() {
		int tempOpenFields = 0;
		int tempAllFields = (mapOfFields.length * mapOfFields[0].length) - numberOfMinesConst;
		for(int i = 0; i < mapOfFields.length; i++) {
			for(int j = 0; j < mapOfFields[i].length; j++) {
				if(mapOfFields[i][j].getIsOpen() && !mapOfFields[i][j].getIsMine()) {
					tempOpenFields++;
				}
			}
		}
		if(tempOpenFields == tempAllFields) {
			System.out.println("Game is won");
			gameIsWon = true;
		}
	}
	
	void rightClickOnField(int h, int b) {
		mapOfFields[h][b].setIsMarked();
	}
}