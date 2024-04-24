package com.mipt.tp.dungeon_sucker.gameplay;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;

public class Damage {
  public Entity dealer; // Чтобы иметь информацию, кто бьет
  public DamageTypeSet type; // Тип урона (колющий, режущий, дробящий, default для заклинаний
  public ElementSet element; // Стихия урона (огонь, свет, яд, лед)
  public boolean isMelee; // Буквально является ли атака милишной. Если да, то будут срабатывать какие-нибудь шипы, например
  public double percentOfElementDamage; // Какая доля урона наносится
  public int defaultDamage; // значение нестихийного урона
  public int elementDamage; // значение стихийного урона
  public int totalDamage;

  public Damage(Entity dealer, DamageTypeSet type, ElementSet element, boolean isMelee, double percentOfElementDamage, int totalDamage) {
    this.dealer = dealer;
    this.type = type;
    this.element = element;
    this.isMelee = isMelee;
    this.percentOfElementDamage = percentOfElementDamage;
    this.elementDamage = (int) (totalDamage * this.percentOfElementDamage);
    this.defaultDamage = totalDamage - this.elementDamage;
    this.totalDamage = totalDamage;
  }



  public Damage(Damage damage, int lastPower, int power) {this.dealer = damage.dealer;
    this.type = damage.type;
    this.element = damage.element;
    this.isMelee = damage.isMelee;
    this.percentOfElementDamage = damage.percentOfElementDamage;
    this.totalDamage = damage.totalDamage * power / lastPower;
    this.elementDamage = (int) (this.totalDamage * this.percentOfElementDamage);
    this.defaultDamage = this.totalDamage - this.elementDamage;
  }

  public Damage copy() {
    return new Damage(this.dealer, this.type, this.element, this.isMelee, this.percentOfElementDamage, this.totalDamage);
  }
}
