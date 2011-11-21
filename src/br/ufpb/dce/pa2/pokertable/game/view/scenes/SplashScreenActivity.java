package br.ufpb.dce.pa2.pokertable.game.view.scenes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Classe responsável pela animação inicial
 * @author Jefferson Araujo, jefferssonaraujo[at]gmail[dot]com
 * @version 0.0.1
 * Copyright (C) 2011 Poker Table
 */
public class SplashScreenActivity extends Activity implements Runnable {
	private Animation myFadeInAnimation;
	private Animation myFadeOutAnimation;
	private ImageView myImageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.splash);

		myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		myFadeOutAnimation = AnimationUtils
				.loadAnimation(this, R.anim.fade_out);
		myImageView = (ImageView) findViewById(R.id.splash);

		// fade it in, and fade it out.
		Handler h = new Handler();
		myImageView.startAnimation(myFadeInAnimation);

		h.postDelayed(this, 4000);

	}

	@Override
	public void run() {

		myImageView.startAnimation(myFadeOutAnimation);
		startActivity(new Intent(this, MainActivity.class));
		finish();

	}
}
