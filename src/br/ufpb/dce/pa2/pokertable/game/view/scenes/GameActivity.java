package br.ufpb.dce.pa2.pokertable.game.view.scenes;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import br.ufpb.dce.pa2.pokertable.game.util.SoundManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Game Activity
 * 
 * @author Jefferson Araujo, jefferssonaraujo[at]gmail[dot]com
 * @version 0.0.1 Copyright (C) 2011 Poker Table
 */
public class GameActivity extends BaseGameActivity {

	// variaveis da camera
	private float cameraWidth;
	private float cameraHeight;
	private Camera mCamera;

	// variaveis utilizadas para o sprite da mesa
	private TextureRegion mRegion;
	private BitmapTextureAtlas mTexture;
	private Sprite mSprite;

	// variaveis utilizadas para o sprite do Score
	private BitmapTextureAtlas mFontTexture;
	private ChangeableText scoreText;
	private Font mFont;

	// variavel utilizadas para o som
	private static SoundManager sm;

	// variaveis utilizadas para o sprite do jogador
	private TextureRegion mPlayerRegion;
	private BitmapTextureAtlas mPlayerTexture;

	private Boolean sair;

	// carrega a engine
	public Engine onLoadEngine() {

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		// se adapta a tela de qualquer aparelho
		cameraWidth = dm.widthPixels;
		cameraHeight = dm.heightPixels;

		// cria a camera
		mCamera = new Camera(0, 0, cameraWidth, cameraHeight);

		// cria o objeto engine
		mEngine = new Engine(new EngineOptions(true,
				ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(
						cameraWidth, cameraHeight), mCamera)
				.setNeedsSound(true).setNeedsMusic(true));
		return mEngine;
	}

	// carrega os recursos alocados
	public void onLoadResources() {

		// cria o objeto de textura, que é onde guardamos as imagens.
		// esses valores tem que ser na base de 2 (2, 4, 8 ...)
		this.mTexture = new BitmapTextureAtlas(1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		// define um caminho padrao dentro da pasta /assets para as imagens
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		// cria um objeto que adiciona a imagem na texture e aponta para ela
		this.mRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mTexture, this, "layouttable.png", 0, 0);

		// carrega a textura na engine para poder ser usada
		this.mEngine.getTextureManager().loadTexture(this.mTexture);

		// Carrega os recursos da fonte de Score
		this.mFontTexture = new BitmapTextureAtlas(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		// Define as propriedades da fonte
		this.mFont = new Font(this.mFontTexture, Typeface.create(
				Typeface.DEFAULT, Typeface.BOLD), 20, true, Color.WHITE);

		this.mEngine.getFontManager().loadFont(this.mFont);

		// carrega a textura da fonte na engine para poder ser usada
		this.mEngine.getTextureManager().loadTexture(this.mFontTexture);

		setVolumeControlStream(AudioManager.STREAM_MUSIC);

		// chama o som do jogo
		sm = SoundManager.getInstance(this);

		// cria o objeto de textura dos jogadores
		this.mPlayerTexture = new BitmapTextureAtlas(128, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		switch (SelectPlayerActivity.playerSelect) {
		case 1:
			this.mPlayerRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(this.mPlayerTexture, this, "girl.png", 0,
							0);
			break;
		case 2:
			this.mPlayerRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(this.mPlayerTexture, this, "menone.png",
							0, 0);
			break;
		case 3:
			this.mPlayerRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(this.mPlayerTexture, this, "mentwo.png",
							0, 0);
			break;
		case 4:
			this.mPlayerRegion = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(this.mPlayerTexture, this, "menthree.png",
							0, 0);
		}

		this.mEngine.getTextureManager().loadTexture(this.mPlayerTexture);
	}

	public Scene onLoadScene() {
		// cria a cena do jogo
		final Scene scene = new Scene();

		// centraliza o sprite no meio da tela
		float centerX = (cameraWidth - this.mRegion.getWidth()) / 2;
		float centerY = (cameraHeight - this.mRegion.getHeight()) / 2;

		// cria o sprite e posiciona no meio da tela
		this.mSprite = new Sprite(centerX, centerY, this.mRegion);

		// adiciona na cena
		scene.attachChild(mSprite);

		// cria o texto do Score
		this.scoreText = new ChangeableText(680, 10, this.mFont, "Score: 0000");

		// adiciona na cena
		scene.attachChild(scoreText);

		// cria o sprite e posiciona na cadeira da mesa
		this.mSprite = new Sprite(100, 10, this.mPlayerRegion);
		// adiciona na cena
		scene.attachChild(mSprite);

		// retorna a cena para a engine ser executada
		return scene;
	}

	// quando completa o carregamento aparece a mensagem
	public void onLoadComplete() {
		Toast.makeText(this, "Seja bem vindo ao Poker Table", Toast.LENGTH_LONG)
				.show();
		// som das fichas caindo
		sm.playSound(1);
	}

	// se o botão voltar for precionado um dialogo é aberto
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if ((keyCode == KeyEvent.KEYCODE_BACK)) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Você realmente deseja sair ?")
					.setCancelable(false)
					.setPositiveButton("Sim",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									sair = true;
									startActivity(new Intent(getBaseContext(),
											SelectPlayerActivity.class));

								}
							})
					.setNegativeButton("Não",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
			return true;

		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		finish();
	}

	public Boolean getSair() {
		return sair;
	}

	public void setSair(Boolean sair) {
		this.sair = sair;
	}

}
