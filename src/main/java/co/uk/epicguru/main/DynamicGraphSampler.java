package co.uk.epicguru.main;

import java.util.ArrayList;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;

public class DynamicGraphSampler implements GraphSampler {

	public static final float MINIMUM_SAMPLE_RATE = 0.001f;
	private ArrayList<Float> points = new ArrayList<Float>();
	private Interpolation interpolation = Interpolation.linear;
	private float sampleRate;
	
	public DynamicGraphSampler(float sampleRate){
		if(sampleRate < DynamicGraphSampler.MINIMUM_SAMPLE_RATE){
			sampleRate = DynamicGraphSampler.MINIMUM_SAMPLE_RATE;
		}
		
		this.sampleRate = 1;	// CURRENTLY DISABLED!!!
		
		for(int i = 0; i < 100; i++){
			points.add(MathUtils.cosDeg(i * 10f) * 20f);
		}		
	}
	
	public float getSampleRate(){
		return this.sampleRate;
	}
	
	public Interpolation getInterpolation(){
		return this.interpolation;
	}
	
	protected float interpolate(float x){
		
		// We need to find the points which the x in in between.
		// And interpolate between them to find the correct value;
		
		if(this.points.isEmpty())
			return 0;
		
		int point = (int) (x / this.getSampleRate());
	
		if(point >= this.points.size())
			return 0;
		
		float start = this.points.get(point);
		float end = this.points.get((point + 1) >= points.size() ? point : point + 1);
		
		return this.getInterpolation().apply(start, end, (x - point) / this.getSampleRate());
		
	}
	
	public float getY(Param p){
		return this.interpolate(p.getX());
	}
	
	@Override
	public float sample(Param p) {
		if(p.getX() < 0)
			return 0;
		return this.getY(p);
	}
	
	public float getEnd(){
		return points.isEmpty() ? -1f : (this.points.size()) * this.getSampleRate();
	}

}
