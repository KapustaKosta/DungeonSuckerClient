package com.mipt.tp.dungeon_sucker.UI.Buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.UI.Drawable;
import com.mipt.tp.dungeon_sucker.UI.text.Text;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class Button implements Drawable {
    private Action action;
    private int[] args;
    private Text text;

    private Texture texture;
    private SpriteBatch batch;
    private IntVector2 leftTopCorner;
    private IntVector2 size;

    public void push() {

        action.run(args);
    }

    public void setPos(IntVector2 newPos) {
        this.leftTopCorner = newPos;
        if (size != null) {
            text.position = new Vector2((float) (leftTopCorner.x),
                    (float) (leftTopCorner.y + size.y / 2));
        }
    }

    public void setSize(IntVector2 newSize) {
        this.size = newSize;
        if (leftTopCorner != null) {
            text.position = new Vector2((float) (leftTopCorner.x),
                    (float) (leftTopCorner.y + size.y / 2));
        }
    }

    public Button(String text, Action action) {
        this.batch = new SpriteBatch();
        this.texture = new Texture("button.png");
        this.text = new Text(text);
        this.action = action;
    }

    @Override
    public void drawInLibGDX() {
        batch.begin();
        batch.draw(texture, leftTopCorner.x, leftTopCorner.y, size.x, size.y);
        batch.end();
        text.drawInLibGDX();
    }

    @Override
    public void drawInConsole() {

    }
}
