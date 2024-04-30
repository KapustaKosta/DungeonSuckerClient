package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class HuntersRing extends Artifact {
  int effectiveness = 5;

  public HuntersRing() {
    super();
    this.description = "Makes its holder more agile";
    this.weight = 1;
    this.name = "Hunter's ring; weight = " + this.weight;
  }

  @Override
  public void triggerByBeingDamaged(Damage damage) {

  }

  public void getObtained(Entity entity) {
    super.getObtained(entity);
    entity.dexterity += this.effectiveness;
    this.holder = entity;
    try {
      this.holder.recountWeapon();
    } catch (Exception ignored) {
    }
  }


  public void getLost() {
    this.holder.dexterity -= this.effectiveness;
    super.getLost();
  }
}
