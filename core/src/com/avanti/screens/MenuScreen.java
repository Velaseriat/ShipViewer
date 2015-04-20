package com.avanti.screens;

import com.avanti.shipviewer.ShipViewer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Array;

public class MenuScreen extends ButtonScreenAdapter{
	private static final FileHandle fileList = Gdx.files.internal("filelist.txt");
	private static Array<FileHandle> modelFiles;
	private VerticalGroup menuBody;
	private HorizontalGroup titleGroup, environmentGroup,  shipSelectGroup;
	private Image  titleGroupImage, environmentGroupImage, shipSelectGroupImage;
	private Label titleLabel;
	private static BitmapFont bitmapFont = new BitmapFont();
	private static final LabelStyle labelStyle = new LabelStyle(bitmapFont, Color.WHITE);

	public MenuScreen(ShipViewer gameInstance) {
		super(gameInstance);
		String files[] = fileList.readString().split("\n");
		modelFiles = new Array<FileHandle>();
		for (String filename: files)
			modelFiles.add(Gdx.files.internal("models/" + filename));

	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		renderBackground(delta);


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

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	protected void initializeButtons() {
		menuBody = new VerticalGroup();
		menuBody.setX(EDGE_TOLERANCE*4f);
		menuBody.setY(EDGE_TOLERANCE*4f);
		menuBody.setWidth(Gdx.graphics.getWidth() - EDGE_TOLERANCE*8f);
		menuBody.setHeight(Gdx.graphics.getHeight() - EDGE_TOLERANCE*8f);

		menuBody.center();
		menuBody.space(EDGE_TOLERANCE*3f);

		initializeTitleGroup();
		initializeShipSelectGroup();
		initializeEnvironmentGroup();
		
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
				gameInstance.setScreen(new ShipViewScreen(gameInstance));
				dispose();
			}
		});
		
		buttonStage.addActor(startButton);

		menuBody.pack();
		menuBody.setWidth(menuBody.getWidth() + EDGE_TOLERANCE*2f);

		menuBody.setX(Gdx.graphics.getWidth()/2 - menuBody.getWidth()/2);
		menuBody.setY(Gdx.graphics.getHeight()/2 - menuBody.getHeight()/2);

		titleGroupImage = new Image(buttonBackground);
		titleGroupImage.setWidth(menuBody.getWidth());
		titleGroupImage.setHeight(titleLabel.getHeight() + EDGE_TOLERANCE*2f);
		titleGroupImage.setX(menuBody.getX());
		titleGroupImage.setY(menuBody.getTop() - titleLabel.getHeight() - EDGE_TOLERANCE);
		buttonStage.addActor(titleGroupImage);

		shipSelectGroupImage = new Image(buttonBackground);
		shipSelectGroupImage.setWidth(menuBody.getWidth());
		shipSelectGroupImage.setHeight(shipSelectGroup.getHeight() + EDGE_TOLERANCE*2f);
		shipSelectGroupImage.setX(menuBody.getX());
		shipSelectGroupImage.setY(menuBody.getTop() - titleGroupImage.getHeight() - shipSelectGroup.getHeight()  - EDGE_TOLERANCE*2f);
		buttonStage.addActor(shipSelectGroupImage);
		
		environmentGroupImage = new Image(buttonBackground);
		environmentGroupImage.setWidth(menuBody.getWidth());
		environmentGroupImage.setHeight(environmentGroup.getHeight() + EDGE_TOLERANCE*2f);
		environmentGroupImage.setX(menuBody.getX());
		environmentGroupImage.setY(menuBody.getTop() - titleGroupImage.getHeight() - shipSelectGroup.getHeight() - environmentGroup.getHeight() - EDGE_TOLERANCE*5f);
		buttonStage.addActor(environmentGroupImage);

		buttonStage.addActor(menuBody);
		inputMultiplexer.addProcessor(buttonStage);
	}

	private void initializeEnvironmentGroup() {
		environmentGroup = new HorizontalGroup();
		environmentGroup.center();
		environmentGroup.space(EDGE_TOLERANCE);

		Array<String> colorNames = new Array<String>();
		colorNames.add("BLACK");
		colorNames.add("BLUE");
		colorNames.add("CLEAR");
		colorNames.add("CYAN");
		colorNames.add("DARK_GRAY");
		colorNames.add("GRAY");
		colorNames.add("GREEN");
		colorNames.add("LIGHT_GRAY");
		colorNames.add("MAGENTA");
		colorNames.add("MAROON");
		colorNames.add("NAVY");
		colorNames.add("OLIVE");
		colorNames.add("ORANGE");
		colorNames.add("PINK");
		colorNames.add("RED");
		colorNames.add("PURPLE");
		colorNames.add("WHITE");
		colorNames.add("YELLOW");
		colorNames.add("TEAL");
		
		Array<Color> colors = new Array<Color>();
		colors.add(Color.BLACK);
		colors.add(Color.BLUE);
		colors.add(Color.CLEAR);
		colors.add(Color.CYAN);
		colors.add(Color.DARK_GRAY);
		colors.add(Color.GRAY);
		colors.add(Color.GREEN);
		colors.add(Color.LIGHT_GRAY);
		colors.add(Color.MAGENTA);
		colors.add(Color.MAROON);
		colors.add(Color.NAVY);
		colors.add(Color.OLIVE);
		colors.add(Color.ORANGE);
		colors.add(Color.PINK);
		colors.add(Color.RED);
		colors.add(Color.PURPLE);
		colors.add(Color.WHITE);
		colors.add(Color.YELLOW);
		colors.add(Color.TEAL);

		for (int i = 0; i < colors.size; i++){
			final Color color = colors.get(i);
			final String name = colorNames.get(i);
			LabelStyle ls = new LabelStyle(bitmapFont, color);
			Label label = new Label(name, ls);
			label.getStyle().fontColor = color;
			label.addListener(new InputListener(){
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}
				public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
					buttonClick.play();
					ShipViewer.selectedColor = color;
				}
			});
			environmentGroup.addActor(label);
		}
		menuBody.addActor(environmentGroup);
	}

	private void initializeShipSelectGroup() {
		shipSelectGroup = new HorizontalGroup();
		shipSelectGroup.center();
		shipSelectGroup.space(EDGE_TOLERANCE);

		for (FileHandle fh : modelFiles){
			System.out.println(modelFiles);
			final FileHandle fileHandle = fh;
			Label l = new Label(fh.nameWithoutExtension(), labelStyle);
			l.addListener(new InputListener(){
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					return true;
				}
				public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
					buttonClick.play();
					ShipViewer.selectedShip = fileHandle;
				}
			});
			shipSelectGroup.addActor(l);
		}
		shipSelectGroup.pack();
		menuBody.addActor(shipSelectGroup);
	}

	private void initializeTitleGroup() {
		titleGroup = new HorizontalGroup();
		titleGroup.center();
		titleLabel = new Label("Ship Viewer Menu", labelStyle);
		titleGroup.addActor(titleLabel);
		menuBody.addActor(titleGroup);
	}

	public void dispose() {
		super.dispose();
	}

}
