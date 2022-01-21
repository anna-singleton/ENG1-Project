package io.github.annabeths;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.annabeths.Projectiles.ProjectileDataHolder;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class GameController implements Screen {

    eng1game game;
    ArrayList<GameObject> gameObjects;
    ArrayList<PhysicsObject> physicsObjects;
    float testRot = 0;
    private SpriteBatch batch;
    
    BitmapFont font;
    GlyphLayout hpTextLayout;
    WaterBackground bg;

    ProjectileDataHolder projectileHolder;

    private Boat playerBoat;

    public GameController(eng1game g){ //passes the game class so that we can change scene back later
        game = g;
        gameObjects = new ArrayList<GameObject>();
        physicsObjects = new ArrayList<PhysicsObject>();
        bg = new WaterBackground(Gdx.graphics.getWidth(),
                                 Gdx.graphics.getHeight());
        
        projectileHolder = new ProjectileDataHolder();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        
        // Create text object for player HP and load font
        font = new BitmapFont(Gdx.files.internal("fonts/bobcat.fnt"), false);
		hpTextLayout = new GlyphLayout();
		
        
        // Create the player boat and place it in the centre of the screen
        playerBoat = new PlayerBoat(this);
        playerBoat.SetPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight()/2); // place the player 
    }

    @Override
    public void render(float delta) {
        // do updates here
    	
        bg.Update(delta);
    	playerBoat.Update(delta);

        if (physicsObjects.size() > 0)
        {
            for (PhysicsObject physicsObject : physicsObjects) {
                physicsObject.Update(delta);
            }
        }
        

        // do draws here
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin(); //begin the sprite batch
        
        bg.Draw(batch);
        playerBoat.Draw(batch);

        if (physicsObjects.size() > 0)
        {
            for (PhysicsObject physicsObject : physicsObjects) {
                physicsObject.Draw(batch);
            }
        }


        // Draw the text showing the player's HP
        hpTextLayout.setText(font, "HP: " + playerBoat.HP);
        font.getData().setScale(1);
        font.draw(batch, hpTextLayout, 5, Gdx.graphics.getHeight() - 10);
        batch.end(); //end the sprite batch
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void gameOver(){

    }
    
    public void NewPhysicsObject(PhysicsObject obj) {
    	// A new PhysicsObject has been created, add it to the list so it receives updates
    	physicsObjects.add(obj);
    }
}
