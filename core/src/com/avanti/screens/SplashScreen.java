package com.avanti.screens;

import com.avanti.shipviewer.ShipViewer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SplashScreen extends ButtonScreenAdapter{
	private Texture logo;
	private Sprite fadingSprite, fadingStripe;
	private final float FADE_IN_TIME = 2;
	private float timeCounter;
	private boolean fadingIn;
	private boolean displaying;

	public SplashScreen(ShipViewer gameInstance) {
		super(gameInstance);
		logo = new Texture(Gdx.files.internal("logo.png"));
		fadingSprite = new Sprite(logo);
		fadingSprite.setBounds(Gdx.graphics.getWidth()/2 - logo.getWidth()/2, Gdx.graphics.getHeight()/2 - logo.getHeight()/2, logo.getWidth(), logo.getHeight());
		fadingStripe = new Sprite(buttonBackground);
		fadingStripe.setBounds(0, Gdx.graphics.getHeight()/2 - logo.getHeight()/2 - EDGE_TOLERANCE, Gdx.graphics.getWidth(), logo.getHeight() + EDGE_TOLERANCE*2);
		timeCounter = 0;
		fadingIn = true;
		displaying = false;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		renderBackground(delta);
		if ( fadingIn && timeCounter <= FADE_IN_TIME ){
			fadingStripe.draw(batch, timeCounter/FADE_IN_TIME);
			fadingSprite.draw(batch, timeCounter/FADE_IN_TIME);
		}
		else
			if (fadingIn) {
				fadingIn = false;
				timeCounter = 0;
				displaying = true;
			}
		if ( displaying ){
			fadingStripe.draw(batch, 1);
			fadingSprite.draw(batch, 1);
		}
		else
			timeCounter += delta;
		
		batch.end();
			
		buttonStage.act(delta);
		buttonStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		if (buttonStage == null)
			buttonStage = new Stage();
		buttonStage.clear();
		initializeButtons();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initializeButtons() {
		atlas = new TextureAtlas(Gdx.files.internal("playButton.atlas"));
		buttonSkin = new Skin(atlas);
		style = new ButtonStyle(buttonSkin.getDrawable("buttonUnpressed"), buttonSkin.getDrawable("buttonPressed"), buttonSkin.getDrawable("buttonPressed"));

		Button startButton = new Button(style);
		startButton.setX(Gdx.graphics.getWidth()/2 - startButton.getWidth()/2);
		startButton.setY(Gdx.graphics.getHeight()/2 - startButton.getHeight()/2 - MENU_BUTTON_HEIGHT);
		startButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				buttonClick.play();
				gameInstance.setScreen(new MenuScreen(gameInstance));
				dispose();
			}
		});
		buttonStage.addActor(startButton);
		inputMultiplexer.addProcessor(buttonStage);
		
	}

	public void dispose() {
		super.dispose();
		logo.dispose();
	}



}
