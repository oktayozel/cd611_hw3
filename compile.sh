
echo "Removing all old compile files. except for the init.txt"

find out/ -type f ! -name "init.txt" -delete 
find out/ -type d -empty -delete 

echo "Compilation started."

javac -encoding UTF-8 -d out puzzles/app/*.java puzzles/core/*.java puzzles/io/*.java puzzles/games/sliding_puzzle_components/*.java \
puzzles/games/dots_and_boxes_components/*.java puzzles/games/quoridor_components/*.java && cp puzzles/resources/config.properties out/

echo "Compilation finished."