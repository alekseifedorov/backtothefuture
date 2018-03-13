This is a simple library written in Java for creating a command line game.
As an example a topic from the 'Back to the Future 2' movie is taken. 

The short story to win the game is following.
George McFly finds and hits Biff to make him drop the Almanac. Marty McFly grabs the Almanac
and finds the Time Machine. To use it Marty must gain experience in fighting with Biff or his friends. 
Each character has initially the health of 100 and the experience of 0.
You should switch from one character to another by typing > I'm <character's name>
(see below). In the begining switch into George ( > I'm G ) and hit Biff otherwise Marty can be bitten
 as he has the small weight of attack.

The following characters are present in the game :
  Name          Sign  Weight of attack   Enemies
--------------  ----  ----------------   -------
 Marty McFly     M        10             Biff, Biff's Friends
 George McFly    G        100            Biff
 Biff            B        30             Marty McFly, George McFly
 Biff's Friend   F        10             Marty McFly, George McFly
 Time Machine    T         -             -
 Almanac         A         -             -

There is in game only one Marty McFly, George McFly, Biff, the Time Machine and the Alamanac.
Using command 'create' you can create more Biff's friend (see below).


1. How to build
Type 'mvn clean install' to build target/backtothefuture-1.0.jar'

2. How to run
Type 'java -jar backtothefuture-1.0.jar' to run the game.
The Main class file is 'BackToTheFuture.class'.

3. How to play
The following commands are available to type in command line:

> save 
   Save the current game state in backtothefuture.dat file.

> resume
   Load the current game state from backtothefuture.dat file.

> I'm <sign>
   Play for the <sign> game character (e.g. I'm M)

> create <name> 
   Create the <name> character (e.g. create Biff's Friend)

> up
> down
> left
> right
  Move the hero who you play for after executing command > I'm <hero_name>

> quit
 Quit the game.

> demo
 The hero is moved automatically in random direction whithout a pause to read user input.

4. How to extend
The size of the game map and the screen showing the window of the map is specified in the constructor
of the GameModel class. The hero is always shown in the center of the screen.

To create a new character, create a class inherited from AbstractGameCharacter.

To create a new artifact, create a class inherited from AbstractThing.

Interaction between a object and the world is specified by overriding notify(Event event).

A character has the following attributes:
  - health
  - weight of attack
  - experience
  - a collection of things
  - a sign to present a charter on screen

To specify the enemies for a character, override isEnemy(GameCharacter character) in AbstractGameCharacter.

To specify a specific damage on a character, override hitBy(GameCharacter character).

Artifacts in sacks of characters are not visible on the screen.

