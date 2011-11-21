package br.ufpb.dce.pa2.pokertable.game.view.scenes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import br.ufpb.dce.pa2.pokertable.game.util.SoundManager;

/**
 * Main Activity
 * 
 * @author Jefferson Araujo, jefferssonaraujo[at]gmail[dot]com
 * @version 0.0.1 Copyright (C) 2011 Poker Table
 */
public class MainActivity extends Activity {

	private static SoundManager sm;
	private boolean exit;

	protected Drawable getDrawable(int id) {
		return this.getResources().getDrawable(id);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// configuração dos sons
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		sm = SoundManager.getInstance(this);
		// carrega todos os sons do jogo
		sm.addSound(R.raw.intro);
		sm.addSound(R.raw.fichas);

		findViewById(R.button.buttonnewgame).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						startGame();
					}
				});

		findViewById(R.button.buttonabout).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						callAbout();
					}
				});

		findViewById(R.button.buttonhelp).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						callHelp();
					}
				});

		findViewById(R.button.buttonexit).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						exitGame();
					}
				});

		findViewById(R.button.facebook).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {

					}
				});

		findViewById(R.button.twitter).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {

					}
				});
	}

	private void startGame() {
		Intent i = new Intent(this, SelectPlayerActivity.class);
		startActivity(i);
	}

	private void callAbout() {
		Intent i = new Intent(this, AboutActivity.class);
		startActivity(i);
	}

	private void callHelp() {
		Intent i = new Intent(this, HelpActivity.class);
		startActivity(i);
	}

	// abre um dialogo para saber se realmente deseja sair
	private void exitGame() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Você realmente deseja sair ?")
				.setCancelable(false)
				.setPositiveButton("Sim",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								exit = true;
								finish();
							}
						})
				.setNegativeButton("Não",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			exitGame();
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

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

}