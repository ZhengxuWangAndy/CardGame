## Files
---------------------------------------------------------------------------
<A brief description of each file and what it does>

Classes below:
Board		define the board data struct and judge winner method for each turn.
drawBoard	draw the board to the console.
Game		the parent of all game
gameInitialization	define the parameters of every game and instance the Board and drawBoard class
Main		Control the top process
OrderChaos	control the process of order and chaos game
player		define the player structure and basic method
PrintAllRecord	use for print the current teams players and their scores situation
selectGame	let user select which game to play
setTeamsPlayers	use for set up teams and players. only need set one time on the beginning
team		define the team struct, add player at this team and get the whole team score.
Tic		control the process of order and chaos game


## Notes
---------------------------------------------------------------------------
1. <Files to be parsed should be stored in ConfigFiles, for parser class to
read class>

Config class, extends by Setplayers. Defined the player's initial money.


2. <Notes to grader>

Auto-compute hand value.

Non-stop playing.

Different color for outputs.

Clearly prompt.

Can exit anytime.

Can robustly process any possible wrong input.(including give String to number, wrong format and etc.) 

Highly completed.

Guarantee every player have different name.

Easily change initial money in config.


## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "pa1" after unzipping the files
2. Run the following instructions:
<Example below>
javac -d bin src/*.java
java -cp bin Main

## Input/Output Example (the output is to much so I only Tag the input line use [input]:)
---------------------------------------------------------------------------

Welcome to the game!		Author: Zhengxu Wang
You can input 'exit' whenever you want to quit the game
Which game do you want to play?
Enter 1 tic tac toe
Enter 2 orders and chaos
Enter 3 Trianta ena
Enter exit quit the game
Please input a valid number
q
Please input a valid number
3
Now we can add players, the first player is first round's dealer.
The first dealer has 100 * n money first.
Each round's highest money player will be dealer.
How many players do you want to add. Please input an integer more than 1.
q
It's not a valid number.
How many players do you want to add. Please input an integer more than 1.
2
What is the No.1 player's name? Please make sure every player have different name.
Tom
What is the No.2 player's name? Please make sure every player have different name.
Tom
What is the No.2 player's name? Please make sure every player have different name.
Jerry
Now we have players.
===================================================
Welcome to the game!		Author: Zhengxu Wang
You can input 'exit' whenever you want to quit the game
 _____  ____  _  ____  _      _____  ____    _____ _      ____ 
/__ __\/  __\/ \/  _ \/ \  /|/__ __\/  _ \  /  __// \  /|/  _ \
  / \  |  \/|| || / \|| |\ ||  / \  | / \|  |  \  | |\ ||| / \|
  | |  |    /| || |-||| | \||  | |  | |-||  |  /_ | | \||| |-||
  \_/  \_/\_\\_/\_/ \|\_/  \|  \_/  \_/ \|  \____\\_/  \|\_/ \|
                                                               
WELCOME!
This is all players.
Name : Tom , Money : 200
Name : Jerry , Money : 100
Hello, Tom . Dealer should be first and face up hand card.
Dealer face up card: ♠10
Hello, Jerry . That's your first card
Your first card: ♥8
Do you want to bet? (y/n)
y
Hi Jerry ! How much you want to bet? (1-100)
q
It's not a valid number.
Hi Jerry ! How much you want to bet? (1-100)
100
You have : ♥8	♣4	♦9	Total value: 21
Do you want to hit? y:hit n:stand
y
Your card: ♥5
You have : ♥8	♣4	♦9	♥5	Total value: 26
Do you want to hit? y:hit n:stand
n
Hello, Tom . You are this round's dealer.
You have : ♠10	♦3	♦Q	Total value: 23
Keep deal
You have : ♠10	♦3	♦Q	♠8	Total value: 31
============================================
Here are this round's result.
============================================
Hello Jerry.
You have : ♥8	♣4	♦9	♥5	Total value: 26
You are lower than dealer
You lost 100 to dealer
Now you have 0
============================================
Hello Tom. You are this round's dealer.
Now you have 300
============================================
============================================
Do you want have another round? (y/n)
y
Dear Tom, You are the only one left! You are final Winner!
GAME ENDED
Which game do you want to play?
Enter 1 tic tac toe
Enter 2 orders and chaos
Enter 3 Trianta ena
Enter exit quit the game
Please input a valid number
3
Now we can add players, the first player is first round's dealer.
The first dealer has 100 * n money first.
Each round's highest money player will be dealer.
How many players do you want to add. Please input an integer more than 1.
exit
Bye!

