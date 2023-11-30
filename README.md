**Due to life changes, I am no longer working on this. Feel free to [browse the forks](https://github.com/agausmann/sign-searcher/forks), or fork it yourself!**

# Sign Searcher

Sign Searcher is designed to help you find signs that match a given query text.
Its intended usage is for finding items in larger multiplayer markets, where
each person may have their own stall and so hunting for specific items can get
difficult or time consuming.

## Install

Install [Fabric and Fabric API](https://fabricmc.net/use/), and copy the latest
`jar` corresponding to your game version to the `mods` folder in your game
directory.

## Usage

To invoke SignSearcher, use the following (client-side) commands:

- `/signsearch <query>` - Starts a new query with the given text (may contain
spaces, does not need to be quoted), and highlights all matching signs.

- `/signsearch` (with no arguments) - Clears the current query, removing the
highlight effect from all signs.

## Compatibility

Because of the changes that Sign Searcher makes to the rendering engine, it is
incompatible with most optimization mods, but in most cases it can be made
compatible. Known combinations are:

- **Sodium** - Fully compatible, maintained by me.

- **Optifine / Optifabric** - Untested, is likely not compatible, and I'm not
willing to maintain it myself; however, PRs are welcome!
