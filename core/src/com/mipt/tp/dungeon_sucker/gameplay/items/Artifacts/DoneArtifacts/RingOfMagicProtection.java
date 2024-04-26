package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class RingOfMagicProtection extends Artifact {
  int effectiveness = 2;
  String description = "Increases defence from magic damage by " + this.effectiveness;
  public RingOfMagicProtection(){
    this.weight = 2;
    this.name = "Ring of MAGIC protection; weight = " + this.weight;
  }

  public void getObtained(Entity entity) {
    super.getObtained(entity);
    this.holder.magicalArmor += this.effectiveness;
  }


  public void getLost() {
    holder.magicalArmor -= this.effectiveness;
    super.getLost();
  }
}
