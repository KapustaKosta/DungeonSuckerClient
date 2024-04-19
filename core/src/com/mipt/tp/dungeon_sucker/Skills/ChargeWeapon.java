package com.mipt.tp.dungeon_sucker.Skills;

import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.ChargableWeapon;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer.Bow;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class ChargeWeapon extends Skill {
  ChargableWeapon weapon;
  double power;
  double lastPower;

  public ChargeWeapon(ChargableWeapon weapon, int i) {
    super();
    this.weapon = weapon;
    this.power = i;
    this.lastPower = i;
  }
  public void use(Room room){
    this.weapon.charges += (int) this.power;
  }
}
