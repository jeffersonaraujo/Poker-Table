package br.ufpb.dce.pa2.pokertable.model;

public class Player{
	private String name;	
	private int money;
		
	public Player(){
		this("Player",0);
	}
	
	public Player(String nome){
		this(nome, 0);
	}
	
	public Player(String name, int money) {
		this.name = name;
		this.money = money;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getMoney() {		
		return this.money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}

	public void subMoney(int value) {
		this.money = this.money - value;
	}	
}