package br.ufpb.dce.pa2.pokertable.model;

public class Player{
	private String name;	
	private int money;
	private String picture;
		
	public Player(){
		this("Player",0, "menone.png");
	}
	
	public Player(String nome, String picture){
		this(nome, 0, picture);
	}
	
	public Player(String name, int money, String picture) {
		this.name = name;
		this.money = money;
		this.picture = picture;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}	
}