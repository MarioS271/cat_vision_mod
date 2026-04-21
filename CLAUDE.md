# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Active version only (fast, use during development)
./gradlew :fabric:build
./gradlew :neoforge:build
./gradlew :forge:build

# All versions of one loader (Stonecutter)
./gradlew :fabric:chiseledBuild
./gradlew :neoforge:chiseledBuild

# Every version of every loader
./gradlew chiseledBuild

# Run client for the active version
./gradlew :fabric:runClient
./gradlew :neoforge:runClient
./gradlew :forge:runClient

./gradlew :common:genSources    # deobfuscated MC sources for IDE
```

No test suite — verification is done by running the client.

## Project Overview

CatVision is a **client-side only** mod (servers never load it) that provides toggleable night vision and immunity to Blindness, Darkness, and Nausea. No server operator permission required.

**Mod ID:** `cat_vision` | **Java:** 21 | **Active MC:** 26.1

## Multi-Loader / Multi-Version Architecture

This is a **Stonecutter + multi-module** project. No Architectury API at runtime.

```
common/          ← all game logic, zero loader imports
fabric/          ← Fabric 1.20.4 / 1.21.4 / 26.1
neoforge/        ← NeoForge 1.21.4 / 26.1
forge/           ← Forge 1.20.4 only
```

**Source sharing:** `fabric/`, `neoforge/`, and `forge/` each include `common/`'s source directories via `srcDirs +=` in their `sourceSets` block. No binary jar dependency between modules.

**Platform abstraction:** `CatVisionPlatform.setConfigDir(path)` is called first in each loader's entry point. `CatVisionCommon.getConfig()` is lazy and safe because the path is set before first access.

**Event flow per loader:**
- Fabric: `CatVisionFabric` (ClientModInitializer) → registers Fabric API events + `KeyMappingHelper`
- NeoForge: `CatVisionNeoForge` (@Mod dist=CLIENT) → registers on NeoForge event buses
- Forge: `CatVisionForge` (@Mod + DistExecutor.CLIENT) → registers on Forge event buses

**Config screen:** `ConfigScreen.java` lives in common (Cloth Config API is multi-loader). ModMenu integration is Fabric-only. NeoForge and Forge register via `IModConfigScreenFactory` / `ConfigScreenHandler`.

## Stonecutter Preprocessor

Version-specific source blocks use Stonecutter comment directives. Since active version is 26.1, the `if >=1.21` branch is always active (uncommented) in VCS:

```java
//? if >=1.21 {
client.player.sendOverlayMessage(msg);   // ← active in VCS (26.1)
//?} else {
/*
client.player.displayClientMessage(msg, true);   // ← 1.20.4 fallback
*/
//?}
```

Switching active version: `./gradlew "Set active version to 1.20.4"` (Stonecutter task).

## Key Dependencies

| Dependency | Loader | How delivered |
|---|---|---|
| `fabric-api` | Fabric | modImplementation |
| `cloth-config-{fabric,neoforge,forge}` | All | Jar-in-Jar (no user download needed) |
| `modmenu` | Fabric | modCompileOnly (optional, not bundled) |

## Adding Features

- **New behaviors:** add a `logic/XxxLogic.java` in common with static methods, then register the event in each loader's event class
- **New config option:** add field to `ConfigData`, add toggle to `ConfigScreen.create()`
- **New MC version:** add version entry to `settings.gradle` stonecutter block + add version-specific dependency values to `gradle.properties` and the version maps in each loader's `build.gradle`
- **Translation keys:** `text.cat_vision.<key>` — add to all three lang files in `common/src/main/resources/assets/cat_vision/lang/`
