package com.nsx.ageoftower.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.nsx.ageoftower.AgeOfTower;

public class OptionScreen extends AbstractScreen {

	private AgeOfTower _aot;
	private Texture texture;
	public OptionScreen(AgeOfTower aot) {
		super(aot);
		_aot = aot;
		super.backstate = STATE_OPTION;
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	public void show() {
		super.show();
		System.out.println(get_currentState());
		Gdx.app.log( AgeOfTower.LOG, "show opt" );
		Table table = super.getTable();

		// Music checkbox
		final CheckBox musicCheckbox = new CheckBox( "Music", getSkin() );
		musicCheckbox.setChecked(_aot.getPreferences().isMusicEnabled() );
		musicCheckbox.addListener( 
        	new ClickListener() {
        		@Override
        		public void clicked(InputEvent arg0, float arg1, float arg2 ){
	                boolean enabled = musicCheckbox.isChecked();
	                _aot.getPreferences().setMusicEnabled( enabled );
	            }
        	} 
        );    

        
     // register the back button
        //TextButton backButton = new TextButton( "Return", getSkin() );
        ImageButton.ImageButtonStyle returnbutton = new ImageButton.ImageButtonStyle();
		returnbutton.up = new TextureRegionDrawable(
	                new TextureRegion(
	                        new Texture(Gdx.files.internal("MenuScreenMedia/button1.png"))));
		ImageButton backbutton = new ImageButton(returnbutton);
        backbutton.addListener( 
        	new ClickListener() {
        		@Override
        		public void clicked(InputEvent arg0, float arg1, float arg2 ){
        			_aot.setScreen( new MenuScreen( _mGame ) );
        		}
        	}
        );
        table.add( musicCheckbox ).uniform().spaceBottom( 10 ).width(100);
		table.center().bottom();
		table.row();
		table.add( backbutton ).uniform();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
