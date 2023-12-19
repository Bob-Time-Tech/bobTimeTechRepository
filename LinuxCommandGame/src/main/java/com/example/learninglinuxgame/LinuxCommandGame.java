package com.example.learninglinuxgame;

import jdk.jfr.Name;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * @author robert.hogan
 */
public class BobsLinuxCommandGame {

  // Constants for the game
  private static final int NUM_ROUNDS = 10;

  // Game state variables
  private int currentIndex;
  private int correctAnswers;

  // Map to store Linux commands and their descriptions
  private Map<String, String> commandDescriptions;
  private String[] commands;

  // Main game frame
  private JFrame frame;
  private JTextArea descriptionArea;
  private JTextField guessField;
  private JLabel resultLabel;

  // Review frame
  private JFrame reviewFrame;

  // Main method to start the game
  public static void main(String[] args) {
    SwingUtilities.invokeLater(
        () -> {
          BobsLinuxCommandGame game = new BobsLinuxCommandGame();
          System.out.println("Bob's Linux Command Game is successfully running");
        });
  }

  // Constructor to initialize the game
  public BobsLinuxCommandGame() {
    initializeCommandDescriptions();
    initializeGUI();
    startGame();
  }

  // Method to start the game
  private void startGame() {
    SwingUtilities.invokeLater(
        () -> {
          commands = commandDescriptions.keySet().toArray(new String[0]);
          List<String> commandList = Arrays.asList(commands);
          Collections.shuffle(commandList);
          commands = commandList.toArray(new String[0]);
          currentIndex = 0;
          correctAnswers = 0;
          displayNextCommand();
          frame.setVisible(true);
        });
  }

  // Method to initialize the map of Linux commands and descriptions. 
  private void initializeCommandDescriptions() {
    // Map to store Linux commands and their descriptions
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
    commandDescriptions.put(
        "traceroute", "Trace the route that packets take to reach a network host.");
    commandDescriptions.put(
        "netstat", "Display network connections, routing tables, interface statistics,");
    commandDescriptions.put("ss", "Utility to investigate sockets.");
    commandDescriptions.put(
        "ip", "Show / manipulate routing, devices, policy routing and tunnels.");
    commandDescriptions.put("fdisk", "Partition table manipulator for Linux.");
    commandDescriptions.put("mount", "Mount a file system.");
    commandDescriptions.put("umount", "Unmount a file system.");
    commandDescriptions.put("lsblk", "List block devices.");
    commandDescriptions.put("free", "Display amount of free and used memory in the system.");
    commandDescriptions.put("history", "Display the command history.");
    commandDescriptions.put("who", "Show who is logged on.");
    commandDescriptions.put("w", "Display information about the users currently on the machine.");
    commandDescriptions.put("users", "Display a list of users currently logged on.");
    commandDescriptions.put("groups", "Display a user's group memberships.");
    commandDescriptions.put("date", "Display the current date and time.");
    commandDescriptions.put("cal", "Display a calendar.");
    commandDescriptions.put("uname", "Display system information.");
    commandDescriptions.put("whoami", "Print effective user ID.");
    commandDescriptions.put("id", "Print real and effective user and group IDs.");
    commandDescriptions.put("hostname", "Show or set the system's host name.");
    commandDescriptions.put("env", "Display environment variables.");
    commandDescriptions.put("export", "Set environment variables.");
    commandDescriptions.put("alias", "Define or display aliases.");
    commandDescriptions.put("umask", "Set the user file-creation mask.");
    commandDescriptions.put("passwd", "Change user password.");
    commandDescriptions.put(
        "rmdir", "Remove empty directories. (Note: Only removes empty directories)");
    commandDescriptions.put("ln", "Create links between files.");
    commandDescriptions.put("file", "Determine file type.");
    commandDescriptions.put("shutdown", "Shutdown or restart the system.");
    commandDescriptions.put("reboot", "Reboot the system.");
    commandDescriptions.put("lscpu", "Display CPU information.");
    commandDescriptions.put("lsusb", "Display USB information.");
    commandDescriptions.put("lshw", "Display hardware information.");
    commandDescriptions.put("ps aux", "Display detailed process information.");
    commandDescriptions.put("journalctl", "Query and display messages from the journal.");
    commandDescriptions.put("systemctl", "Control the systemd system and service manager.");
    commandDescriptions.put("chroot", "Run a command with a different root directory.");
    commandDescriptions.put("dmesg", "Print or control the kernel ring buffer.");
    commandDescriptions.put("lsof", "List open files and the processes that opened them.");
    commandDescriptions.put("ldd", "Print shared library dependencies.");
    commandDescriptions.put("ldconfig", "Configure dynamic linker run-time bindings.");
    commandDescriptions.put("mkfs", "Build a Linux file system.");
    commandDescriptions.put("fdformat", "Low-level format a floppy disk.");
    commandDescriptions.put("dpkg", "Package manager for Debian-based systems.");
    commandDescriptions.put("rpm", "Package manager for RPM-based systems.");
    commandDescriptions.put("yum", "Package manager for RPM-based systems.");
    // ... (add descriptions for other commands)
  }

  /** Method to initialize the main game GUI */
  private void initializeGUI() {
    frame = new JFrame("Bob's Linux Command Game");
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    descriptionArea = new JTextArea();
    setupTextArea(descriptionArea);

    guessField = new JTextField();
    setupTextField(guessField);

    JButton submitButton = new JButton("Submit");
    setupButton(submitButton, e -> checkGuess());

    JButton reviewButton = new JButton("Review");
    setupButton(reviewButton, e -> startReview());

    resultLabel = new JLabel();
    setupLabel(resultLabel);

    JPanel inputPanel = createInputPanel();
    JPanel buttonPanel = createButtonPanel(reviewButton);
    JPanel mainPanel = createMainPanel(inputPanel, buttonPanel);

    setupMainFrameLayout(mainPanel);
  }

  private JPanel createMainPanel(JPanel inputPanel, JPanel buttonPanel) {
    // Main panel layout
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.add(inputPanel, BorderLayout.CENTER);
    mainPanel.add(resultLabel, BorderLayout.NORTH);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    return mainPanel;
  }

  private void setupMainFrameLayout(JPanel mainPanel) {
    // Frame layout - Configure layout and components of the main frame
    frame.setLayout(new BorderLayout());
    frame.add(mainPanel, BorderLayout.CENTER);
  }
  // Set text area properties
  private void setupTextArea(JTextArea textArea) {
    // Text area settings
    textArea.setEditable(false);
    textArea.setFont(new Font("Arial", Font.PLAIN, 24));
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
  }

  private void setupTextField(JTextField textField) {
    // Text field settings
    textField.setFont(new Font("Arial", Font.PLAIN, 24));
    // Set text field properties
  }

  private void setupButton(JButton button, ActionListener listener) {
    // Button settings: Set button properties and add action listener
    button.setFont(new Font("Arial", Font.PLAIN, 24));
    button.addActionListener(listener);
  }

  private void setupLabel(JLabel label) {
    // Label settings: Set label properties
    label.setFont(new Font("Arial", Font.PLAIN, 24));
  }

  private JPanel createInputPanel() {
    // Create and configure input panel
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new BorderLayout());
    inputPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);
    inputPanel.add(guessField, BorderLayout.SOUTH);
    inputPanel.add(createSubmitButton(), BorderLayout.EAST);
    return inputPanel;
  }

  private JButton createSubmitButton() {
    //Submit button creation: Create and configure submit button
    JButton submitButton = new JButton("Submit".toUpperCase());
    setupButton(submitButton, e -> checkGuess());
    return submitButton;
  }

  private JPanel createButtonPanel(JButton reviewButton) {
    // Button panel creation: Create and configure button panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(reviewButton);
    return buttonPanel;
  }

  private void setupMainPanel(JPanel mainPanel, JPanel inputPanel, JPanel buttonPanel) {
    // Main panel layout: Configure layout and components of the main panel
    mainPanel.setLayout(new BorderLayout());
    mainPanel.add(inputPanel, BorderLayout.CENTER);
    mainPanel.add(resultLabel, BorderLayout.NORTH);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);
  }

  // Method to check the user's guess
  private void checkGuess() {
    SwingUtilities.invokeLater(
        () -> {
          // Get user's guess from the text field
          String userGuess = guessField.getText().trim();

          // Check if the user's guess is empty
          if (userGuess.isEmpty()) {
            // Display a warning message if the guess is empty
            showWarningMessage(
                "Empty Guess", "Please type your answer in the field then click the SUBMIT Button");
            return;
          }
          // Get the correct command for the current round
          String selectedCommand = commands[currentIndex];

          // Check if the user's guess is correct
          boolean isCorrect = userGuess.equalsIgnoreCase(selectedCommand);

          // Generate feedback message based on correctness
          String feedbackMessage =
              isCorrect ? "Correct! Good Job!" : getIncorrectFeedbackMessage(selectedCommand);

          // Display the feedback message in the result label
          resultLabel.setText(feedbackMessage);

          // Update the UI after the guess
          updateUIAfterGuess(isCorrect);
        });
  }

  private String getIncorrectFeedbackMessage(String selectedCommand) {
    // Construct and return the incorrect feedback message
    String correctCommand = "Correct command: " + selectedCommand;
    String commandDescription = "Description: " + commandDescriptions.get(selectedCommand);
    return "Incorrect. " + correctCommand + "\n" + commandDescription;
  }

  private void showWarningMessage(String title, String message) {
    // Display a warning message dialog with the specified title and message
    JOptionPane.showMessageDialog(frame, message, title, JOptionPane.WARNING_MESSAGE);
  }

  private void updateUIAfterGuess(boolean isCorrect) {
    SwingUtilities.invokeLater(
        () -> {
          // Check if there are more rounds to play
          if (currentIndex < NUM_ROUNDS - 1) {
            // If more rounds, increment the index and display the next command
            currentIndex++;
            displayNextCommand();
          } else {
            // If no more rounds, display final results
            displayFinalResults(); // keep this line outside the "} else {" block
          }
          // Clear the text field for the next guess
          guessField.setText("");
          // Revalidate and repaint the main frame
          frame.revalidate();
          frame.repaint();
          // Check correctness and display messages
          if (isCorrect) {
            // If correct, clear the result label
            resultLabel.setText("");
            // Increment correctAnswers when the guess is correct
            correctAnswers++;
            // Display an info message for correct guess
            showInfoMessage(frame, "Correct", "Correct! Good Job!");
          } else {
            // If incorrect, show an error message with correct command and description
            String selectedCommand = commands[currentIndex - 1];
            showErrorMessage(frame, "Incorrect", getIncorrectFeedbackMessage(selectedCommand));
          }
        });
  }

  private void showErrorMessage(JFrame frame, String title, String message) {
    // Display an error message dialog with the specified title and message
    JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE);
  }

  private void displayNextCommand() {
    SwingUtilities.invokeLater(
        () -> {
          // Get the selected command and its description
          String selectedCommand = commands[currentIndex];
          String commandDescription = commandDescriptions.get(selectedCommand);

          // Set text in the description area
          descriptionArea.setText("Description: " + commandDescription + "\n\nYour guess:");

          // Update progress in the result label
          resultLabel.setText("Progress: " + (currentIndex + 1) + "/" + NUM_ROUNDS);
        });
  }
  // Method to start a new round
  private void startNewRound() {
    SwingUtilities.invokeLater(
        () -> {
          // Check if there are more rounds to play
          if (currentIndex < NUM_ROUNDS - 1) {
            // If more rounds, increment the index and reset correctAnswers
            currentIndex++;
            correctAnswers = 0;
            // Display the next command for the new round
            displayNextCommand();
          } else {
            // If no more rounds, display final results
            displayFinalResults(); // keep this line outside the "} else {" block
          }
          // Clear the text field for the next guess
          guessField.setText("");

          // Revalidate and repaint the main frame
          frame.revalidate();
          frame.repaint();
        });
  }
  // Method to display final results and provide options for a new round or quitting
  private void displayFinalResults() {
    SwingUtilities.invokeLater(
        () -> {
          // Calculate percentage of correct answers
          double percentage = ((double) correctAnswers / NUM_ROUNDS) * 100;

          // Calculate the grade based on the percentage % 10 questions if answer all correctly=100==A see private string calculateGrade
          String grade = calculateGrade(percentage);

          // Display final results in the result label
          resultLabel.setText(
              "Game Over. \n"
                  + "Correct Answers: "
                  + correctAnswers
                  + "\n"
                  + " Percentage:  "
                  + String.format("%.2f", percentage)
                  + "%\n"
                  + "Grade:  "
                  + grade
                  + "\n\nDo you want to start a new round?");

          // Create buttons for starting a new round or quitting
          JButton newRoundButton = new JButton("Start New Round");
          setupButton(newRoundButton, e -> startNewRound());
          JButton quitButton = new JButton("Quit");
          setupButton(quitButton, e -> System.exit(0));

          // Create a button panel for the final results screen
          JPanel buttonPanel = new JPanel();
          buttonPanel.add(newRoundButton);
          buttonPanel.add(quitButton);

          // Create a main panel for the final results screen
          JPanel mainPanel = createMainPanel(createInputPanel(), buttonPanel);
          // Set up layout for the main frame
          setupMainFrameLayout(mainPanel);

          // Revalidate and repaint the main frame
          frame.revalidate();
          frame.repaint();
        });
  }
//Method to calculate the garde based on the percentage
  private String calculateGrade(double percentage) {
    // Determine the grade based on the percentage
    if (percentage >= 95) {
      return "A";
    } else if (percentage >= 85) {
      return "B";
    } else if (percentage >= 75) {
      return "C";
    } else if (percentage >= 60) {
      return "D";
    } else {
      return "F";
    }
  }

  // Method to start the review
  private void startReview() {
    SwingUtilities.invokeLater(
        () -> {
          // Create and show the review frame
          reviewFrame = createReviewFrame();
          reviewFrame.setVisible(true);

          // Display the first review question
          displayNextReview();
        });
  }

  private JFrame createReviewFrame() {
    // Create and configure the review frame
    JFrame reviewFrame = new JFrame("Bob's Linux Commands Review|Study Cards");
    reviewFrame.setSize(800, 600);

    JTextArea reviewArea = new JTextArea();
    setupTextArea(reviewArea);

    JTextField reviewInputField = new JTextField();
    setupTextField(reviewInputField);

    JButton submitReviewButton = new JButton("Submit Review");
    setupButton(submitReviewButton, e -> checkReviewAnswer(reviewInputField, reviewArea));

    JButton nextButton = new JButton("Next");
    setupButton(nextButton, e -> displayNextReview());

    JPanel reviewPanel = createReviewPanel(reviewArea);
    JPanel inputPanel = createReviewInputPanel(reviewInputField, submitReviewButton);
    JPanel buttonPanel = createButtonPanelForReview(nextButton);

    JPanel controlPanel = createReviewControlPanel(inputPanel, buttonPanel);

    setupReviewFrameLayout(reviewFrame, reviewPanel, controlPanel);

    return reviewFrame;
  }

  private JPanel createReviewPanel(JTextArea reviewArea) {
    // Create and configure the review panel
    JPanel reviewPanel = new JPanel();
    reviewPanel.setLayout(new BorderLayout());
    reviewPanel.add(new JScrollPane(reviewArea), BorderLayout.CENTER);
    return reviewPanel;
  }

  private JPanel createReviewInputPanel(JTextField reviewInputField, JButton submitReviewButton) {
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new BorderLayout());
    inputPanel.add(reviewInputField, BorderLayout.CENTER);
    inputPanel.add(submitReviewButton, BorderLayout.EAST);
    return inputPanel;
  }

  private JPanel createButtonPanelForReview(JButton nextButton) {
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(nextButton);
    return buttonPanel;
  }

  private JPanel createReviewControlPanel(JPanel inputPanel, JPanel buttonPanel) {
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new BorderLayout());
    controlPanel.add(inputPanel, BorderLayout.NORTH);
    controlPanel.add(buttonPanel, BorderLayout.SOUTH);
    return controlPanel;
  }

  private void setupReviewFrameLayout(JFrame reviewFrame, JPanel reviewPanel, JPanel controlPanel) {
    reviewFrame.setLayout(new BorderLayout());
    reviewFrame.add(reviewPanel, BorderLayout.CENTER);
    reviewFrame.add(controlPanel, BorderLayout.SOUTH);
  }
//Method to display the next review question
  private void displayNextReview() {
    SwingUtilities.invokeLater(
        () -> {
          if (currentIndex < commands.length) {
            String selectedCommand = commands[currentIndex];
            String commandDescription = commandDescriptions.get(selectedCommand);

            JTextArea reviewArea = getReviewArea(reviewFrame);
            JTextField reviewInputField = getReviewInputField(reviewFrame);

            reviewArea.setText(
                "Command: " + selectedCommand + "\nDescription: " + commandDescription);
            reviewInputField.setText("");
            reviewInputField.requestFocus();
          } else {
            showInfoMessage(reviewFrame, "Review Complete", "End of commands");
            reviewFrame.dispose(); // Close the review frame when the review is complete
          }
        });
  }
// Method to check the user's review answer
  private void checkReviewAnswer(JTextField reviewInputField, JTextArea reviewArea) {
    SwingUtilities.invokeLater(
        () -> {
          String userAnswer = reviewInputField.getText().trim();

          if (userAnswer.isEmpty()) {
            showWarningMessage(
                "Empty Answer", "Please type your answer in the field then click Submit");
            return;
          }

          if (currentIndex < commands.length) {
            String selectedCommand = commands[currentIndex];
            String feedbackMessage;

            if (userAnswer.equalsIgnoreCase(selectedCommand)) {
              feedbackMessage = "Correct Linux Command Entered!";
            } else {
              feedbackMessage =
                  getIncorrectReviewFeedbackMessage(selectedCommand, reviewInputField);
            }
            showInfoMessage(reviewFrame, "Review Result", feedbackMessage);
          }
        });
  }

  private String getIncorrectReviewFeedbackMessage(
      String selectedCommand, JTextField reviewInputField) {
    String correctCommand = "Correct Command: " + selectedCommand;
    String correctDescription = "Description: " + commandDescriptions.get(selectedCommand);
    return "Incorrect. The Linux Command you entered ("
        + reviewInputField.getText()
        + ") is incorrect. "
        + correctCommand
        + "\n"
        + correctDescription;
  }

  private void showInfoMessage(JFrame parentFrame, String title, String message) {
    JOptionPane.showMessageDialog(parentFrame, message, title, JOptionPane.INFORMATION_MESSAGE);
  }

  private JTextArea getReviewArea(JFrame reviewFrame) {
    return findComponent(reviewFrame.getContentPane(), JTextArea.class);
  }

  private JTextField getReviewInputField(JFrame reviewFrame) {
    return findComponent(reviewFrame.getContentPane(), JTextField.class);
  }

  private <T> T findComponent(Container container, Class<T> type) {
    Component[] components = container.getComponents();
    for (Component component : components) {
      if (type.isInstance(component)) {
        return type.cast(component);
      }
      if (component instanceof Container) {
        T result = findComponent((Container) component, type);
        if (result != null) {
          return result;
        }
      }
    }
    return null;
  }
}
