package co.uk.epicguru.main;

public final class Param {

	private float x;
	private float timer;
	
	public float getX(){
		return this.x;
	}
	
	public float getTimer(){
		return this.timer;
	}
	
	public Param(float x, float timer){
		this.x = x;
		this.timer = timer;
	}
	
	protected void setX(float x){
		this.x = x;
	}
	
}
