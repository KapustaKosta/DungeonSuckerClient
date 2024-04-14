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

  public void triggerByBeingDamaged(Damage damage) {
    //public Damage(Entity dealer, String type, String element, boolean isMelee, double percentOfElementDamage, int totalDamage)
    System.out.println("stinging " + damage.dealer.name);
    if (Objects.equals(damage.type, "Smash")) {
      damage.totalDamage *= 2;
      damage.defaultDamage *= 2;
      damage.elementDamage *= 2;
    }
    // Если будет скилл, активирующийся от получения дистанционного урона, то надо будет слегка переделать. ПОЭТОМУ ТАК НЕ ДЕЛАЕМ
  }
}
