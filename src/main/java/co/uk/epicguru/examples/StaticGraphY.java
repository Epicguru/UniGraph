package co.uk.epicguru.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import co.uk.epicguru.main.Param;
import co.uk.epicguru.main.StaticGraphSampler;

public class StaticGraphY extends StaticGraphSampler{
	
	private float timer;
	
	public void runGraph(Param p){
		
		float timesPerSecond = 10;
		float interval = 1f / timesPerSecond;
		
		timer += p.getDelta();
		while(timer >= interval){
			timer -= interval;
			
			// X times per second...
			super.addPoint(Gdx.input.getDeltaY() / 5f);		
		}
		
	}
	
	public Color getColor(){
		return Color.RED;
	}

}
