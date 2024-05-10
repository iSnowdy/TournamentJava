# Java Project - Martial Arts Tournament

## Fight Tournament

### Introduction

This is a school project with the purpose of learning about Java Object Oriented Programming. It is not by any means a guide on the subject. Its purely for learning purposes, so its bound to have many mistakes.

The project will keep growing, adding new features and fixing bugs. The final objective is to have a running program with GUI, showcasing a wide range of Java's utilities.

### Description

The project is a simulation of fights between two Fighters. The Fighter can either be a human being or the CPU. Resulting in a **PvE / PvP simulation** depending on how many people are willing to play.

At the beginning you are going to be asked if you want to fight against a fellow human or against the CPU. CPU actions are randomized within certain parameters. Once you have decided, you will be directed to a **Character Creation Menu**.

In this menu you are going to have two options. (1) Create your Character/Fighter from scrath or (2) load an already built-in Fighter. Any option is viable.

When one or both players are done with creating the Fighter, you will be redirected to the **Main Menu**. This menu will control the flow of the program. **You can start the fights, level up your Fighter, load the Instructions, load the Log or exit the program**.

Fights between fighter will involve two MiniGames. The first MiniGame is a Rock-Paper-Scissors implementation to decide who is going first. After that, both Fighter instances will take turns to play the second MiniGame, a KeyListener. Users will be given a string of characters composed of a randomized sequence of arrows *( ↑ → ↓ ← )*. If the user is able to input all of the arrows correctly and in the alloted time, he/she will be able to assert the attack to the opponent and inflict a certain amount of damage. On the other hand, if it is failed, then the damage will be reversed to him/her.

**After each Fight, the user will be awarded Statistics Points to Level Up the Fighter stats and also Ranking Points**. Rankings Points will count towards an Internal Ranking that depending on specific ranges the Fighter will be assigned a Rank. If its possible to both climb up the ladder but also fall down.

The final objective of the program is to reach the highest rank.

The stats of the Fighter and the log of wins, losses and some other details will be recorded in .json files that can be consulted in the MainMenu.

## Classes

### ScannerCreation

It is a *utility class* with the sole purpose of initializing a static Scanner to use across the Project. It also contains critical methods needed for user inputs, such as nextInt(), nextLine(), next() and closeScanner().

### GSONCreator

A *utility class* with the objective of controlling everything related to the JSON files that are implemented in the program. Amongst the things it can do are: instantiate GSON class, load, read, modify & print .json files. 

* Instantiate GSON.
* Load .json files. Either the full file or specific data.
* Read .json files.
* Modify .json files. Thanks to the parameters entered it can modify anything in the JSON file. It will also save the changes.
* Read .json files. Either the full .json or specific things about it.
* Add new Fighters to the file.

### Log

A class purely designed to register in a .json file the trails of the user. It includes: username, fightername, wins, loses, ranking points and the opponents they have fought against.

When the Fighter is created it will be registered to the Log. Also every time a fight is over it will be updated with fresh data.

### FeedBack

When the user decides to leave the program, they will be asked to leave a FeedBack. It will be registered in a separate .txt file and can be consulted any time. The user name will also be recorded.

### Randomizer

Its purpose is to return randomized Strings and integers to randomize certain actions while the fight is going on and also the decisions taken by the CPU.

### Ranking

Will manage everything related to the Ranking Points. Initializing the values, increasing/decreasing the Ranking Points (win/lose), calculating the Ranking Difference and receveing the updated values.

The Ranking Difference is needed because the user is able to fighter against higher ranked Fighters. If the Ranking Difference is <= 1, he/she will receive greater rewards. However, if the Ranking Difference is >= 2, upon suffering a defeat, all Ranking Point will be lost.

The Ranks are the following, from highest to lowest:

1. Maa.
2. Sensei.
3. Expert_Fighter.
4. Almost_Human.
5. Clown.

### StatType & Ranks

EnumType classes to restrict some entry values.

### Opponent

It will be the first Menu that the user will see. It is going to offer the option to either fight against the CPU or against another player (PvE / PvP mode).

### Fighter Creation

Menu to create or load the Fighter. First of all, the user will be asked to name their future Fighter.

If the user decides to **create a new Fighter** from scratch, it will ask the user the appoint the stats. Every stat will start at 1 and the user will be given a certain number of stat points to increase the statistics however he/she wants.

On the other hand, if the user decides to **load a fighter from the database**, an already created instance will be loaded and assigned the user's name and fighter's name previously allocated.

It will also assign and update the Fighter Type. It is an attribute that using an algorithm based on the spectrum of the statistics of the Fighter will assign the pre-defined values of types of Fighters.

### Main Menu

Main class in charge of handling the flow of the program. Here the user can choose to:

1. Fight.
2. Level Up. After each Fight, a certain number of stat points will be awarded to level up the Fighter's statistics. Here the user will be able to allocate them to strengthen their Fighter.
3. Show Stats. Shows the current stats of the Fighter.
4. Show Log. Shows the current Log of the Fighter. Specific information about the user, not the whole Log file.
5. Instructions. Prints the different array of instructions. They can be about:
      * Opponent Type.
      * Stats Explanation.
      * Tournament Format Explanation.
      * MiniGames.
6. Exit. Leaves the program. FeedBack will be asked (optional).

### Actions, StatsManager & Statistics

Actions and StatsManager are interfaces. Their purpose is solely to make sure that their methods are implemented in their child classes.

Statistics on the contrary is an abstract class. It is designed to handle everything related to the statistics. From initiating them to their increase and update. It has an abstract method that will be inherited to FighterCreation and Fighter.

Notice that stat points are gained both with victories and defeats. In fact, defeats give a higher amount of stat points.

Further information about how exactly the statistics work can be found inside the project (Instructions.txt).

### Fighter

The Fighter class is in charge of running the actual fight between the Fighters. It uses a number of methods and calls to other classes to accomplish this.

### TurnMinigame

A very simple MiniGame of Rock-Paper-Scissors with the sole purpose of adressing who goes first in the first round of the Fight.

### Cron & KeyListenerMiniGame

The **Cron class will start a cronometer based on a timer**. This timer is set depending on the difficulty level of the Fight that will be held up. The difficulty varies depending on the Ranks of both Fighters. The higher the difficulty, less time for the user to input the String.

Once the timer is over, the class, thanks to taking as a parameter the Window (created in the KeyListener MiniGame), will be able to close that Window, signalling the failure of completing the MiniGame in time.

The CPU on the other hand since it can't input the parameters, will be given an increased amount of success chance with every difficulty level. So at the lowest Rank, it will have a relatively low chance of succeeding the MiniGame. However, on the highest rank, it will be almost impossible for it to actually fail the MiniGame.

The KeyListenerMiniGame class uses a combination of the libraries JFrame and KeyListener to implement a simple User Interface and a KeyListener.

The User Interface will show the user what combination of arrows they must input. Finally, the KeyListener is in charge of taking in, recording and printing the actual keys pressed by the user.

It is also responsible for initializing the cronometer and deciding wether the user input is right or wrong.
 
## List of things learnt throughout the project:

* Linking Multiple Menus.
* Usage of Scanner as a Utility Class.
* Usage of GSON Library to load, read, modify and save into .json files.
* Usage of Files (.txt) to load, read, modify and save changes into .txt files.
* Implementation of utility classes.
* Static property. Usage across the project.
* KeyListener. "Hearing" inputs from the User.
* Cron. A way to set-up a cronometer in Java. Highly customizable.
* ENUM.
* ThreadSleep. Usage to stop/restart the flow of the program.
* Navigating through the flow of a program.
* Java Oriented Programming. Which was the main objective of the project.
* The process of starting with nothing but a: "Do a project". Phases of thinking on what to do, how to do it, how to implement all those things and so on. Transforming just an idea to documentation, diagrams, code and a functioning program.

## Some of the reference sources/websites visited to help with the Code:

* [Stat Balance](https://upcommons.upc.edu/bitstream/handle/2117/393564/311_Memoria_TFG.pdf?sequence=2&isAllowed=y)
* [KeyListener](https://www.geeksforgeeks.org/java-keylistener-in-awt/)
* [Cron](https://www.delftstack.com/es/howto/java/countdown-timer-java/)
* [JFrame - Interface](https://www.jairogarciarincon.com/clase/interfaces-de-usuario-con-java-swing/componentes-jtextfield-y-jtextarea)
* [JFrame - Focusing Window](https://docs.oracle.com/javase/tutorial/uiswing/misc/focus.html)
* [Static Concept](https://codegym.cc/es/groups/posts/es.141.10-cosas-que-debes-saber-sobre-el-modificador-estatico-en-java)
* [Similar Example of a Fight with Characters and Levels](https://aprenderaprogramar.com/foros/index.php?topic=7893.0)
* [Polymorphism](https://www3.uji.es/~belfern/Docencia/Presentaciones/ProgramacionAvanzada/Tema1/conceptosPolimorfismoSobrecarga.html#8)
* [Multiple Line Scanner](https://stackoverflow.com/questions/46769218/java-multiline-scanner-input)
* [GSON Library 1](https://howtodoinjava.com/gson/gson/)
* [GSON Library 2](https://jarroba.com/gson-json-java-ejemplos/)
* [GSON Library 3](https://www.tutorialspoint.com/gson/gson_quick_guide.htm)
