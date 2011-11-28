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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.Toast;
import br.ufpb.dce.pa2.pokertable.game.util.SoundManager;
import br.ufpb.dce.pa2.pokertable.model.PlayerEngine;
import br.ufpb.dce.pa2.pokertable.model.TableDummy;

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

	// variaveis utilizadas para o sprite do pote
	private TextureRegion mRegionPote;
	private BitmapTextureAtlas mPoteTexture;
	private Sprite mSprite;

	// variaveis utilizadas para o sprite do Score
	private BitmapTextureAtlas mFontTexture;
	private ChangeableText scoreText;
	private Font mFont;

	// variaveis utilizadas para o pote
	private BitmapTextureAtlas mFontPoteTexture;
	private ChangeableText poteText;
	private Font mFontPote;

	// variavel utilizadas para o som
	private static SoundManager sm;
	private Boolean sair;

	// variaveis utilizadas para o sprite do jogador
	private PlayerEngine player;

	private TextureRegion mPlayerRegion;
	private TextureRegion mPlayerRegion2;
	private TextureRegion mPlayerRegion3;
	private TextureRegion mPlayerRegion4;
	private BitmapTextureAtlas mPlayerTexture;
	private BitmapTextureAtlas mPlayerTexture2;
	private BitmapTextureAtlas mPlayerTexture3;
	private BitmapTextureAtlas mPlayerTexture4;
	private static final int PLAYER_1_X = 100;
	private static final int PLAYER_1_Y = 25;
	private static final int PLAYER_2_X = 575;
	private static final int PLAYER_2_Y = 10;
	private static final int PLAYER_3_X = 670;
	private static final int PLAYER_3_Y = 350;
	private static final int PLAYER_4_X = 100;
	private static final int PLAYER_4_Y = 370;

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
		// setContentView(R.layout.table);
		
		// define um caminho padrao dentro da pasta /assets para as imagens
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		// chama o som do jogo
		sm = SoundManager.getInstance(this);
		
		// carrega o pote na mesa
		onLoadPot();

		// carrega os dados do score na mesa
		onLoadFontScore();

		// carrega os dados do pote na mesa
		onLoadFontPot();

		// carrega os jogadores na mesa
		onLoadPlayers();
	}

	public Scene onLoadScene() {

		// cria a cena do jogo
		final Scene scene = new Scene();

		// centraliza o sprite do pote no meio da tela
		float centerX = (cameraWidth - this.mRegionPote.getWidth()) / 2;
		float centerY = (cameraHeight - this.mRegionPote.getHeight()) / 2;

		// cria o sprite do pote e posiciona no meio da tela
		this.mSprite = new Sprite(centerX, centerY, this.mRegionPote);

		// adiciona o pote na cena
		scene.attachChild(mSprite);

		// cria o texto do Score
		this.scoreText = new ChangeableText(680, 10, this.mFont, "Score: 0000");

		// adiciona na cena
		scene.attachChild(scoreText);

		// cria o texto do Pote
		this.poteText = new ChangeableText(380, 200, this.mFontPote, "10");

		// adiciona na cena
		scene.attachChild(poteText);

		// cria o sprite e posiciona na cadeira da mesa
		this.mSprite = new Sprite(PLAYER_1_X, PLAYER_1_Y, this.mPlayerRegion);
		mSprite.setZIndex(100);
		// adiciona na cena
		scene.attachChild(mSprite);
		this.mSprite = new Sprite(PLAYER_2_X, PLAYER_2_Y, this.mPlayerRegion2);
		// adiciona na cena
		scene.attachChild(mSprite);

		this.mSprite = new Sprite(PLAYER_3_X, PLAYER_3_Y, this.mPlayerRegion3);
		// adiciona na cena
		scene.attachChild(mSprite);

		this.mSprite = new Sprite(PLAYER_4_X, PLAYER_4_Y, this.mPlayerRegion4);
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

	private void onLoadFontScore() {
		// Carrega os recursos da fonte de Score
		this.mFontTexture = new BitmapTextureAtlas(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		// Define as propriedades da fonte
		this.mFont = new Font(this.mFontTexture, Typeface.create(
				Typeface.DEFAULT, Typeface.BOLD), 20, true, Color.WHITE);

		this.mEngine.getFontManager().loadFont(this.mFont);

		// carrega a textura da fonte na engine para poder ser usada
		this.mEngine.getTextureManager().loadTexture(this.mFontTexture);
	}

	private void onLoadFontPot() {
		// Carrega os recursos da fonte do Pote
		this.mFontPoteTexture = new BitmapTextureAtlas(64, 64,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		// Define as propriedades da fonte do pote
		this.mFontPote = new Font(this.mFontPoteTexture, Typeface.create(
				Typeface.DEFAULT, Typeface.BOLD), 15, true, Color.WHITE);

		this.mEngine.getFontManager().loadFont(this.mFontPote);

		// carrega a textura da fonte na engine para poder ser usada
		this.mEngine.getTextureManager().loadTexture(this.mFontPoteTexture);
	}

	private void onLoadPlayers() {
		// cria o objeto de textura do jogador 1
		this.mPlayerTexture = new BitmapTextureAtlas(128, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		// cria o objeto de textura do jogador 2
		this.mPlayerTexture2 = new BitmapTextureAtlas(128, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		// cria o objeto de textura do jogador 3
		this.mPlayerTexture3 = new BitmapTextureAtlas(128, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		// cria o objeto de textura do jogador 4
		this.mPlayerTexture4 = new BitmapTextureAtlas(128, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		this.mPlayerRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mPlayerTexture, this, TableDummy
						.getInstance().getPlayers().get(0).getPicture(), 0, 0);

		this.mPlayerRegion2 = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mPlayerTexture2, this, TableDummy
						.getInstance().getPlayers().get(1).getPicture(), 0, 0);

		this.mPlayerRegion3 = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mPlayerTexture3, this, TableDummy
						.getInstance().getPlayers().get(2).getPicture(), 0, 0);

		this.mPlayerRegion4 = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.mPlayerTexture4, this, TableDummy
						.getInstance().getPlayers().get(3).getPicture(), 0, 0);

		this.mEngine.getTextureManager().loadTexture(this.mPlayerTexture);
		this.mEngine.getTextureManager().loadTexture(this.mPlayerTexture2);
		this.mEngine.getTextureManager().loadTexture(this.mPlayerTexture3);
		this.mEngine.getTextureManager().loadTexture(this.mPlayerTexture4);

	}

	private void onLoadPot() {
		// cria o objeto de textura.
		// esses valores tem que ser na base de 2 (2, 4, 8 ...)
		this.mPoteTexture = new BitmapTextureAtlas(128, 128,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		// cria um objeto que adiciona a imagem na texture e aponta para ela
		this.mRegionPote = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				this.mPoteTexture, this, "pote.png", 0, 0);

		// carrega a textura na engine
		this.mEngine.getTextureManager().loadTexture(this.mPoteTexture);

	}

	public Boolean getSair() {
		return sair;
	}

	public void setSair(Boolean sair) {
		this.sair = sair;
	}

}
