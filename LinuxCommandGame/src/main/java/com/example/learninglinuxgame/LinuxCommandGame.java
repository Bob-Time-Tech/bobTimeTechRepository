package com.example.learninglinuxgame;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LinuxCommandGame {

  private Map<String, String> commandDescriptions;

  public LinuxCommandGame() {
    initializeCommandDescriptions();
  }

  public static void main(String[] args) {
    LinuxCommandGame game = new LinuxCommandGame();
    game.startGame();
  }

  public void startGame() {
    Scanner scanner = new Scanner(System.in);

    boolean playAgain = true;

    while (playAgain) {
      if (commandDescriptions.isEmpty()) {
        System.out.println("No command descriptions available. Exiting.");
        return;
      }

      int correctGuesses = 0;
      int totalAttempts = 5;

      for (int attempt = 1; attempt <= totalAttempts; attempt++) {
        System.out.println("\nAttempt " + attempt + ":");

        String[] commands = commandDescriptions.keySet().toArray(new String[0]);

        // Check if there are commands available
        if (commands.length == 0) {
          System.out.println("No commands available. Exiting.");
          return;
        }

        int randomIndex = (int) (Math.random() * commands.length);
        String selectedCommand = commands[randomIndex];
        String commandDescription = commandDescriptions.get(selectedCommand);

        System.out.println("Description: " + commandDescription);

        System.out.print("Your guess: ");
        String userGuess = scanner.nextLine();

        if (userGuess.equalsIgnoreCase(selectedCommand)) {
          System.out.println("Correct! You guessed it!");
          correctGuesses++;
        } else {
          System.out.println("Incorrect. The correct command is: " + selectedCommand);
        }
      }

      // Calculate and display the score
      int score = (correctGuesses * 100) / totalAttempts;
      System.out.println("\nYour score: " + score + "%");

      // Determine pass or fail
      if (score >= 70) {
        System.out.println("Congratulations! You passed!");
      } else {
        System.out.println("Sorry, you didn't pass. Keep practicing!");
      }

      // Ask if the user wants to play again
      System.out.print("Do you want to play again? (yes/no): ");
      String playAgainResponse = scanner.nextLine().toLowerCase();
      playAgain = playAgainResponse.equals("yes") || playAgainResponse.equals("y");
    }

    System.out.println("Thank you for playing! Goodbye.");

    // Close the scanner
    scanner.close();
  }

  private void initializeCommandDescriptions() {
    commandDescriptions = new HashMap<>();
    commandDescriptions.put("ls", "List files and directories.");
    commandDescriptions.put("pwd", "Print the current working directory.");
    commandDescriptions.put("cd", "Change the current directory.");
    commandDescriptions.put("mkdir", "Create a new directory.");
    commandDescriptions.put("cp", "Copy files or directories.");
    commandDescriptions.put("mv", "Move or rename files or directories.");
    commandDescriptions.put("rm", "Remove files or directories.");
    commandDescriptions.put("cat", "Display the contents of a file.");
    commandDescriptions.put("echo", "Print text to the terminal.");
    commandDescriptions.put("nano", "Text editor in the terminal.");
    commandDescriptions.put("touch", "Create an empty file.");
    commandDescriptions.put("chmod", "Change file permissions.");
    commandDescriptions.put("chown", "Change file owner and group.");
    commandDescriptions.put("ps", "List processes.");
    commandDescriptions.put("kill", "Terminate a process.");
    commandDescriptions.put("top", "Display system resource usage.");
    commandDescriptions.put("df", "Display disk space usage.");
    commandDescriptions.put("du", "Display file and directory space usage.");
    commandDescriptions.put("grep", "Search for patterns in files.");
    commandDescriptions.put("find", "Search for files and directories.");
    commandDescriptions.put("tar", "Archive files and directories.");
    commandDescriptions.put("head", "Display the beginning of a file.");
    commandDescriptions.put("tail", "Display the end of a file.");
    commandDescriptions.put("wget", "Download files from the internet.");
    commandDescriptions.put("sed", "Stream editor for text.");
    commandDescriptions.put("awk", "Pattern scanning and processing language.");
    commandDescriptions.put("uptime", "Display system uptime.");
    commandDescriptions.put("ifconfig", "Configure network interfaces.");
    commandDescriptions.put("route", "Show and manipulate IP routing table.");
    commandDescriptions.put("ping", "Send ICMP ECHO_REQUEST to network hosts.");
    commandDescriptions.put("traceroute", "Print the route packets take to a network host.");
    commandDescriptions.put("netstat", "Display network connections, routing tables, interface statistics, masquerade connections, and multicast memberships.");
    commandDescriptions.put("ss", "Utility to investigate sockets.");
    commandDescriptions.put("ip", "Show / manipulate routing, devices, policy routing and tunnels.");
    commandDescriptions.put("fdisk", "Partition table manipulator for Linux.");
    commandDescriptions.put("mount", "Mount a file system.");
    commandDescriptions.put("umount", "Unmount a file system.");
    commandDescriptions.put("lsblk", "List block devices.");
    commandDescriptions.put("df", "Display free disk space.");
    commandDescriptions.put("du", "Estimate file space usage.");
    commandDescriptions.put("free", "Display amount of free and used memory in the system.");
    // Add more commands and descriptions as needed
  }
}
