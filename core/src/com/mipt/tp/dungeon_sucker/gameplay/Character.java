package com.mipt.tp.dungeon_sucker.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Character extends Entity {
    public void getInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            if (level.canIGoTo((int) levelPosition.x, (int) levelPosition.y + 1)) {
                levelPosition.y += 1;
                updateRealPosition();
            }

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            if (level.canIGoTo((int) levelPosition.x + 1, (int) levelPosition.y)) {
                levelPosition.x += 1;
                updateRealPosition();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            if (level.canIGoTo((int) levelPosition.x - 1, (int) levelPosition.y)) {
                levelPosition.x -= 1;
                updateRealPosition();
            }

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            if (level.canIGoTo((int) levelPosition.x, (int) levelPosition.y - 1)) {
                levelPosition.y -= 1;
                updateRealPosition();
            }
        }

    }


    public Character(Vector2 position, Texture texture, Level level) {
        super(position, texture, level);
    }
}
