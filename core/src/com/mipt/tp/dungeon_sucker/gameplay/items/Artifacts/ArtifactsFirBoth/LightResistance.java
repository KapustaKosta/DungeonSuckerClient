package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsFirBoth;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class LightResistance extends Artifact {
  public LightResistance() {
    super();
    this.triggerableByBeingDamaged = true;
    this.weight = 5;
    this.effectiveness = 3;
    this.description = "Light damage is twice less effective";
    this.name = "Light Resistance";
  }

  public void triggerByBeingDamaged(Damage damage) {
    if (damage.element == ElementSet.Light) {
      damage.elementDamage /= 2;
      damage.totalDamage = damage.defaultDamage + damage.elementDamage;
    }
  }
}
