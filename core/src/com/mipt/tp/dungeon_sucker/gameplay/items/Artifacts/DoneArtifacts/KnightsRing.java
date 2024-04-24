package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class KnightsRing extends Artifact {
  int effectiveness = 5;

  public KnightsRing() {
    super();
    this.description = "Makes its holder more strong";
    this.weight = 1;
    this.name = "Knight's ring; weight = " + this.weight;
  }

  public void getObtained(Entity entity) {
    super.getObtained(entity);
    entity.strength += this.effectiveness;
    this.holder = entity;
    this.holder.recountWeapon();
  }


  public void getLost() {
    this.holder.strength -= this.effectiveness;
    super.getLost();
  }
}
