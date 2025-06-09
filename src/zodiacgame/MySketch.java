/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zodiacgame;



/**
 *
 * @author 342354727
 */
import processing.core.PApplet;
import processing.core.PImage;

public class MySketch extends PApplet {

    private Character rat, ox, tiger, rabbit, dragon, snake, horse, lamb, monkey, chicken, dog, pig;
    private PImage image;    
    

    public void settings() {
        //sets the size of the window
        size(600, 400);
    }

    public void setup() {
        //sets the background colour using R,G,B (https://rgbcolorpicker.com/)
        background(100, 100, 100);
        textSize(20);//set text size
        
        //initialize every character
        rat = new Character(this, 100, 100, "User", 0, "images/rat.png"); 
        ox = new Character(this, 200, 100, "User", 0, "images/ox.png"); 
        tiger = new Character(this, 300, 100, "User", 0, "images/tiger.png"); 
        rabbit = new Character(this, 400, 100, "User", 0, "images/rabbit.png"); 
        dragon = new Character(this, 100, 200, "User", 0, "images/dragon.png"); 
        snake = new Character(this, 200, 200, "User", 0, "images/snake.png"); 
        horse = new Character(this, 300, 200, "User", 0, "images/horse.png"); 
        lamb = new Character(this, 400, 200, "User", 0, "images/lamb.png"); 
        monkey = new Character(this, 100, 300, "User", 0, "images/monkey.png"); 
        chicken = new Character(this, 200, 300, "User", 0, "images/chicken.png"); 
        dog = new Character(this, 300, 300, "User", 0, "images/dog.png"); 
        pig = new Character(this, 400, 300, "User", 0, "images/pig.png"); 
        
        this.image = loadImage("images/river.png"); //sets background image to river

    }

    public void draw() {
        //draw background
        image(image, 0, 0);
       
        //draw background
       // background(255); 
        
        //draw every character
        rat.draw();
        ox.draw();
        tiger.draw();
        rabbit.draw();
        dragon.draw();
        snake.draw();
        horse.draw();
        lamb.draw();
        monkey.draw();
        chicken.draw();
        dog.draw();
        pig.draw();
        
        

        if (keyPressed) {
            if (keyCode == LEFT) {
                rat.move(-5, 0);
            } else if (keyCode == RIGHT) {
                rat.move(5, 0);
            } else if (keyCode == UP) {
                rat.move(0, -5);
            } else if (keyCode == DOWN) {
                rat.move(0, 5);
            }
        }
   
        drawCollisions();
    }

    public void drawCollisions() {
        if (rat.isCollidingWith(ox)) {
            fill(255, 0, 0);
            this.text("Boost Found!", 250, 50);
        }
    }

}
