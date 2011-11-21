package br.ufpb.dce.pa2.pokertable.model;

import java.util.LinkedList;
import java.util.List;

public class TableDummy implements ITable{
	private List<Player> players;
	private int dealerPosition;
	private int currentPlayerPosition;
	private int pot;
	private int currentTurnPot;
	private int currentTurnBet;
	private int minimumBet;
	private List<Player> playersOnTurn;

	public TableDummy() {
		players = new LinkedList<Player>();
		dealerPosition = 0;
		pot = 0;
		currentTurnPot = 0;
		currentTurnBet = 0;
		minimumBet = 0;
		players.add(new Player("Joãzinho", 18, 500));
		players.add(new Player("Zezinho", 19, 1000));
		players.add(new Player("Luizinho", 19, 750));
		players.add(new Player("Juninho", 20, 1250));
		playersOnTurn = players;
	}
	
	@Override
	public void setMinimumBet(int minimumBet) {
		this.minimumBet = minimumBet;
	}
	@Override
	public void begin() {
		if (players.get(getSmallBlindPosition()).getMoney() >= getSmallBlindBet()) {
			currentTurnPot += getSmallBlindBet();
			players.get(getSmallBlindPosition()).setMoney(players.get(getSmallBlindPosition()).getMoney() - getSmallBlindBet());
		}
		if (players.get(getBigBlindPosition()).getMoney() >= getBigBlindBet()) {
			currentTurnPot += getBigBlindBet();
			players.get(getSmallBlindPosition()).setMoney(players.get(getSmallBlindPosition()).getMoney() - getSmallBlindBet());
		}
	}
	@Override
	public int getDealerPosition() {
		return dealerPosition;
	}
	@Override
	public int getSmallBlindPosition() {
		if(getDealerPosition() < players.size() - 1)
			return getDealerPosition() + 1;
		else
			return 0;
	}
	@Override
	public int getBigBlindPosition() {
		if(getSmallBlindPosition() < players.size() - 1)
			return getSmallBlindPosition() + 1;
		else
			return 0;
	}
	@Override
	public int getSmallBlindBet() {
		return getBigBlindBet() / 2;
	}
	@Override
	public int getBigBlindBet() {
		return minimumBet;
	}
	@Override
	public int getCurrentTurnBet() {
		return currentTurnBet;
	}
	@Override
	public int getCurrentTurnPlayerBet(Player player) {
		return 0;
	}
	@Override
	public int getPot() {
		return pot;
	}
	@Override
	public int getCurrentTurnPot() {
		return currentTurnPot;
	}
	@Override
	public void nextPlayer(){
		if(currentPlayerPosition == 1)
			nextTurn();
			
		if(currentPlayerPosition < playersOnTurn.size() - 1)
			currentPlayerPosition++;
		else
			currentPlayerPosition = 0;
	}
	@Override
	public void bet(int value) {
		Player currentPlayer = playersOnTurn.get(currentPlayerPosition);
		int betValue;
		if(getCurrentTurnBet() + minimumBet - getCurrentTurnPlayerBet(currentPlayer) > currentPlayer.getMoney())
			betValue = currentPlayer.getMoney();
		else
			betValue = getCurrentTurnPlayerBet(currentPlayer) + minimumBet - getCurrentTurnBet();
		
		currentPlayer.setMoney(currentPlayer.getMoney() - betValue);
		currentTurnPot += betValue;
	}
	@Override
	public void call() {
		Player currentPlayer = playersOnTurn.get(currentPlayerPosition);
		int callValue;
		if(getCurrentTurnBet() - getCurrentTurnPlayerBet(currentPlayer) > currentPlayer.getMoney())
			callValue = currentPlayer.getMoney();
		else
			callValue = getCurrentTurnPlayerBet(currentPlayer) - getCurrentTurnBet();
		
		currentPlayer.setMoney(currentPlayer.getMoney() - callValue);
		currentTurnPot += callValue;
	}
	@Override
	public void check() {
		
	}
	@Override
	public void fold() {
		playersOnTurn.remove(currentPlayerPosition);
		nextPlayer();
	}
	@Override
	public void nextTurn() {
		pot += currentTurnPot;
		currentTurnPot = 0;
		currentTurnBet = 0;
	}
	@Override
	public void passDealer() {
		dealerPosition = getSmallBlindPosition();
	}
	@Override
	public void setDealerPostion(int dealerPosition) {
		this.dealerPosition = dealerPosition;
	}
	@Override
	public boolean EndOfTheGame() {
		return false;
	}
}