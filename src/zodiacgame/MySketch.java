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
    private PImage river;  
    private PImage titlepage,inputpage, instructionspage, selectionpage; 
    String input = "";
    int stage = 0;
    int i = 0;
    private boolean showInfo = false;
    
    
    public void settings() {
        //sets the size of the window
        size(600, 400);
    }

    public void setup() {
        //sets the background colour using R,G,B (https://rgbcolorpicker.com/)
        background(100, 100, 100);
        textSize(15);//set text size

        //set background images
        this.river = loadImage("images/river.png"); 
        this.titlepage = loadImage("images/titlepage.png"); 
        this.inputpage = loadImage("images/inputpage.png"); 
        this.instructionspage = loadImage("images/instructionspage.png"); 
        this.selectionpage = loadImage("images/selectionpage.png"); 
        
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
            Character [] animals = {rat, ox, tiger, rabbit, dragon, snake, horse, lamb, monkey, chicken, dog, pig};
            
            
            animals[i].draw();
            if (keyPressed) {
                if (keyCode == LEFT) {
                    i--;
                    (animals[i]).draw();
                } else if (keyCode == RIGHT) {
                    i++;
                    (animals[i]).draw();
                }
            }
        }else if (stage == 4){
     
            image(river, 0, 0);//draw background
            
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
    }

}
