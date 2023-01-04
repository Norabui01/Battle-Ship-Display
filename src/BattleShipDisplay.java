//Author: Ngoc Bui.
//Date: 11/09/2022.

import java. util.*;
import javax.swing.*;

public class BattleShipDisplay {
    public static void main(String[] args) {
        String intro_prompt = "<html><h2>Welcome to Battleships Peg Board!</h2>" +
                              "This program will let you place your battleships randomly<br>" +
                              "on a square peg board.<br>" +
                              "You will have the right to choose the size of the peg board,<br>" +
                              "maximum length of ship, and the number of ships you want to have.<br>" +
                              "For example, you can have 4 ships with maximum length of 6 on<br> " +
                              "a square 10 * 10 peg board.<br>" +
                              "However, you will have some stuff needed to be considered while<br> " +
                              "choosing size, length,...<br>" +
                              "Don't worry, the instruction will keep showing up later for you.<br>" +
                              "You can have multiple times to have new board until you want to quit.<br>" +
                              "Ready? Have a fun time placing your own ships!";
        ImageIcon icon = new ImageIcon("my_battleship.png");
        JOptionPane.showMessageDialog(null, intro_prompt,
                "          ".repeat(10) + "Introduction", 1, icon);

        String choosing = "<html><h3>Which size of pegBoard do you like to have?</h3>" +
                          "Please enter a number, for example: 10 means size 10 * 10,<br>" +
                          "enter 5 means size 5 * 5, or 20 means size 20 * 20.<br>" +
                          "(Your size shouldn't be too small(avoid 1, 2, 3) or too large, better be<br>" +
                          "between 5 and 20. Usually, people choose size 10 for a beautiful peg board!)<br>";
        String user_size_pegBoard = JOptionPane.showInputDialog(null, choosing,
                "       ".repeat(8) + "Choose Board's size", 1);
        int size_pegBoard = Integer.parseInt(user_size_pegBoard);

        choosing = "<html><h3>What maximum length of a ship do you want?</h3>" +
                   "Please enter a number, for example: 6 means you will have at least one ship<br>" +
                   "with length 6 and can have other smaller ships.<br>" +
                   "(Be careful, you cannot have the maximum length of a ship greater than the<br>" +
                   "size of pegBoard you chose or less than 2)";
        String user_max_length_ship = JOptionPane.showInputDialog(null, choosing,
                "        ".repeat(7) + "Choose max length of Ship", 1);
        int max_length_ship = Integer.parseInt(user_max_length_ship);

        choosing = "<html><h3>How many ships do you like to have?</h3>" +
                   "Please enter a number less than 8, for example: 5 means you will<br>" +
                   "have 5 different ships (represented by 5 different symbols) display.<br>"+
                   "(Be careful, you cannot have more than 8 ships.<br>" +
                   "Also, to avoid your pegBoard is full, you should just have the number of<br>" +
                   "ships less than the board's size. It means if you chose a 5 * 5 board,<br>" +
                   "you should just have maximum 3 ships)";
        String user_num_ships = JOptionPane.showInputDialog(null, choosing,
                "       ".repeat(7) + "Choose numbers of Ship", 1);
        int num_ships = Integer.parseInt(user_num_ships);

        Random ranGen = new Random();

        int restrict_length_ships = size_pegBoard / 2;//to avoid the board is full,
        // not only I told the user what should they enter numbers of ship, but also I restrict
        // the length of the smaller ships.
        String[] user_options = {"Quit", "New Board"};

        while (true) {
            String[][] pegBoard = new String[size_pegBoard][size_pegBoard];

            for (int row = 0; row < pegBoard.length; row++) {
                for (int col = 0; col < pegBoard[0].length; col++) {
                    pegBoard[row][col] = "- ";
                }
            }

            shipLocation(pegBoard, size_pegBoard, max_length_ship, "x");//user will have at least
            // one ship with maximum length they chose.

            String[] ship_symbols = {"v", "e", "u", "n", "h", "g", "p"};

            int length_ship;

            //this is for displaying other smaller ships.
            for (int num = 0; num < num_ships - 1; num++) {
                if (max_length_ship > restrict_length_ships) {
                    length_ship = ranGen.nextInt(restrict_length_ships - 1) + 2;// this is when restrict length.
                } else {
                    length_ship = ranGen.nextInt(max_length_ship - 1) + 2;
                }
                shipLocation(pegBoard, size_pegBoard, length_ship, ship_symbols[num]);
            }

            icon = new ImageIcon("my_ships.png");
            int user_option = JOptionPane.showOptionDialog(null,
                    "Your Board:\n" + displayBattle(pegBoard),"         ".repeat(4) + "Battleships",
                    1, 1, icon, user_options, user_options[0]);

            if (user_options[user_option].equals("Quit")){
                break;//break back door.
            }
        }

        String terminate_prompt = "<html><h3>Hi Dr. Anderson, the program is end. Hope you like it!</h3>" +
                                  "P/s: This time, we are sure that it is definitely worth full 130 points.<br>" +
                                  "Please click OK to out!";
        icon = new ImageIcon("my_battleship.png");
        JOptionPane.showMessageDialog(null, terminate_prompt,
                "          ". repeat(10) + "Goodbye", 1, icon);
    }

    public static void shipLocation(String[][] pegBoard, int size_pegBoard, int length_ship, String symbol) {
        Random ranGen = new Random();

        int ship_row_position;
        int ship_col_position;

        String[] directions = {"horizontal", "vertical"};
        int ver_or_hor = ranGen.nextInt(2);

        if (directions[ver_or_hor].equals("horizontal")) {
            ship_row_position = ranGen.nextInt(size_pegBoard);
            ship_col_position = ranGen.nextInt(size_pegBoard - length_ship + 1);

            while (!canPlaceShip(pegBoard, length_ship, ship_row_position, ship_col_position, "horizontal")) {
                ship_row_position = ranGen.nextInt(size_pegBoard);
                ship_col_position = ranGen.nextInt(size_pegBoard - length_ship + 1);
            }

            for (int num = 0; num < length_ship; num++){
                pegBoard[ship_row_position][ship_col_position + num] = symbol;
            }

        }else{
            ship_row_position = ranGen.nextInt(size_pegBoard - length_ship + 1);
            ship_col_position = ranGen.nextInt(size_pegBoard);

            while (!canPlaceShip(pegBoard, length_ship, ship_row_position, ship_col_position, "vertical")) {
                ship_row_position = ranGen.nextInt(size_pegBoard - length_ship + 1);
                ship_col_position = ranGen.nextInt(size_pegBoard);
            }

            for(int num = 0; num < length_ship; num++){
                pegBoard[ship_row_position + num][ship_col_position] = symbol;
            }
        }
    }

    public static boolean canPlaceShip(String[][] pegBoard, int length_ship,
                                       int ship_row_position, int ship_col_position, String direction){
        boolean freeSpace = true;

        if (direction.equals("horizontal")){
            for (int num = 0; num < length_ship; num++){
                if (!pegBoard[ship_row_position][ship_col_position + num].equals("- ")){
                    freeSpace = false;
                    break;
                }
            }
        }else {
            for (int num = 0; num < length_ship; num++){
                if (!pegBoard[ship_row_position + num][ship_col_position].equals("- ")){
                    freeSpace = false;
                    break;
                }
            }
        }
        return freeSpace;
    }

    public static String displayBattle(String[][] pegBoard) {
        String pegBoardDisplay = "";
        for (int row = 0; row < pegBoard.length; row++) {
            for (int col = 0; col < pegBoard[0].length; col++) {
                pegBoardDisplay += pegBoard[row][col] + "    ";
            }
            pegBoardDisplay += "\n";
        }
        return pegBoardDisplay;
    }
}





