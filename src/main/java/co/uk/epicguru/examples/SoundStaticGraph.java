package co.uk.epicguru.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.AudioRecorder;

import co.uk.epicguru.main.Param;
import co.uk.epicguru.main.StaticGraphSampler;

public class SoundStaticGraph extends StaticGraphSampler {
	
	static AudioRecorder recorder;
	public void runGraph(Param p){
			
		if(recorder == null)
			recorder = Gdx.audio.newAudioRecorder(22050, true);
			
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			super.clearPoints();			
			
			short[] test = new short[22050 * 10];
			recorder.read(test, 0, test.length);
			
			for(short s : test){
				super.addPoint((float)s);
			}			
		}		
	}	
}
