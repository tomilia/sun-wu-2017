package com.nsx.ageoftower.screen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.nsx.ageoftower.AgeOfTower;

public class MenuScreen extends AbstractScreen {
		public static final float FLINGING_BG_REDUCER = 0.2f;
	
	private AgeOfTower _aot;
    private Texture texture;
    float _startTargetOffsetPos;
    float _targetOffsetPos;
    float _bg_offset;
	public MenuScreen(AgeOfTower aot) {
		super(aot);
		_aot = aot;
		super.backstate = STATE_MAINMENU;
		//back = new BackKeyHandling(_mGame);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		//_startTargetOffsetPos = GAME_VIEWPORT_WIDTH/2-GAME_VIEWPORT_HEIGHT/2;
		//_bg_offset = -_targetOffsetPos*FLINGING_BG_REDUCER;

		TextureRegion region = new TextureRegion(texture, 0, 0,1024, 512);
		Image TitleImage = new Image( region);
		TitleImage.setWidth(width);
		TitleImage.setHeight(height);
		//TitleImage.setPosition(_targetOffsetPos*FLINGING_BG_REDUCER+_bg_offset, 0);
		
		
		
		// this is needed for the fade-in effect to work correctly; we're just
		// making the image completely transparent
		//TitleImage.getColor().a = 0f;
		
		TitleImage.addAction( 
				sequence( 
					fadeIn( 0.75f ), 
					new Action() {
					@Override
					public boolean act(float delta ){
						// the last action will move to the next screen
						
						return true;
					}
				}
					
				)
	            	);

		
		
		_mStage.addActor( TitleImage );
		TitleImage.toBack();
	
		
	}
	
	
	public void show(){
		super.show();
		System.out.println(get_currentState()); 
		// retrieve the default table actor
		Table table = super.getTable();
		Gdx.app.log( AgeOfTower.LOG, "prepare to add label" );
		//table.add( "Welcome to Age of Tower for Android!" ).spaceBottom( 50 );
		table.row();

		texture = new Texture(Gdx.files.internal("MenuScreenMedia/menu1024.png"));
		//texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		// register the button "start game"
		Gdx.app.log( AgeOfTower.LOG, "prepare to add Start game button" );
		ImageButton.ImageButtonStyle start = new ImageButton.ImageButtonStyle();
		start.up = new TextureRegionDrawable(
	                new TextureRegion(
	                        new Texture(Gdx.files.internal("MenuScreenMedia/StartButton.png"))));
		 ImageButton StartButton = new ImageButton(start);
		//TextButton startGameButton = new TextButton( "Start game", getSkin() );
		StartButton.addListener(
			new ClickListener() {
				@Override
				public void clicked(InputEvent arg0, float arg1, float arg2) {
					Gdx.app.log( AgeOfTower.LOG, "click" );
				}
				@Override
				public void touchUp(InputEvent event,float x,float y,int pointer,int button ){
					Gdx.app.log( AgeOfTower.LOG, "touchUp" );
					//-- chargement du profile
					_aot.setProfile("default");
					_aot.setScreen( new LevelSelectorScreen( _mGame ) );
				}
			}
		);
	        	
		
		
		// register the button "Option"
		ImageButton.ImageButtonStyle option = new ImageButton.ImageButtonStyle();
		option.up = new TextureRegionDrawable(
	                new TextureRegion(
	                        new Texture(Gdx.files.internal("MenuScreenMedia/Option.png"))));
		 ImageButton OptionButton = new ImageButton(option); 
		//TextButton optionButton = new TextButton( "Option", getSkin() );
		OptionButton.addListener(
			new ClickListener() {
				@Override
				public void clicked(InputEvent arg0, float arg1, float arg2) {
					Gdx.app.log( AgeOfTower.LOG, "click" );
				}
				@Override
				public void touchUp(InputEvent event,float x,float y,int pointer,int button ){
					Gdx.app.log( AgeOfTower.LOG, "touchUp" );
					//-- chargement du profile
					_aot.setProfile("default");
					_aot.setScreen( new OptionScreen( _mGame ) );
				}
			}
		);
	    
		
		// register the button "Credit"
		ImageButton.ImageButtonStyle credit = new ImageButton.ImageButtonStyle();
		credit.up = new TextureRegionDrawable(
	                new TextureRegion(
	                        new Texture(Gdx.files.internal("MenuScreenMedia/Credits.png"))));
		 ImageButton CreditButton = new ImageButton(credit); 
		 CreditButton.addListener(
			new ClickListener() {
				@Override
				public void clicked(InputEvent arg0, float arg1, float arg2) {
					Gdx.app.log( AgeOfTower.LOG, "click" );
				}
				@Override
				public void touchUp(InputEvent event,float x,float y,int pointer,int button ){
					Gdx.app.log( AgeOfTower.LOG, "touchUp" );
					//-- chargement du profile
					_aot.setProfile("default");
					_aot.setScreen( new CreditScreen( _mGame ) );
				}
			}
		);
		table.add( StartButton ).size( 300, 80 ).uniform().spaceBottom( 30 );
		table.row();
		table.add( OptionButton ).size( 300, 80 ).uniform().spaceBottom( 30 );
		table.row();
		table.add( CreditButton).size ( 300 , 80).uniform().spaceBottom (30);
	}
}
