package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.UndoneArtifacts;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class ElementResistance extends Artifact {
  public ElementResistance() {
    super();
    this.triggerableByBeingDamaged = true;
    this.weight = 0;
    this.effectiveness = 3;
    this.description = "All elemental damage is twice less effective";
    this.name = "Elemental Resistance";
  }

  public void triggerByBeingDamaged(Damage damage) {
    damage.elementDamage /= 2;
    damage.totalDamage = damage.defaultDamage + damage.elementDamage;

  }
}
