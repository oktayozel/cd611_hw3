# CS611-Assignment 2
## Sliding Puzzle & Dots and Boxes
---------------------------------------------------------------------------
- Name: Oktay Ozel
- Email: oozel@bu.edu
- Student ID: U93822204


- Name: Liang Yu Lin
- Email: lin0326@bu.edu
- Student ID: U55834159



## Files 
---------------------------------------------------------------------------
assignment_2/  
├── out/                                        (Compiled class files are going into this folder.)   
│   └── puzzles/                                (Main game module)  
│       ├── app/                                (Entry point of the program)  
│       │   └── Main.java                       (Initializes the game and starts the program)
│       ├── core/                               (This is the directory where core game classes are being hold. Classes that ensures extendibility sit here.)  
│       │   ├── Board.java                      (This is an interface defining the must have functions of a board.)
│       │   ├── Cell.java                       (This is an abstract class which has the basic methods and fields that a board game would have.)
│       │   ├── GameManager.java                (This is an abstract class defining the basic methods of a game manager like initialize the game, run the game.x)
│       │   ├── GameSelectionManager.java  
│       │   ├── LeaderBoard.java                (This class has been placed to hold the leaderboard storing the data in leaderboard.txt. Helps keeping track of the leaderboard)
│       │   ├── Settings.java  
│       │   └── User.java                       (This class holds the user details like username and move count.)
│       ├── games/                               
│       │   ├── dots_and_boxes_components/          (dots_and_boxes module)
│       │   │   ├── DotsAndBoxesBoard.java          (Represents the game board; manages cells, edge claiming, and scoring logic)
│       │   │   ├── DotsAndBoxesCell.java           (Represents a single cell (box) on the board also tracks edge status and ownership)
│       │   │   ├── DotsAndBoxesGameManager.java    (Orchestrates the game flow; handles turns, win conditions, and board updates)
│       │   │   └── DotsAndBoxesUser.java           (Stores player information such as username, score, and identity (P1, P2))
│       │   └── sliding_puzzle_components/          (sliding_puzzle module)
│       │       ├── SlidingPuzzleBoard.java         (This class is responsible for holding the board and board operations.)
│       │       ├── SlidingPuzzleCell.java          (This class is reponsible for operations in the Cell level and denotes each cell in a board.)
│       │       ├── SlidingPuzzleGameManager.java   (Manages all the game, basically holds one instance from other classes and orchestrates them.)
│       │       └── SlidingPuzzleUser.java  
│       ├── io/                                 
│       │   ├── Input.java                          (This class handles all input operations in the game.)
│       └── └── Output.java                         (This class handles all output operations in the game. )  
├── resources/                     
│   └── config.properties        (The settings of the game)  
├── data/                         
│   └── leaderboard.txt          (Record players' leardeboard)  
├── compile.sh                   (Compilation script for the game.)  
├── run.sh                       (Running script for the game.)  
├── README.md                      



## Notes
---------------------------------------------------------------------------
Please explain the cool features of your program. Anything that you feel like you did a good job at or were creative about, explain it in bullets here. Additionally, any design decisions should be made here.

Overall:
- Implement a Leaderboard and a mock database in a txt file which keeps track of how many games played by users.
- Seperating the components into subdirectories app, components, core, data, io. 

Sliding Puzzle:
- Tracks and displays move count in real time.
- Clears screen after each move for a clean UI experience.
- Input and Output are modular and separated from game logic.
- Organized into app, components, core, data, and io directories.

Dots and Boxes:
- Each player’s moves are shown in distinct colors (red vs blue).
- Supports dynamic board sizes and edge claiming logic.
- Displays real-time ownership of boxes and scores.
- Includes animated welcome screen and stylized victory messages.



## Source and Citations
https://datawookie.dev/blog/2019/04/sliding-puzzle-solvable/

I have used the "build it (by randomly moving tiles from a known solvable configuration)"approach and got this idea from this blog.



## How to compile and run
---------------------------------------------------------------------------
Your directions on how to run the code. Make sure to be as thorough as possible! Ideally should resemble the lines below

1. Navigate to the directory "assignment_2" after unzipping the files
2. Run the following instructions:

javac -d out puzzles/app/*.java puzzles/core/*.java puzzles/io/*.java puzzles/games/sliding_puzzle_components/*.java \
java -cp out puzzles.app.Main

NOTE: Instead you can simply run ./compile.sh and then ./run.sh

If you get a permission error you can simply give permission to those scripts with chmod +x compile.sh and  chmod +x run.sh 



## Input/Output Example
---------------------------------------------------------------------------
Please give us a full execution of what we should see on the screen. Label each text with input and output. For example:

```
Output:
              ,---------------------------,
              |  /---------------------\  |
              | |                       | |
              | |      Welcome to       | |
              | |        CS-611         | |
              | |        Games          | |
              | |                       | |
              |  \_____________________/  |
              |___________________________|
            ,---\_____     []     _______/------,
          /         /______________\           /|
        /___________________________________ /  | ___
        |                                   |   |    )
        |  _ _ _                 [-------]  |   |   (
        |  o o o                 [-------]  |  /    _)_
        |__________________________________ |/     /  /
    /-------------------------------------/|      ( )/
  /-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /
/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Available games:
[1] SlidingPuzzle
[2] DotsAndBoxes
Which game would you like to play?
You can simply type exit to finish the game  >>> 

Input:
1

Output:
###########################################################################################################################################
#                                                                                                                                         #
#                                                                                                                                         #
#     ######    #        #####      ####    #####  #    #    ######         ######    #     #   #######   #######   #         #######     #
#     #         #          #        #    #    #    ##   #   #               #     #   #     #        #          #   #         #           #
#     #         #          #        #    #    #    # #  #   #               #     #   #     #       #          #    #         #           #
#     #####     #          #        #    #    #    #  # #   #   ###         ######    #     #      #          #     #         #####       #
#          #    #          #        #    #    #    #   ##   #     #         #         #     #     #          #      #         #           #
#          #    #          #        #    #    #    #    #   #     #         #         #     #    #          #       #         #           #
#     ######    #######   ###       ####     ###   #    #    #####          #          #####    #######    #######  ######    #######     #
#                                                                                                                                         #
#                                                                                                                                         #
###########################################################################################################################################

Welcome to the Sliding Puzzle Game!
Swap adjacent cells to order them.
 Press enter to start... 

Input:
(Press "Enter")

Output:
Enter a username >>> 

Input:
Jason

Output:
Hey Jason Enter number of rows: 

Input:
2

Output:
Hey Jason Enter number of columns:

Input:
2

Output:
 - -
| |3|
 - -
|2|1|
 - -                                      move count = 0


Which cell do you want to move?(example : 2)
Enter cell to move (1 to 3) >>>

Input:
2

Output:
 - -
|2|3|
 - -
| |1|
 - -                                      move count = 1


Which cell do you want to move?(example : 2)
Enter cell to move (1 to 3) >>>


Input:
1

Output:

 - -
|2|3|
 - -
|1| |
 - -                                      move count = 2


Which cell do you want to move?(example : 2)
Enter cell to move (1 to 3) >>>

Input:
2

Output:
Invalid input. Please enter a valid number.
Enter cell to move (1 to 3) >>>

...


Input:
3

Output:

!!!!!     Congratulations     !!!!!

You solved the puzzle in 6 moves!

Leaderboard:
Username   Total   D&B   SlidingP   D&B_Win   D&B_Loss
--------------------------------------------------------------
aaaaa           3     2       0     0      1
bbbbb           3     2       0     1      0
oktay           3     1       1     1      1
2               1     0       1     0      0
gsd             1     0       1     0      0
hakan           1     0       1     0      0
jaon            1     0       1     0      0
Jason           1     0       1     0      0
sdf             1     0       0     0      0
tarik           1     0       1     0      0
lin             1     0       1     0      0
Press enter to continue...

Input:
(Press "Enter")

Output:
To play a new game type y/Y, to exit press any key >>> 
To go back to main menu type m/M to
 any other input will end the game.

Input:
M

Output:
Available games:
[1] SlidingPuzzle
[2] DotsAndBoxes
Which game would you like to play?
You can simply type exit to finish the game  >>>

Input:
2

Output:
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
X                                                                                                       X
X   XXXXX                                             X     XXXXXXX                                     X
X   XX   X                                            X     XX    XX                                    X
X   XX    X          X                                X     XX    XX                                    X
X   XX     X  XXX  XXXXX XXXXX      XXXX   X XXX    XXX     XXXXXXXXX    XXX   X    X   XXXXX   XXXXX   X
X   XX     X X   X   X   X         X   x   XX  XX XX  X     XXX     XX  X   X   X  X   X     X  X       X
X   XX     X X   X   X   XXXXX     X   X   X    X X   X     XX      XX  X   X    XX    XXXXXXX  XXXXX   X
X   XX    X  X   X   X       X     X   XX  X    X X   X     XX     XX   X   X   X  X   X            X   X
X   XXXXXX    XXX    X   XXXXX      XXX XX X    X  XXXX     XXXXXXXX     XXX   X    X   XXXXX   XXXXX   X
X                                                                                                       X
X                                                                                                       X
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

Welcome to the Dots and Boxes Game!
Be the one who puts the last piece to conquer as much boxes as you can. Enjoy!
You are going to see the row and column numbers for each dot.
To put an edge you need to pick the dot on the left of the edge, if the edge is horizontal.
To put an edge you need to pick the dot on the top of the edge, if the edge is vertical.
 Press enter to start...

Input:
(Press "Enter")

Output:
To put an edge you need to pick the dot on the top of the edge, if the edge is vertical.
 Press enter to start...
Enter Player 1 name: 

Input:
oktay

Output:
Enter Player 2 name:

Input:
lin

Output:
Enter number of rows:

Input:
2

Output:
Enter number of columns:

Input:
abc

Output:
Invalid input. Please enter a valid number.
Enter number of columns:

Input:
15

Output:
Invalid input!
The input should be between 2 and 10.
Enter number of columns:

Input:
2

Output:
    0  1  2
0   ○  ○  ○

1   ○  ○  ○

2   ○  ○  ○
Player1 oktay's turn(Blue Line). Score: 0
Enter row:

Input:
0

Output:
Enter column:

Input:
0

Output:
Enter direction (H for horizontal right line, V for vertical down line):

Input:
H

Output:
    0  1  2
0   ○──○  ○

1   ○  ○  ○

2   ○  ○  ○
Player2 lin's turn(Red Line). Score: 0
Enter row:

Input:
1

Output:
Enter column:

Input:
5

Output:
The input should be between 0 and 2.
Enter column:

Input:
1

Output:
Enter direction (H for horizontal right line, V for vertical down line): 

Input:
S

Output:
Invalid direction. Please enter H or V.
Enter direction (H for horizontal right line, V for vertical down line):

Input:
V

Output:
    0  1  2
0   ○──○  ○

1   ○  ○  ○
       │
2   ○  ○  ○
Player1 oktay's turn(Blue Line). Score: 0
Enter row:

...

Output:
    0  1  2
0   ○──○──○
    │  │
1   ○  ○  ○

2   ○  ○  ○
Player1 cc's turn(Blue Line). Score: 0
Enter row:

Input:
1

Output:
Enter column: 

Input:
0

Output:
Enter direction (H for horizontal right line, V for vertical down line):

Input:
H

Output:
    0  1  2
0   ○──○──○
    │P1│
1   ○──○  ○

2   ○  ○  ○
Player1 cc's turn(Blue Line). Score: 1
Enter row:

Input:
exit

Output:
Exiting game. Goodbye!

end of program

```

