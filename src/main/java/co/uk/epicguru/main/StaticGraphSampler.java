package co.uk.epicguru.main;

import java.util.ArrayList;

import com.badlogic.gdx.math.Interpolation;

public abstract class StaticGraphSampler implements GraphSampler {

	public static final float MINIMUM_SAMPLE_RATE = 0.001f;
	private ArrayList<Float> points = new ArrayList<Float>();
	private Interpolation interpolation = Interpolation.linear;
	private float sampleRate;
	
	public StaticGraphSampler(){
		if(sampleRate < StaticGraphSampler.MINIMUM_SAMPLE_RATE){
			sampleRate = StaticGraphSampler.MINIMUM_SAMPLE_RATE;
		}
		
		this.sampleRate = 1;	// CURRENTLY DISABLED!!!	
	}
	
	public float getSampleRate(){
		return this.sampleRate;
	}
	
	public Interpolation getInterpolation(){
		return this.interpolation;
	}
	
	/**
	 * Removes last (index 0) point and adds the new point on to the end.
	 */
	public void shiftPoint(float y){
		if(points.size() > 0)
			points.remove(0);
		points.add(y);
	}
	
	public void addPoint(float y){
		this.points.add(y);
	}
	
	public void removePoint(int index){
		this.points.remove(index);
	}
	
	public int getPointsLength(){
		return this.points.size();
	}
	
	public float getPoint(int x){
		return points.get(x);
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
	
	public void runGraph(Param p) { };
	
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
