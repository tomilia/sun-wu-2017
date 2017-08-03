
package com.nsx.ageoftower.Towers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.nsx.ageoftower.screen.AbstractScreen;
import com.nsx.ageoftower.utils.Foe;

public class SpeedTower extends Tower {
	
	public SpeedTower(int tileWidth, int tileHeight, int xIndex, int yIndex, Group _ennemies) {
		super(tileWidth, tileHeight, xIndex, yIndex, _ennemies);
		this.setSize(tileWidth, tileHeight);
		this.setPosition(tileWidth*xIndex,AbstractScreen.GAME_VIEWPORT_HEIGHT-tileHeight*(yIndex+1));
		//-- the tower image
		Texture t = new Texture(Gdx.files.internal("GameScreenMedia/towers/tower3.png"));
		_img = new Image(t);
		_img.setSize(32,32);
		addActor(_img);
		_ennemi=_ennemies;
		//-- pour recuperer les evenements
		this.addListener(this);
		_state = STATE_ENABLE;
		// TODO Auto-generated constructor stub
	}
	public void act(float delta){
		
		switch(_state){
		case STATE_DISABLE:
			_img.setVisible(false);
			
			break;
			
		case STATE_ENABLE:
			
			act2(delta,findTarget());
			_img.setVisible(true);
			break;
		}
		
	}

	private void act2(float delta,Foe bestT) {
		// TODO Auto-generated method stub
		super.act(delta);
		fireRate=0.5f;
    	timeToFire += delta;
    	
    	// check enemy is still alive

    	// check if can fire, if possible fire
		if(timeToFire >= fireRate)
		{
	    	if(bestT != null)
	    	{
	    		if(bestT.isAlive())
	    		{
	            	// check enemy is still in range
	        		Vector2 dist = new Vector2(bestT.getX() - getX(),bestT.getY() - getY());
	        		if(dist.len() <= 100)
	        		{
        				timeToFire = 0;
        				attack(bestT);
	        		} else {
	        			bestT.remove();
	        		}
	    		} else {
	    			bestT.remove();
	    		}
	    	} else {
	    		bestT= findTarget();
	    	}
		}
		
	}
}

