package com.mipt.tp.dungeon_sucker.UI.text;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.UI.Drawable;
import com.mipt.tp.dungeon_sucker.helper.Constants;

public class Text implements Drawable {
    private BitmapFont font;
    private String text;
    private String displayedText;
    private SpriteBatch batch;
    public Vector2 position;
    private int charSize;

    public Text(String text) {
        this.batch = new SpriteBatch();
        this.position = null;
        this.font = FixedSysFont.getInstance();
        this.text = text;
        this.charSize = Constants.CHAR_SIZE;
    }

    public Text(BitmapFont font, String text) {
        this(text);
        this.font = font;
    }

    public Text(BitmapFont font, String text, Vector2 position) {
        this(font, text);
        this.position = position;
    }

    public void setText(String text) {
        this.text = text;
        setDisplayedText(text);
    }
    public void setDisplayedText(String text) {
        this.displayedText = text;
    }

    public String getText() {
        return text;
    }

    public void setCharSize(int newSize) {
        charSize = newSize;
    }

    public int getCharSize() {
        return charSize;
    }

    public void drawInLibGDX() {
        batch.begin();
        font.draw(batch, displayedText, position.x, position.y);
        batch.end();
    }

    @Override
    public void drawInConsole() {

    }
}
