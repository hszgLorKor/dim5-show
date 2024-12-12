class Feld {
	boolean isMine;
	int nearbyMines;
	boolean isOpen = false;
	
	Feld (boolean mine) {
		isMine = mine;
	}
	void setIsMine (boolean mine) {isMine = mine;}
	boolean getIsMine() {
		return isMine;
	}	
	void setNearbyMines() {
		nearbyMines++;
	}
	int getNearbyMines() {
		return nearbyMines;
	}	
	void setIsOpen() {
		isOpen = true;
	}
	boolean getIsOpen() {
		return isOpen;
	}
}