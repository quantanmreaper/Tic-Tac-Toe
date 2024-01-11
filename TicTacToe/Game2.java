import java.util.Scanner;

public class Game2 {
    String player1;
    String player2;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Created a new instance of the Game class in that way the variables are then
        // accessed and modified through this instance and helps to keep the variables
        // non-static
        // cause otherwise if i declared the variables in the main method i would need
        // to make the variables static when declaring the instance variables in the
        // class Game
        Game2 tictac = new Game2();
        // Get The Name of the two players
        System.out.println("Enter Name Of Player 1 : ");
        tictac.player1 = input.nextLine();

        System.out.println("Enter Name of Player 2 : ");
        tictac.player2 = input.nextLine();

        System.out.println("Player 1 please choose between the symbol x and o");

        // Now we are going to create the xo/Tic tac toe board using arrays
        // - represents empty space in the array
        // x-player1
        // -o player2

        char[][] gameboard = new char[3][3];

        // We will fill the board with dashes

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameboard[i][j] = '-';
            }
        }

        // This keeps track of whose turn it is
        boolean isPlayer1 = true;
          
        //keeping track of whether the game has ended or not
        boolean endOfGame=false;
       // boolean endOfGame = true;
        while(!endOfGame){
            // Draw the board
            drawBoard(gameboard);


            // Keeping track of the symbol being used to play
            char symbol = ' ';
            if (isPlayer1) {
                symbol = 'x';
            } else {
                symbol = 'o';
            }

            // Printing out message diaplaying which player's turn it is to play
            if (isPlayer1) {
                System.out.println(tictac.player1 + "'s  turn to play");
            } else {
                System.out.println(tictac.player2 + "'s  turn to play ");
            }
            int row = 0;
            int col = 0;
            // Initializing the row and col variables outside the while loop so they can be
            // reused without the need to declare them again
            while (true) {
                // Getting the row and column from the user to know where he/she is going to put
                // the x and o
                System.out.print("Enter the row number from either 0 or 1 or 2 : ");
                row = input.nextInt();

                // The col represents the column number
                System.out.print("Enter the column number from either 0 or 1 or 2 : ");
                col = input.nextInt();

                // check and validate the users input for row and column number
                if (row < 0 || col < 0 || row > 2 || col > 2) {

                    System.out.println(
                            "Your row and column number are wrong! Please enter an appropriate row and column number. You can only input from the numbers 0 or 1 or 2");

                }
                // This board position already has either an x or o
                else if (gameboard[row][col] != '-') {
                    System.out.println("That spot is already occupied, please enter another one.");
                } else {
                    // The selected row and column are valid! so proceed on
                    break;
                }
            }
            // Set the appropriate symbol in the relevant place on the board
            gameboard[row][col] = symbol;
            //drawBoard(gameboard);
            
            //checking which player has won and displaying the winners name
            if(hasWon(gameboard) == 'x'){
                System.out.println(tictac.player1 + "has won the game hurray!!!");
                endOfGame = true;
            }
            else if(hasWon(gameboard) == 'o'){
                System.out.println(tictac.player2 + "has won the game !!!");
                endOfGame = true;
            }
            else {
                //tie breaker where nobody has won the game
                //System.out.println("It's a draw!");
                if((hasTied(gameboard))){
                    System.out.println("It is a tie breaker no one has won");
                    endOfGame = true;
                }
                else {
                // continuing the game and switches between the players turn
                isPlayer1 = !isPlayer1;
                }
            }
        }
          //print out the final board
          drawBoard(gameboard);
    }

    // Print out the actual gameboard
    public static void drawBoard(char[][] gameboard) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(gameboard[i][j]);
            }
            System.out.println();
        }

    }

    // This method will check each row, each column and both diagonals of the
    // gameboard
    public static char hasWon(char[][] gameboard) {

        // checking for the row winners
        for (int i = 0; i < 3; i++) {
            if (gameboard[i][0] == gameboard[i][1] && gameboard[i][1] == gameboard[i][2] && gameboard[i][0] != '-') {

                return gameboard[i][0];
        
                
            }
        }

        // checking for the column winners
        for (int j = 0; j < 3; j++) {
            if (gameboard[0][j] == gameboard[1][j] && gameboard[1][j] == gameboard[2][j] && gameboard[0][j] != '-') {
                return gameboard[0][j];
            }
        }
        // Checking for diagonal winners
        if(gameboard[0][0] == gameboard[1][1] && gameboard[1][1] == gameboard[2][2] && gameboard[0][0] != '-'){
            return gameboard[0][0];
        }
        if(gameboard[2][0] == gameboard[1][1] && gameboard[1][1] == gameboard[0][2] && gameboard[2][0] != '-'){
            return gameboard[2][0];
        }
        //If none of the two players have won and then obviously its a tie so tie breaker
        return '-';
    }

    //Now checking for whether there is a tie and the board is full or not
    public static boolean hasTied(char[][] gameboard){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(gameboard[i][j] == '-'){
                    return false;
                }
            }
        }
        return true;
    }
}

//This is because of how arrays are passed in Java. Arrays in Java are reference types. This means that when you pass an array to a method, you are actually passing a reference to the original array, not a copy of the array.