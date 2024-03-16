package com.mipt.tp.dungeon_sucker.gameplay.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.gameplay.level.Level;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class Character extends Entity {
    public void getInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            if (level.canIGoTo(levelPosition.y + 1, levelPosition.x)) {
                levelPosition.y += 1;
                updateRealPosition();
            }

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (level.canIGoTo(levelPosition.y, levelPosition.x + 1)) {
                levelPosition.x += 1;
                updateRealPosition();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (level.canIGoTo(levelPosition.y, levelPosition.x - 1)) {
                levelPosition.x -= 1;
                updateRealPosition();
            }

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            if (level.canIGoTo(levelPosition.y - 1, levelPosition.x)) {
                levelPosition.y -= 1;
                updateRealPosition();
            }
        }

    }


    public Character(IntVector2 position, Texture texture, Level level) {
        super(position, texture, level);
    }
}
