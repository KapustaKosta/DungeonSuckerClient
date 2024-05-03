package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsFirBoth;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.WeaponTypes;
import com.mipt.tp.dungeon_sucker.gameplay.items.Mastery;

public class RangedMastery extends Mastery {
  public WeaponTypes upgradable = WeaponTypes.ranged;
  public RangedMastery(){
    super();
    this.upgradable = WeaponTypes.ranged;
    this.triggerableByBeingDamaged = false;    this.weight = 5;
    this.effectiveness = 3;
    this.description = "You feel much better when using ranged weapons";
    this.name = "Ranged mastery";
  }
  @Override
  public void triggerByBeingDamaged(Damage damage) {
    return;
  }
}
