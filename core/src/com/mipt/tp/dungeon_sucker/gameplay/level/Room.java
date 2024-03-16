package com.mipt.tp.dungeon_sucker.gameplay.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.UI.Drawable;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class Room implements Drawable {
  private final IntVector2 levelPosition;
  private Texture texture;
  private SpriteBatch batch;

  protected boolean isLocked = false;

  public Room(IntVector2 levelPosition, Texture texture)
  {
    this.levelPosition = levelPosition;
    this.texture = texture;
    this.batch = new SpriteBatch();
  }

  @Override
  public void draw() {
    batch.begin();
    batch.draw(texture, levelPosition.x * Constants.cellSize, levelPosition.y * Constants.cellSize);
    batch.end();
  }

  public IntVector2 getPosition() {
    return levelPosition;
  }

  public boolean isLocked() {
    return isLocked;
  }
}
