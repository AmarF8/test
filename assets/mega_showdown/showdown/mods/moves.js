"use strict";
var __defProp = Object.defineProperty;
var __getOwnPropDesc = Object.getOwnPropertyDescriptor;
var __getOwnPropNames = Object.getOwnPropertyNames;
var __hasOwnProp = Object.prototype.hasOwnProperty;
var __export = (target, all) => {
  for (var name in all)
    __defProp(target, name, { get: all[name], enumerable: true });
};
var __copyProps = (to, from, except, desc) => {
  if ((from && typeof from === "object") || typeof from === "function") {
    for (let key of __getOwnPropNames(from))
      if (!__hasOwnProp.call(to, key) && key !== except)
        __defProp(to, key, {
          get: () => from[key],
          enumerable: !(desc = __getOwnPropDesc(from, key)) || desc.enumerable,
        });
  }
  return to;
};
var __toCommonJS = (mod) =>
  __copyProps(__defProp({}, "__esModule", { value: true }), mod);
var moves_exports = {};
__export(moves_exports, {
  Moves: () => Moves,
});
module.exports = __toCommonJS(moves_exports);
// Atlas: Judgment falls back to Arceus's species type when no Plate is held.
//
// Atlas consumes plates on right-click (the plate isn't equipped to Arceus's held
// slot), so by battle time Arceus has no held item even though its form has changed
// to Arceus-<Type>. Stock Showdown's `Judgment.onModifyType` reads
// `pokemon.getItem().onPlate` — with no plate item, `onPlate` is undefined and
// Judgment stays Normal. Cobblemon still sends Arceus-Fairy / -Water / etc. as the
// species (so damage typing / weaknesses already follow the form via the species
// dex), but the move-type pick-up needs this override to match.
const Moves = {
  judgment: {
    inherit: true,
    onModifyType(move, pokemon) {
      if (pokemon.ignoringItem()) return;
      const item = pokemon.getItem();
      if (item.id && item.onPlate && !item.zMove) {
        move.type = item.onPlate;
        return;
      }
      // Fallback to the pokemon's current primary type so consumed-plate Arceus
      // still launches a typed Judgment matching its form.
      const speciesType = pokemon.species && pokemon.species.types && pokemon.species.types[0];
      if (speciesType && speciesType !== "Normal") {
        move.type = speciesType;
      }
    },
  },
};
