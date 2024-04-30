package com.mipt.tp.dungeon_sucker.Skills;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;

public class DamagingSkill extends Skill {
  protected Damage damage;
  protected Weapon weapon;
  protected DamageTypeSet type;
  public DamagingSkill(){
    this.identifier = 0;
  }
}
