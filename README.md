# Sign Searcher

Sign Searcher is designed to help you find signs that match a given query text. Its intended usage is for finding items in larger multiplayer markets, where each person may have their own stall and so hunting for specific items can get difficult or time consuming.

## Install

Install [Fabric and Fabric API](https://fabricmc.net/use/), and copy the latest `jar` corresponding to your game version to the `mods` folder in your game directory.

## Usage

To invoke SignSearcher, use the following (client-side) commands:

- `/signsearch <query>` - Starts a new query with the given text (may contain spaces, does not need to be quoted), and highlights all matching signgs.
- `/signsearch` (with no arguments) - Clears the current query, removing the highlight effect from all signs.

## Compatibility

Because of the changes that Sign Searcher makes to the rendering engine, it is incompatible with most optimization mods:

- It is not, and will likely never be, compatible with Optifine/OptiFabric. 
- It is not compatible with Sodium, but work is in progress to make it compatible.