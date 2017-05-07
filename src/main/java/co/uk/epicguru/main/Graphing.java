package co.uk.epicguru.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public final class Graphing {

	private static ShapeRenderer shapes = new ShapeRenderer();

	public static void renderGraph(OrthographicCamera camera, Batch batch, float unit, GraphSampler sampler, float sampleRate, float sampleStart, float sampleEnd){

		if(sampleRate <= 0){
			sampleRate = 0.0001f;
		}
		
		Vector3 mousePos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));		
		Vector2 mousePosReal = new Vector2(mousePos.x, mousePos.y);	
		Circle c = new Circle();
		c.radius = 10;
		
		batch.end();
		shapes.setProjectionMatrix(camera.combined);
		shapes.begin(ShapeType.Filled);

		float oldY = sampler.sample(sampleStart);
		for(float x = sampleStart + sampleRate; x < sampleEnd; x += sampleRate){
			
			float currentY = sampler.sample(x);

			float X = x - sampleRate;
			
			line(X, oldY, x, currentY, unit, Color.RED, Color.BLUE);
			
			c.x = x * unit;
			c.y = currentY * unit;
			if(c.contains(mousePosReal)){
				RenderUtils.mousePos = mousePosReal;
				int DP = 1;
				RenderUtils.mouseValue = "X: " + String.format("%." + DP + "f", x) + "\nY: " + String.format("%." + DP + "f", currentY);
			}
			
			if(X == 0){				
				c.x = X * unit;
				c.y = oldY * unit;
				if(c.contains(mousePosReal)){
					RenderUtils.mousePos = mousePosReal;
					int DP = 1;
					RenderUtils.mouseValue = "X: " + String.format("%." + DP + "f", X) + "\nY: " + String.format("%." + DP + "f", oldY);
				}
			}
			
			oldY = currentY;
		}
		shapes.end();
		batch.begin();

	}

	private static void line(float x, float y, float x2, float y2, float unit, Color a, Color b){
		// x * unit, y * unit, x2 * unit, y2 * unit, a, b
		float width = UniGraph.camera.zoom * 3f;
		shapes.rectLine(x * unit, y * unit, x2 * unit, y2 * unit, width, a, b);
	}

}
