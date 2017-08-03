package com.nsx.ageoftower.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nsx.ageoftower.Towers.SlowingTower;
import com.nsx.ageoftower.Towers.SpeedTower;

public class TowerRemoveButton extends Actor {
	
	private Tower mTower;
	private Texture texture;
	private Sprite sprite;
	private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private int cost;

	public TowerRemoveButton( Tower t ) {
		mTower = t;
		
		texture = new Texture(Gdx.files.internal("GameScreenMedia/towers/remove.png"));
		setHeight(32);
		setWidth(32);
        sprite = new Sprite(texture);
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont(true);
        font.setColor(0, 0, 0, 1);
        cost = 0;
	}
	
	public TowerRemoveButton(SlowingTower a) {
		
		texture = new Texture(Gdx.files.internal("GameScreenMedia/towers/remove.png"));
		setHeight(32);
		setWidth(32);
        sprite = new Sprite(texture);
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont(true);
        font.setColor(0, 0, 0, 1);
        cost = 0;
	}

	public TowerRemoveButton(SpeedTower a) {
		// TODO Auto-generated constructor stub
		texture = new Texture(Gdx.files.internal("GameScreenMedia/towers/remove.png"));
		setHeight(32);
		setWidth(32);
        sprite = new Sprite(texture);
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont(true);
        font.setColor(0, 0, 0, 1);
        cost = 0;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(sprite,getX(),getY(),getOriginX(),getOriginY(),32,32,1,1,0);
		
    	batch.end();
        /*shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(getStage().getCamera().combined);
	    shapeRenderer.begin(ShapeType.FilledRectangle);
   		shapeRenderer.setColor(new Color(1,1,1,1));
   		shapeRenderer.filledRect(getX()+7, getY()-13, getWidth()-15, 17);
   		shapeRenderer.end();
	    shapeRenderer.begin(ShapeType.Rectangle);
   		shapeRenderer.setColor(new Color(0,0,0,1));
   		shapeRenderer.rect(getX()+7, getY()-13, getWidth()-15, 17);
   		shapeRenderer.end();*/
        batch.begin();
	}
	
	
	public Tower getTower()
	{
		return mTower;
	}
}
