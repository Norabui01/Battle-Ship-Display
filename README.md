# Battle-Ship-Display program.

<html><h3>This program is one of my Java assignments. It displays the Peg board for Battleship Game.</h3>

My professor required us to implement a program that will initialize a battle ship game board with a single 5 unit long ship, 
positioned randomly but legally within the confines of the game board as you can see my program below.


<img src="https://user-images.githubusercontent.com/101363199/211139208-89b91998-b9cf-4af3-a50b-4c2669c6a042.png" width="352" height="280">   <img src="https://user-images.githubusercontent.com/101363199/211139245-b8c638cf-ee71-4ade-8cfe-6e6213a44619.png" width="350" height="280">
<img src="https://user-images.githubusercontent.com/101363199/211139287-fbbc6a40-a866-43cf-88bc-0f537fae9ab5.png" width="352" height="280">   <img src="https://user-images.githubusercontent.com/101363199/211139952-1eaebb59-26d2-497c-9ad8-c389c6883117.png" width="352" height="280">

(The ship has to be randomly located horizontal or vertical in all 4 quadrants of the square board)

<h3>However, she also gives extra credits for who can display multiple ships without overlaps and also allow user 
to choose the board's size, ship's size and the number of ships. I was excited to do that extra credits part <^_^></h3>

My program has a simple solution to advoid overlaps that i wrote a method to check the location that can place a ship.


<img src="https://user-images.githubusercontent.com/101363199/211140567-07413947-390f-4073-89c9-eb3b6c5433b4.png" width="352" height="280">


I also allow user to:
+ Pick the size of the square board (they can pick any, 4\*4, 5\*5, 10\*10 or even 30\*30 but better in between 4\*4 and 20\*20).
+ Pick the maximum length of a ship they want to have (so they can have at least one ship with that max length and other ships
can be equal or smaller, also maximum length of a ship cannot exceed the board size).
+ Pick how many ships they like to have (they can pick up to maximum 8 ships, the number of ships should smaller than the board size)

Let's say that user will always have at least one ship with the max length they want, and can have other ships 
with vary length(can be equal to that max length or smaller). I will always represent the max length ship is x-symbol cause it is fixed.
And I will say max length ship(x-symbol) and "other ships"(can be equal or smaller) from now.

So the problem appears that if the board size is small, the board can not have enough space for the certain amount of ships that user wants to have.
So not only I told user though introduction pop-up window in program which size they should get but also i restrict the length of "other ships"
compare to board size by divide the board size to two (restrict length of other ships = board size/2). There are two cases:

<h4>Case 1: Board size / 2 < maximum length of ship -> restrict length of other ships is impletemented.</h4>

For example: User chooses a board with size 5\*5, and the max length ship they is 5 unit, and user wants 3 ships. 
             So user will have just only one ship with length of 5, and 2 ships with length of 2.(Picture below)


<img src="https://user-images.githubusercontent.com/101363199/211141469-5de6f132-b57c-4bdd-96c8-399d1367110f.png" width="352" height="280">

(The ship with max length is always with x-symbol, and two other ships's length cannot exceed the "board size / 2")


<h4>Case 2: Board size / 2 >= maximum length of ship -> no need to impletement restrct length of other ships</h4>

For example: User chooses a board with size 10\*10, and the max length ship is 5 unit, and user wants 6 ships.
             So user will have one or more ships with length of 5, or other smaller ones. 
             No restrict about other ships's length needed cause the board size is big enough.


<img src="https://user-images.githubusercontent.com/101363199/211142018-dd081b39-466f-4b68-b525-a58989fb7b47.png" width="350" height="280">
(That big board can have 3 ships with max length of 5 units, x-symbol, n-symbol and v-symbol)





     








