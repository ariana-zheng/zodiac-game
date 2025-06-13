/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zodiacgame;

import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author arian
 */

public class Obstacle {
    PApplet p;
    float x, y, speed;
    PImage obstacleImg;

    Obstacle(PApplet p, float x, String imgPath) {
        this.p = p;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.obstacleImg = p.loadImage(imgPath);
    }

    void move() {
        y += speed;  // Moves obstacle downward
        if (y > p.height) {
            y = 0;  // Reset position when it reaches the bottom
            x = p.random(50, p.width - 50);  // Randomize position
        }
    }

    void display() {
        p.image(obstacleImg, x, y);
    }

    boolean isCollidingWith(Character player) {
        return p.dist(x, y, player.x, player.y) < 30;
    }
}

