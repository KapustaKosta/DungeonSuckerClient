package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts;

import com.mipt.tp.dungeon_sucker.gameplay.entities.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class RingOfHealth extends Artifact {
  Entity holder;
  int effectiveness = 10;

  public void getObtained(Entity entity) {
    super.getObtained(entity);
    entity.health += this.effectiveness;
  }


  public void getLost() {
    this.holder.health -= this.effectiveness;
    super.getLost();
  }
}
