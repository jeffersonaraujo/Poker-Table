package br.ufpb.dce.pa2.pokertable.model;

/**
 * 
 * @author Vicente Neto
 *
 */
interface ITable {
	/**
	 * Sets the initial configuration of the table
	 */
	void begin();
	
	/**
	 * Sets the minimum bet of the table
	 * @param minimumBet the minimum bet of the table
	 */
	void setMinimumBet(int minimumBet);
	
	/**
	 * Returns the number of the Dealer position
	 * @return An integer referring to the number of the Dealer position on the table
	 */
	int getDealerPosition();
	
	/**
	 * Returns the number of the Small Blinder position
	 * @return An integer referring to the number of the Small Blinder position on the table
	 */
	int getSmallBlindPosition();
	
	/**
	 * Returns the number of the Big Blinder position
	 * @return An integer referring to the number of the Big Blinder position on the table
	 */
	int getBigBlindPosition();
	
	/**
	 * Change the Dealer to the next left player on the table
	 */
	void passDealer();
	
	/**
	 * Sets the position of the Dealer
	 * @param dealerPosition An integer referring to the new position of the Dealer on the table
	 */
	void setDealerPostion(int dealerPosition);
	
	/**
	 * Returns the sum of all bets
	 * @return The sum of all bets
	 */
	int getPot();
	
	/**
	 * 
	 * @return
	 */
	int getCurrentTurnPot();
	
	/**
	 * 
	 */
	void nextPlayer();
	
	/**
	 * Returns the minimum bet of the Small Blinder
	 * @return The minimum bet of the Small Blinder
	 */
	int getSmallBlindBet();
	
	/**
	 * Returns the minimum bet of the Big Blinder
	 * @return The minimum bet of the Big Blinder
	 */
	int getBigBlindBet();
	
	/**
	 * Returns the sum of bet's on the current turn
	 * @return The sum of bet's on the current turn
	 */
	int getCurrentTurnBet();
	
	/**
	 * Returns the sum of player's bet in the current turn
	 * @param player The player
	 * @return The sum of bet's in the current turn
	 */
	int getCurrentTurnPlayerBet(Player player);
	
	/**
	 * Increases the value of the bets on the current turn
	 * @param player The player who increases the bet
	 * @param value The value of the bet
	 */
	void bet(int value);
	
	/**
	 * 
	 * @param player 
	 */
	void check();
	
	/**
	 * Removes a player from the rest of the match
	 * @param player The player who will be removed from the match
	 */
	void fold();
	
	/**
	 * 
	 */
	void nextTurn();
	
	/**
	 * Increases the bet of a player in the current turn
	 * @param player The player
	 * @param value The value of the bet
	 */
	void call();
	
	/**
	 * Returns a Boolean value to tell if the game is over or not
	 * @return true if the game is over and False if the game is not over
	 */
	boolean EndOfTheGame();
}