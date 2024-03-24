package com.mipt.tp.dungeon_sucker.gameplay.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mipt.tp.dungeon_sucker.UI.Drawable;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.gameplay.entities.Entity;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class Room implements Drawable {
  DungeonMasster master;
  public Entity[] friendlyEntities = new Entity[8];
  public Entity[] hostileEntities = new Entity[1];
  private final IntVector2 levelPosition;
  private Texture texture;
  private SpriteBatch batch;
  public int amountOfFriendlyEntities = 0;
  public boolean isHaunted = false;
  public int amountOfHostileEntities = 0;

  protected boolean isLocked = false;

  public Room(IntVector2 levelPosition, Texture texture)
  {
    this.levelPosition = levelPosition;
    this.texture = texture;
    this.batch = new SpriteBatch();
  }

  public Room(IntVector2 levelPosition, Texture texture, DungeonMasster master) {
    this.levelPosition = levelPosition;
    this.texture = texture;
    this.batch = new SpriteBatch();
    this.master = master;
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

  public void insert(Entity entity, boolean isHostile) {
    if(isHostile){
      this.hostileEntities[this.amountOfHostileEntities++] = entity;
      return;
    }
    entity.place = this;
    this.friendlyEntities[this.amountOfFriendlyEntities++] = entity;
  }
}
