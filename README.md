# ![Treasurehunt](/src/main/resources/img/treasure.png) Treasurehunt
This program is based off of Minesweeper. However the mines are replaced with treasures, the goal is to find all the treasures
in the least amount of steps as possible. 

In this game, there is a board that has MxN number of tiles and X number of treasures, these parameters are set through the
menu. Upon revealing a tile it can either be a treasure tile or a number tile. Number tile shows how many treasure tiles 
are adjacent to it (max 8). Upon revealing all treasure tiles the game is over and the moves spent to find all of the 
treasures along with your name will be saved if the score is better than on the Top 10 list.

This game project was a home assignment for a Java course at the university.

* Libraries used
  * JavaFX
  * Gson
  
The project requires Java 8 because of the use of lambdas


