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
    
    public Score(int points){
        this.points = points;
    }
    
    public Score(){
        this.points = 0;
    }
    
    public int getScore(){
        return points;
    }
    
    public void setScore(int points){
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }
}
