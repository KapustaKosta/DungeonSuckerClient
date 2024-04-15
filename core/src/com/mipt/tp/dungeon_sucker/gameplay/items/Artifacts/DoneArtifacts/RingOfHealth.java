package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class RingOfHealth extends Artifact {
  // Todo: модификаторы доступа
  Entity holder;
  int effectiveness = 10;

  @Override
  public void triggerByBeingDamaged(Damage damage) {

  }

  public void getObtained(Entity entity) {
    super.getObtained(entity);
    entity.maxHealth += this.effectiveness;
    entity.health += this.effectiveness;
    this.name = "Ring of health; weight = " + this.weight;
    this.description = "";
  }


  public void getLost() {
    this.holder.decreaseMaxHP(this.effectiveness);
    super.getLost();
  }
}
