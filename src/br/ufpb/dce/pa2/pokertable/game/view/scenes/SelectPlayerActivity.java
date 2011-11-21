package br.ufpb.dce.pa2.pokertable.game.view.scenes;

import br.ufpb.dce.pa2.pokertable.game.util.SoundManager;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * Main Activity
 * 
 * @author Jefferson Araujo, jefferssonaraujo[at]gmail[dot]com
 * @version 0.0.1 Copyright (C) 2011 Poker Table
 */
public class SelectPlayerActivity extends Activity {

	private ImageButton playerOne, playerTwo, playerThree, playerFour, back, audio;
	private int cont;

	public static int playerSelect;
	public static boolean mute;
	private static SoundManager sm;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player_select);

		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		sm = SoundManager.getInstance(this);
		mute = false;
		playerOne = (ImageButton) findViewById(R.button.selectplayerone);
		playerTwo = (ImageButton) findViewById(R.button.selectplayertwo);
		playerThree = (ImageButton) findViewById(R.button.selectplayerthree);
		playerFour = (ImageButton) findViewById(R.button.selectplayerfour);
		back = (ImageButton) findViewById(R.button.back_button);
		audio = (ImageButton) findViewById(R.button.audio_button);

		// toca a musica inicial
		sm.playSound(0);

		try {
			playerOne.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					sm.stopSounds();
					playerSelect = 1;
					Intent i = new Intent(SelectPlayerActivity.this,
							GameActivity.class);
					startActivity(i);
				}
			});
		} catch (NullPointerException np) {
			Log.e("Null","player button is null. See the names of the IDs in player_select.xml" + np);
		}

		try {
			playerTwo.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					sm.stopSounds();
					playerSelect = 2;
					Intent i = new Intent(SelectPlayerActivity.this,
							GameActivity.class);
					startActivity(i);
				}
			});
		} catch (NullPointerException np) {
			Log.e("Null","player button is null. See the names of the IDs in player_select.xml" + np);
		}

		try {
			playerThree.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					sm.stopSounds();
					playerSelect = 3;
					Intent i = new Intent(SelectPlayerActivity.this,
							GameActivity.class);
					startActivity(i);
				}
			});
		} catch (NullPointerException np) {
			Log.e("Null","player button is null. See the names of the IDs in player_select.xml" + np);
		}
		
		try {
			playerFour.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					sm.stopSounds();
					playerSelect = 4;
					Intent i = new Intent(SelectPlayerActivity.this,
							GameActivity.class);
					startActivity(i);
				}
			});
		} catch (NullPointerException np) {
			Log.e("Null","player button is null. See the names of the IDs in player_select.xml" + np);
		}

		try {
			back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					sm.stopSounds();
					startActivity(new Intent(getBaseContext(),
							MainActivity.class));
				}
			});
		} catch (NullPointerException np) {
			Log.e("Null","back button is null. See the names of the IDs in player_select.xml" + np);
		}

		try {
			audio.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (cont == 0) {
						v.setBackgroundResource(R.drawable.mute);
						mute = true;
						sm.stopSounds();
						cont++;
					} else {
						v.setBackgroundResource(R.drawable.audio);
						mute = false;
						sm.playSound(0);
						cont = 0;
					}
				}
			});
		} catch (NullPointerException np) {
			Log.e("Null","audio button is null. See the names of the IDs in player_select.xml" + np);
		}

	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			startActivity(new Intent(getBaseContext(), MainActivity.class));
			sm.stopSounds();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		finish(); // Close the screen
	}
}