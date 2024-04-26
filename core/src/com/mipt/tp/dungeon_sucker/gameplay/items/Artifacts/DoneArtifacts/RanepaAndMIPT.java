package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class RanepaAndMIPT extends Artifact {
  public RanepaAndMIPT() {
    super();
    this.triggerableByEnteringRoom = true;
    this.weight = 0;
    this.effectiveness = 3;
    this.description = "Kills you upon entering room with more that two enemies";
    this.name = "Ranepa & MIPT";
  }

  public void triggerByEnteringRoom(Room room) {
    if(room.amountOfHostileEntities > 1){
      this.holder.die();
    }
  }
}
