package com.nsx.ageoftower.utils;


import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.nsx.ageoftower.event.AotEvent;
import com.nsx.ageoftower.hud.AotHudGold;
import com.nsx.ageoftower.hud.AotHudHealthBar;
import com.nsx.ageoftower.screen.AbstractScreen;


public class Foe extends Group{

	private int _life;
	private float _speed;
	private float _armor;
	private float _actualLife;
	private Texture _texture;
	private ArrayList<Vector2> _path;
	private Image _enemieimg;
	private String _imagePath;
	private float traveledDist = 0;
	private boolean alive=true;
	Vector2 v = new Vector2(0,0); // use to store the next node in the path
	
	public Foe(){}

	public Foe(int _life, float _speed, float _armor) {
		this._life = _life;
		this._speed = _speed;
		this._armor = _armor;
		this._actualLife = _life;
		_path = new ArrayList<Vector2>();
		
	}
	
	public void init(){
	
		_texture = new Texture(Gdx.files.internal(_imagePath));
		_enemieimg = new Image(_texture);
		this.addActor(_enemieimg);
	}

	public int get_life() {
		return _life;
	}

	public void set_life(int _life) {
		this._life = _life;
	}

	public float get_speed() {
		return _speed;
	}

	public void set_speed(float _speed) {
		this._speed = _speed;
	}

	public float get_armor() {
		return _armor;
	}

	public void set_armor(float _armor) {
		this._armor = _armor;
	}

	public float get_actualLife() {
		return _actualLife;
	}

	public void set_actualLife(float _actualLife) {
		this._actualLife = _actualLife;
	}
	

	public void set_path(ArrayList<Vector2> path){
		this._path = path;
	}

	
	public boolean isAlive() {
		if (get_life()<=0)
		{
		
			remove();
			System.out.print(get_life());
			die();
		return false;
		}
		else
		{
			return true;
		}
	}
	
	
	private void die() {
		AotHudGold g=new AotHudGold();
		g.addGold(10);
	}

	public void act(float delta){
		
		
		super.act(delta);
		
		if (this.getActions().size == 0 && !_path.isEmpty()){
			float x,y=0;
			
			x = this.getX();
			y = this.getY();
			Vector2 vec=new Vector2 (x,y);
					
			v = _path.remove(0);
			v.x = v.x * 32;
			v.y = v.y * 32;
			
		
			
			NeedforSpeed(v.x,v.y,get_speed(),x,y);
			
			traveledDist += _speed*delta;
		}else if(_path.isEmpty() && this.getX() == v.x && this.getY() == v.y ){
			this.getOut();
		}
		
	}
	private void NeedforSpeed(float x, float y, float _speed2, float x2, float y2) {
		// TODO Auto-generated method stub
		//System.out.println(_speed2);
		this.addAction(Actions.moveTo(x, y,v.dst(x2, y2)/(_speed2*50)));
		
	}

	public float getTraveledDist() {
		return traveledDist;
	}
	
	/**
	 * Fire an exit type event when the Foe reach the last point
	 */
	private void getOut() {
		AotEvent event = new AotEvent(AotEvent.Type.exit,this);
		this.fire(event);
		this.remove();
	}

	public void setStartPosition(){
		Vector2 v = _path.remove(0);
		v.x = v.x * 32;
		v.y = v.y * 32;
		this.setPosition(v.x, v.y);
	}
}


