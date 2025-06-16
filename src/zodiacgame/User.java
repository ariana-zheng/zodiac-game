/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zodiacgame;

/**
 *
 * @author arian
 */
public class User {
    public String name;
    private static int total_users;
    
    public User(String age){
        this.name = name;
        total_users++;
    }
    
    public User(){
        this.name = "No Name";
        total_users++;
    }

    public String getName() {
         return name;
    }
    
    public void setName(String name) {
         this.name = name;
    }
    
    public static int getTotalUsers(){
        return total_users;
    }
}
