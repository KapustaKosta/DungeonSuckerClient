package com.mipt.tp.dungeon_sucker.gameplay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.gameplay.level.Level;

public class Enemy extends Entity {

    public Enemy(Vector2 position, Texture texture, Level level) {
        super(position, texture, level);
    }
}