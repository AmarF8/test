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
var items_exports = {};
__export(items_exports, {
  Items: () => Items,
});
module.exports = __toCommonJS(items_exports);
// Atlas: Suppress held-item in-battle form changes handled by Atlas.
//
// Stock Showdown calls `pokemon.formeChange("Zacian-Crowned", ...)` from the rusted
// sword's `onStart`. Cobblemon's `ShowdownPokemon.uuid` delegate parses
// `details.split(",")[1]` for the in-battle pokemon UUID, but a mid-battle
// formeChange regenerates `details` without the UUID injection that BattleRegistry
// adds at team-pack time. Result: `IndexOutOfBoundsException: 1 / size 1` in
// `BattleGUI.deriveRootActionSelection` — client crash.
//
// Atlas handles Zacian/Zamazenta pre-battle in `MegaService.preApplyHeldItemForm`,
// so their in-battle transform is redundant. Red Orb / Blue Orb are intentionally
// inert as held items; leaving Showdown's `runPrimal` path active mutates the
// persistent Cobblemon form through `FORME_CHANGE` and leaves the held item in an
// inconsistent state after battle. No-op hooks prevent the formeChange call while
// preserving the item ids for ordinary battle item lookup.
const Items = {
  rustedsword: {
    onTakeItem: false,
    onStart() {},
  },
  rustedshield: {
    onTakeItem: false,
    onStart() {},
  },
  redorb: {
    onTakeItem: false,
    onSwitchIn() {},
    onPrimal() {},
  },
  blueorb: {
    onTakeItem: false,
    onSwitchIn() {},
    onPrimal() {},
  },
};
