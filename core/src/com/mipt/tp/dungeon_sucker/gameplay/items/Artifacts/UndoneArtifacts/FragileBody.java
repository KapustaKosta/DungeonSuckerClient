package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.UndoneArtifacts;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
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

  @Override
  public void triggerByBeingDamaged(Damage damage) {
    System.out.println("stinging " + damage.dealer.name);
    if (Objects.equals(damage.type, "Smash")) {
      damage.totalDamage *= 2;
      damage.defaultDamage *= 2;
      damage.elementDamage *= 2;
    }
  }
}
