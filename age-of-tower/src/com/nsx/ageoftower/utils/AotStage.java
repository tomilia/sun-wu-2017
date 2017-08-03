package com.nsx.ageoftower.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLayer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.nsx.ageoftower.AgeOfTower;
import com.nsx.ageoftower.Towers.SlowTowerButton;
import com.nsx.ageoftower.Towers.SlowingTower;
import com.nsx.ageoftower.Towers.SpeedTower;
import com.nsx.ageoftower.Towers.SpeedTowerButton;
import com.nsx.ageoftower.hud.AotHud;
import com.nsx.ageoftower.hud.AotHudGold;
import com.nsx.ageoftower.screen.AbstractScreen;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;


/*
 * This class let us catch event in our engine, a cutom node just after the real stage root
 * We have overridden the addActor() method to catch all actors
 */
public class AotStage extends Stage implements InputProcessor {
	Stage stage;
	AotGameEngine _myRoot;
	Group _towers;
	Group _ennemies;
	Group _SlowingTower;
	TiledMap _map;
	AotHud _hud;
	private int modifiedY;
	private boolean havetower = false;
	private boolean removingtower = false;
	private int counthavetower = -1;
	private int countslowtower=-1;
	private int countspeedtower=-1;
	boolean haveslowtower=false;
	boolean havespeedtower=false;

	
	public AotStage(int width, int height, boolean b,String levelName, TiledMap map, AgeOfTower aot) {
		super(width,height,b);
		
		_map = map;
		_ennemies = new Group();
		_towers =  new Group();
		//-- a supprimer lorsque tout sera rassembl�, issue:15
		TextureAtlas hudAtlas = new TextureAtlas(Gdx.files.internal("GameScreenMedia/HUD/hud.pack"));
		AotHud _hud = new AotHud(new Skin(Gdx.files.internal("skin/default2.skin"),hudAtlas ));
		_myRoot = new AotGameEngine(_hud,levelName,this,aot);
		super.addActor(_myRoot);

		_towers.clear();
		//_towers = extractTower(_map);
		this.addActor(_towers);
		this.addActor(_ennemies);
		
	}
	
	/*
	 * This method extracts tiles from the layer "tower" in the tiledmap
	 */
	private boolean Towerconstructable(TiledMap map,int x , int y) {
		//AotHudGold g=new AotHudGold();

		for(TiledLayer tl :map.layers){
			if(tl.name.equals("towers")){
				for(int i=0;i<tl.getWidth();i++){
					for(int j=0;j<tl.getHeight();j++){
						if(tl.tiles[j][i]!=0){
							//System.out.println(j +" " +i);
							if (j == y && i == x){
								return true;

							}
						}
					}
				}
			}
		}
		
		return false;
	}
	public boolean touchUp(int screenX, int screenY, int pointer, int button){
		Group grp = new Group();
		Vector2 hover = this.screenToStageCoordinates(new Vector2(screenX,screenY));
		//System.out.println(hover.x + " " + hover.y);
		Vector2 tileSize = new Vector2(_map.tileWidth,_map.tileHeight);
		//System.out.println(_map.tileWidth + "tile" +_map.tileHeight );
		Vector2 constructionTile = new Vector2((int)(hover.x / tileSize.x), (int)(hover.y / tileSize.y));
		//System.out.println(constructionTile.x + "construction" + constructionTile.y);
		switch((int) constructionTile.y){
			case 14:
				modifiedY = 0;
				break;
			case 13:
				modifiedY = 1;
				break;
			case 12:
				modifiedY = 2;
				break;
			case 11:
				modifiedY = 3;
				break;
			case 10:
				modifiedY = 4;
				break;
			case 9:
				modifiedY = 5;
				break;
			case 8:
				modifiedY = 6;
				break;
			case 7:
				modifiedY = 7;
				break;
			case 6:
				modifiedY = 8;
				break;
			case 5:
				modifiedY = 9;
				break;
			case 4:
				modifiedY = 10;
				break;
			case 3:
				modifiedY = 11;
				break;
			case 2:
				modifiedY = 12;
				break;
			case 1:
				modifiedY = 13;
				break;
			case 0:
				modifiedY = 14;
				break;
		}
		
		for(Actor e:this._towers.getChildren()){
			if(e instanceof Tower){
				int xx = (int)constructionTile.x;
				int yy = (int)constructionTile.y;
				Tower a = (Tower) e;
				if((int)(a.getX()/tileSize.x) == xx && (int)(a.getY()/tileSize.y) == yy){
					System.out.println("You cant build here");
					counthavetower++;
					if(counthavetower%2==0 || counthavetower == 0){
					hit(xx,yy,a);
					}
					if(counthavetower%2==1){
						for(Actor h:this._towers.getChildren()){
							if(h instanceof TowerRemoveButton){
								int xxxx = (int)constructionTile.x;
								int yyyy = (int)constructionTile.y;
								TowerRemoveButton c = (TowerRemoveButton) h;
								if((int)(c.getX()/tileSize.x) == xxxx+1 && (int)(c.getY()/tileSize.y) == yyyy+1){
									c.remove();
								}
							}
						}
						for(Actor h:this._towers.getChildren()){
							if (h instanceof SlowTowerButton){
								int xxxx = (int)constructionTile.x;
								int yyyy = (int)constructionTile.y;
								SlowTowerButton d = (SlowTowerButton) h;
								if((int)(d.getX()/tileSize.x) == xxxx && (int)(d.getY()/tileSize.y) == yyyy+1){
									d.remove();
								}
							}
						}
						for(Actor h:this._towers.getChildren()){
							if (h instanceof SpeedTowerButton){
								int xxxx = (int)constructionTile.x;
								int yyyy = (int)constructionTile.y;
								SpeedTowerButton d = (SpeedTowerButton) h;
								if((int)(d.getX()/tileSize.x) == xxxx+1 && (int)(d.getY()/tileSize.y) == yyyy){
									d.remove();
								}
							}
						}
						Timer.schedule(new Task(){
						    @Override
						    public void run() {
						        
						    }
						}, 3);
					}
					havetower = true;
					removingtower = true;
					}
				else {
					havetower = false;
					removingtower = false;
				}
				
		    }
			if(e instanceof SlowingTower)
			{
				int xx = (int)constructionTile.x;
				int yy = (int)constructionTile.y;
				SlowingTower a = (SlowingTower) e;
				if((int)(a.getX()/tileSize.x) == xx && (int)(a.getY()/tileSize.y) == yy){
					System.out.println("You cant build here");
					countslowtower++;
					if(countslowtower%2==0 || countslowtower == 0){
					Slowhit(xx,yy,a);
					}
					if(countslowtower%2==1){
						for(Actor h:this._towers.getChildren()){
							if(h instanceof TowerRemoveButton){
								int xxxx = (int)constructionTile.x;
								int yyyy = (int)constructionTile.y;
								TowerRemoveButton c = (TowerRemoveButton) h;
								if((int)(c.getX()/tileSize.x) == xxxx+1 && (int)(c.getY()/tileSize.y) == yyyy+1){
									c.remove();
								}
							}
							
						}
						
					}
					havetower = true;
					removingtower = true;
					haveslowtower=true;
					}
				else {
					havetower = false;
					removingtower = false;
					haveslowtower=false;
				}
				
			}
			if(e instanceof SpeedTower)
			{
			
				int xx = (int)constructionTile.x;
				int yy = (int)constructionTile.y;
				SpeedTower a = (SpeedTower) e;
				if((int)(a.getX()/tileSize.x) == xx && (int)(a.getY()/tileSize.y) == yy){
					System.out.println("You cant build here");
					countspeedtower++;
					if(countspeedtower%2==0 || countspeedtower == 0){
						 Speedhit(xx,yy,a);
					}
					if(countspeedtower%2==1){
						for(Actor h:this._towers.getChildren()){
							if(h instanceof TowerRemoveButton){
								int xxxx = (int)constructionTile.x;
								int yyyy = (int)constructionTile.y;
								TowerRemoveButton c = (TowerRemoveButton) h;
								if((int)(c.getX()/tileSize.x) == xxxx+1 && (int)(c.getY()/tileSize.y) == yyyy+1){
									c.remove();
								}
							}
							
						}
						
					}
					havetower = true;
					removingtower = true;
					haveslowtower=true;
					havespeedtower=true;
					}
				else {
					havetower = false;
					removingtower = false;
					haveslowtower=false;
					havespeedtower=false;
				}
				
			}
	
			if(e instanceof TowerRemoveButton){
				int xx = (int)constructionTile.x;
				int yy = (int)constructionTile.y;
				TowerRemoveButton b = (TowerRemoveButton) e;
				if((int)(b.getX()/tileSize.x)== xx && (int)(b.getY()/tileSize.y) == yy){
					System.out.println("You clicked me");
					b.remove();
					removingtower = true;
					for(Actor g:this._towers.getChildren()){
						if(g instanceof Tower){
							int xxx = (int)constructionTile.x;
							int yyy = (int)constructionTile.y;
							Tower a = (Tower) g;
							if((int)(a.getX()/tileSize.x) == xxx-1 && (int)(a.getY()/tileSize.y) == yyy-1){
								System.out.println("yeahyeah");
								a.remove();
								Timer.schedule(new Task(){
								    @Override
								    public void run() {
								        removingtower = false;
								    }
								}, 1);
							}
						}
					}
					for(Actor g:this._towers.getChildren()){
						if(g instanceof SlowingTower){
							int xxx = (int)constructionTile.x;
							int yyy = (int)constructionTile.y;
							SlowingTower a = (SlowingTower) g;
							if((int)(a.getX()/tileSize.x) == xxx-1 && (int)(a.getY()/tileSize.y) == yyy-1){
								System.out.println("yeahyeah");
								a.remove();
								Timer.schedule(new Task(){
								    @Override
								    public void run() {
								        removingtower = false;
								    }
								}, 1);
							}
						}
					}
					for(Actor g:this._towers.getChildren()){
						if(g instanceof SpeedTower){
							int xxx = (int)constructionTile.x;
							int yyy = (int)constructionTile.y;
							SpeedTower a = (SpeedTower) g;
							if((int)(a.getX()/tileSize.x) == xxx-1 && (int)(a.getY()/tileSize.y) == yyy-1){
								System.out.println("yeahyeah");
								a.remove();
								Timer.schedule(new Task(){
								    @Override
								    public void run() {
								        removingtower = false;
								    }
								}, 1);
							}
						}
					}
					for(Actor g:this._towers.getChildren()){
						if(g instanceof SlowTowerButton){
							int xxx = (int)constructionTile.x;
							int yyy = (int)constructionTile.y;
							SlowTowerButton a = (SlowTowerButton) g;
							if((int)(a.getX()/tileSize.x) == xxx-1 && (int)(a.getY()/tileSize.y) == yyy){
								System.out.println("yeahyeah");
								a.remove();
								Timer.schedule(new Task(){
								    @Override
								    public void run() {
								        removingtower = false;
								    }
								}, 1);
							}
						}
					}
					for(Actor g:this._towers.getChildren()){
						if(g instanceof SpeedTowerButton){
							int xxx = (int)constructionTile.x;
							int yyy = (int)constructionTile.y;
							SpeedTowerButton a = (SpeedTowerButton) g;
							if((int)(a.getX()/tileSize.x) == xxx && (int)(a.getY()/tileSize.y) == yyy-1){
								System.out.println("yeahyeah");
								a.remove();
								Timer.schedule(new Task(){
								    @Override
								    public void run() {
								        removingtower = false;
								    }
								}, 1);
							}
						}
					}
				}
			}
			if(e instanceof SlowTowerButton){
				int xx = (int)constructionTile.x;
				int yy = (int)constructionTile.y;
				SlowTowerButton b = (SlowTowerButton) e;
				if((int)(b.getX()/tileSize.x)== xx && (int)(b.getY()/tileSize.y) == yy){
					System.out.println("You clicked me");
					b.remove();
					removingtower = true;
					for(Actor g:this._towers.getChildren()){
						if(g instanceof Tower){
							int xxx = (int)constructionTile.x;
							int yyy = (int)constructionTile.y;
							Tower a = (Tower) g;
							if((int)(a.getX()/tileSize.x) == xxx && (int)(a.getY()/tileSize.y) == yyy-1){
								System.out.println("yeahyeah");
								a.remove();
								SlowingTower st=new SlowingTower(32,32,(int)constructionTile.x,modifiedY+1,_ennemies);
								_towers.addActor(st);
								for(Actor h:this._towers.getChildren()){
									if(h instanceof TowerRemoveButton){
										int xxxx = (int)constructionTile.x;
										int yyyy = (int)constructionTile.y;
										TowerRemoveButton c = (TowerRemoveButton) h;
										if((int)(c.getX()/tileSize.x) == xxxx+1 && (int)(c.getY()/tileSize.y) == yyyy){
											c.remove();
										}
									}
									
								}
								for(Actor h:this._towers.getChildren()){
									if(h instanceof SpeedTowerButton){
										int xxxx = (int)constructionTile.x;
										int yyyy = (int)constructionTile.y;
										SpeedTowerButton c = (SpeedTowerButton) h;
										if((int)(c.getX()/tileSize.x) == xxxx+1 && (int)(c.getY()/tileSize.y) == yyyy-1){
											c.remove();
										}
									}
									
								}
								Timer.schedule(new Task(){
								    @Override
								    public void run() {
								        removingtower = false;
								    }
								}, 1);
							}
						}
					}
				}
			}
			if(e instanceof SpeedTowerButton){
				int xx = (int)constructionTile.x;
				int yy = (int)constructionTile.y;
				SpeedTowerButton b = (SpeedTowerButton) e;
				if((int)(b.getX()/tileSize.x)== xx && (int)(b.getY()/tileSize.y) == yy){
					System.out.println("You clicked me");
					b.remove();
					removingtower = true;
					for(Actor g:this._towers.getChildren()){
						if(g instanceof Tower){
							int xxx = (int)constructionTile.x;
							int yyy = (int)constructionTile.y;
							Tower a = (Tower) g;
							if((int)(a.getX()/tileSize.x) == xxx-1 && (int)(a.getY()/tileSize.y) == yyy){
								System.out.println("yeahyeah");
								a.remove();
								SpeedTower spt=new SpeedTower(32,32,(int)constructionTile.x-1,modifiedY,_ennemies);
								_towers.addActor(spt);
								for(Actor h:this._towers.getChildren()){
									if(h instanceof TowerRemoveButton){
										int xxxx = (int)constructionTile.x;
										int yyyy = (int)constructionTile.y;
										TowerRemoveButton c = (TowerRemoveButton) h;
										if((int)(c.getX()/tileSize.x) == xxxx && (int)(c.getY()/tileSize.y) == yyyy+1){
											c.remove();
										}
									}
									
								}
								for(Actor h:this._towers.getChildren()){
									if(h instanceof SlowTowerButton){
										int xxxx = (int)constructionTile.x;
										int yyyy = (int)constructionTile.y;
										SlowTowerButton c = (SlowTowerButton) h;
										if((int)(c.getX()/tileSize.x) == xxxx-1 && (int)(c.getY()/tileSize.y) == yyyy+1){
											c.remove();
										}
									}
									
								}
								Timer.schedule(new Task(){
								    @Override
								    public void run() {
								        removingtower = false;
								    }
								}, 1);
							}
						}
					}
				}
			}
		}
		if ( Towerconstructable(_map,(int)constructionTile.x, modifiedY) == true && havetower == false && removingtower == false && hasEnoughGold() && haveslowtower==false && havespeedtower==false ){
			Tower t = new Tower(32,32,(int)constructionTile.x,modifiedY,_ennemies);
			_towers.addActor(t);
			
			}
			
			
		
		return true;
	}
	
	private void Speedhit(int xx, int yy, SpeedTower a) {
	
		 TowerRemoveButton buttonss = new TowerRemoveButton(a);
		 buttonss.setPosition(a.getX()+32,a.getY()+32);
		 _towers.addActor(buttonss);
	}

	private void Slowhit(int xx, int yy, SlowingTower a) {
		
		  TowerRemoveButton buttonss = new TowerRemoveButton(a);
		    buttonss.setPosition(a.getX()+32,a.getY()+32);
		    _towers.addActor(buttonss);
	}

	@Override
	public void addActor (Actor actor) {
		_myRoot.addActor(actor);
	}

	public void addFoe(Actor foe){
		_ennemies.addActor(foe);
		
	}

	public Group getEnnemies() {
		return _ennemies;
		
	}
	public Actor hit(float x, float y,Tower t)
	 {
	   
	  TowerRemoveButton buttonss = new TowerRemoveButton(t);
	    buttonss.setPosition(t.getX()+32,t.getY()+32);
	  SlowTowerButton slows = new SlowTowerButton();
	  	slows.setPosition(t.getX(), t.getY()+32);
	  SpeedTowerButton speeds = new SpeedTowerButton();
	  	speeds.setPosition(t.getX()+32, t.getY());
	   _towers.addActor(buttonss);
	   _towers.addActor(slows);
	   _towers.addActor(speeds);
	    return buttonss;
	 
	 }
	public Group getTowers() {
		return _towers;
	}

	public void setTowers(Group _towers) {
		this._towers = _towers;
	}
	
	private boolean hasEnoughGold() {
		AotHudGold Gold=new AotHudGold();
		if(Gold.getGold()<5)
			return false;
		else
			return true;
		
	}
	private void closeTowerUI()
	{
		
	}
	
	
}
