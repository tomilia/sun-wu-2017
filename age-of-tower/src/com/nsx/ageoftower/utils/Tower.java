package com.nsx.ageoftower.utils;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.nsx.ageoftower.event.AotEvent;
import com.nsx.ageoftower.hud.AotHudGold;
import com.nsx.ageoftower.hud.AotHudHealthBar;
import com.nsx.ageoftower.screen.AbstractScreen;

public class Tower extends Group implements  EventListener{
	public static final int STATE_DISABLE = 0;
	public static final int STATE_ENABLE = 1;
	int tried=0;
	int _state;
	AotStage _stage;
	boolean _clicked = false;
	Image _img;
	int _range;
	Foe target;
	Group _ennemi;
	float Speed=5;
	float fireRate=1f;
	private float timeToFire = 0;
	private boolean haveTarget = false;
	
	private HashMap<String, HashMap<String,String>> towerTypes;
	private Stage stage;
	public Tower(int tileWidth, int tileHeight, int xIndex, int yIndex, Group _ennemies) {
		this.setSize(tileWidth, tileHeight);
		this.setPosition(tileWidth*xIndex,AbstractScreen.GAME_VIEWPORT_HEIGHT-tileHeight*(yIndex+1));
		//-- the tower image
		Texture t = new Texture(Gdx.files.internal("GameScreenMedia/towers/tower1.png"));
		_img = new Image(t);
		_img.setSize(32,32);
		addActor(_img);
		_ennemi=_ennemies;
		//-- pour recuperer les evenements
		this.addListener(this);
		_state = STATE_ENABLE;
		minusGold();
	}
	public void setState(int s) {
		_state = s;
	}

	@Override
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
	
	public void attack(final Foe Besttarget){
	
		Besttarget.set_life(Besttarget.get_life()-20);
		//System.out.print("shoot!");
		//System.out.print(Besttarget.get_life());
		
			
	}
	
	
	 public Foe findTarget()
	    {
	    	float x,y=0,targetHealth=0;
	    	float maxTravelled=0;
	    	Foe bestTarget=null; 
	    	for(Actor d: _ennemi.getChildren())
    		{
	    		x=((Foe)d).get_life();
	    		if(y<x)
	    		{
	    			targetHealth=y;
	    			y=x;
	    		}
	    		else
	    			targetHealth=x;
    		}
	    	for(Actor a: _ennemi.getChildren())
			{
	    	
				
	    		if(a instanceof Foe)
	    		{
	    			
	    			float p=((Foe)a).get_life();
	    			((Foe) a).isAlive();
	    			Foe e=(Foe)a;
	    			float dx= a.getX() - getX();
	        		float dy= a.getY() - getY();
	        		if(Math.sqrt(dx*dx+dy*dy) <= 100)
	        		{
	                float t=e.getTraveledDist();
	                if(t>maxTravelled&&p==targetHealth||e.getChildren().size==1)
	                {
	                	System.out.print(targetHealth);
	                	bestTarget=e;
	                	maxTravelled=t;
	                	
	                }
	        		}
	        		
	        		
	    		}
			}
	    	if(bestTarget!=null)
	    	{
	    	haveTarget = true;
	    	
	    	}
	    	else
	    	{
	    		haveTarget = false;
	    		System.out.print("");
	    		
	    	}
	    	
	    	//ask for list of target in range
			//check distance of targets..
			
	    	return bestTarget;

	    }
	public void minusGold()
	{
		AotHudGold Gold=new AotHudGold();
		Gold.minusGold(5);
		
	}
	@Override
	public boolean handle(Event event) {
		
		if(event instanceof AotEvent){
			//System.out.println("    Tower AotEvent received:"+event);
			
			switch(((AotEvent)event).getType()){
				case restartLevel: //-- evenement declanch嚙par le  
					this.setState(STATE_DISABLE);
					break;
				case gameOver: //-- evenement declanch嚙par le  
					//-- boom?
					break;
			}
		}
	
		if (!(event instanceof InputEvent)) return false;
		if(((InputEvent)event).getButton() == 0 && event.getTarget()==this){
			if(hasEnoughGold())
			{
			if( !_clicked){
				
				this.fire(new AotEvent(AotEvent.Type.towerClicked,this));
				minusGold();
				System.out.println("click me");
				_clicked = true;
				}

			}else{
				_clicked = false;
			}
		}
		
		return true;
	
	}
	private boolean hasEnoughGold() {
		AotHudGold Gold=new AotHudGold();
		if(Gold.getGold()<5)
			return false;
		else
			return true;
		
	}
}
