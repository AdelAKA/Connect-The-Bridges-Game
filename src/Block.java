/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.util.Pair;

/**
 *
 * @author ASUS
 */
public class Block {

    public BlockType BT;
    public Color color;
    public int blockID;
    public List<Coordinates> sells = new ArrayList<Coordinates>();

    public Block() {
    }

    public Block(BlockType Bt, int blockID, Color color) {
        this.BT = Bt;
        this.blockID = blockID;
        this.color = color;
    }

    public void CopyBlock(Block b) {
        b.BT = this.BT;
        b.color = this.color;
        b.blockID = this.blockID;
        for (int i = 0; i < sells.size(); i++) {
            b.sells.add(new Coordinates(this.sells.get(i).x, this.sells.get(i).y));
        }

    }

    public boolean canMove(Grid G, char dir) {
        for (int i = 0; i < sells.size(); i++) {
            int x = 0;
            int y = 0;
            if (dir == 'l') {
                x = (int) sells.get(i).x;
                y = (int) sells.get(i).y - 1;
            } else if (dir == 'r') {
                x = (int) sells.get(i).x;
                y = (int) sells.get(i).y + 1;
            } else if (dir == 'u') {
                x = (int) sells.get(i).x - 1;
                y = (int) sells.get(i).y;
            } else {
                x = (int) sells.get(i).x + 1;
                y = (int) sells.get(i).y;
            }
            if (x >= G.hight || x < 0 || y >= G.width || y < 0) {
                return false;
            }
            if (G.used(x, y) == true) {
                if (partOfBlock(x, y) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean moveBlock(Grid G, char dir) {
        switch (dir) {
            case 'l':
                if (canMove(G, dir)) {
                    moveSells(dir);
                    return true;

                }
                break;
            case 'r':
                if (canMove(G, dir)) {
                    moveSells(dir);
                    return true;
                }
                break;
            case 'u':
                if (canMove(G, dir)) {
                    moveSells(dir);
                    return true;
                }
                break;
            case 'd':
                if (canMove(G, dir)) {
                    moveSells(dir);
                    return true;
                }
                break;
        }
        return false;
    }

    private void moveSells(char dir) {
        switch (dir) {
            case 'l':
                for (int i = 0; i < sells.size(); i++) {
                    sells.get(i).y -= 1;
                }
                break;
            case 'r':
                for (int i = 0; i < sells.size(); i++) {
                    sells.get(i).y += 1;
                }
                break;
            case 'u':
                for (int i = 0; i < sells.size(); i++) {
                    sells.get(i).x -= 1;
                }
                break;
            case 'd':
                for (int i = 0; i < sells.size(); i++) {
                    sells.get(i).x += 1;
                }
                break;
        }
    }

    private boolean partOfBlock(int x, int y) {
        for (int i = 0; i < sells.size(); i++) {
            if ((int) sells.get(i).x == x && (int) sells.get(i).y == y) {
                return true;
            }
        }
        return false;
    }
}
