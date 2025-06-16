/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zodiacgame;



/**
 *
 * @author 342354727
 */
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class MySketch extends PApplet {
    
    //characters for selection
    ArrayList<Character> characters;
    int i = 0; // Tracks selected character
    String input = "";//users inputted name
    
    private Character rat, ox, tiger, rabbit, dragon, snake, horse, lamb, monkey, chicken, dog, pig;
    
    //images for scenes
    private PImage river;  
    private PImage titlepage,inputpage, instructionspage, selectionpage; 
    
    int stage = 0;
    private boolean showInfo = false;
    ArrayList<Obstacle> obstacles; 
    
    boolean selecting = true; // Enable selection mode
    private Character player;
    
    public void settings() {
        //sets the size of the window
        size(600, 400);
    }

    public void setup() {
        //sets the background colour using R,G,B (https://rgbcolorpicker.com/)
        background(100, 100, 100);
        textSize(15);//set text size
        
        characters = new ArrayList<Character>();
        //initialize every character
        rat = new Character(this, 100, 100, input, "rat", "images/rat.png"); 
        ox = new Character(this, 200, 100, input, "ox","images/ox.png"); 
        tiger = new Character(this, 300, 100, input,"tiger", "images/tiger.png"); 
        rabbit = new Character(this, 400, 100, input,"rabbit", "images/rabbit.png"); 
        dragon = new Character(this, 100, 200, input,"dragon", "images/dragon.png"); 
        snake = new Character(this, 200, 200, input,"snake", "images/snake.png"); 
        horse = new Character(this, 300, 200, input,"horse", "images/horse.png"); 
        lamb = new Character(this, 400, 200, input,"lamb", "images/lamb.png"); 
        monkey = new Character(this, 100, 300, input,"monkey", "images/monkey.png"); 
        chicken = new Character(this, 200, 300, input,"chicken", "images/chicken.png"); 
        dog = new Character(this, 300, 300, input,"dog", "images/dog.png"); 
        pig = new Character(this, 400, 300, input,"pig", "images/pig.png"); 
        //add all characters to the array
        characters.add(rat);
        characters.add(ox);
        characters.add(tiger);
        characters.add(rabbit);
        characters.add(dragon);
        characters.add(snake);
        characters.add(horse);
        characters.add(lamb);
        characters.add(monkey);
        characters.add(chicken);
        characters.add(dog);
        characters.add(pig);

        //set background images
        this.river = loadImage("images/river.png"); 
        this.titlepage = loadImage("images/titlepage.png"); 
        this.inputpage = loadImage("images/inputpage.png"); 
        this.instructionspage = loadImage("images/instructionspage.png"); 
        this.selectionpage = loadImage("images/selectionpage.png"); 

        obstacles = new ArrayList<Obstacle>();
        for (int i = 0; i < 5; i++) { // Create multiple obstacles
            obstacles.add(new Obstacle(this, random(3, 6), "images/obstacle.png"));
        }
    }

    public void draw() {
        //draw background
        background(255,0,0); 
        
        if (stage == 0){
            image(titlepage, 0, 0);//draw background
        }else if (stage == 1){
            image(inputpage, 0, 0);//draw background
            text(input,125, 200 );
        }else if (stage == 2){
            image (instructionspage, 0, 0);
        }else if (stage == 3){
            image (selectionpage, 0, 0);
            characters.get(i).draw();
            text("Character: " + characters.get(i).name, 200,350);
            
            text("Press LEFT/RIGHT to switch, ENTER to select", 150, 380);
            if (keyPressed && i >= 1) {
                if (keyCode == LEFT) {
                    i = (i > 0) ? i - 1 : characters.size() - 1;
                } else if (keyCode == RIGHT) {
                    i = (i < characters.size() - 1) ? i + 1 : 0;
                } else if (keyCode == ENTER) {
                    selecting = false; // Confirm selection
                    player = characters.get(i); // Assign selected character
                    stage = 4; // Move to the game stage
                }
            }
        }else if (stage == 4){
            
            image(river, 0, 0);//draw background
            player = characters.get(i);
            player.draw();
            
            if (keyPressed) {
                if (keyCode == LEFT) {
                    player.move(-5, 0);
                } else if (keyCode == RIGHT) {
                    player.move(5, 0);
                } 
            }
            

            for (Obstacle obs : obstacles) {
                obs.move();
                obs.display();

                if (obs.isCollidingWith(player)) {
                    fill(255, 0, 0);
                    text("Hit! Game Over.", 250, 50);
                    noLoop(); // Stop the game
                }
            }

            // Player only moves left and right
            if (keyPressed) {
                if (keyCode == LEFT) {
                    player.move(-5, 0);
                } else if (keyCode == RIGHT) {
                    player.move(5, 0);
                }
            }
            player.draw();

            if(showInfo){
                rat.displayInfo(this);
            }  
            drawCollisions();
        }
    }

    public void drawCollisions() {
        if (rat.isCollidingWith(ox)) {
            fill(255, 0, 0);
            this.text("Boost Found!", 250, 50);
        }
    }
    
    public void mousePressed(){
        if(stage == 2){
            if (rat.isClicked(mouseX, mouseY)){
                showInfo = !showInfo;
            }else{
                showInfo = false;
            }
        }
    }
    
    public void keyPressed(){
        if(stage < 3){
            if(keyCode == ENTER){
                stage++; //move to next stage
            }else if (key == BACKSPACE){
                input = input.substring(0, (input.length())-1);
            }else if (key!= CODED){
                input += key;
            }
        }

        if (stage == 3 && selecting) {
            if (keyCode == LEFT) {
                i = (i > 0) ? i - 1 : characters.size() - 1;
            } else if (keyCode == RIGHT) {
                i = (i < characters.size() - 1) ? i + 1 : 0;
            } else if (keyCode == ENTER) {
                selecting = false; // Confirm selection
                stage = 4; // Move to the game stage
            }
        }
    }
}
