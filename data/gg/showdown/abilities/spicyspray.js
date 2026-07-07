{
  onDamagingHit(damage, target, source, move) {
      source.trySetStatus("brn", target);
  },
  flags: {},
  name: "Spicy Spray",
  rating: 5,
  num: 211
}