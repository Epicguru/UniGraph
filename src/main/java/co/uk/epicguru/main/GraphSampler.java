package co.uk.epicguru.main;

import com.badlogic.gdx.graphics.Color;

public interface GraphSampler {

	public float sample(Param p);
	
	public default void runGraph(Param p){
		
	}
	
	public default float getEnd(){
		return 1000f;
	}
	
	public default Color getColor(){
		return Color.BLACK;
	}
	
}
