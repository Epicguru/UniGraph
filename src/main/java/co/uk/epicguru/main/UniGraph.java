package co.uk.epicguru.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class UniGraph extends Game{

	public static void log(Object text){
		System.out.println(text == null ? "null" : text.toString());
	}
	
	public static void main(String... args){
		UniGraph.args(args);

		// Create game instance
		UniGraph graph = new UniGraph();

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Uni Graph");

		new Lwjgl3Application(graph, config);

		// Done
		System.gc();
		System.exit(0);
	}
	
	private static void args(String[] args){
		
	}
	
	public static Color backgroundColour = new Color(0.9f, 0.9f, 0.9f, 1);
	public static ColourTheme theme = ColourTheme.DARK;
	private static GraphSampler graph = (x) -> {
		return MathUtils.cosDeg(x * 20f) * 20f;
	};
	private Batch batch;
	private OrthographicCamera camera;
	private float UNIT = 32f;
	
	public void create() {
		log("Hello world!");
		log("UniGraph created by James Billy, this is free software and source code is available @ " + "https://github.com/Epicguru/UniGraph");
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
	}
	
	public void resize (int width, int height) {
		
		this.camera.setToOrtho(false, width, height);
		
	}
	
	private void updateBG(){
		theme = ColourTheme.DARK;
		
		switch(theme){
		case DARK:
			UniGraph.backgroundColour = Color.DARK_GRAY;
			break;
		case LIGHT:			
			UniGraph.backgroundColour = Color.LIGHT_GRAY;
			break;
		}
	}
	
	public void update(float delta){
		
		// Update others
		this.updateBG();
		Gdx.graphics.setTitle("Uni Graph - " + Gdx.graphics.getFramesPerSecond() + "fps");
		CameraUtils.moveCamera(camera);
		
		this.camera.update();
	}
	
	public void render(){
		
		float deltaTime = Gdx.graphics.getDeltaTime();
		this.update(deltaTime);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		Gdx.gl.glClearColor(backgroundColour.r, backgroundColour.g, backgroundColour.b, backgroundColour.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		RenderUtils.renderGrid(camera, UNIT, batch);
		
		batch.end();
	}
}
