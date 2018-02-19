# Tennis Game Kata
Tennis game kata for practice TDD implemented in Java by Arthur Latimier 

# Problem Description
This Kata is about implementing a simple tennis game.

The scoring system is the following :

* The score sequence is 0 15 30 40
* If a player have 40 and win the point, then he win the game, unless other player also have 40...
* If both players have same score then the score is 0/A, 15/A, 30/A or 40/A
* If both players have 40, the next one to score will have advantage (ex : Advantage playerOne)
* If the player with advantage scores then he win the game, otherwise there is a "Deuce" and next point will give another advantage.
* The server score must be announced before the receiver score (ex : 0/15 if receiver scored first)