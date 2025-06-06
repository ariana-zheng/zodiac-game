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

public class Character {

    public int x, y;
    private String name; // name of the person
    private int age; // age of the person
    private PApplet app;
    private PImage image;
    private int width, height;

    public Character(PApplet p, int x, int y, String name, int age, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void draw() {
        app.image(image, x, y);
    }

    public boolean isCollidingWith(Character other) {
        boolean isLeftOfOtherRight = x < other.x + other.width;
        boolean isRightOfOtherLeft = x + width > other.x;
        boolean isAboveOtherBottom = y < other.y + other.height;
        boolean isBelowOtherTop = y + height > other.y;

        return isLeftOfOtherRight && isRightOfOtherLeft && isAboveOtherBottom && isBelowOtherTop;
    }

    public boolean isClicked(int mouseX, int mouseY) {
        int centerX = x + (image.pixelWidth / 2);
        int centerY = y + (image.pixelHeight / 2);
        float d = PApplet.dist(mouseX, mouseY, centerX, centerY);

        System.out.println("image height" + image.pixelHeight);
        System.out.println("image width" + image.pixelWidth);

        return d < 16;
    }

    public void displayInfo(PApplet p) {
        app.fill(0);
        app.text("name: " + name, x, y - 50);
        app.text("Age: " + age, x, y - 30);
    }
}
