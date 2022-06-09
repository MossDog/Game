# Big Awesome Idle Tower Defense Game
--------------------------------------
## Ideas
- Different save games.
- Mode that grants player all upgrade options.
- Mode that is like a candy crush escalating difficulty road, auto generated changes allow for alot of content for little effort. Different things sprinkled throughout diffrent level ranges Eg lvl 0-10 normal enemies of increasing difficulty, lvl 10 - 20 normal enemies plus a special enemy, etc. Starting levels could be bespoke with auto generated levels afterward or these could be seperate.
- Change state to a String to make code more readable?

## To Do List
- Move needs to be adapted to work based off of time.
- Consider different Art styles.
- Figure out different fields like health and damage, how do these scale depending on the round?
- Add upgrading player stats.
- Add saving player stats to playerstats.csv.
- Cookie clicker esque click to gain points at bare start (noStat).
- Draw two circles with noStroke() instead of 1 with stroke,fill.
- Consider relocating collision from Enemy to VisualSetup, this may also help with different weapon collision system in the future.
- fix damage cooldown on enemies

### Completed
- implemented cooldown times so gamespeed isn't dependant on frames.
- Implemented the beam as the primary weapon.
- implemented rudimentary health system and speed system.
- rounds are now cycled when arrayList is not populated.
- enemies can now die.
- player can now die.
- Added loading player stats from csv.
- Improved health and damage system to be more viable, could see further change.
- Enemy cooldown times now work properly.
- player now regenerates health over time
- fire function now split into drawbeam and dealdmg to accomodate different firing methods

## Notes
- There are redundant libraries in /lib/.
- Enemies and Player access eachother differently, the Enemy objects are passed the player upon creation, but the Player only accesses enemies after being passed the Enemies arraylist in a method call.