# CS665 Summer 2018 Assignment 3

This is assignment 2 for CS665 Summer 2018.  The following readme can be used to guide the user 
to install and run the application using Java.  

-Nick Cuneo


# Program Assumptions

Battleship is a well known game, with well defined rules.  Given that, some assumptions were made.
The game is meant to convey design patterns and not be completely accurate.  As such, it only has
a small board, 5x5 compares to the 10x10 board.  The assumption is the game cannot accept boards 
larger than 10x10.  Players are allowed to pick the same location they've already guessed.  Players
don't need to see their own board state since they are just guessing and racing the other player and 
the ships are placed randomly.

# Implementation Description
There are a few design patterns at work in this assignment.  We use the facade pattern to convey a 
game engine, and separate concerns between the game components.  We provide simple public methods to
convey game rules, such as shoot().  Shoot will fire at a location by asking the opponent if they have
a ship in that grid, and mark the appropriate grids for the players pending the result.  It will also
advance turn order for the game to progress to the next player.

We also use the singleton pattern to ensure we only have 1 instance of the GameManager.  This is to be
sure we only have one game running at a time and state doesn't get mixed up.

The factory pattern is used to generate grids for the players via GridBuilder.  It allows for grids
to be created with a default state or pre-loaded with ships placed in random order.

# Setup
 
You need to install Apache Maven (https://maven.apache.org/)  on your system.

# Compilation 

Compilation and packaging is done with maven, and is easily ran from windows or linux with one of
the following scripts.

## Windows
```bash
compile.bat
```

## Linux

```bash
./compile.sh
```


# Running

The application can be ran by executing one of the following scripts on windows or linux.

## Windows

```bash
run.bat
```

## Linux

```bash
./run.sh 
```

# Using Findbugs 

To see bug detail using the Findbugs GUI, use the following command "mvn findbugs:gui"

Or you can create a XML report by using  


```bash
mvn findbugs:gui 
```

or 


```bash
mvn findbugs:findbugs
```

# Run Checkstyle 

To analyse this example using CheckStyle run 

```bash
mvn checkstyle:check
```


CheckStyle code styling configuration files are in config/ directory. Maven checkstyle plugin is set to use google code style. You can change it to other styles like sun checkstyle. 

# Unit Tests

A sampling of unit tests are included to show functionality and to illustrate the design patterns
ability to make testing easier.  The tests themselves are by no means complete nor offer full coverage
of the assignment as it was not requested.




