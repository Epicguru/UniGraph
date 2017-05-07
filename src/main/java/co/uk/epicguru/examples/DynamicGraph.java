package co.uk.epicguru.examples;

import com.badlogic.gdx.math.MathUtils;

import co.uk.epicguru.main.GraphSampler;
import co.uk.epicguru.main.Param;

public class DynamicGraph implements GraphSampler {

	@Override
	public float sample(Param p) {
		
		// We want to make a sine wave that 'moves'. We can use the timer value for this.
		// Y = cos(X * 10 + Time * 300) * 10
		// In this equation, 20 is the amplitude of the wave, 300 is the speed of the wave and 20 is the height of the wave.
		
		return MathUtils.cosDeg(p.getX() * 10f + p.getTimer() * 300f) * 10f;
	}
	
	public float getEnd(){
		
		// We need to say where this graph end, by default it will end at 1000.
		
		return 100f;
	}

}
