package com.avanti.screens;

import java.util.Random;

import com.avanti.shipviewer.ShipViewer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class ButtonScreenAdapter implements Screen{
	protected static final Texture backgroundTexture = new Texture("background.jpg");
	protected static final Texture buttonBackground = new Texture("black.png");
	protected TextureAtlas atlas;
	protected Skin buttonSkin;
	protected ButtonStyle style;
	
	protected Stage buttonStage;
	protected ShipViewer gameInstance;
	protected InputMultiplexer inputMultiplexer;

	//dimensions for buttons
	protected static final float BUTTON_WIDTH = (1f/10f)*Gdx.graphics.getWidth();
	protected static final float BUTTON_HEIGHT = (1f/10f)*Gdx.graphics.getWidth();

	//Menu buttons are larger
	protected static final float MENU_BUTTON_WIDTH = (2f/5f)*Gdx.graphics.getWidth();
	protected static final float MENU_BUTTON_HEIGHT = (1f/10f)*Gdx.graphics.getWidth();

	//distance between buttons and between the edge and a button
	protected static final float EDGE_TOLERANCE = (.03f)*Gdx.graphics.getHeight();

	//Batch for background rendering
	protected SpriteBatch batch;
	
	protected static final Random random = new Random();
	private static Vector2 frontPoint = new Vector2();
	private static Vector2 middlePoint  = new Vector2();
	private static Vector2 backPoint  = new Vector2();
	protected static Vector2 backgroundPoint  = new Vector2();
	protected static int bufferX = (backgroundTexture.getWidth() - Gdx.graphics.getWidth())/2;
	protected static int bufferY = (backgroundTexture.getHeight() - Gdx.graphics.getHeight())/2;
	
	private static Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));
	protected  static Sound buttonClick = Gdx.audio.newSound(Gdx.files.internal("click.wav"));
	

	public ButtonScreenAdapter(ShipViewer gameInstance) {
		this.gameInstance = gameInstance;
		batch = new SpriteBatch();
		buttonStage = new Stage();
		inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);
		backgroundMusic.setVolume((float) 0.35);
		backgroundMusic.play();
	}

	public void dispose() {
		atlas.dispose();
		buttonSkin.dispose();
		buttonStage.dispose();
		if (batch != null)
			batch.dispose();
	}

	protected abstract void initializeButtons();

	public void resetInputProcessors() {
		Gdx.input.setInputProcessor(inputMultiplexer);
	}
	
	public void renderBackground(float delta){
		shakeBackground(delta);
		batch.draw(backgroundTexture, backgroundPoint.x - bufferX, backgroundPoint.y - bufferY, backgroundTexture.getWidth(), backgroundTexture.getHeight());
	}
	
	public void shakeBackground(float delta){
		if (middlePoint.dst(frontPoint) < 5){
			frontPoint.x = random.nextInt(40 + 10) - 25;
			frontPoint.y = random.nextInt(40 + 10) - 25;
		}
		middlePoint.lerp(frontPoint, delta);
		backPoint.lerp(middlePoint, delta*4);
		backgroundPoint.lerp(backPoint, delta);
	}
}
