package co.uk.epicguru.main;

public final class Param {

	private float x;
	private float timer;
	private float delta;
	
	public float getX(){
		return this.x;
	}
	
	public float getTimer(){
		return this.timer;
	}
	
	public Param(float x, float timer, float delta){
		this.x = x;
		this.timer = timer;
		this.delta = delta;
	}
	
	protected void setX(float x){
		this.x = x;
	}
	
	public float getDelta(){
		return this.delta;
	}
	
}
