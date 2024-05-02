package com.mipt.tp.dungeon_sucker.UI.text;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.UI.LibGDXDrawable;

public class Text implements LibGDXDrawable {
  private BitmapFont font;
  private String text;
  private SpriteBatch batch;
  public Vector2 position;

  public Text(String text) {
    this.batch = new SpriteBatch();
    this.position = null;
    this.font = FixedSysFont.getInstance();
    this.text = text;
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
  }

  public void draw() {
    batch.begin();
    font.draw(batch, text, position.x, position.y);
    batch.end();
  }
}
