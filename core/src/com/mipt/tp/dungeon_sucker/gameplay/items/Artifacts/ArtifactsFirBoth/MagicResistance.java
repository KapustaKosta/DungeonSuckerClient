package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsFirBoth;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class MagicResistance extends Artifact {
  public MagicResistance() {
    super();
    this.triggerableByBeingDamaged = true;
    this.weight = 5;
    this.description = "Magic attacks deal two times less damage";
    this.name = "Magic Resistance";
  }

  public void triggerByBeingDamaged(Damage damage) {
    if (damage.type == DamageTypeSet.Magic) {
      damage.totalDamage /= 2;
      damage.defaultDamage /= 2;
      damage.elementDamage /= 2;
    }
  }
}
