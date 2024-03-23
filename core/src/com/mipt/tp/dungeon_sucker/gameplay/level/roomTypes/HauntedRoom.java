package com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.gameplay.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.gameplay.entities.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class HauntedRoom extends Room {
  public Entity[] hostileEntities;

  public HauntedRoom(IntVector2 position, Texture texture, Creature[] creatures, DungeonMasster master) {
    super(position, texture, master);
    this.isHaunted = true;
    this.hostileEntities = creatures;
    for (int i = 0; i < this.hostileEntities.length; ++i) {
      creatures[i].setPlace(this);
      master.add(this.hostileEntities[i].weight, this.hostileEntities[i]);
    }
  }
  public HauntedRoom(IntVector2 levelPosition,
                     Texture texture) {
    super(levelPosition, texture);
  }
}
