package co.uk.epicguru.examples;

import co.uk.epicguru.main.GraphSampler;
import co.uk.epicguru.main.Param;

public class SimpleGraph implements GraphSampler {

	@Override
	public float sample(Param p) {
		
		// Y = X / 2
		// About as simple as it gets.
		
		return p.getX() / 2f;
	}

}
