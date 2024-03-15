package com.mipt.tp.dungeon_sucker.gameplay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.UI.Drawable;

public class Room implements Drawable {
  private Vector2 position;
  private Texture texture;
  private SpriteBatch batch;

  public Room(Vector2 position, Texture texture)
  {
    this.position = position;
    this.texture = texture;
    this.batch = new SpriteBatch();
  }

  @Override
  public void draw() {
    batch.begin();
    batch.draw(texture, position.x, position.y);
    batch.end();
  }
}
