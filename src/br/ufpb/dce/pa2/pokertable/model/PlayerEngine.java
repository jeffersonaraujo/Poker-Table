package br.ufpb.dce.pa2.pokertable.model;

/**
 * PlayerEngine
 * 
 * @author Vicente
 *
 */
public class PlayerEngine extends Player {
	private int posX;
	private int posY;
	
	public PlayerEngine(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}
	public PlayerEngine(String nome, String picture, int posX, int posY) {
		super(nome, picture);
		this.posX = posX;
		this.posY = posY;
	}
	public PlayerEngine(String nome, int money, String picture, int posX, int posY) {
		super(nome, money, picture);
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
}