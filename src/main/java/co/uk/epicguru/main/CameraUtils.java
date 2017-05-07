package co.uk.epicguru.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public final class CameraUtils{

	public static float zoom = 1;
	
	public static void moveCamera(OrthographicCamera camera){		
		if(Gdx.input.isButtonPressed(Buttons.MIDDLE)){
			// Pan
			camera.position.x += -Gdx.input.getDeltaX() * camera.zoom;
			camera.position.y += Gdx.input.getDeltaY() * camera.zoom;
		}	
		
		camera.zoom += (zoom - camera.zoom) * 0.1f;
		
		Gdx.input.setInputProcessor(new InputProcessor(){

			@Override
			public boolean keyDown(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				float size = 0.2f;
				if(amount > 0){
					zoom *= 1f + size;
				}else{
					zoom *= 1f - size;					
				}
				return false;
			}
			
		});
		
		
		
	}	
}
