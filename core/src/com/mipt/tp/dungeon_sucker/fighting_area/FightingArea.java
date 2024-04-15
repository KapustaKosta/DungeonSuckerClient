package com.mipt.tp.dungeon_sucker.fighting_area;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.UI.Drawable;

public class FightingArea implements Drawable {
    public BattleParticipant[] oneSide;  // участники битвы, на стороне игрока
    public BattleParticipant[] otherSide;  // участники битвы, против игрока

    private Vector2 position;
    private Vector2 size; // Размер битвы

    private Texture texture;
    private SpriteBatch batch;

    public FightingArea(Vector2 position, Vector2 size, Texture texture) {
        this.position = position;
        this.size = size;
        this.batch = new SpriteBatch();
        this.texture = texture;
    }

    @Override
    public void draw() {
        batch.begin();
        batch.draw(texture, position.x, position.y);
        batch.end();
    }
}
