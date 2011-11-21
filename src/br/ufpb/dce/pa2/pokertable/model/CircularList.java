package br.ufpb.dce.pa2.pokertable.model;

public class CircularList{
	private Player[] lista;
	private int size;
	
	public CircularList() {
		this.lista = new Player[4];
		this.size = 0;
	}
	
	public boolean add(Player o){
		for(int i = 0; i < lista.length; i++){
			if(lista[i].equals(null)){
				lista[i] = o;
				this.size++;
				return true;
			}
		}
		return false;
	}
	
	public boolean remove(Player o){
		for(int i = 0; i < lista.length; i++)
			if(lista[i].equals(o)){
				lista[i] = null;
				this.size--;
				return true;
			}
		return false;
	}
	
	public boolean remove(int i){
		if(lista[i] != null){
			lista[i] = null;
			this.size--;
			return true;
		}
		return false;
	}
	
	public Player get(int i){
		return lista[i];
	}
	
	public Player next(int i){
		if(i < lista.length - 1)
			return lista[i + 1];
		else
			return lista[0];
	}
	
	public int size(){
		return this.size;
	}
	
	public String toString(){
		String string = "";
		for(int i = 0; i < lista.length; i++)
			if(!lista[i].equals(null))
				string += "Nome: " + lista[i].getName() + "Posição: " + i + "\n";
		return string;
	}
}