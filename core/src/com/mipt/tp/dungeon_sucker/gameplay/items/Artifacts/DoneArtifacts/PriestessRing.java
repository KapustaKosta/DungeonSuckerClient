package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class PriestessRing extends Artifact {
  int effectiveness = 5;

  public PriestessRing() {
    super();
    this.description = "Makes its holder more faithful";
    this.weight = 1;
    this.name = "Priestess's ring; weight = " + this.weight;
  }

  public void getObtained(Entity entity) {
    super.getObtained(entity);
    entity.faith += this.effectiveness;
    this.holder = entity;
    this.holder.recountWeapon();
  }


  public void getLost() {
    this.holder.faith -= this.effectiveness;
    super.getLost();
  }
}
