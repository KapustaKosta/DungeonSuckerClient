package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.UndoneArtifacts;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class DeathResistance extends Artifact {
  public DeathResistance() {
    super();
    this.triggerableByBeingDamaged = true;
    this.weight = 0;
    this.effectiveness = 3;
    this.description = "Death damage is twice less effective";
    this.name = "Death damage Resistance";
  }

  public void triggerByBeingDamaged(Damage damage) {
    if (damage.element == ElementSet.Death) {
      damage.elementDamage /= 2;
      damage.totalDamage = damage.defaultDamage + damage.elementDamage;
    }
  }
}
