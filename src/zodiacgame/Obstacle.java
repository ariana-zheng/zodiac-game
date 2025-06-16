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
    private PApplet app; // Reference to the PApplet instance for drawing and random functions
    public float x, y; // Position of the obstacle (center of the image)
    private float speed; // Vertical speed at which the obstacle moves downwards
    private PImage image; // The PImage object for this obstacle's visual representation
    public int width, height; // Dimensions of the obstacle's image

    /**
     * Constructor for the Obstacle class.
     * Initializes a new obstacle with its speed and image, then sets its initial position.
     *
     * @param p The PApplet instance.
     * @param speed The vertical movement speed of the obstacle.
     * @param imagePath The file path to the obstacle's image.
     */
    public Obstacle(PApplet p, float speed, String imagePath) {
        this.app = p;
        this.speed = speed;
        this.image = app.loadImage(imagePath); // Load the obstacle's image
        // Set the obstacle's dimensions based on the loaded image
        this.width = this.image.width;
        this.height = this.image.height;
        reset(); // Call reset to set initial random position
    }

    /**
     * Resets the obstacle's position to a random X coordinate at the top of the screen
     * (just above the visible area). This is used for initial placement and recycling.
     */
    public void reset() {
        // Random X position, ensuring the whole obstacle is within screen bounds
        x = app.random(width / 2, app.width - width / 2);
        y = -height / 2; // Start just above the screen
    }

    /**
     * Moves the obstacle downwards by its speed.
     */
    public void move() {
        y += speed;
    }

    /**
     * Displays (draws) the obstacle's image on the screen at its current position.
     * The image is drawn centered around its (x, y) coordinates.
     */
    public void display() {
        app.image(image, x - width / 2, y - height / 2); // Draw image centered
    }

    /**
     * Checks if the obstacle has moved completely off-screen (below the bottom edge).
     *
     * @return true if the obstacle is off-screen, false otherwise.
     */
    public boolean isOffscreen() {
        return y > app.height + height / 2;
    }

    /**
     * Checks for collision between this obstacle and a Character object (the player).
     * This uses Axis-Aligned Bounding Box (AABB) collision detection.
     *
     * @param player The Character object (player) to check for collision against.
     * @return true if collision is detected, false otherwise.
     */
    public boolean isCollidingWith(Character player) {
        boolean isLeftOfOtherRight = x < player.x + player.width;
        boolean isRightOfOtherLeft = x + width > player.x;
        boolean isAboveOtherBottom = y < player.y + player.height;
        boolean isBelowOtherTop = y + height > player.y;

        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }
}
