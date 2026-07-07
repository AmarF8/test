{
    effectiveWeather() {
		const weather = this.battle.field.effectiveWeather();
		switch (weather) {
		case 'sunnyday':
		case 'raindance':
		case 'desolateland':
		case 'primordialsea':
			if (this.hasItem('utilityumbrella')) return '';
		}
		if (this.hasAbility('megasol') && this.battle.activePokemon === this) return 'sunnyday';
		return weather;
	},
    isGrounded(negateImmunity = false) {
    if ("gravity" in this.battle.field.pseudoWeather && !this.hasAbility("moonshot"))
      return true;

    if ("ingrain" in this.volatiles && this.battle.gen >= 4)
      return true;
    if ("smackdown" in this.volatiles)
      return true;
    const item = this.ignoringItem() ? "" : this.item;
    if (item === "ironball")
      return true;
    if (!negateImmunity && this.hasType("Flying") && !(this.hasType("???") && "roost" in this.volatiles))
      return false;
    if (this.hasAbility("levitate") && !this.battle.suppressingAbility(this))
      return null;
     if (this.hasAbility("ionbattery") && !this.battle.suppressingAbility(this))
      return null;
    if ("magnetrise" in this.volatiles)
      return false;
    if ("telekinesis" in this.volatiles)
      return false;
    return item !== "airballoon";
  },
  setStatus(status, source = null, sourceEffect = null, ignoreImmunities = false) {
    if (!this.hp)
      return false;
    status = this.battle.dex.conditions.get(status);
    if (this.battle.event) {
      if (!source)
        source = this.battle.event.source;
      if (!sourceEffect)
        sourceEffect = this.battle.effect;
    }
    if (!source)
      source = this;
    if (this.status === status.id) {
      if (sourceEffect?.status === this.status) {
        this.battle.add("-fail", this, this.status);
      } else if (sourceEffect?.status) {
        this.battle.add("-fail", source);
        this.battle.attrLastMove("[still]");
      }
      return false;
    }
    if (!ignoreImmunities && status.id && !(source?.hasAbility("corrosion") && ["tox", "psn"].includes(status.id) || source?.hasAbility("sweetslime") && ["tox", "psn"].includes(status.id))) {
      if (!this.runStatusImmunity(status.id === "tox" ? "psn" : status.id)) {
        this.battle.debug("immune to status");
        if (sourceEffect?.status) {
          this.battle.add("-immune", this);
        }
        return false;
      }
    }
    const prevStatus = this.status;
    const prevStatusState = this.statusState;
    if (status.id) {
      const result = this.battle.runEvent("SetStatus", this, source, sourceEffect, status);
      if (!result) {
        this.battle.debug("set status [" + status.id + "] interrupted");
        return result;
      }
    }
    this.status = status.id;
    this.statusState = { id: status.id, target: this };
    if (source)
      this.statusState.source = source;
    if (status.duration)
      this.statusState.duration = status.duration;
    if (status.durationCallback) {
      this.statusState.duration = status.durationCallback.call(this.battle, this, source, sourceEffect);
    }
    if (status.id && !this.battle.singleEvent("Start", status, this.statusState, this, source, sourceEffect)) {
      this.battle.debug("status start [" + status.id + "] interrupted");
      this.status = prevStatus;
      this.statusState = prevStatusState;
      return false;
    }
    if (status.id && !this.battle.runEvent("AfterSetStatus", this, source, sourceEffect, status)) {
      return false;
    }
    return true;
  }
}