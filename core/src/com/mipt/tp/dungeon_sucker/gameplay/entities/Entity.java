package com.mipt.tp.dungeon_sucker.gameplay.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.UI.Drawable;
import com.mipt.tp.dungeon_sucker.gameplay.level.Level;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class Entity implements Drawable {
    protected IntVector2 levelPosition;

    protected Vector2 position;
    protected Level level;
    private Texture texture;
    private SpriteBatch batch;


    public Entity(IntVector2 position, Texture texture, Level level) {
        this.levelPosition = position;
        this.position = new Vector2(position.x * Constants.CELL_SIZE, position.y * Constants.CELL_SIZE);
        this.texture = texture;
        this.batch = new SpriteBatch();
        this.level = level;
    }

    @Override
    public void drawInLibGDX() {
        batch.begin();
        batch.draw(texture, position.x, position.y);
        batch.end();
    }

    @Override
    public void drawInConsole() {

    }

    public void updateRealPosition() {
        this.position = new Vector2(levelPosition.x * Constants.CELL_SIZE, levelPosition.y * Constants.CELL_SIZE);
    }

}
