package com.company;

import java.util.Scanner;
import java.util.Random;

public class RPSGame {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String[] rps = {"r", "p", "s"};
            String ComputerTurn = rps[new Random().nextInt(rps.length)];

            String PlayerTurn;

            while(true) {
                System.out.println("Player's turn! Enter your selection (r for rock, p for paper, or s for scissors):");
                PlayerTurn = scanner.nextLine();
                if (PlayerTurn.equals("r") || PlayerTurn.equals("p") || PlayerTurn.equals("s")) {
                    break;
                }
                System.out.println(PlayerTurn + " is not a valid move; bummer!");
            }

            System.out.println("Computer player's move: " + ComputerTurn);

            if (PlayerTurn.equals(ComputerTurn)) {
                System.out.println("Tie!");
            }
            else if (PlayerTurn.equals("r")) {
                if (ComputerTurn.equals("p")) {
                    System.out.println("Player loses!");

                } else if (ComputerTurn.equals("s")) {
                    System.out.println("Player wins!");
                }
            }

            else if (PlayerTurn.equals("p")) {
                if (ComputerTurn.equals("r")) {
                    System.out.println("Player wins!");
                }
                else if (ComputerTurn.equals("s")) {
                    System.out.println("Player loses!");
                }
            }

            else if (PlayerTurn.equals("s")) {
                if (ComputerTurn.equals("p")) {
                    System.out.println("Player wins!");
                }
                else if (ComputerTurn.equals("r")) {
                    System.out.println("Player loses!");
                }
            }

            System.out.println("Replay? (yes/no)");
            String replay = scanner.nextLine();

            if(replay.equals("no")) {
                System.out.println("Game Over");
                break;
            }
        }
    }
}

