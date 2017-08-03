package com.nsx.ageoftower.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.nsx.ageoftower.AgeOfTower;

public abstract class AbstractScreen implements Screen {
	// the fixed viewport dimensions (ratio: 1.6)
	public static final int GAME_VIEWPORT_WIDTH = 800;
	public static final int GAME_VIEWPORT_HEIGHT = 480;
	public static final int MENU_VIEWPORT_WIDTH = 800,  MENU_VIEWPORT_HEIGHT = 480;
	
	public static final int STATE_RENDERING_NOT_STARDED = 0;
	public static final int STATE_RENDERING_STARTED = 1;
	
	public static final int STATE_MAINMENU = 1;
	public static final int STATE_OPTION = 2;
	public static final int STATE_CREDIT = 3;
	public static final int STATE_LEVELSELECT = 4;
	public static final int STATE_GAME = 5;
	
	private BitmapFont font;
	protected SpriteBatch batch;
	protected AgeOfTower _mGame;
	protected Stage _mStage;
	protected Skin _mSkin;
	private Table _mTable;
	private TextureAtlas atlas;
	private int _state;
	public int backstate;
	public AbstractScreen(AgeOfTower space){
		this._mGame = space;
		int width = ( isGameScreen() ? GAME_VIEWPORT_WIDTH : MENU_VIEWPORT_WIDTH );
		int height = ( isGameScreen() ? GAME_VIEWPORT_HEIGHT : MENU_VIEWPORT_HEIGHT );
		this._mStage = new Stage( width, height, true );
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("skin/default.atlas"));
		this._mSkin =  new Skin(Gdx.files.internal("skin/default.skin"),atlas);
	}

	protected boolean isGameScreen(){
        	return false;
	}
	
	@Override
	public void render(float delta) {
		if(_state == STATE_RENDERING_NOT_STARDED){
			//-- fonction appeler une foi au debut du rendering, cela est parfoi pratique (cf DummyScreen)
			this.renderStarted();
			_state = STATE_RENDERING_STARTED;
		}
		
		_mStage.act( delta );
		_mStage.draw();
	}
	
	public void renderStarted() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void resize(int width, int height) {
		// resize the stage
		_mStage.setViewport( width, height, true );
	}

	@Override
	public void show() {
		Gdx.app.log( AgeOfTower.LOG, "Showing com.nsx.ageoftower.screen: " + getName() );

		// set the stage as the input processor
		Gdx.input.setInputProcessor(_mStage);
	}

	@Override
	public void dispose() {
		batch.dispose();
		_mStage.dispose();
	}
	
	protected Skin getSkin(){
		return _mSkin;
    }

	protected Table getTable(){
		if( _mTable == null ) {
			_mTable = new Table( getSkin() );
			_mTable.setFillParent( true );
			_mStage.addActor( _mTable );
		}
		return _mTable;
	}

	protected String getName(){
		return getClass().getSimpleName();
	}
    
	public BitmapFont getFont(){
		if( font == null ) {
			font = new BitmapFont();
		}
		return font;
	}
    
	public SpriteBatch getBatch(){
		if( batch == null ) {
			batch = new SpriteBatch();
		}
		return batch;
	}
    
	public TextureAtlas getAtlas(){
		if( atlas == null ) {
			atlas = new TextureAtlas( Gdx.files.internal( "image-atlases/pages.atlas" ) );
		}
		return atlas;
	}
    
	public Stage getStage(){
		return _mStage;
	}

	public void render() {
		
	}
	
	public int get_currentState(){
		return backstate;
	}
}
