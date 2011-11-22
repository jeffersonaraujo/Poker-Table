package br.ufpb.dce.pa2.pokertable.model;

public class Player{
	private String name;	
	private int money;
	private int picture;
		
	public Player(){
		this("Player",0, 1);
	}
	
	public Player(String nome, int picture){
		this(nome, 0, picture);
	}
	
	public Player(String name, int money, int picture) {
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

	public int getPicture() {
		return picture;
	}

	public void setPicture(int picture) {
		this.picture = picture;
	}	
}