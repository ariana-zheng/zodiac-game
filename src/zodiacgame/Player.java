/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zodiacgame;

/**
 *
 * @author arian
 */
public class Player extends User{
    private Score score;
    /**
     * Constructor for player object.
     * @param name name of player
     * @param score score of player
     */
    public Player(String name, Score score){
        super(name);
        this.score = score;
    }
    /**
     * Default constructor for player object, sets to default value.
     */
    public Player(){
        super();
        this.score = new Score(0);
    }
    
    /**
     * Gets score value.
     * @return score
     */
    public Score getScore(){
        return this.score;
    }
}
