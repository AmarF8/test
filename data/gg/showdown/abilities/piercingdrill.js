{
  onTryHit(target, source, move) {
    if (target.volatiles['protect']) {
      this.add('-message', `${source.name}'s attack pierces Protect!`);
      
      const damage = this.getDamage(source, target, move);
      if (damage) {
        this.damage(damage * 0.25, target, source, move);
      }
      return null; 
    }
  },
  name: "Piercing Drill",
  rating: 2,
  num: 260,
}