package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class RingOfHealth extends Artifact {
  Entity holder;
  int effectiveness = 10;

  public void getObtained(Entity entity) {
    super.getObtained(entity);
    entity.maxHealth += this.effectiveness;
    entity.health += this.effectiveness;
    this.name = "Ring of health; weight = " + this.weight;
  }


  public void getLost() {
    this.holder.getPermanentlyDamaged(this.effectiveness);
    super.getLost();
  }
}
