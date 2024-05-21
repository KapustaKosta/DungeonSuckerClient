package com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures;

import com.google.gson.Gson;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Character;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.generators.ArtifactGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.WeaponGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class FallenHero extends Creature {
    public FallenHero(String jsonObject, boolean isHostile, Room place){
        this(new Gson().fromJson(jsonObject, int[][].class)[2], new Gson().fromJson(jsonObject, int[][].class)[0],
                new Gson().fromJson(jsonObject, int[][].class)[1], isHostile, place);
    }

    public FallenHero(int[] stats, int[] artifactsForObtaining, int[] weaponInfo, boolean isHostile, Room place) {
        super(stats[8], stats[0], stats[1], isHostile, place, "Fallen Hero");
        int power = stats[0];
        int baseWeight = stats[1];
        int vigor = stats[2];
        int carrying = stats[3];
        int strength = stats[4];
        int dexterity = stats[5];
        int intellect = stats[6];
        int faith = stats[7];
        int baseHealth = stats[8];
        int weight = stats[9];
        this.vigor = vigor;
        this.carrying = carrying;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intellect = intellect;
        this.faith = faith;
        this.maxHealth = baseHealth * vigor;
        this.health = this.maxHealth;
        for (int j : artifactsForObtaining) {
            ArtifactGenerator.generateArtifact(j).getObtained(this);
        }
        this.weapon = new WeaponGenerator().generateWeapon(ElementSet.getByID(weaponInfo[0]),
                weaponInfo[1], RaritySet.getByID(weaponInfo[2]), weaponInfo[4]);
        this.recountWeight();
    }
    public void summon(Action doAfterMove){
        this.weapon.getObtained(this);
        this.weapon.recount();
        super.summon(doAfterMove);
    }
}
