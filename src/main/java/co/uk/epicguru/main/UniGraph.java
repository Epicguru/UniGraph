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

import co.uk.epicguru.examples.SoundStaticGraph;

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
	
	// HERE
	private static GraphSampler[] graphs = {
			//new StaticGraphX(),
			//new StaticGraphY()
			new SoundStaticGraph()
	};
	
	
	public static Color backgroundColour = new Color(0.9f, 0.9f, 0.9f, 1);
	public static ColourTheme theme = ColourTheme.LIGHT;
	private static Batch batch;
	public static OrthographicCamera camera;
	public static float timer;
	private float UNIT = 64f;
	
	public void create() {
		log("Hello world!");
		log("UniGraph created by James Billy, this is free software and source code is available @ " + "https://github.com/Epicguru/UniGraph");
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
	}
	
	public void resize (int width, int height) {
		
		camera.setToOrtho(false, width, height);
		
	}
	
	private void updateBG(){
		theme = ColourTheme.LIGHT;
		
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
		
		// Timer
		timer += delta;
		
		// Update others
		this.updateBG();
		Gdx.graphics.setTitle("Uni Graph - " + Gdx.graphics.getFramesPerSecond() + "fps");
		CameraUtils.moveCamera(camera);
		
		camera.update();
	}
	
	public void render(){
		
		float deltaTime = Gdx.graphics.getDeltaTime();
		this.update(deltaTime);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		Gdx.gl.glClearColor(backgroundColour.r, backgroundColour.g, backgroundColour.b, backgroundColour.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		RenderUtils.renderGrid(camera, UNIT, batch);
		RenderUtils.renderNumbers(camera, UNIT, batch);
		for(GraphSampler g : graphs){
			Graphing.renderGraph(camera, batch, UNIT, g, 1f, 0, g.getEnd() == -1 ? 1000 : g.getEnd());			
		}
		RenderUtils.renderMouseValue(batch);
		
		batch.end();
	}
}
