package co.uk.epicguru.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public final class RenderUtils {

	private static ShapeRenderer shapes;
	
	private static void createShapes(){
		
		if(shapes == null){
			shapes = new ShapeRenderer();
		}
		
	}
	
	public static void renderGrid(OrthographicCamera camera, float unit, Batch batch){
		
		createShapes();
		shapes.setAutoShapeType(true);
		
		batch.end();
		switch(UniGraph.theme){
		case DARK:
			shapes.setColor(Color.WHITE);
			break;
		case LIGHT:
			shapes.setColor(Color.MAROON);
			break;
			
		}
		shapes.begin();
		shapes.setProjectionMatrix(camera.combined);
		
		float width = camera.viewportWidth / unit * camera.zoom;
		float height = camera.viewportHeight / unit * camera.zoom;
		
		final int EXTRA = 1;
		
		int startX = (int)(camera.position.x / unit - width / 2);
		int startY = (int)(camera.position.y / unit - height / 2 - EXTRA);
		int endX = (int)(camera.position.x / unit + width / 2 + EXTRA * 2);
		int endY = (int)(camera.position.y / unit + height / 2 + EXTRA * 2);
		
		for(int x = startX; x < endX; x++){
			for(int y = startY; y < endY; y++){
				shapes.rect(x * unit, y * unit, unit, unit);
			}
		}
		
		shapes.end();
		batch.begin();
		
	}
	
}
