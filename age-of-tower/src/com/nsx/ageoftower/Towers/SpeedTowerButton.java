package com.nsx.ageoftower.Towers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SpeedTowerButton extends Actor {
	
	private Tower mTower;
	private Texture texture;
	private Sprite sprite;
	private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private int cost;

	public SpeedTowerButton( ) {
		texture = new Texture(Gdx.files.internal("GameScreenMedia/towers/speed.png"));
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
        batch.begin();
	}
	
	
}
