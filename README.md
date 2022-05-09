# COMP127 Final Project: The hardest Java version of diep.io

#### Project Contributors: Quang Nguyen, Andrew Nguyen

## Project Description
This game is a simpler but harder version of webgame diep.io. This version has:
- A tank which consists of a body and a rotating cannon. The tank can shoot bullets.
- The enemies which move around and hurt the tank.
- The boss enemy which kills the tank if the tank touches the boss.
- Starting screen, winning screen, and losing screen.

### Game Walk-though

<img src='https://i.imgur.com/4hPv4T6.gif' title='Video Walkthrough' width= 400 alt='Video Walkthrough Starting Screen'/>

<img src='https://i.imgur.com/qsrjLwQ.gif' title='Video Walkthrough Losing Screen' width= 400 alt='Video Walkthrough Losing Screen'/>

<img src='https://i.imgur.com/vNXIISq.gif' title='Video Walkthrough Winning Screen' width= 400 alt='Video Walkthrough Winning Screen'/>

## Functionalities
- The game runs when running the Main class in DiepGame.java
- Player should see a start screen with a start button, a quit button, and a text box asking for username. 
- Player should see a tank consisting of a body and cannon. 
- Player should see geometric objects/enemies floating on the playing screen.
- Player should see bullet shot from the tank when the player clicks on the playing screen.
- Player should see a health bar under each object on the playing screen.
- Player should see the tank moving when press AWSD on keyboard.
- If the tank touches the enemy, it loses health, but loses all of its health if the tank touches the boss.
- The boss takes damage when bullets hit its core/center.
- Player wins the game when the boss is dead.
- Player loses the game when the tank loses all of its health or the boss moves out of the canvas.

## Challenges
- Time: We had to reduce the scope of the project significantly to complete the project on time.
- Inheritance: We overcomplicated our code by overusing inheritance. 
- Graphical inaccuracy: The graphics of the game is not perfectly accurate because sometimes the bullets miss the objects.

## Possible Extensions
- Making the game multiplayers: Use the concept of Sockets and Multithreading in Java.
- Diversifying enemy's behaviors: For example, enemy can shoot projectiles, move to dodge the bullets, and chase the players.
- Upgrading tank based on experience points: Gain experience points by killing enemies. When reaching a certain amount of experience, the player can upgrade the tank with faster bullets, movement, or more cannon heads, etc.
- Adding levels of difficulty and playing modes: Easy, Medium, Hard, Survival, Team Battle, Tag. The current playing mode is a Survival mode that does not have a time limit.

## Acknowledgement
- Browser game diep.io
- Professors: Joslenne Peña, Paul Cantrell
- Preceptors: Nam, Tim, Myles, Hari
- Classmates
- Friends and family
