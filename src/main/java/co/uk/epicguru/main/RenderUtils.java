package co.uk.epicguru.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

public final class RenderUtils {

	private static ShapeRenderer shapes;
	private static BitmapFont font = new BitmapFont();
	public static String mouseValue;
	public static Vector2 mousePos;

	private static void createShapes(){

		if(shapes == null){
			shapes = new ShapeRenderer();
		}

	}
	
	private static Color c = new Color();
	private static Color lerp(Color a, Color b, float p){
		
		Interpolation i = Interpolation.linear;
		
		c.r = i.apply(a.r, b.r, p);
		c.g = i.apply(a.g, b.g, p);
		c.b = i.apply(a.b, b.b, p);
		c.a = i.apply(a.a, b.a, p);
		
		return c;
	}

	public static void renderGrid(OrthographicCamera camera, float unit, Batch batch){

		createShapes();
		shapes.setAutoShapeType(true);
		
		batch.end();
		shapes.begin();
		shapes.setProjectionMatrix(camera.combined);
		
		final float LIMIT = 5f;
		float p = (camera.zoom / LIMIT);
		if(p < 0)
			p = 0;
		if(p > 1)
			p = 1;
		
		if(camera.zoom > LIMIT){
			shapes.end();
			batch.begin();
			return;
		}		
		
		Color c = lerp(Color.FIREBRICK, UniGraph.backgroundColour, p);
		shapes.setColor(c);		
		

		float width = camera.viewportWidth / unit * camera.zoom;
		float height = camera.viewportHeight / unit * camera.zoom;

		final int EXTRA = 1;

		int startX = (int)(camera.position.x / unit - width / 2 - EXTRA);
		int startY = (int)(camera.position.y / unit - height / 2 - EXTRA);
		int endX = (int)(camera.position.x / unit + width / 2 + EXTRA * 2);
		int endY = (int)(camera.position.y / unit + height / 2 + EXTRA * 2);

		if(startX < 0)
			startX = 0;
		
		for(int x = startX; x < endX; x++){
			for(int y = startY; y < endY; y++){
				
				shapes.rect(x * unit, y * unit, unit, unit);
			}
		}

		shapes.end();
		batch.begin();

	}
	
	public static void renderMouseValue(Batch batch){
		
		if(mousePos == null || mouseValue == null)
			return;
		
		font.draw(batch, mouseValue, mousePos.x + 15, mousePos.y + 3);
		
		mousePos = null;
	}	

	public static void renderNumbers(OrthographicCamera camera, float unit, Batch batch){
		
		if(camera.zoom > 5f)
			return;
		
		float width = camera.viewportWidth / unit * camera.zoom;
		float height = camera.viewportHeight / unit * camera.zoom;

		final int EXTRA = 1;

		int startY = (int)(camera.position.y / unit - height / 2 - EXTRA);
		int endY = (int)(camera.position.y / unit + height / 2 + EXTRA * 2);
		
		font.setColor(Color.BLACK);
		
		float x = camera.position.x - width * unit / 2f;
		if(x < -20)
			x = -20;
		
		batch.end();
		shapes.setColor(Color.WHITE);
		shapes.begin(ShapeType.Filled);
		shapes.rect(x - 5, (camera.position.y) - (height / 2f * unit), 26, height * unit);
		shapes.end();
		batch.begin();
		
		
		for(int y = startY; y < endY; y++){
			font.draw(batch, "" + y, x + 3, y * unit + 5);
		}
	}
}
