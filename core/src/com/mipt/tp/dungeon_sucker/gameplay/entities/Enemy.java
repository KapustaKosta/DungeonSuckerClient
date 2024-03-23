package com.mipt.tp.dungeon_sucker.gameplay.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.gameplay.level.Level;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class Enemy extends Entity {

    public Enemy(IntVector2 position, Texture texture, Level level) {
        super(position, texture, level);
    }
}
