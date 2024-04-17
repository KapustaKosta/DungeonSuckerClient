package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class ThornsOfRevenge extends Artifact {
  public ThornsOfRevenge() {
  super();
  this.triggerableByBeingDamaged = true;
  this.weight = 2;
  this.effectiveness = 3;
  this.description = "If you are getting hit, enemy gets stung by these thorns taking " + this.effectiveness + " of sting damage";
  this.name = "Thorns of Revenge; weight = " + this.weight;
}

  public void triggerByBeingDamaged(Damage damage) {
    //public Damage(Entity dealer, String type, String element, boolean isMelee, double percentOfElementDamage, int totalDamage)
    System.out.println("stinging " + damage.dealer.name);
    if (damage.isMelee){
      damage.dealer.getDamaged(new Damage(this.holder, DamageTypeSet.Point, ElementSet.None, false, 0, this.effectiveness));
    }
    // Если будет скилл, активирующийся от получения дистанционного урона, то надо будет слегка переделать. ПОЭТОМУ ТАК НЕ ДЕЛАЕМ
  }
}
