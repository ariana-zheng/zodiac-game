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
    public Player(String name, Score score){
        super(name);
        this.score = score;
    }
    public Player(){
        super();
        this.score = new Score(0);
    }
}
