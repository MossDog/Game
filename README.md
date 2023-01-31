# Big Awesome Idle Tower Defense Game

## Ideas
- Different save games.
- Mode that grants player all upgrade options.
- Mode that is like a candy crush escalating difficulty road, auto generated changes allow for alot of content for little effort. Different things sprinkled throughout diffrent level ranges Eg lvl 0-10 normal enemies of increasing difficulty, lvl 10 - 20 normal enemies plus a special enemy, etc. Starting levels could be bespoke with auto generated levels afterward or these could be seperate.
- Change ```int state``` to a ```String``` to make code more readable?
- make a kanban board on trello.
- background color could change according to kind of round it is, cool animation could be made to transition these states.
- different objects can be given ```color``` variables to accomodate things changing color in response to events.
- consider changing switch statement in ```draw()``` to have ```case 1:``` call a ```method``` like ```runGame()``` to run it's current contents.
- Consider giving ```Enemy``` a second ```constructor``` that allows you to pass all stats instead of the usual ```rank``` to allow for ```custom enemies```.
- ending the game in a ```higher round``` can apply a ```score multiplier``` like ```+100%``` score every ```ten rounds```. simple way to implement ```power and reward scaling```. E.g. ```price scales``` increasingly with the ```tiers of items``` in the shop, this keeps higher tiers somewhat out of players reach aside from being able to save points to unlock more powerful items (a tier or two higher than current). Before getting powerfull enough to make it to a higher level range, income multiplies, entire next tier gained quickly from there. ```No lvl requirement or XP gain```.

## To Do List
- Implement tick counter based on time so that time can be accelerated and decelerated much easier
- Consider different Art styles.
- Figure out different fields like health and damage, how do these scale depending on the round?
- Add upgrading player stats.
- Add saving player stats to ```playerstats.csv```.
- Cookie clicker esque click to gain points at bare start (noStat).
- Draw two circles with ```noStroke()``` instead of 1 with ```stroke()```, ```fill()```.
- Consider relocating collision from ```Enemy``` to ```VisualSetup```, this may also help with different weapon collision system in the future.
- fix damage cooldown on enemies.
- figure out how to export to jar so people can run the game more easily.
- give ```enemies``` a ```string``` that is passed to them which determines their type. simplifies ```generateEnemies()``` in ```visualSetup```.
- change drawBeam() call back to fire() which has ```switch(fireMode){drawBeam(), etc}```.
- update ```debugHud()```.

## Completed
- implemented cooldown times so gamespeed isn't dependant on frames.
- Implemented the beam as the primary weapon.
- implemented rudimentary health and speed systems.
- rounds are now cycled when arrayList is not populated.
- enemies can now die.
- player can now die.
- Added loading player stats from ```csv```.
- Improved health and damage system to be more viable, could see further change.
- Enemy cooldown times now work properly.
- player now regenerates health over time.
- fire function now split into drawbeam and dealdmg to accomodate different firing methods.
- Enemy movement is now time based rather than frame based.

## Notes
- There are redundant libraries in ```/lib/```.
- ```Enemies``` and ```Player``` access eachother differently, the Enemy objects are passed the player upon creation, but the ```Player``` only accesses ```enemies``` after being passed the ```Enemies``` arraylist in a method call.