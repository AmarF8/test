{
    name: "Attarctplus",
    noCopy: true,
 onStart(pokemon, source, effect) {
        if (!this.runEvent("Attarctplus", pokemon, source)) {
          this.debug("Attarctplus event failed");
          return false;
        }
       if (ability.name === "Retreat") {
          this.add("-start", pokemon, "Attarctplus", "[from] ability: Retreat", "[of] " + source);
        }
      },
      onUpdate(pokemon) {
        if (this.effectState.source && !this.effectState.source.isActive && pokemon.volatiles["attractplus"]) {
          this.debug("Removing Attarctplus volatile on " + pokemon);
          pokemon.removeVolatile("attractplus");
        }
      },
      onBeforeMovePriority: 2,
      onBeforeMove(pokemon, target, move) {
       try{
        this.add("-activate", pokemon, "ability: Attarctplus", "[of] " + this.effectState.source);
        if (this.randomChance(1, 2)) {
          this.add("cant", pokemon, "Attarctplus");
          return false;
        }
        } catch (error) {
               this.add('-message', `${error}`);
             }
      },
      onEnd(pokemon) {
        this.add("-end", pokemon, "Attarctplus", "[silent]");
      }

  }