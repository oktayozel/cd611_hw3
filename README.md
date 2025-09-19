# CS611-Assignment 1
## Sliding Puzzle
---------------------------------------------------------------------------
- Name: Oktay Ozel
- Email: oozel@bu.edu
- Student ID: U93822204

## Files 
---------------------------------------------------------------------------
assignment_1/ 
├── out/ (Compiled class files are going into this folder.)                          
├── sliding_puzzle/
│   ├── app/ (Entry point of the program)
│   │   └── Main.java (Initializes the game and starts the program)
│   ├── components/ (Specialized versions of the core classes is in this dir.)
│   │   ├── SlidingPuzzleBoard.java (This class is responsible for holding the board and board operations.)
│   │   ├── SlidingPuzzleCell.java (This class is reponsible for operations in the Cell level and denotes each cell in a board.)
│   │   └── SlidingPuzzleGameManager.java (Manages all the game, basically holds one instance from other classes and orchestrates them.)
│   ├── core/ (This is the directory where core game classes are being hold. Classes that ensures extendibility sit here.)
│   │   ├── Board.java (This is an interface defining the must have functions of a board.)
│   │   ├── Cell.java (This is an abstract class which has the basic methods and fields that a board game would have.)
│   │   ├── GameManager.java (This is an abstract class defining the basic methods of a game manager like initialize the game, run the game.x)
│   │   ├── LeaderBoard.java (This class has been placed however not implemented in the game. Helps keeping track of the leaderboard)
│   │   └── User.java (This class holds the user details like username and move count.)
│   ├── data/
│   │   └── leaderboard.txt (Serves as a database for the leaderboard.)
│   └── io/
│        ├── Input.java (This class handles all input operations in the game.)
│        └── Output.java (This class handles all output operations in the game. )
├──run.sh (Running script for the game.)
├──compile.sh (Compilation script for the game.)
├──README.md



## Notes
---------------------------------------------------------------------------
Please explain the cool features of your program. Anything that you feel like you did a good job at or were creative about, explain it in bullets here. Additionally, any design decisions should be made here.

Example.
1. I did a nice job seperating the components into subdirectories app, components, core, data, io. 
2. Input and Output are completely seperated from the program flow.
3. I have implemeted a UI like visual so that game screen clears after every move.
4. I have implemented the movecount feature which holds the move count of the user and displays it real time.

## How to compile and run
---------------------------------------------------------------------------
Your directions on how to run the code. Make sure to be as thorough as possible! Ideally should resemble the lines below

1. Navigate to the directory "assignment_1" after unzipping the files
2. Run the following instructions:
javac -d out sliding_puzzle/app/*.java sliding_puzzle/core/*.java sliding_puzzle/io/*.java sliding_puzzle/components/*.java
java -cp out sliding_puzzle.app.Main        


NOTE: Instead you can simply run ./compile.sh and then ./run.sh

If you get a permission error you can simply give permission to those scripts with chmod +x compile.sh and  chmod +x run.sh 

## Input/Output Example
---------------------------------------------------------------------------
Please give us a full execution of what we should see on the screen. Label each text with input and output. For example:

```
Output:
[+] Hello there! Let's play a game!
[+] Enter a number for the following options:
[+] 1. Tic Tac Toe
[+] 2. Connect Four
[+] 3. Order and Chaos: 
Input:
1
Output:
[+] Welcome to Tic Tac Toe!
[+] Enter the number of teams: 
Input:
2
Output:
[+] Team 1 , please enter your team name: 
Input:
East
Output:
[+] Enter the number of players for team East: 
Input:
2
Output:
[+] Team 1 , please enter your team color: 
Input:
Red
Output:
[+] Team East, Player 1 , please enter your name: 
Input:
Biggie
Output:
[+] Team East, Player 1 , please enter your symbol: 
Input:
X
Output:
[+] Team East, Player 2 , please enter your name: 
Input:
Nas
Output:
[+] Team East, Player 2 , please enter your symbol: 
Input:
O
Output:
[+] Team 2 , please enter your team name: 
Input:
West
Output:
[+] Enter the number of players for team West: 
Input:
1  
Output:
[+] Team 2 , please enter your team color: 
Input:
Blue
Output:
[+] Team West, Player 1 , please enter your name: 
Input:
Pac
Output:
[+] Team West, Player 1 , please enter your symbol: 
Input:
X
Output:
[+] Would you like to change the board size? (y/n):
Input:
Y
Output:
[+] Enter the number of columns for the board: 
Input:
4
Output:
[+] Enter the number of rows for the board: 
Input:
3
Output:
[+] Would you like to change number of symbols in a row to win? (y/n): 
Input:
N
Output:
+---------------+---------------+---------------+---------------+
|         1         |         2         |         3        |       4       |
+---------------+---------------+---------------+---------------+
|         5         |         6         |         7        |       8       |
+---------------+---------------+---------------+---------------+
|         9         |         10       |         11      |       12      |
+---------------+---------------+---------------+---------------+
[+] Biggie, it's your turn! Please enter a valid position: 
Input:
1
+---------------+---------------+---------------+---------------+
|         X         |         2       |         3         |         4         |
+---------------+---------------+---------------+---------------+
|         5         |         6        |         7        |         8         |
+---------------+---------------+---------------+---------------+
|         9         |        10       |        11       |         12        |
+---------------+---------------+---------------+---------------+
[Omitted because Kevin is Lazy]
Output:
TEAM: East, PLAYER: Biggie played X's.
WINS: 1
LOSSES: 0
GAMES PLAYED: 1

TEAM: East, PLAYER: Nas played O's.
WINS: 1
LOSSES: 0
GAMES PLAYED: 1

TEAM: West, PLAYER: Pac played X's.
WINS: 0
LOSSES: 1
GAMES PLAYED: 1

[+] Would you like to play again? (y/n):
Input:
N
Output:
[+] Would you like to play a different game? (y/n):
Input:
Y
Output:
[+] Hello there! Let's play a game!
[+] Enter a number for the following options:
[+] 1. Tic Tac Toe
[+] 2. Connect Four
[+] 3. Order and Chaos:
Input:
Quit
Output:
[+] Goodbye!
```

