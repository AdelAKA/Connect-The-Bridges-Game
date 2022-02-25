/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import javafx.scene.paint.Color;

/**
 *
 * @author ASUS
 */
public class Grid implements Comparator<Grid> {

    public int hight;
    public int width;
    public ArrayList<Block> blocks;
    public List<Integer[]> sells = new ArrayList<>();
    public List<Boolean[]> visited = new ArrayList<>();
    public ArrayList<Move> moves = new ArrayList<>();
    public int forI[] = {0, 0, 1, -1};
    public int forJ[] = {1, -1, 0, 0};
    public boolean winStatment = false;
    public int pressedBlockID = -1;
    public int Gcost = 0;
    public int Hcost = 0;
    public int Fcost = 0;
    public Grid parent = null;

    @Override
    public int compare(Grid o1, Grid o2) {
        if (o1.Fcost > o2.Fcost) {
            return 1;
        } else if (o1.Fcost < o2.Fcost) {
            return -1;
        }
        return 0;
    }

    public Grid(String gridSize) {
        super();
        Block b1 = new Block();
        System.out.println(gridSize);
        switch (gridSize) {
            case "16-3":
                blocks = new ArrayList<Block>();
                hight = 16;
                width = 3;
                makeAllNothing();
                checkUnVisited();
                b1 = new Block(BlockType.Rock, 0, Color.GRAY);
                b1.sells.add(new Coordinates(0, 0));
                b1.sells.add(new Coordinates(0, 1));
                b1.sells.add(new Coordinates(1, 0));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Rock, 1, Color.GRAY);
                b1.sells.add(new Coordinates(15, 0));
                b1.sells.add(new Coordinates(14, 0));
                b1.sells.add(new Coordinates(13, 0));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Grass, 2, Color.GREEN);
                b1.sells.add(new Coordinates(3, 1));
                b1.sells.add(new Coordinates(4, 1));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Grass, 3, Color.GREEN);
                b1.sells.add(new Coordinates(5, 1));
                b1.sells.add(new Coordinates(6, 1));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Bridge, 4, Color.RED);
                b1.sells.add(new Coordinates(0, 2));
                b1.sells.add(new Coordinates(1, 2));
                b1.sells.add(new Coordinates(2, 2));
                b1.sells.add(new Coordinates(3, 2));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Bridge, 5, Color.RED);
                b1.sells.add(new Coordinates(6, 2));
                b1.sells.add(new Coordinates(7, 2));
                b1.sells.add(new Coordinates(8, 2));
                b1.sells.add(new Coordinates(9, 2));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Bridge, 6, Color.RED);
                b1.sells.add(new Coordinates(3, 0));
                b1.sells.add(new Coordinates(4, 0));
                b1.sells.add(new Coordinates(5, 0));
                b1.sells.add(new Coordinates(6, 0));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Bridge, 7, Color.RED);
                b1.sells.add(new Coordinates(9, 0));
                b1.sells.add(new Coordinates(10, 0));
                b1.sells.add(new Coordinates(11, 0));
                b1.sells.add(new Coordinates(12, 0));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Bridge, 8, Color.RED);
                b1.sells.add(new Coordinates(15, 2));
                b1.sells.add(new Coordinates(14, 2));
                b1.sells.add(new Coordinates(13, 2));
                b1.sells.add(new Coordinates(12, 2));
                blocks.add(b1);
                addSells(b1);

                //sells.get(1)[12] = 8;
                //sells.get(1)[9] = 8;
                //sells.get(1)[6] = 8;
                //sells.get(1)[3] = 8;
                break;

            case "4-3":
                blocks = new ArrayList<Block>();
                hight = 4;
                width = 3;
                makeAllNothing();
                checkUnVisited();
                b1 = new Block(BlockType.Grass, 0, Color.GREEN);
                b1.sells.add(new Coordinates(1, 1));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Grass, 1, Color.GREEN);
                b1.sells.add(new Coordinates(2, 1));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Bridge, 2, Color.RED);
                b1.sells.add(new Coordinates(1, 0));
                b1.sells.add(new Coordinates(2, 0));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Bridge, 3, Color.RED);
                b1.sells.add(new Coordinates(0, 2));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Bridge, 4, Color.RED);
                b1.sells.add(new Coordinates(3, 2));
                blocks.add(b1);
                addSells(b1);
                //sells.get(1)[12] = 8;
                //sells.get(1)[9] = 8;
                //sells.get(1)[6] = 8;
                //sells.get(1)[3] = 8;
                break;
            case "2-3":
                blocks = new ArrayList<Block>();
                hight = 2;
                width = 3;
                makeAllNothing();
                checkUnVisited();
                b1 = new Block(BlockType.Bridge, 0, Color.RED);
                b1.sells.add(new Coordinates(1, 1));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Bridge, 1, Color.RED);
                b1.sells.add(new Coordinates(0, 0));
                blocks.add(b1);
                addSells(b1);
                //sells.get(1)[12] = 8;
                //sells.get(1)[9] = 8;
                //sells.get(1)[6] = 8;
                //sells.get(1)[3] = 8;
                break;
            case "3-3":
                blocks = new ArrayList<Block>();
                hight = 3;
                width = 3;
                makeAllNothing();
                checkUnVisited();
                b1 = new Block(BlockType.Bridge, 0, Color.RED);
                b1.sells.add(new Coordinates(1, 1));
                b1.sells.add(new Coordinates(0, 1));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Bridge, 1, Color.RED);
                b1.sells.add(new Coordinates(0, 0));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Grass, 2, Color.GREEN);
                b1.sells.add(new Coordinates(1, 0));
                blocks.add(b1);
                addSells(b1);

                b1 = new Block(BlockType.Grass, 3, Color.GREEN);
                b1.sells.add(new Coordinates(2, 0));
                blocks.add(b1);
                addSells(b1);
                //sells.get(1)[12] = 8;
                //sells.get(1)[9] = 8;
                //sells.get(1)[6] = 8;
                //sells.get(1)[3] = 8;
                break;
        }
    }

    public Grid() {
        super();
        hight = 0;
        width = 0;
        blocks = new ArrayList<>();
        sells = new ArrayList<>();
        visited = new ArrayList<>();
        moves = new ArrayList<>();
        Fcost = 0;

    }

    /*private static Object cloneObject(Object obj){
        try{
            Object clone = obj.getClass().newInstance();
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                field.set(clone, field.get(obj));
            }
            return clone;
        }catch(Exception e){
            return null;
        }
    }*/
    public void CopyArray(Grid copy) {
        for (Block b : blocks) {
            Block tempBlock = new Block();
            b.CopyBlock(tempBlock);
            copy.blocks.add(tempBlock);
        }
        for (Block b : copy.blocks) {
            for (Coordinates c : b.sells) {
                //System.out.print(c.x + "|" + c.y + ",");
            }
            //System.out.println();
        }
        for (Integer[] i : sells) {
            Integer[] tempInt = new Integer[this.width];
            for (int j = 0; j < this.width; j++) {
                tempInt[j] = i[j];
            }
            copy.sells.add(tempInt);
        }
        for (Integer[] i : copy.sells) {
            for (Integer ii : i) {
                //System.out.print(ii + ", ");
            }
        }
        copy.checkUnVisited();
        for (Move m : moves) {
            copy.moves.add(new Move(m.blockID, m.direction));
        }

    }

    public void CopyGrid(Grid copy) {
        copy.hight = this.hight;
        copy.width = this.width;

        copy.blocks = new ArrayList<>();
        copy.sells = new ArrayList<>();
        copy.visited = new ArrayList<>();
        copy.moves = new ArrayList<>();

        CopyArray(copy);

        copy.pressedBlockID = this.pressedBlockID;
        copy.Fcost = this.Fcost;

    }

    public void addSells(Block block) {
        for (Coordinates cor : block.sells) {
            sells.get(cor.x)[cor.y] = block.blockID;
        }
    }

    public void makeAllNothing() {
        for (int i = 0; i < hight; i++) {
            Integer[] arr = new Integer[width];
            for (int j = 0; j < width; j++) {
                arr[j] = new Integer(-1);
            }
            sells.add(arr);
        }
    }

    public void checkUnVisited() {
        for (int i = 0; i < hight; i++) {
            Boolean[] arr = new Boolean[width];
            for (int j = 0; j < width; j++) {
                arr[j] = new Boolean(false);
            }
            visited.add(arr);
        }
    }

    public void makeUnVisited() {
        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < width; j++) {
                visited.get(i)[j] = false;
            }
        }
    }

    public void dfsForWin(int x, int y) {
        if (visited.get(x)[y] == true) {
            return;
        }

        if (x == 0) {
            winStatment = true;
        }

        visited.get(x)[y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + forI[i];
            int newY = y + forJ[i];
            if (newX >= hight || newX < 0 || newY >= width || newY < 0) {
                continue;
            }
            int curr = sells.get(newX)[newY];
            if (curr != -1) {
                if (blocks.get(curr).BT == BlockType.Bridge) {
                    dfsForWin(newX, newY);
                }
            }
        }
    }

    public boolean checkForSol() {
        for (int i = 0; i < width; i++) {
            int curr = sells.get(hight - 1)[i];
            if (curr != -1) {
                if (blocks.get(curr).BT == BlockType.Bridge) {
                    dfsForWin(hight - 1, i);
                }
            }
        }
        makeUnVisited();
        if (winStatment == true) {
            //System.out.println("congrats you won .............................. ");
            for (Move m : moves) {
                System.out.print("(" + m.blockID + ", " + m.direction + ") ");
            }
            return true;
        }
        //System.out.println("no win");

        return false;
    }

    public void makeAMove(char dir) {
        if (pressedBlockID != -1) {
            if (blocks.get(pressedBlockID).moveBlock(this, dir) && blocks.get(pressedBlockID).BT != BlockType.Rock) {
                moves.add(new Move(pressedBlockID, dir));
                if (dir == 'l') {
                    for (Coordinates c : blocks.get(pressedBlockID).sells) {
                        sells.get(c.x)[c.y + 1] = -1;
                    }
                    for (Coordinates c : blocks.get(pressedBlockID).sells) {
                        sells.get(c.x)[c.y] = pressedBlockID;
                    }

                } else if (dir == 'r') {
                    for (Coordinates c : blocks.get(pressedBlockID).sells) {
                        sells.get(c.x)[c.y - 1] = -1;
                    }
                    for (Coordinates c : blocks.get(pressedBlockID).sells) {
                        sells.get(c.x)[c.y] = pressedBlockID;
                    }

                } else if (dir == 'u') {
                    for (Coordinates c : blocks.get(pressedBlockID).sells) {
                        sells.get(c.x + 1)[c.y] = -1;
                    }
                    for (Coordinates c : blocks.get(pressedBlockID).sells) {
                        sells.get(c.x)[c.y] = pressedBlockID;
                    }

                } else if (dir == 'd') {
                    for (Coordinates c : blocks.get(pressedBlockID).sells) {
                        sells.get(c.x - 1)[c.y] = -1;
                    }
                    for (Coordinates c : blocks.get(pressedBlockID).sells) {
                        System.out.println(c.x + ", " + c.y);
                        sells.get(c.x)[c.y] = pressedBlockID;
                    }

                }
            }
        }
    }

    public void printGrid() {
        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < width; j++) {
                if (sells.get(i)[j] == -1) {
                    System.out.print("." + " ");
                } else {
                    System.out.print(sells.get(i)[j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");
    }

    public void checkAvailableMoves() {
        for (Block b : blocks) {
            if (b.BT == BlockType.Grass || b.BT == BlockType.Bridge) {
                if (b.canMove(this, 'l')) {
                    System.out.print("[" + b.BT + " " + b.blockID + " L] ");
                }
                if (b.canMove(this, 'r')) {
                    System.out.print("[" + b.BT + " " + b.blockID + " R] ");
                }
                if (b.canMove(this, 'u')) {
                    System.out.print("[" + b.BT + " " + b.blockID + " U] ");
                }
                if (b.canMove(this, 'd')) {
                    System.out.print("[" + b.BT + " " + b.blockID + " D] ");
                }
                System.out.println();
            }
        }
        System.out.println("---------------------------------------------");
    }

    public boolean used(int x, int y) {
        if (sells.get(x)[y] != -1) {
            return true;
        }
        return false;
    }

    public boolean CompareTwo(Grid gg) {
        for (int i = 0; i < hight; i++) {
            //Integer[] arr = new Integer[hight];
            for (int j = 0; j < width; j++) {
                if (!gg.sells.get(i)[j].equals(this.sells.get(i)[j])) {
                    //System.out.println("compare" + i + ", " + j);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean IsVisited(ArrayList<Grid> grids) {
        for (Grid g : grids) {
            if (CompareTwo(g) == true) {
                return true;
            }
        }
        return false;
    }

    public Grid IsVisited2(ArrayList<Grid> grids) {
        for (Grid g : grids) {
            if (CompareTwo(g) == true) {
                if (this.Fcost < g.Fcost) {
                    return g;
                } else {
                    return null;
                }
            }
        }
        return this;
    }

    public Grid makeTempMove(Move dir) {
        Grid g = new Grid("new");
        CopyGrid(g);
        if (dir.blockID != -1) {
            if (g.blocks.get(dir.blockID).moveBlock(this, dir.direction) && g.blocks.get(dir.blockID).BT != BlockType.Rock) {
                if (dir.direction == 'l') {
                    for (Coordinates c : g.blocks.get(dir.blockID).sells) {
                        g.sells.get(c.x)[c.y + 1] = -1;
                    }
                    for (Coordinates c : g.blocks.get(dir.blockID).sells) {
                        g.sells.get(c.x)[c.y] = dir.blockID;
                    }

                } else if (dir.direction == 'r') {
                    for (Coordinates c : g.blocks.get(dir.blockID).sells) {
                        g.sells.get(c.x)[c.y - 1] = -1;
                    }
                    for (Coordinates c : g.blocks.get(dir.blockID).sells) {
                        g.sells.get(c.x)[c.y] = dir.blockID;
                    }

                } else if (dir.direction == 'u') {
                    for (Coordinates c : g.blocks.get(dir.blockID).sells) {
                        g.sells.get(c.x + 1)[c.y] = -1;
                    }
                    for (Coordinates c : g.blocks.get(dir.blockID).sells) {
                        g.sells.get(c.x)[c.y] = dir.blockID;
                    }

                } else if (dir.direction == 'd') {
                    for (Coordinates c : g.blocks.get(dir.blockID).sells) {
                        g.sells.get(c.x - 1)[c.y] = -1;
                    }
                    for (Coordinates c : g.blocks.get(dir.blockID).sells) {
                        g.sells.get(c.x)[c.y] = dir.blockID;
                    }

                }
            }
        }
        return g;
    }

    public ArrayList<Move> checkMovesForChildren() {
        ArrayList<Move> children = new ArrayList<>();

        for (Block b : blocks) {
            if (b.BT == BlockType.Grass || b.BT == BlockType.Bridge) {
                if (b.canMove(this, 'l')) {
                    children.add(new Move(b.blockID, 'l'));
                }
                if (b.canMove(this, 'r')) {
                    children.add(new Move(b.blockID, 'r'));
                }
                if (b.canMove(this, 'u')) {
                    children.add(new Move(b.blockID, 'u'));
                }
                if (b.canMove(this, 'd')) {
                    children.add(new Move(b.blockID, 'd'));
                }
            }
        }
        return children;
    }

    public ArrayList<Grid> GetChildren() {
        ArrayList<Grid> children = new ArrayList<>();
        for (Move m : checkMovesForChildren()) {
            //System.out.print("[" + m.blockID + "|" + m.direction + "]");
            children.add(makeTempMove(m));
        }
        return children;
    }

    public int giveValue() {
        int addCost = 0;
        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < width; j++) {
                int id = sells.get(i)[j];
                if (id == -1) {
                    continue;
                }
                if (blocks.get(id).BT == BlockType.Bridge) {
                    break;
                }
            }
            addCost += 1;
        }

        int[] count = new int[width];
        for (int i = 0; i < width; i++) {
            count[i] = 0;
        }
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < hight; j++) {
                int id = sells.get(j)[i];
                if(id == -1)continue;
                if (blocks.get(id).BT == BlockType.Bridge) {
                    count[i]++;
                }
            }
        }
        
        int maxCol = 0;
        int colID = 0;
        for (int i = 0; i < width; i++) {
            if(count[i] > maxCol)
            {
                maxCol = count[i];
                colID = i;
            }
        }
        
        for (int i = 0; i < hight; i++) {
            int id = sells.get(i)[colID];
            if(id == -1)continue;
            if(blocks.get(id).BT == BlockType.Rock)
            {
                addCost += 20;
            }
        }
        
        return addCost;
    }

    public ArrayList<Grid> GetChildren2() {
        ArrayList<Grid> children = new ArrayList<>();
        for (Move m : checkMovesForChildren()) {
            //System.out.print("[" + m.blockID + "|" + m.direction + "]");
            Grid temp = makeTempMove(m);
            temp.Gcost = Fcost;
            temp.Hcost = temp.giveValue();
            temp.Fcost = temp.Gcost + temp.Hcost;
            children.add(temp);
        }
        return children;
    }

    /*public boolean BFS() throws CloneNotSupportedException {
        ArrayList<Grid> vis = new ArrayList<>();
        Queue<Grid> q = new LinkedList<>();
        Grid tempGrid = new Grid("new");
        CopyGrid(tempGrid);
        
        q.add(tempGrid);

        vis.add(tempGrid);
  
        int count = 0;
        //printGrid();
        while (q.isEmpty() == false) {
            
            Grid temp = q.remove();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < hight; j++) {
                    //System.out.print(temp.sells.get(i)[j] + "|" + ", ");
                }
            }
            //temp.checkAvailableMoves();
            if (temp.checkForSol()) {
                System.out.println("number of moves:" + count);
                return true;
            }
            //temp.checkAvailableMoves();
            ArrayList<Grid> lol = temp.GetChildren();
            for (Grid c : lol) {
                if (!c.IsVisited(vis)) {
                    count++;
                    System.out.println("step number :" + count);
                    //c.printGrid();
                    vis.add(c);
                    q.add(c);
                }
            }
            //count = 0;
        }
        System.out.println("can't solve:" + count);
        return false;
    }*/
    public boolean BFS() throws CloneNotSupportedException {
        ArrayList<Grid> vis = new ArrayList<>();
        ArrayList<Grid> wins = new ArrayList<>();
        Stack<Grid> q = new Stack<>();
        Grid tempGrid = new Grid("new");
        CopyGrid(tempGrid);

        q.add(tempGrid);
        vis.add(tempGrid);

        //printGrid();
        int count = 0;
        //printGrid();
        while (q.isEmpty() == false) {

            Grid temp = q.pop();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < hight; j++) {
                    //System.out.print(temp.sells.get(i)[j] + "|" + ", ");
                }
            }
            //temp.checkAvailableMoves();
            if (temp.checkForSol()) {
                System.out.println("number of moves:" + count);
                wins.add(temp);
                return true;
                //continue;
            }
            //temp.checkAvailableMoves();
            temp.printGrid();
            count++;
            System.out.println("step number :" + count);
            ArrayList<Grid> lol = temp.GetChildren();
            for (Grid c : lol) {
                if (!c.IsVisited(vis)) {
                    //c.printGrid();
                    vis.add(c);
                    q.add(c);
                }
            }
            //count = 0;
        }
        System.out.println("can't solve:" + count);
        System.out.println("number of solves " + wins.size());
        return false;
    }

    public void searchAndEdit(ArrayList<Grid> gg, Grid compare) {
        for (int i = 0; i < gg.size(); i++) {
            if (compare.CompareTwo(gg.get(i))) {
                gg.get(i).Fcost = compare.Fcost;
                return;
            }
        }
    }

    public void generateRoad(Grid road)
    {
        road.printGrid();
        //if(road.parent == null) return;
        generateRoad(road.parent);
        return;
        
    }
    
    public boolean UniForm() throws CloneNotSupportedException {
        ArrayList<Grid> vis = new ArrayList<>();
        ArrayList<Grid> wins = new ArrayList<>();
        PriorityQueue<Grid> q = new PriorityQueue<>(new Grid());
        Grid tempGrid = new Grid("new");
        CopyGrid(tempGrid);

        q.add(tempGrid);
        vis.add(tempGrid);

        int count = 0;
        while (q.isEmpty() == false) {
            Grid temp = q.remove();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < hight; j++) {
                    //System.out.print(temp.sells.get(i)[j] + "|" + ", ");
                }
            }
            //System.out.println("number of moves:" + count);
            //temp.checkAvailableMoves();
            if (temp.checkForSol()) {
                //System.out.println("number of moves:" + count);
                wins.add(temp);
                temp.printGrid();
                temp.generateRoad(temp.parent);
                System.out.println("puzzelgame.Grid.UniForm()");
                //temp.printGrid();
                //continue;
                return true;
            }
            //temp.checkAvailableMoves();
            //System.out.println("cost:" + temp.Fcost);
            //temp.printGrid();
            
            ArrayList<Grid> lol = temp.GetChildren2();
            count++;
            for (Grid c : lol) {
                c.parent = new Grid("new");
                temp.CopyGrid(c.parent);
                Grid tempor = c.IsVisited2(vis);
                if (tempor == null) {
                    //found better wight
                } else if (tempor.Fcost == c.Fcost) //didn't found anythig to compare
                {
                    vis.add(c);
                    q.add(c);
                } else if (tempor.Fcost != c.Fcost)//this is the better wight
                {
                    searchAndEdit(vis, c);
                }
            }
        }
        System.out.println("can't solve:" + count);

        System.out.println("number of solves " + wins.size());
        return false;
    }
}
