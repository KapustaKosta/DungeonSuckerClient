package com.mipt.tp.dungeon_sucker.gameplay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.UI.Drawable;

public class Entity implements Drawable {
    protected Vector2 levelPosition;

    protected Vector2 position;
    protected Level level;
    private Texture texture;
    private SpriteBatch batch;


    public Entity(Vector2 position, Texture texture, Level level) {
        this.levelPosition = position;
        this.position = new Vector2(position.x, position.y);
        this.texture = texture;
        this.batch = new SpriteBatch();
        this.level = level;
    }

    @Override
    public void draw() {
        batch.begin();
        batch.draw(texture, position.x, position.y);
        batch.end();
    }

    public void updateRealPosition() {
        this.position = new Vector2(levelPosition.x * 36, levelPosition.y * 36);
    }

}
