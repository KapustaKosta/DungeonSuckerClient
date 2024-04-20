package com.mipt.tp.dungeon_sucker;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.WeaponGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts.IronChestplate;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer.Club;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Character;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.Rat;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.math.IntVector2;
import org.w3c.dom.ls.LSOutput;

import java.util.LinkedList;

public class FightTry {
  public static void aboba() {
    DungeonMasster DM = new DungeonMasster();
    Character vasya = new Character(15, 9999, "Vasya", DM);
    IronChestplate chestplate = new IronChestplate();
    chestplate.getObtained(vasya);
    DM.add(vasya.weight, vasya);
    Creature[] entities = new Creature[4];
    for (int i = 0; i < 4; ++i) {
      entities[i] = new Rat(
          true,
          new Room(new IntVector2(), new Texture("room.png"), new Creature[0], DM),
          "rat" + i);
    }
    Room room = new Room(new IntVector2(), new Texture("room.png"), entities, DM);
    Club club = new Club(1, 10, "club", RaritySet.Common);
    vasya.setActiveWeapon(club);
    vasya.moveToRoom(room);
    DM.move();
  }

  public static void generateWeapons() {
    LinkedList<Weapon> weapons = new LinkedList<>();
    for(int i = 0; i < 1000; ++i){
      Weapon weapon = new WeaponGenerator().generateWeapon(1);
      weapons.add(weapon);
      System.out.println(weapon.name);
    }
    System.out.println("lol");}
}
