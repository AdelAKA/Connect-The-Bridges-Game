/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Move {
    public int blockID;
    public char direction;
    
    public Move(int blockID, char direction)
    {
        this.blockID = blockID;
        this.direction = direction;
    }
}
