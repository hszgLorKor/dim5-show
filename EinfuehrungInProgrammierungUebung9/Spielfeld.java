class Spielfeld {

	// Spielfeld felder mit Höhe h und Breite b und Feldern als Inhalt
	Feld[][] felder;
	// Anzahl anzM der Minen auf dem Spielfeld
	int anzM;
	boolean gamestateActive = true;
	//Spielfeldkonstruktur
	Spielfeld(int h, int b, int inputMines) {
		felder = new Feld[h][b];
		if(inputMines < 0) {
			anzM = inputMines * (-1);
		}
		else {
			anzM = inputMines;
		}
		fillMinesIntoFeld();
	}
	//automatische Füllung des Spielfelds mit Minen und Feldern
	void fillMinesIntoFeld() {
		double feldCount = felder.length * felder[0].length;
		for(int i = 0; i < felder.length; i++) {
			for(int j = 0; j < felder[i].length; j++) {
				if (anzM > 0 && Math.random() < (anzM / feldCount) || anzM == feldCount){
					felder[i][j] = new Feld(true);
					anzM--;
					
				}
				else {
					felder[i][j] = new Feld(false);
				}
				feldCount--;
			}
		}
		minenChecker();
	}
	//checkt, ob ein Feld neben einer Mine liegt, wenn ja, dann "nearby Mines" Attribut vom Feld ++
	void minenChecker() {
		for(int i = 0; i < felder.length; i++) {
			for(int j = 0; j < felder[i].length; j++) {
				if(felder[i][j].getIsMine()) {
					if((j+1)< felder[i].length) {
						felder[i][(j + 1)].setNearbyMines();
						if((i - 1) > -1) {
							felder[i-1][j + 1].setNearbyMines();
						}
						if((i+1) < felder.length) {
							felder[i+1][j + 1].setNearbyMines();
						}
					}
					if((j-1) >= 0) {
						felder[i][(j - 1)].setNearbyMines();
						if((i - 1) > -1) {
							felder[i-1][j - 1].setNearbyMines();
						}
						if((i+1) < felder.length) {
							felder[i+1][j-1].setNearbyMines();
						}
					}
					if((i-1) > -1) {
						felder[i-1][j].setNearbyMines();
					}
					if((i+1) < felder.length) {
						felder[i+1][j].setNearbyMines();
					}
				}
			}
		}
	}
	//manuelles Minenlegen
	void minenVerleger(int[][] testMinePosition) {
		for(int i = 0; i < testMinePosition[0].length; i++) {
			felder[testMinePosition[0][i]][(testMinePosition[1][i])].setIsMine(true);
		}
		minenChecker();
	}
	//manuelles abchecken ob der Korrektheit des "nearby Mines" Attributs eines Feldes
	public int checkNearbyMines(int height, int width) {
		return (felder[height][width].getNearbyMines());
	}
	//checken, ob Feld eine Mine ist
	public boolean checkIsFeldMine(int height, int width) {
		return (felder[height][width].getIsMine());
	}
	//gibt ein aufgedecktes Spielfeld aus
	void commandlineAllOpenOutput() {
		for(int i = 0; i < felder.length; i++) {
			for(int j = 0; j < felder[i].length; j++) {
				if(felder[i][j].getIsMine()) {
					System.out.print("X ");
				}
				else {
					if(felder[i][j].getNearbyMines() == 0) {
						System.out.print("_ ");
					}
					else {
						System.out.print(felder[i][j].getNearbyMines() + " ");
					}
				}
			}
			System.out.println(" ");
		}
	}

	void commandLineCurrentStateOutput() {
		for(int i = 0; i < felder.length; i++) {
			for(int j = 0; j < felder[i].length; j++) {
				if(felder[i][j].getIsOpen()) {
					if (felder[i][j].getIsMine()) {
						System.out.print("X ");
					} else {
						if (felder[i][j].getNearbyMines() == 0) {
							System.out.print("_ ");
						} else {
							System.out.print(felder[i][j].getNearbyMines() + " ");
						}
					}
				}
				else System.out.print("░ ");
			}
			System.out.println(" ");
		}
		System.out.println("");
	}
	//deckt ein Feld (nach indexen) auf
	void leftClickOnFeld(int h, int b) {
		if(!felder[h][b].getIsOpen()) {
			felder[h][b].setIsOpen();
			if (felder[h][b].getIsMine()) {
				//TODO GAME OVER
			} else {
				if (felder[h][b].getNearbyMines() == 0) {
					if ((b + 1) < felder[0].length) {
						leftClickOnFeld(h, b + 1);
						if ((h - 1) > -1) {
							leftClickOnFeld(h - 1, b + 1);
						}
						if ((h + 1) < felder.length) {
							leftClickOnFeld(h + 1, b + 1);
						}
					}
					if ((b - 1) >= 0) {
						leftClickOnFeld(h, b - 1);
						if ((h - 1) > -1) {
							leftClickOnFeld(h - 1, b - 1);
						}
						if ((h + 1) < felder.length) {
							leftClickOnFeld(h + 1, b - 1);
						}
					}
					if ((h - 1) > -1) {
						leftClickOnFeld(h - 1, b);
					}
					if ((h + 1) < felder.length) {
						leftClickOnFeld(h + 1, b);
					}
					//alle nebenliegenden Felder öffnen
				} else felder[h][b].getNearbyMines();
			}
		}
	}

	int getAnzM() {
		return anzM;
	}
}