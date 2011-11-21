package br.ufpb.dce.pa2.pokertable.model;

public class Player{
	private String name;	
	private int age;
	private int money;
	private int points;
		
	public Player(){
		this("Player",0,0);
		this.points = 0;
	}
	
	public Player(String nome){
		this(nome, 0, 0);
		this.points = 0;
	}
	
	public Player(String name, int age, int money) {
		this.name = name;
		this.age = age;
		this.money = money;
		this.points = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPoints() {
		return this.points;
	}
	
	public int getMoney() {		
		return this.money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}	
}