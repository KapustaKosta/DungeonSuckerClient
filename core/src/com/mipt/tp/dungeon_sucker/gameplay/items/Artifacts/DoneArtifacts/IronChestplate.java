package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class IronChestplate extends Artifact {

  public IronChestplate() {
    super();
    this.effectiveness = 3;
    description = "That is just an Iron chestplate. Somehow it can be worn with more armor. " +
        "Increases your Physical Defence by " + this.effectiveness;
    this.weight = 5;
    this.name = "Iron Chestplate; weight = " + this.weight;
  }

  public void getObtained(Entity entity) {
    super.getObtained(entity);
    this.holder.physicalArmor += this.effectiveness;
  }


  public void getLost() {
    this.holder.physicalArmor -= this.effectiveness;
    super.getLost();
  }
}
