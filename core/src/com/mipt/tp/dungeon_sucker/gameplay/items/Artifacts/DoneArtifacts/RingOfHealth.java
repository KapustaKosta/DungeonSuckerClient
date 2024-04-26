package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class RingOfHealth extends Artifact {
  int effectiveness = 10;

  public RingOfHealth() {
    super();
    this.description = "Makes its holder more survivable";
    this.weight = 1;
    this.name = "Ring of Health; weight = " + this.weight;
  }

  public void getObtained(Entity entity) {
    super.getObtained(entity);
    this.holder.maxHealth += this.effectiveness;
    this.holder.health += this.effectiveness;try {
      this.holder.recountWeapon();
    } catch (Exception ignored) {
    }
  }


  public void getLost() {
    this.holder.decreaseMaxHP(this.effectiveness);
    super.getLost();
  }
}
