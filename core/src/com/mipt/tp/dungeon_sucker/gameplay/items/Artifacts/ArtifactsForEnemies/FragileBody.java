package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForEnemies;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

import java.util.Objects;

public class FragileBody extends Artifact {
  public FragileBody() {
    super();
    this.triggerableByBeingDamaged = true;
    this.weight = 2;
    this.effectiveness = 3;
    this.description = "Smashing attacks deal twice as much damage as they should've";
    this.name = "Fragile Body";
  }

  public void triggerByBeingDamaged(Damage damage) {
    if (damage.type == DamageTypeSet.Smash) {
      damage.totalDamage *= 2;
      damage.defaultDamage *= 2;
      damage.elementDamage *= 2;
    }
  }
}
