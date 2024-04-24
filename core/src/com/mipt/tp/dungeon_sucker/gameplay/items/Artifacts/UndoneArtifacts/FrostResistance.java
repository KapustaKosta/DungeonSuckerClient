package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.UndoneArtifacts;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class FrostResistance extends Artifact {
  public FrostResistance() {
    super();
    this.triggerableByBeingDamaged = true;
    this.weight = 0;
    this.effectiveness = 3;
    this.description = "Fire damage is twice less effective";
    this.name = "Frost Resistance";
  }

  public void triggerByBeingDamaged(Damage damage) {
    if (damage.element == ElementSet.Frost) {
      damage.elementDamage /= 2;
      damage.totalDamage = damage.defaultDamage + damage.elementDamage;
    }
  }
}
