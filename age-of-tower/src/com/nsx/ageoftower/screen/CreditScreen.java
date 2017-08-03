package com.nsx.ageoftower.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.nsx.ageoftower.AgeOfTower;

public class CreditScreen extends AbstractScreen{
	private AgeOfTower _aot;
	private Texture texture;
	public CreditScreen(AgeOfTower aot) {
		super(aot);
		_aot = aot;
		super.backstate = STATE_CREDIT;
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width,height);
		
		
	}

	public void show() {
		super.show();
		System.out.println(get_currentState());
		texture = new Texture(Gdx.files.internal("MenuScreenMedia/CreditScreen.png"));
		TextureRegion region = new TextureRegion(texture, 0, 0, 1024, 512);
		Image Creditimage = new Image( region);
		Creditimage.setWidth(Gdx.app.getGraphics().getWidth());
		Creditimage.setHeight(Gdx.app.getGraphics().getHeight());
		_mStage.addActor( Creditimage );

		Gdx.app.log( AgeOfTower.LOG, "show CreditScreen" );
		Table table = super.getTable();
		ImageButton.ImageButtonStyle returnbutton = new ImageButton.ImageButtonStyle();
		returnbutton.up = new TextureRegionDrawable(
	                new TextureRegion(
	                        new Texture(Gdx.files.internal("MenuScreenMedia/button1.png"))));
		ImageButton backbutton = new ImageButton(returnbutton);
        //TextButton backButton = new TextButton( "Return", getSkin() );
        backbutton.addListener( 
        	new ClickListener() {
        		@Override
        		public void clicked(InputEvent arg0, float arg1, float arg2 ){
        			_aot.setScreen( new MenuScreen( _mGame ) );
        		}
        	}
        );
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
