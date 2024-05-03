package com.mipt.tp.dungeon_sucker;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.*;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.WeaponGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts.IronChestplate;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsFirBoth.HammerMastery;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForBoth.Club;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Character;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

import java.util.LinkedList;

public class FightTry {
  public static void aboba() {
    DungeonMasster DM = new DungeonMasster();
    Character vasya = new Character(15, 9999, "Vasya", DM);
    IronChestplate chestplate = new IronChestplate();
    chestplate.getObtained(vasya);
    DM.add(vasya.weight, vasya);//  public Vampire(int health, int power, int weight, boolean isHostile, Room place) {
    Creature[] entities = new Creature[1];
    for (int i = 0; i < 1; ++i) {
      entities[i] = new Rat(9000, 1, 1,
          true,
          new Room(new IntVector2(), new Texture("room.png"), new Creature[0], DM));
    }
    Room room = new Room(new IntVector2(), new Texture("room.png"), entities, DM);
    room.threatLevel = 3;
    Club club = new Club(1, 1, "club", RaritySet.Common);
    club.getObtained(vasya);
    new HammerMastery().getObtained(vasya);
    vasya.setActiveWeapon(club);
    vasya.weight = 1000;
    vasya.moveToRoom(room);
    DM.move();
  }

  public static void generateWeapons() {
    LinkedList<Weapon> weapons = new LinkedList<>();
    for (int i = 0; i < 1000; ++i) {
      Weapon weapon = new WeaponGenerator().generateWeapon(1);
      weapons.add(weapon);
      System.out.println(weapon.name);
    }
    System.out.println("lol");
  }
}
