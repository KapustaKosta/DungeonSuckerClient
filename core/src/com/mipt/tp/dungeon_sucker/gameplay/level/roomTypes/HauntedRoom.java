package com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class HauntedRoom extends Room {

  public HauntedRoom(IntVector2 position, Texture texture, Creature[] creatures, DungeonMasster master) {
    super(position, texture, master);
    super.isHaunted = true;
    super.isCleared = false;
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

  public HauntedRoom() {
    super();
  }
}
