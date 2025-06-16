/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zodiacgame;

/**
 *
 * @author 342354727
 */
public class Score {
    private int points;
    /**
     * Constructor for score object
     * @param points the number of points calculated
     */
    public Score(int points){
        this.points = points;
    }
    /**
     * Default constructor, sets default values
     */
    public Score(){
        this.points = 0;
    }
    /**
     * Access the points value.
     * @return number of points scored
     */
    public int getScore(){
        return points;
    }
    /**
     * Setting the score value
     * @param points new number of points
     */
    public void setScore(int points){
        this.points = points;
    }
}
