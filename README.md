# README - COSI 12b Programming Assignment 2

The code provided in this repository contains the solutions to PA2 for COSI 12b - Advanced Programming Techniques in Java. The 2 different problems will be described below, as well as the instructions for how to install and run the code. Each problem is solved within its own Java file/class. 

Note: This assignment was done for a class, and we were limited to using only Java features we had covered in class thus far. This may make the assignment solutions more complicated/longer than they would have to be. 


## Installation and Execution 

Get the files from GitHub and in your terminal/console move into the src folder of the project. To compile all of the java files for the entire assignment, run the following line: 

``` javac *.java ```

After that, to run the solutions to each of the indidual problems included in the assignment, run ``` java Problem# ```. In this case, the # will indicate the solution to which problem number of the assignment you are running. For exeample, to run the solution for Problem 1 of the assignment, you would run the following line: 

``` java Problem1 ```

If you wanted to run the solution for Problem 2, you would run the following line instead: 

``` java Problem2 ```

## Problem Descriptions 

### Problem 1

The program allows the user to play a simple guessing game where they think up an integer and the computer guesses until it gets it correct. Each incorrect guess requires the user to type in whether the correct answer is ``` higher ``` or ``` lower ```, and if the computer has guessed correctly, the user will type in ``` correct ```. For the range of possible values the user can select, I have chosen a range with a minimum of 1 and a maximum of 100. 

Once the computer has correctly guessed the user's chosen integer, it will ask the user if they would like to play again. If the user's response begins with a "Y" (case is ignored), then a new game will begin with the user's new desired value. If the user's response begins with an "N" (case is ignore), then the program will end/exit and print out overall game stats to the user. 

The game stats reported at the end of one continuous session includes the total number of games played, the total number of guesses made (all games included), the average number of guesses per game, and the maximum number of guesses used in any single game. 

Note: This program assumes that the user always types ``` higher ```, ``` lower ```, or ``` correct ``` in response to each and every one of the program's guesses. It also assumes the user provides a one-word answer beginning with "Y" or "N" (case ignored, so both lowercase and uppercase versions of the letter are accepted) when asked to play again. This program does not handle cases where these assumptions are not met. Furthermore, it is assumed that the user is always truthful in their responses. 

### Problem 2

This program is a reverse Hangman game - the user thinks of a word and the computer tries to guess the letters in that word. The user tells the computer how many letters the word contains, and will also tell the computer what their chosen word is at the beginning of the game. 

The solution to this problem uses pseudorandom guesses on each turn. For each correct guess, the letter is filled in to show the partially completed word that the user has entered. The program doesn't just try all letters of the alphabet in order, and it does not guess the same letter more than once. 

Note: Once the game is over (either the computer has guessed correctly and won the game, or the computer was unable to fully correctly guess the input word before the hangman image was completed and the user has won the game), the program will automatically exit. To play again, the user should rerun the program with their desired new input. 
