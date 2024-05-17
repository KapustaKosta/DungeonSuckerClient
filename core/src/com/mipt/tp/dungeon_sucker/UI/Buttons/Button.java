package com.mipt.tp.dungeon_sucker.UI.Buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.UI.Drawable;
import com.mipt.tp.dungeon_sucker.UI.text.Text;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.helper.Constants;
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
            text.position = new Vector2((float) (leftTopCorner.x + size.x * Constants.MARGIN_PERCENT / 100),
                    (float) (leftTopCorner.y + size.y - size.y * Constants.MARGIN_PERCENT / 100));
        }
    }

    public void setSize(IntVector2 newSize) {
        this.size = newSize;
        if (leftTopCorner != null) {
            text.position = new Vector2((float) (leftTopCorner.x + size.x * Constants.MARGIN_PERCENT / 100),
                    (float) (leftTopCorner.y + size.y - size.y * Constants.MARGIN_PERCENT / 100));
        }
        String message = text.getText();
        int messagePixelLength = message.length() * text.getCharSize();
        int freeArea = size.x - 2 * size.y * Constants.MARGIN_PERCENT / 100;
        int count = messagePixelLength / freeArea;
        int interval = freeArea / text.getCharSize();
        int add = 0;
        for (int i = 1; i <= count; i++) {
            int position = i * interval + add;
            if (position >= message.length()) {
                break;
            }
            message = message.substring(0, position) + "\n" + message.substring(position);
            add += 2; // длина \n
        }
        text.setDisplayedText(message);
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
