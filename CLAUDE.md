# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
./gradlew build          # Build the mod jar (output in build/libs/)
./gradlew runClient      # Launch Minecraft client with the mod loaded
./gradlew clean          # Clean build artifacts
./gradlew genSources     # Generate deobfuscated Minecraft sources for reference
```

No test suite exists — verification is done by running the client.

## Project Overview

CatVision is a **client-side only** Fabric mod that provides toggleable night vision and immunity to negative visual effects (Blindness, Darkness, Nausea). It works without server operator permissions and persists state across sessions.

**Mod ID:** `cat_vision`  
**Entry point:** `net.marios271.cat_vision.CatVision` (implements `ClientModInitializer`)  
**Java:** 21+ (targets Java 25)  
**Minecraft:** 26.1

## Architecture

The mod is event-driven with a shared static config instance (`CatVision.CONFIG`).

**Main class** (`CatVision.java`): Loads config from disk and registers all event listeners and keybindings at client startup. This is the single wiring point.

**Event listeners** (`event/` package): Four single-responsibility listeners:
- `EndTickListener` — reapplies night vision and removes negative effects every tick
- `JoinListener` — restores night vision state on world join
- `RespawnListener` — reapplies effects after player respawn
- `DisconnectListener` — saves config to disk on disconnect

**Config system** (`config/` package):
- `ConfigData.java` — GSON-serialized settings stored in the Fabric config directory as `cat_vision.json`; holds both user preferences (toggles) and current mod state (whether NV is active)
- `ConfigScreen.java` — Cloth Config API screen registered via ModMenu
- `ModMenuIntegration.java` — ModMenu factory hook

**Input** (`handler/KeyInputHandler.java`): Registers the `,` keybinding and toggles night vision state, sending an overlay message to the player.

## Key Dependencies

| Dependency | Role |
|---|---|
| `fabric-api` | Core event hooks (tick, join, respawn, disconnect, keybindings) |
| `modmenu` | Exposes the config screen in the in-game mods list |
| `cloth-config-fabric` | Provides the config GUI components |

Both ModMenu and Cloth Config are optional at runtime but required at compile time.

## Adding Features

- New toggleable behaviors go in a new listener in `event/`, registered in `CatVision.onInitializeClient()`
- New config options are fields on `ConfigData` with a matching toggle in `ConfigScreen`
- Translation keys follow the pattern `text.cat_vision.<key>` and must be added to all three lang files (`en_us.json`, `de_de.json`, `de_at.json`)
