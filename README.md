# CS611-Assignment 1
## Sliding Puzzle
---------------------------------------------------------------------------
- Name: Oktay Ozel
- Email: oozel@bu.edu
- Student ID: U93822204


- Andrew
-
-


!!!!! CHANGE BELOW!!!!! IT BELONGS TO THE OLD PUZZLE!!!!
!!!!! CHANGE BELOW!!!!! IT BELONGS TO THE OLD PUZZLE!!!!
!!!!! CHANGE BELOW!!!!! IT BELONGS TO THE OLD PUZZLE!!!!
!!!!! CHANGE BELOW!!!!! IT BELONGS TO THE OLD PUZZLE!!!!
!!!!! CHANGE BELOW!!!!! IT BELONGS TO THE OLD PUZZLE!!!!
!!!!! CHANGE BELOW!!!!! IT BELONGS TO THE OLD PUZZLE!!!!
!!!!! CHANGE BELOW!!!!! IT BELONGS TO THE OLD PUZZLE!!!!
!!!!! CHANGE BELOW!!!!! IT BELONGS TO THE OLD PUZZLE!!!!
!!!!! CHANGE BELOW!!!!! IT BELONGS TO THE OLD PUZZLE!!!!
!!!!! CHANGE BELOW!!!!! IT BELONGS TO THE OLD PUZZLE!!!!
!!!!! CHANGE BELOW!!!!! IT BELONGS TO THE OLD PUZZLE!!!!
!!!!! CHANGE BELOW!!!!! IT BELONGS TO THE OLD PUZZLE!!!!
!!!!! CHANGE BELOW!!!!! IT BELONGS TO THE OLD PUZZLE!!!!
!!!!! CHANGE BELOW!!!!! IT BELONGS TO THE OLD PUZZLE!!!!
!!!!! CHANGE BELOW!!!!! IT BELONGS TO THE OLD PUZZLE!!!!

## Files 
---------------------------------------------------------------------------
assignment_1/ 
├── out/ (Compiled class files are going into this folder.)  
├── data/
│   └── leaderboard.txt (Serves as a database for the leaderboard.)                        
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
│   │   ├── LeaderBoard.java (This class has been placed to hold the leaderboard storing the data in leaderboard.txt. Helps keeping track of the leaderboard)
│   │   └── User.java (This class holds the user details like username and move count.)
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
1. I have implement a Leaderboard and a mock database in a txt file which keeps track of how many games played by users.
2. I have implemented the movecount feature which holds the move count of the user and displays it real time.
3. I have implemeted a UI like visual so that game screen clears after every move.
4. Input and Output are completely seperated from the program flow.
5. I did a nice job seperating the components into subdirectories app, components, core, data, io. 


## Source and Citations
https://datawookie.dev/blog/2019/04/sliding-puzzle-solvable/

I have used the "build it (by randomly moving tiles from a known solvable configuration)"approach and got this idea from this blog.



## How to compile and run
---------------------------------------------------------------------------
Your directions on how to run the code. Make sure to be as thorough as possible! Ideally should resemble the lines below

1. Navigate to the directory "assignment_1" after unzipping the files
2. Run the following instructions:

javac -d out sliding_puzzle/app/*.java sliding_puzzle/core/*.java sliding_puzzle/io/*.java sliding_puzzle/components/*.java
java -cp out sliding_puzzle.app.Main        

NOTE: You need to create an empty directory /out next to /sliding_puzzle

NOTE: Instead you can simply run ./compile.sh and then ./run.sh

If you get a permission error you can simply give permission to those scripts with chmod +x compile.sh and  chmod +x run.sh 

## Input/Output Example
---------------------------------------------------------------------------
Please give us a full execution of what we should see on the screen. Label each text with input and output. For example:

```
Output:

Welcome to the Sliding Puzzle Game!
You can swap adjacent cells to solve the puzzle. Press any key to start...

Input:
x

Output:
Enter a username >>> 

Input:
oktay

Output:

Alright oktay, let's customize your puzzle
Prompt your puzzle size.(example: 3 4 creates a 3 row 4 column puzzle for you.)
>>> 

Input:

2 2

Output:

 - -  
|2| |
 - -  
|1|3|
 - -                                      move count = 0


Which cell do you want to move?
>>> 
Input:

2

Output:

 - -  
| |2|
 - -  
|1|3|
 - -                                      move count = 1


Which cell do you want to move?
>>> 

Input:

1

Output:

 - -  
|1|2|
 - -  
| |3|
 - -                                      move count = 2


Which cell do you want to move?
>>> 

Input:

3

Output:

!!!!!               Congratulations             !!!!!

 You solved the puzzle in 3 moves!

To play a new game type y/Y, to exit press any key >>> 

Input:

y

Output:

Good to see you again oktay. Let's customize your new puzzle
Prompt your puzzle size.(example 3 4 creates a 3 row 4 column puzzle for you.)
>>> 

Input:
1 1 
Output:
Invalid input!
The dimensions of the puzzle must be at least 2 x 2.
>>> 
Input:

10 11

Output:

Invalid input!
The puzzle size must not exceed 10 rows and 10 columns.
>>> 

Input:
2 2 
Output:

 - -  
|2|3|
 - -  
| |1|
 - -                                      move count = 0


Which cell do you want to move?
>>> 

Input:

1

Output:

 - -  
|2|3|
 - -  
|1| |
 - -                                      move count = 1


Which cell do you want to move?
>>> 

Input:
3
Output:

 - -  
|2| |
 - -  
|1|3|
 - -                                      move count = 2

Input:

2

Output:


Which cell do you want to move?
>>> 



 - -  
| |2|
 - -  
|1|3|
 - -                                      move count = 3


Which cell do you want to move?
>>> 
Input:
1

Output:

 - -  
|1|2|
 - -  
| |3|
 - -                                      move count = 4


Which cell do you want to move?
>>> 


Input:

3


Output:

!!!!!               Congratulations             !!!!!

 You solved the puzzle in 5 moves!

Leaderboard:
Username |  Games Played
---------------------
jackson         5
oktay           5
sengun          3
michael         2
john            2
okt             1
alperen         1

Press enter to continue...

Input:
\n
Output:

To play a new game type y/Y, to exit press any key >>> 

Input:
q

Output:
end of program

```

