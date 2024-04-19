package com.mipt.tp.dungeon_sucker.gameplay.generators;

import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts.*;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts.RingOfHealth;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.UndoneArtifacts.RingOfMagicProtection;

import java.util.Random;

public class ArtifactGenerator {
  final int NUMBER_OF_ARTIFACTS = 5;

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
    }
    throw new RuntimeException("You wasn't supposed to see that, contact us and we'll change some code with Artifact Generation");
  }
}
