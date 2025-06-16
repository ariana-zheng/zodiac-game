/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zodiacgame;

/**
 *
 * @author 342354727
 */
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MySketch extends PApplet {
    
    // Characters for selection
    private ArrayList<Character> characters;
    private int currentCharacterIndex = 0; // Tracks currently displayed character in stage 3
    private String userName = ""; // User's inputted name
    private String [] animalDescriptions = new String [12]; //array that holds all 12 animal stories
    
    //images for scenes
    private PImage river;  
    private PImage titlepage,inputpage, instructionspage, selectionpage; 
    
    private int stage = 0; 
    private boolean showInfoForCurrentCharacter = false;
    private Character playerCharacter; // The character chosen by the player
    private ArrayList<Obstacle> obstacles;
    private int score = 0;
    private long lastObstacleSpawnTime = 0;
    private long obstacleSpawnInterval = 1000; // milliseconds between obstacles (this part was reseearched)
    private boolean gameOver = false;

    public void settings() {
        //sets the size of the window
        size(600, 400);
    }

    public void setup() {
        //sets the background colour using R,G,B (https://rgbcolorpicker.com/)
        background(100, 100, 100);
        textSize(15);//set text size
        
        try{
            //Setting each animal's description to their corresponding line in the story file
            Scanner input = new Scanner(new File("story.txt"));
            for (int i = 0; i<animalDescriptions.length; i++){
                String currentLine = input.nextLine();
                animalDescriptions[i] = currentLine;
            }
            input.close();
            System.out.println("Debug: animalDescriptions[0]: " + animalDescriptions[4]);
            System.out.println("Debug: animalDescriptions[1]: " + animalDescriptions[11]);
        } catch(IOException e){
            System.out.print(e);
        }
        
        characters = new ArrayList<Character>();
        
        //initialize every character and add all characters to the array
        characters.add(new Character(this, 100, 100, userName, "rat", "images/rat.png", animalDescriptions[0]));
        characters.add(new Character(this, 200, 100, userName, "ox","images/ox.png", animalDescriptions[1]));
        characters.add(new Character(this, 300, 100, userName,"tiger", "images/tiger.png", animalDescriptions[2]));
        characters.add(new Character(this, 400, 100, userName,"rabbit", "images/rabbit.png", animalDescriptions[3]));
        characters.add(new Character(this, 100, 200, userName,"dragon", "images/dragon.png", animalDescriptions[4]));
        characters.add(new Character(this, 200, 200, userName,"snake", "images/snake.png", animalDescriptions[5]));
        characters.add(new Character(this, 300, 200, userName,"horse", "images/horse.png", animalDescriptions[6]));
        characters.add(new Character(this, 400, 200, userName,"lamb", "images/lamb.png", animalDescriptions[7]));
        characters.add(new Character(this, 100, 300, userName,"monkey", "images/monkey.png", animalDescriptions[8]));
        characters.add(new Character(this, 200, 300, userName,"chicken", "images/chicken.png", animalDescriptions[9]));
        characters.add(new Character(this, 300, 300, userName,"dog", "images/dog.png", animalDescriptions[10]));
        characters.add(new Character(this, 400, 300, userName,"pig", "images/pig.png", animalDescriptions[11]));

        //set background images
        this.river = loadImage("images/river.png"); 
        this.titlepage = loadImage("images/titlepage.png"); 
        this.inputpage = loadImage("images/inputpage.png"); 
        this.instructionspage = loadImage("images/instructionspage.png"); 
        this.selectionpage = loadImage("images/selectionpage.png"); 

        obstacles = new ArrayList<Obstacle>();
    }

    public void draw() {

        if (stage == 0){
            image(titlepage, 0, 0);//draw background
        }else if (stage == 1){
            image(inputpage, 0, 0);//draw background
            text(userName,125, 200 );
        }else if (stage == 2){
            image (instructionspage, 0, 0);
        }else if (stage == 3){
            image (selectionpage, 0, 0);
            
            Character current = characters.get(currentCharacterIndex);
            current.x = width / 2;
            current.y = height / 2;
            current.draw();
            //image(current.image, 300, 200);

            //display text
            text("Press LEFT/RIGHT to switch, ENTER to select",width / 2, height - 50);
            text("Click on the character for more info!", width / 2, height - 20);
            
            if (showInfoForCurrentCharacter) {
                current.displayInfo(this);
            }

        } else if (stage == 4) { // Game Play Stage
            image(river, 0, 0); // Draw the game background 

            // Check if the game is over and display appropriate messages
            if (gameOver) {
                fill(255, 0, 0); // Red text for Game Over message
                textSize(36);
                text("GAME OVER!", 250, 100); // Game Over title
                textSize(24);
                text("Final Score: " + score, 250, 150); // Display final score
                textSize(18);
                noLoop(); // Stop the draw loop when the game is over
                return; // Exit draw loop 
            }

            // Draw the player character at its current position
            playerCharacter.draw();

            // Check if enough time has passed since the last obstacle was spawned (this part was researched)
            if (millis() - lastObstacleSpawnTime > obstacleSpawnInterval) {
                // Add a new obstacle to the list with a random speed and image
                obstacles.add(new Obstacle(this, random(3, 6), "images/obstacle.png"));
                lastObstacleSpawnTime = millis(); // Reset the spawn timer
            }

            // Move obstacles
            for (int i = obstacles.size() - 1; i >= 0; i--) {
                Obstacle obs = obstacles.get(i);
                obs.move(); // Move the obstacle downwards
                obs.display(); // Draw the obstacle

                // Check for collision between the current obstacle and the player character
                if (obs.isCollidingWith(playerCharacter)) {
                    gameOver = true; // game over if a collision occurs
                }

                // If the obstacle has moved off-screen, remove it and increment the score
                if (obs.isOffscreen()) {
                    obstacles.remove(i); // Remove the obstacle
                    score++; // Increment score forsuccessfully dodged obstacle
                }
            }
        }
    }
    /**
     * Depending on the stage, check which key is being pressed and run code accordingly
     */
    public void keyPressed() {
        if (stage == 0) { // Title Stage: Press ENTER to move to the next stage
            if (keyCode == ENTER) {
                stage = 1; // Move to input name stage
            }
        } else if (stage == 1) { 
            if (keyCode == ENTER) {
                if (userName.length() > 0) { // Only proceed if a name has been entered
                    // Assign the entered name to all characters (only the selected one matters)
                    for (Character c : characters) {
                        c.setName(userName);
                    }
                    stage = 2; // Move to instructions stage
                }
            } else if (keyCode == BACKSPACE) {
                // Allow backspace to remove characters from the input string
                if (userName.length() > 0) {
                    userName = userName.substring(0, userName.length() - 1);
                }
            } else if (key != CODED) { 
                userName += key; // Append the key to the user name string
            }
        } else if (stage == 2) { 
            if (keyCode == ENTER) {
                stage = 3; // Move to character selection stage
            }
        } else if (stage == 3) { 
            showInfoForCurrentCharacter = false;

            if (keyCode == LEFT) {
                // Move to the previous character, looping to the end if at the beginning (this part was researched)
                currentCharacterIndex = (currentCharacterIndex > 0) ? currentCharacterIndex - 1 : characters.size() - 1;
            } else if (keyCode == RIGHT) {
                // Move to the next character, looping to the beginning if at the end (this part was researched)
                currentCharacterIndex = (currentCharacterIndex < characters.size() - 1) ? currentCharacterIndex + 1 : 0;
            } else if (keyCode == ENTER) {
                // Select the current character and prepare for the game stage
                playerCharacter = characters.get(currentCharacterIndex);
                // Set the initial position for the player character in the game stage
                playerCharacter.x = 300;
                playerCharacter.y = 350; 
                stage = 4; // Move to the game play stage
                loop(); // Ensure the draw loop is running
            }
        } else if (stage == 4) { 
            if (!gameOver) { // Only allow movement if the game is not over
                if (keyCode == LEFT) {
                    playerCharacter.move(-20, 0); // Move player left
                } else if (keyCode == RIGHT) {
                    playerCharacter.move(20, 0); // Move player right
                }
                // Constrain player's X position to keep them within the screen bounds (this part was researched)
                playerCharacter.x = constrain(playerCharacter.x, playerCharacter.width / 2, width - playerCharacter.width / 2);
            } else { // If the game is over
                Score finalScore = new Score(score); //Creating a score object using final score.
                Player savedPlayer = new Player (userName, finalScore); //Creating player object with score.
                savePlayerToFile(savedPlayer, playerCharacter.getAnimal());
            }
        }
    }
    /**
     * Checks when mouse is pressed during stage 3 for show info method.
     */
    public void mousePressed(){
        // Check if we are in the character selection stage (stage 3)
        if(stage == 3){
            // Get the character currently displayed
            Character current = characters.get(currentCharacterIndex);
            // Check if the currently displayed character's image was clicked
            if (current.isClicked(mouseX, mouseY)){
                showInfoForCurrentCharacter = !showInfoForCurrentCharacter; // Toggle info display
            } else {
                showInfoForCurrentCharacter = false; // Hide info if click is not on the character
            }
        }
    }
   
   /**
     * Saves the provided player object's data with its score to "players.txt".
     * The animal type is passed separately for inclusion in the log.
     * @param player The Player object containing the game record.
     * @param animalType The string name of the animal character chosen by the player.
     */
    private void savePlayerToFile(Player player, String animalType) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("players.txt", true)); 
            // Write the player's name, animal type, and score into file.
            writer.println("Name: " + userName + ", Animal: " + animalType + ", Score: " + score);
            writer.close();
         //Print error message in case of error.   
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
    }
}