import javax.swing.*; // import for the Swing UI components
import java.awt.*; // import for the action listener
import java.awt.event.*; // import for event listener

public class Game {
    public static void main(String[] args) {
        // Starts the game
        new PlayDarts();
    }
}

class PlayDarts extends JFrame {
    // initalise match
    Match match;

    PlayDarts() {
        // array for choice of number of legs to play
        String[] legOptions = { "1", "2", "3", "4", "5" };

        // array for choice of starting score in each leg
        String[] scoreOptions = { "201", "301", "401", "501" };
        // JOptionPane to store players names
        String playerOneName = JOptionPane.showInputDialog(null, "Player 1 Name", "Player 1",
                JOptionPane.INFORMATION_MESSAGE);
        String playerTwoName = JOptionPane.showInputDialog(null, "Player 2 Name", "Player 2",
                JOptionPane.INFORMATION_MESSAGE);

        // store the number of legs the user wants to play
        int legs = 1 + JOptionPane.showOptionDialog(null, "How many legs do you want to play?", "Legs",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, legOptions, 0);
        // store the starting score for each leg
        String score = scoreOptions[JOptionPane.showOptionDialog(null, "Starting score for each leg?", "Starting Score",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, scoreOptions, 0)];

        // create player instances for each player
        Player one = new Player(playerOneName);
        Player two = new Player(playerTwoName);

        // creates options to check who should go first
        String[] throwFirstOptions = { one.getName(), two.getName() };

        // take the user input to see who whould throw first
        int whoGoesFirst = JOptionPane.showOptionDialog(null, "Which player will throw first?", "Who Throws First?",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, throwFirstOptions, 0);

        // check which player should throw first and set the match up
        // using Player objects, starting score and legs
        if (whoGoesFirst == 0) {
            match = new Match(one, two, Integer.parseInt(score), legs);
        } else {
            match = new Match(two, one, Integer.parseInt(score), legs);
        }

        // set size of JFrame, exit on close and layout type
        this.setBounds(100, 100, 600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Darts Scoreboard");
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        String font = "Verdana";

        // create panel with grid layout to hold other panels
        JPanel mainPanel = new JPanel();
        this.getContentPane().add(mainPanel);
        mainPanel.setLayout(new GridLayout(0, 3, 0, 0));

        // create panel to hold player 1's information
        JPanel p1Panel = new JPanel();
        p1Panel.setBackground(new Color(241, 250, 238));
        mainPanel.add(p1Panel);
        p1Panel.setLayout(new GridLayout(7, 1, 0, 0));

        // create various labels for player 1's stats and scores
        JLabel playerOneLabel = new JLabel(match.playerOne.getName());
        playerOneLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerOneLabel.setFont(new Font(font, Font.BOLD, 21));
        playerOneLabel.setBackground(Color.WHITE);
        playerOneLabel.setForeground(new Color(29, 53, 87));
        p1Panel.add(playerOneLabel);

        JLabel playerOneScore = new JLabel("" + match.playerOne.getScore());
        playerOneScore.setHorizontalAlignment(SwingConstants.CENTER);
        playerOneScore.setFont(new Font(font, Font.PLAIN, 21));
        p1Panel.add(playerOneScore);

        JLabel playerOneLegs = new JLabel("" + match.playerOne.getLegsWon());
        playerOneLegs.setHorizontalAlignment(SwingConstants.CENTER);
        playerOneLegs.setFont(new Font(font, Font.PLAIN, 21));
        p1Panel.add(playerOneLegs);

        JLabel playerOneAverage = new JLabel("" + match.playerOne.getAverage());
        playerOneAverage.setHorizontalAlignment(SwingConstants.CENTER);
        playerOneAverage.setFont(new Font(font, Font.PLAIN, 21));
        p1Panel.add(playerOneAverage);

        JLabel playerOneHighest = new JLabel("" + match.playerOne.searchLargest());
        playerOneHighest.setHorizontalAlignment(SwingConstants.CENTER);
        playerOneHighest.setFont(new Font(font, Font.PLAIN, 21));
        p1Panel.add(playerOneHighest);

        // create text field to input player 1's score
        JTextField playerOneTextField = new JTextField();
        playerOneTextField.setHorizontalAlignment(SwingConstants.CENTER);
        playerOneTextField.setColumns(4);
        p1Panel.add(playerOneTextField);

        // create button to enter player 1's score
        JButton playerOneSubmit = new JButton(match.playerOne.getName() + " Submit");
        playerOneSubmit.setFont(new Font(font, Font.BOLD, 14));
        playerOneSubmit.setBackground(new Color(29, 53, 87));
        playerOneSubmit.setForeground(new Color(241, 250, 238));
        p1Panel.add(playerOneSubmit);

        // panel to hold the stats headings
        JPanel statsPanel = new JPanel();
        statsPanel.setBackground(new Color(29, 53, 87));
        mainPanel.add(statsPanel);
        statsPanel.setLayout(new GridLayout(7, 1, 0, 0));

        // create various labels for the stats headings
        JLabel legLabel = new JLabel("Leg " + match.getCurrentLeg());
        legLabel.setHorizontalAlignment(SwingConstants.CENTER);
        legLabel.setForeground(new Color(255, 255, 255));
        legLabel.setFont(new Font(font, Font.BOLD, 24));
        statsPanel.add(legLabel);

        JLabel scoreLabel = new JLabel("SCORE");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font(font, Font.BOLD, 24));
        statsPanel.add(scoreLabel);

        JLabel legsLabel = new JLabel("LEGS (" + match.getLegs() + ")");
        legsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        legsLabel.setForeground(Color.WHITE);
        legsLabel.setFont(new Font(font, Font.BOLD, 24));
        statsPanel.add(legsLabel);

        JLabel averageLabel = new JLabel("AVERAGE");
        averageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        averageLabel.setForeground(Color.WHITE);
        averageLabel.setFont(new Font(font, Font.BOLD, 24));
        statsPanel.add(averageLabel);

        JLabel highestLabel = new JLabel("TOP SCORE");
        highestLabel.setHorizontalAlignment(SwingConstants.CENTER);
        highestLabel.setForeground(Color.WHITE);
        highestLabel.setFont(new Font(font, Font.BOLD, 24));
        statsPanel.add(highestLabel);

        JLabel atTheOcheLabel = new JLabel("<--- " + match.playerOne.getName() + "'s turn!");
        atTheOcheLabel.setHorizontalAlignment(SwingConstants.CENTER);
        atTheOcheLabel.setForeground(Color.WHITE);
        atTheOcheLabel.setFont(new Font(font, Font.BOLD, 18));
        statsPanel.add(atTheOcheLabel);

        // create panel to hold player 2's information
        JPanel p2Panel = new JPanel();
        p2Panel.setBackground(new Color(241, 250, 238));
        mainPanel.add(p2Panel);
        p2Panel.setLayout(new GridLayout(7, 1, 0, 0));

        // create various labels for player 2's stats and scores
        JLabel playerTwoLabel = new JLabel(match.playerTwo.getName());
        playerTwoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerTwoLabel.setFont(new Font(font, Font.BOLD, 21));
        playerTwoLabel.setBackground(Color.WHITE);
        playerTwoLabel.setForeground(new Color(29, 53, 87));
        p2Panel.add(playerTwoLabel);

        JLabel playerTwoScore = new JLabel("" + match.playerTwo.getScore());
        playerTwoScore.setHorizontalAlignment(SwingConstants.CENTER);
        playerTwoScore.setFont(new Font(font, Font.PLAIN, 21));
        p2Panel.add(playerTwoScore);

        JLabel playerTwoLegs = new JLabel("" + match.playerTwo.getLegsWon());
        playerTwoLegs.setHorizontalAlignment(SwingConstants.CENTER);
        playerTwoLegs.setFont(new Font(font, Font.PLAIN, 21));
        p2Panel.add(playerTwoLegs);

        JLabel playerTwoAverage = new JLabel("" + match.playerOne.getAverage());
        playerTwoAverage.setHorizontalAlignment(SwingConstants.CENTER);
        playerTwoAverage.setFont(new Font(font, Font.PLAIN, 21));
        p2Panel.add(playerTwoAverage);

        JLabel playerTwoHighest = new JLabel("" + match.playerTwo.searchLargest());
        playerTwoHighest.setHorizontalAlignment(SwingConstants.CENTER);
        playerTwoHighest.setFont(new Font(font, Font.PLAIN, 21));
        p2Panel.add(playerTwoHighest);

        // create text field to input player 2's score
        JTextField playerTwoTextField = new JTextField();
        playerTwoTextField.setHorizontalAlignment(SwingConstants.CENTER);
        playerTwoTextField.setColumns(4);
        p2Panel.add(playerTwoTextField);

        // create button to enter player 2's score
        JButton playerTwoSubmit = new JButton(match.playerTwo.getName() + " Submit");
        playerTwoSubmit.setFont(new Font(font, Font.BOLD, 14));
        playerTwoSubmit.setBackground(new Color(241, 250, 238));
        playerTwoSubmit.setForeground(new Color(241, 250, 238));
        playerTwoSubmit.setEnabled(false);
        p2Panel.add(playerTwoSubmit);

        // create panel to display various in game messages
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(new Color(230, 57, 70));
        this.getContentPane().add(messagePanel);
        messagePanel.setLayout(new GridLayout(1, 0, 0, 0));

        JLabel messageLabel = new JLabel("");
        messageLabel.setForeground(new Color(241, 250, 238));
        messageLabel.setFont(new Font(font, Font.BOLD, 16));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messagePanel.add(messageLabel);

        // display the frame
        this.setVisible(true);

        // listen for the user pressing enter on their keyboard while on the text field
        // for player 1
        playerOneTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateScore(match.playerOne, match.playerTwo, messageLabel, legLabel, atTheOcheLabel,
                        playerOneTextField, playerOneScore, playerOneLegs, playerOneAverage, playerOneHighest,
                        playerOneSubmit, playerTwoTextField, playerTwoScore, playerTwoLegs, playerTwoAverage,
                        playerTwoHighest, playerTwoSubmit);
            }
        });

        playerOneSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateScore(match.playerOne, match.playerTwo, messageLabel, legLabel, atTheOcheLabel,
                        playerOneTextField, playerOneScore, playerOneLegs, playerOneAverage, playerOneHighest,
                        playerOneSubmit, playerTwoTextField, playerTwoScore, playerTwoLegs, playerTwoAverage,
                        playerTwoHighest, playerTwoSubmit);
            }
        });
        // listen for the user pressing enter on their keyboard while on the text field
        // for player 2
        playerTwoTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateScore(match.playerTwo, match.playerOne, messageLabel, legLabel, atTheOcheLabel,
                        playerTwoTextField, playerTwoScore, playerTwoLegs, playerTwoAverage, playerTwoHighest,
                        playerTwoSubmit, playerOneTextField, playerOneScore, playerOneLegs, playerOneAverage,
                        playerOneHighest, playerOneSubmit);
            }
        });

        playerTwoSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateScore(match.playerTwo, match.playerOne, messageLabel, legLabel, atTheOcheLabel,
                        playerTwoTextField, playerTwoScore, playerTwoLegs, playerTwoAverage, playerTwoHighest,
                        playerTwoSubmit, playerOneTextField, playerOneScore, playerOneLegs, playerOneAverage,
                        playerOneHighest, playerOneSubmit);
            }
        });
    }

    // method that is used to update the UI when a player submits their score
    public void updateScore(Player playerThrowing, Player otherPlayer, JLabel messageLabel, JLabel legLabel,
            JLabel ocheLabel, JTextField throwingTextField, JLabel throwingScore, JLabel throwingLegs,
            JLabel throwingAverage, JLabel throwingHighest, JButton throwingButton, JTextField otherTextField,
            JLabel otherScore, JLabel otherLegs, JLabel otherAverage, JLabel otherHighest, JButton otherButton) {
        // clear any message label text
        messageLabel.setText("");
        // try catch block to prevent user entering anything other than numbers
        try {
            // store the value in the text field
            int score = Integer.parseInt(throwingTextField.getText());
            // check if the current player should be sumbitting
            if (match.onTheOche == playerThrowing) {
                // checking if the score is a valid darts score
                if (playerThrowing.score - score <= 1)
                    messageLabel.setText("Bust! Better luck next time! " + otherPlayer.getName() + "'s turn.");
                else if (!playerThrowing.isScoreValid(score))
                    messageLabel.setText(score + " is not possible! " + otherPlayer.getName() + "'s turn.");
                // adds the user score to the game
                match.throwDarts(match.onTheOche, score);
            }
            // checks if the leg is over and sets the UI up for the next leg
            if (match.legOver()) {
                messageLabel.setText("");
                otherScore.setText("" + otherPlayer.getScore());
                otherLegs.setText("" + otherPlayer.getLegsWon());
                otherAverage.setText("" + otherPlayer.getAverage());
                messageLabel.setText("Leg " + (match.currentLeg - 1) + " winner is: " + playerThrowing.getName());
                legLabel.setText("Leg " + match.getCurrentLeg());
            }
            // updates the throwing player's stats after they have thrown
            updatePlayer(playerThrowing, throwingScore, throwingLegs, throwingAverage, throwingHighest);

            // checks if the match is over and sets the UI for the end of game if so
            if (match.matchOver()) {
                legLabel.setText("");
                otherButton.setEnabled(false);
                otherButton.setBackground(new Color(241, 250, 238));
                ocheLabel.setText("Game Over!");
                messageLabel.setText(match.getWinner());
            } else {
                // sets the UI up for the next player to throw
                if (playerThrowing == match.playerOne)
                    ocheLabel.setText(otherPlayer.getName() + "'s turn " + "--->");
                else
                    ocheLabel.setText("<--- " + otherPlayer.getName() + "'s turn");
                otherButton.setBackground(new Color(29, 53, 87));
                otherButton.setEnabled(true);
                otherTextField.requestFocusInWindow();
            }
            // clears the throwing players text field and disables the submit button
            setUpNextPlayer(throwingTextField, throwingButton);
        } catch (Exception z) {
            // displays a message if the user enters anything other than numbers
            messageLabel.setText("You can only enter Numbers!");
            throwingTextField.setText("");
            throwingTextField.requestFocusInWindow();
        }

    }

    // method to update the current players data
    public void updatePlayer(Player player, JLabel score, JLabel legs, JLabel average, JLabel highest) {
        score.setText("" + player.getScore());
        legs.setText("" + player.getLegsWon());
        average.setText("" + player.getAverage());
        highest.setText("" + player.searchLargest());
    }

    // method to set the UI up for the next player
    public void setUpNextPlayer(JTextField textField, JButton button) {
        textField.setText("");
        button.setEnabled(false);
        button.setBackground(new Color(241, 250, 238));
    }
}

// class to create a Match instance
class Match {
    Player playerOne; // create Player 1
    Player playerTwo; // create Player 2
    Player onTheOche; // create reference to whos turn it is
    int startingScore; // the starting score for each leg in the match
    int legs; // the number of legs to be played in the match
    int currentLeg = 1; // the starting leg is always leg 1

    // constructor for the Match
    public Match(Player one, Player two, int startingScore, int legs) {
        this.playerOne = one;
        this.playerOne.setScore(startingScore);
        this.playerTwo = two;
        this.playerTwo.setScore(startingScore);
        this.startingScore = startingScore;
        this.onTheOche = playerOne;
        this.legs = legs;
    }

    // method to get the number of legs
    public int getLegs() {
        return this.legs;
    }

    // method to get the current leg
    public int getCurrentLeg() {
        return this.currentLeg;
    }

    // method that performs a single round of darts
    public void throwDarts(Player player, int score) {
        player.reduceScore(score);
        if (player == this.playerOne) {
            this.onTheOche = this.playerTwo;
        } else {
            this.onTheOche = this.playerOne;
        }
    }

    // method to check if the match is over;
    public boolean matchOver() {
        if (this.getLegs() == 0) {
            return true;
        }
        return false;
    }

    // method to check if a players score has reached 0,
    // resets the game for the next leg if so
    public boolean legOver() {
        if (this.playerOne.getScore() == 0) {
            this.legs--;
            this.playerOne.setScore(this.startingScore);
            this.playerOne.legsWon++;
            this.playerTwo.setScore(this.startingScore);
            this.currentLeg++;
            return true;
        }
        if (this.playerTwo.getScore() == 0) {
            this.legs--;
            this.playerOne.setScore(this.startingScore);
            this.playerTwo.legsWon++;
            this.playerTwo.setScore(this.startingScore);
            this.currentLeg++;
            return true;
        }
        return false;
    }

    // method to return the winner of the match
    public String getWinner() {
        if (this.playerOne.legsWon == this.playerTwo.legsWon) {
            return "The game is a draw!";
        } else if (playerOne.legsWon > playerTwo.legsWon) {
            return this.playerOne.name + " Wins!";
        } else {
            return this.playerTwo.name + " Wins!";
        }
    }
}

// class to create a Player instance
class Player {
    String name; // players name
    int score; // players score
    int totalScored; // players total score across all legs
    double average; // players average score
    int attempts; // number of darts thrown
    int legsWon; // number of legs won
    DartsLinkedList scoreHistory; // linked list to store the players history of darts thrown

    // constructor for player class
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.totalScored = 0;
        this.average = 0.0;
        this.attempts = 0;
        this.scoreHistory = new DartsLinkedList();
    }

    // method to set the players current score
    public void setScore(int score) {
        this.score = score;
    }

    // method to get the players current score
    public int getScore() {
        return this.score;
    }

    // method to get the player's name
    public String getName() {
        return this.name;
    }

    // method to get the player's legs won
    public int getLegsWon() {
        return this.legsWon;
    }

    // method to get the player's average rounded to 2 decimal places
    public double getAverage() {
        return Math.round(this.average * 100.0) / 100.0;
    }

    // method to check if the score inut is a valid darts score
    public boolean isScoreValid(int score) {
        if (score > 180 || score < 0 || score == 163 || score == 169 || score == 166 || score == 172 || score == 173
                || score == 175 || score == 176 || score == 178 || score == 179 || this.score - score <= 1) {
            return false;
        }
        return true;
    }

    // method to reduce the players score
    public void reduceScore(int score) {
        // checks for invalid score
        // this could also be a score to win the leg
        if (!this.isScoreValid(score)) {
            // checks if the score can win the leg
            if (this.score == score && this.score <= 170) {
                // checks for checkouts that are impossbile
                if (score == 168 || score == 165 || score == 162 || score == 159) {
                    // adds the submitted score to the linked list
                    this.scoreHistory.insert(0);
                } else {
                    // the checkout is possbile and the players score is set to 0
                    // adds the submitted score to the linked list
                    this.scoreHistory.insert(score);
                    this.score = 0;
                }
            }

            else {
                // adds 0 to the linked list
                this.scoreHistory.insert(0);
            }
        } else if ((this.score >= 170 && this.score <= 180) && (score > 170 && score < 180)) {
            // adds 0 to the linked list
            this.scoreHistory.insert(0);
        }
        // otherwise the score is valid and the game continues
        // the players score is reduced
        else {
            this.scoreHistory.insert(score);
            this.score -= score;
            this.totalScored += score;
        }
        // calucations to update the players average
        this.attempts += 1;
        this.average = (this.totalScored * 1.0) / this.attempts;
    }

    // method to search the linked list to find the players highest single score
    public int searchLargest() {
        int largest;
        // checks if the linked list is empty
        if (this.scoreHistory.isEmpty()) {
            largest = 0;
        } else {
            largest = this.scoreHistory.get(0);
            // searches through the linked list to find the largest value
            for (int i = 0; i <= this.scoreHistory.size(); i++) {
                if (this.scoreHistory.get(i) > largest) {
                    largest = this.scoreHistory.get(i);
                }
            }
        }
        // returns largest value found or else 0
        return largest;
    }
}

// create a single link/node for the linked list
class Link {
    public int data;
    public Link next;

    // constructor for Link
    public Link(int data) {
        this.data = data;
    }
}

// create the linked list
class DartsLinkedList {
    public Link head;

    // constructor for linked list
    public DartsLinkedList() {
        this.head = null;
    }

    // method to check if the linked list is empty
    public boolean isEmpty() {
        return (this.head == null);
    }

    // method that takes an index and returns the link at the given index
    public int get(int index) {
        Link check = this.head;
        for (int i = 0; i < index; i++) {
            check = check.next;
        }
        return check.data;

    }

    // method that returns the number of links currently in the linked list
    public int size() {
        Link currentLink = this.head;
        int count = 0;
        while (currentLink.next != null) {
            currentLink = currentLink.next;
            count++;
        }
        return count;
    }

    // method to insert a new link at the head of the linked list
    public void insert(int data) {
        Link newLink = new Link(data);
        newLink.next = null;
        if (this.head == null) {
            this.head = newLink;
        } else {
            Link currentLink = this.head;
            while (currentLink.next != null) {
                currentLink = currentLink.next;
            }
            currentLink.next = newLink;
        }
    }
}