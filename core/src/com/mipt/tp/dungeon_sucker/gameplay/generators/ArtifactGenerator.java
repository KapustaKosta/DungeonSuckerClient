package com.mipt.tp.dungeon_sucker.gameplay.generators;

import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsFirBoth.*;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForEnemies.ElementVulnerability;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts.*;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts.RingOfHealth;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts.RingOfMagicProtection;

import java.util.Random;

public class ArtifactGenerator {
  final int NUMBER_OF_ARTIFACTS = 19;

  public Artifact generateArtifact() {
    int whatToGenerate = new Random().nextInt(this.NUMBER_OF_ARTIFACTS);
    switch (whatToGenerate) {
      case 0:
        return new IronChestplate();
      case 1:
        return new RingOfFortune();
      case 2:
        return new RingOfHealth();
      case 3:
        return new FuryOfTheFallen();
      case 4:
        return new RingOfMagicProtection();
      case 5:
        return new HuntersRing();
      case 6:
        return new KnightsRing();
      case 7:
        return new PriestessRing();
      case 8:
        return new ScholarsRing();
      case 9:
        return new DarkResistance();
      case 10:
        return new ElementResistance();
      case 11:
        return new FireResistance();
      case 12:
        return new FrostResistance();
      case 13:
        return new HardShell();
      case 14:
        return new LightResistance();
      case 15:
        return new MagicResistance();
      case 16:
        return new PointResistance();
      case 17:
        return new PoisonResistance();
      case 18:
        return new ThornsOfRevenge();
    }
    throw new RuntimeException("You wasn't supposed to see that, contact us and we'll change some code with Artifact Generation");
  }
}
