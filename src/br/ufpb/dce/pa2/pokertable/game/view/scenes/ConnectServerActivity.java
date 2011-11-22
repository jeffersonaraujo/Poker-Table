package br.ufpb.dce.pa2.pokertable.game.view.scenes;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class ConnectServerActivity extends BaseGameActivity {

	private static final int SERVER_PORT = 4444;

	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 320;

	private static final int DIALOG_ENTER_SERVER_IP_ID = 1;

	private static final short FLAG_MESSAGE_CLIENT_ADD_FACE = 1;
	private static final short FLAG_MESSAGE_CLIENT_MOVE_FACE = 2;
	private static final short FLAG_MESSAGE_SERVER_ADD_FACE = 3;
	private static final short FLAG_MESSAGE_SERVER_MOVE_FACE = 4;

	private Camera camera;

	@Override
	public Engine onLoadEngine() {
		camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera));
	}

	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub

	}

	@Override
	public Scene onLoadScene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}

}
