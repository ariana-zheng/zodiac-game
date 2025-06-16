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

public class Character extends User {

    public int x, y;
    private String animal;
    private PApplet app;
    public PImage image;
    public int height,width;
    private String description;
    
    /**
     * Constructor for the Character class that initializes a new character with its properties and loads its image.
     * @param p The PApplet instance, necessary for drawing and image loading.
     * @param x The initial X coordinate of the character (center).
     * @param y The initial Y coordinate of the character (center).
     * @param name The name of the user controlling this character (inherited from User).
     * @param animal The animal type of this character.
     * @param imagePath The file path to the character's image.
     */
    public Character(PApplet p, int x, int y, String name, String animal, String imagePath, String description) {
        super(name);
        this.app = p;
        this.x = x;
        this.y = y;
        this.animal = animal;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
        this.description = description;
    }
    /**
     * Constructor for the Character class that initializes a new character with default properties
     * */
    public Character(){
        super("John Doe");
        this.x = 0;
        this.y = 0;
        this.animal = "No Animal";
        this.image = app.loadImage("rat");
        this.width = 50;
        this.height = 50;
    }
    /**
     * Moves the character by the specified values
     * @param dx The amount to move horizontally (positive for right, negative for left).
     * @param dy The amount to move vertically (positive for down, negative for up).
     */
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    /**
     * Draws the character's image on the screen at its current position.
     * The image is drawn centered around its (x, y) coordinates.
     */
    public void draw() {
        app.image(image, x, y);
    }
    /**
     * Checks for collision between this character and another Character object using rectangle collision
     * @param other The other Character object to check for collision against.
     * @return true if collision is detected, false otherwise.
     */
    public boolean isCollidingWith(Character other) {
        boolean isLeftOfOtherRight = x < other.x + other.width;
        boolean isRightOfOtherLeft = x + width > other.x;
        boolean isAboveOtherBottom = y < other.y + other.height;
        boolean isBelowOtherTop = y + height > other.y;

        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }
    /**
     * Checks if a given mouse click collides with character.
     * @param mouseX The X coordinate of the mouse click.
     * @param mouseY The Y coordinate of the mouse click.
     * @return true if the mouse click is within the character's bounds, false otherwise.
     */
    public boolean isClicked(int mouseX, int mouseY) {
        int centerX = x + (image.pixelWidth / 2);
        int centerY = y + (image.pixelHeight / 2);
        float d = PApplet.dist(mouseX, mouseY, centerX, centerY);
        return d < 16;
    }
    /**
     * Returns description for animal.
     * @return description message.
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Returns animal type.
     * @return animal
     */
    public String getAnimal() {
        return this.animal;
    }
     /**
     * Displays the character's info on screen (the formatting was researched)
     * This method assumes the x, y of the character object have been set for display purposes.
     * @param p The PApplet.
     */
    public void displayInfo(PApplet p) {
        p.text("Animal: " + animal, 100, 100);
        p.text("Name: " + super.getName(),100, 150);
        p.text("Story: " + this.description, 100, 200, p.width - 200, 150);
    }
}
