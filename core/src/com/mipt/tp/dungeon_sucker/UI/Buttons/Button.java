package com.mipt.tp.dungeon_sucker.UI.Buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mipt.tp.dungeon_sucker.UI.Drawable;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class Button implements Drawable {
    private Action onPress;
    private int[] args;

    private Texture texture;
    private SpriteBatch batch;
    private IntVector2 leftTopCorner;
    private IntVector2 size;

    public void push() {
        onPress.run(args);
    }

    public void setPos(IntVector2 newPos) {
        System.out.println(newPos.x);
        System.out.println(newPos.y);
        this.leftTopCorner = newPos;
    }
    public void setSize(IntVector2 newSize) {
        this.size = newSize;
    }

    public Button(Texture texture) {
        this.batch = new SpriteBatch();
        this.texture = texture;
    }

    @Override
    public void draw() {
        batch.begin();
        batch.draw(texture, leftTopCorner.x, leftTopCorner.y, size.x, size.y);
        batch.end();
    }
}
