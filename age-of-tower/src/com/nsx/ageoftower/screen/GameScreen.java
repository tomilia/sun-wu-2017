package com.nsx.ageoftower.screen;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.tiled.TileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.nsx.ageoftower.AgeOfTower;
import com.nsx.ageoftower.hud.AotHudGold;
import com.nsx.ageoftower.utils.AotStage;
import com.nsx.ageoftower.utils.MusicManager;
import com.nsx.ageoftower.utils.MusicManager.AotMusic;
import com.nsx.ageoftower.utils.Tower;


public class GameScreen extends AbstractScreen {

	AgeOfTower _mAot ;

	//tiled map
	TileMapRenderer tileMapRenderer;     
	TiledMap map;        
	TileAtlas atlas;

	private Stage stage;
	/**
	 * 
	 * @param aot
	 * @param string
	 */
	public GameScreen(AgeOfTower aot, String levelName){
		super(aot);
		_mAot = aot ;
		super.backstate = STATE_GAME;
		// Music
		MusicManager musicManager = _mAot.getMusicManager();
		//musicManager = new MusicManager();
		musicManager.setVolume( _mAot.getPreferences().getVolume() );
        musicManager.setEnabled( _mAot.getPreferences().isMusicEnabled() );

        // if the music is now enabled, start playing the menu music
        if( musicManager.isEnabled() ) 
        	_mAot.getMusicManager().play( AotMusic.LEVEL );
		
		// Tiled Map
		map = TiledLoader.createMap(Gdx.files.internal("GameScreenMedia/levels/"+levelName+"/"+levelName+".tmx"));
		
		// Create the map renderer      
		atlas = new TileAtlas(map, Gdx.files.internal("GameScreenMedia/levels/"+levelName));     
		tileMapRenderer = new TileMapRenderer(map, atlas, 1, 1, 32,32);
		
		this._mStage = new AotStage( AbstractScreen.GAME_VIEWPORT_WIDTH/2, AbstractScreen.GAME_VIEWPORT_HEIGHT/2, true,levelName,map,_mAot);
		
	}


	@Override
	public void render(float delta) {
		OrthographicCamera camera = (OrthographicCamera) _mStage.getCamera();
		_mStage.setCamera(camera);
		_mStage.setViewport(800, 480, false);
		tileMapRenderer.render(camera);
        super.render(delta);
	}

}