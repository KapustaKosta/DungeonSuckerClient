package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons;

import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;

public class ChargableWeapon extends Weapon {
    public int charges;
    public int[] chargesForSkill;

    public ChargableWeapon(int numberOfSkills) {
        super(numberOfSkills);
    }
}
