package co.uk.epicguru.main;

public interface GraphSampler {

	public float sample(Param p);
	
	public default float getStart(){
		return -1f;
	}
	
	public default float getEnd(){
		return -1f;
	}
	
}
