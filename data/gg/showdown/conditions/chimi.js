{
  name: "chimi",
  noCopy: true,
  onStart(pokemon, source) {
    this.add("-start", pokemon, "痴迷", "[from] ability: 曼妙身姿", "[of] " + source);
  },
  onBeforeMovePriority: 2,
  onBeforeMove(pokemon) {
    this.add("-activate", pokemon, "痴迷", "[of] " + this.effectState.source);
    if (this.randomChance(1, 2)) {
      this.add("cant", pokemon, "痴迷");
      return false;
    }
  },
  onUpdate(pokemon) {
    if (this.effectState.source && !this.effectState.source.isActive && pokemon.volatiles["chimi"]) {
      this.debug("Removing chimi volatile on " + pokemon);
      pokemon.removeVolatile("chimi");
    }
  },
  onEnd(pokemon) {
    this.add("-end", pokemon, "痴迷", "[silent]");
  }
}