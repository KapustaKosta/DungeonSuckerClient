package com.mipt.tp.dungeon_sucker.Skills;

import com.mipt.tp.dungeon_sucker.UI.Buttons.Button;
import com.mipt.tp.dungeon_sucker.UI.Buttons.ButtonsGroup;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Predicate;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.ArrayList;

public class DamagingSkill extends Skill {

  protected Damage damage;
  protected Weapon weapon;
  protected DamageTypeSet type;

  private ArrayList<Action> listeners = new ArrayList<>();

  public DamagingSkill() {
    this.identifier = 0;
  }

  public void chooseVictimToAttack(Room room, Action listener) {
    ButtonsGroup.getInstance().clear();
    ButtonsGroup.getInstance().setArticle("Choose enemy");
    for (int i = 0; i < (room).hostileEntities.length; ++i) {
      if (room.hostileEntities[i] != null) {
        if (room.hostileEntities[i].isAlive) {
          final int index = i;
          ButtonsGroup.getInstance().addButton(new Button(((room.hostileEntities[i])).name + ": "
              + ((room).hostileEntities[i]).health + " hp", args -> onVictimChosen(index)));
        }
      }
    }
    listeners.add(listener);
  }

  private void onVictimChosen(int index) {
    for (Action listener : listeners) {
      listener.run(index);
    }
    listeners.clear();
  }

  public void chooseVictimToAttackUntilPredicate(Room room, Action listener, Predicate predicate) {
    ButtonsGroup.getInstance().clear();
    for (int i = 0; i < (room).hostileEntities.length; ++i) {
      if (room.hostileEntities[i] != null) {
        if (room.hostileEntities[i].isAlive) {
          final int index = i;
          ButtonsGroup.getInstance().addButton(new Button(((room.hostileEntities[i])).name + ": "
              + ((room).hostileEntities[i]).health + " hp",
              args -> onVictimChosenWithPredicate(room, index, predicate)));
        }
      }
    }
    listeners.add(listener);
  }

  public void onVictimChosenWithPredicate(Room room, int index, Predicate predicate) {
    if (!predicate.valueFits(index)) {
      chooseVictimToAttackUntilPredicate(room, null, predicate);
      return;
    }
    for (Action listener : listeners) {
      if (listener != null) {
        listener.run(index);
      }
    }
    listeners.clear();
  }
}
