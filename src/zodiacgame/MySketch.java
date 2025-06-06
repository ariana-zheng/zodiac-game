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

public class MySketch extends PApplet {

    private Character user1, user2;
    private boolean showInfo = false;

    public void settings() {
        //sets the size of the window
        size(400, 400);
    }

    public void setup() {
        //sets the background colour using R,G,B (https://rgbcolorpicker.com/)
        background(100, 100, 100);
        textSize(20);
        user1 = new Person(this, 0, 0, "Ariana", 17, "images/person.png");
        user2 = new Person(this, 100, 200, "Noohn", 17, "images/person.png");

    }

    public void draw() {
        background(255);
        person2.draw();

        if (keyPressed) {
            if (keyCode == LEFT) {
                person1.move(-5, 0);
            } else if (keyCode == RIGHT) {
                person1.move(5, 0);
            } else if (keyCode == UP) {
                person1.move(0, -5);
            } else if (keyCode == DOWN) {
                person1.move(0, 5);
            }
        }
        person1.draw();
        drawCollisions();
    }

    public void drawCollisions() {
        if (person1.isCollidingWith(person2)) {
            fill(255, 0, 0);
            this.text("ouch", person2.x, person2.y);
        }
    }

}
