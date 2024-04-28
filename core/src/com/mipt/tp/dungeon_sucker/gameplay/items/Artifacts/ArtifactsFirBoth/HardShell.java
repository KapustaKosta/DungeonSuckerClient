package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsFirBoth;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

import java.util.Objects;

public class HardShell extends Artifact {
  public HardShell() {
    super();
    this.triggerableByBeingDamaged = true;
    this.weight = 0;
    this.description = "Smashing attacks deal two times less damage";
    this.name = "Hard Shell";
  }

  public void triggerByBeingDamaged(Damage damage) {
    if (damage.type == DamageTypeSet.Smash) {
      damage.totalDamage /= 2;
      damage.defaultDamage /= 2;
      damage.elementDamage /= 2;
    }
  }
}
