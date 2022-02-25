/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author ASUS
 */
public class GUI extends Application {

    Stage window;
    int h, w;
    Grid G = new Grid();
    KeyPressing press;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = new Stage();

        window.setTitle("This is the game window");

        askInfo();
        MakeGameGrid();
        //G.BFS();

        window.show();

    }

    public void ChekKeyAndMove(char k) {
        if (k == 'a') {
            //System.out.println("pressedLeft");
            G.makeAMove('l');
            update();
        } else if (k == 'd') {
            //System.out.println("pressedRight");
            G.makeAMove('r');
            update();
        } else if (k == 'w') {
            //System.out.println("pressedUp");
            G.makeAMove('u');
            update();
        } else if (k == 's') {
            //System.out.println("pressedDown");
            G.makeAMove('d');
            update();
        }
    }

    public void askInfo() {
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(5, 5, 5, 5));
        gp.setVgap(5);
        gp.setHgap(10);
        gp.setAlignment(Pos.CENTER);

        Label sizeLab = new Label("Choose Grid Size:");
        GridPane.setConstraints(sizeLab, 0, 0);

        ComboBox comboBox = new ComboBox();
        comboBox.setPromptText("16-3");
        comboBox.setValue("16-3");
        comboBox.getItems().addAll(
                "16-3",
                "4-3",
                "2-3",
                "3-3"
        );
        GridPane.setConstraints(comboBox, 1, 0);

        Button bot = new Button("Submit");
        GridPane.setConstraints(bot, 1, 1);

        gp.getChildren().addAll(sizeLab, comboBox, bot);

        bot.setOnAction(e -> {
            getInfoAndFlipScene((String) comboBox.getValue());
            window.close();
            return;
        });

        Scene scene1 = new Scene(gp, 300, 250);
        window.setScene(scene1);
        window.showAndWait();
    }

    public void getInfoAndFlipScene(String size) {
        G = new Grid(size);
        return;
    }

    transient Rectangle[][] rect;
    transient Text[][] names;

    public void MakeGameGrid() {
        h = G.hight;
        w = G.width;
        rect = new Rectangle[h][w];
        names = new Text[h][w];
        
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);

        root.setVgap(2);
        root.setHgap(2);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                rect[i][j] = new Rectangle(33, 33);
                int curr = G.sells.get(i)[j];
                if (curr == -1) {
                    rect[i][j].setFill(Color.WHITE);
                } else {
                    Color temp = G.blocks.get(curr).color;
                    if (G.blocks.get(curr).blockID == G.pressedBlockID) {
                        temp = temp.darker();
                    }
                    rect[i][j].setFill(temp);
                }
                //rect[i][j].setArcWidth(50 / 3);
                //rect[i][j].setArcHeight(50 / 3);
                //rect[i][j].setStroke(Color.WHITE);
                //rect[i][j].setStrokeWidth(4.5);
                rect[i][j].setX(i);
                rect[i][j].setY(j);
                //rect[i][j].setEffect(b);

                if (G.sells.get(i)[j] != -1) {
                    names[i][j] = new Text(G.sells.get(i)[j].toString());
                } else {
                    names[i][j] = new Text("");
                }

                names[i][j].setFill(Color.BLACK);
                names[i][j].setFont(Font.font(null, FontWeight.SEMI_BOLD, 33 / 1.7));
                //N[i][j].setVisible(false);
                //names[i][j].setX(50 / 2.6);
                names[i][j].setTranslateX(33 / 2 - 33 / 8);
                //names[i][j].setTranslateY(50 / -26);

                root.add(rect[i][j], j, i);
                root.add(names[i][j], j, i);
            }
        }
        setOnSomething();
        Scene scene = new Scene(root, 600, 600);

        scene.setOnMousePressed((MouseEvent e) -> {
            if ("PRIMARY".equals(e.getButton().toString())) {
                
            }
            else
            {
                try {
                    //G.BFS();
                    G.UniForm();
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        ChekKeyAndMove('w');
                        break;
                    case DOWN:
                        ChekKeyAndMove('s');
                        break;
                    case LEFT:
                        ChekKeyAndMove('a');
                        break;
                    case RIGHT:
                        ChekKeyAndMove('d');
                        break;
                }
            }
        });

        G.checkAvailableMoves();

        window.setScene(scene);
        window.show();
    }

    void update() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                //rect[i][j] = new Rectangle(33, 33);
                int curr = G.sells.get(i)[j];
                if (curr == -1) {
                    rect[i][j].setFill(Color.WHITE);
                } else {
                    Color temp = G.blocks.get(curr).color;
                    if (G.blocks.get(curr).blockID == G.pressedBlockID) {
                        temp = temp.darker();
                    }
                    rect[i][j].setFill(temp);
                }
                //rect[i][j].setArcWidth(50 / 3);
                //rect[i][j].setArcHeight(50 / 3);
                //rect[i][j].setStroke(Color.WHITE);
                //rect[i][j].setStrokeWidth(4.5);
                rect[i][j].setX(i);
                rect[i][j].setY(j);
                //rect[i][j].setEffect(b);

                if (G.sells.get(i)[j] != -1) {
                    names[i][j].setText(G.sells.get(i)[j].toString());
                } else {
                    names[i][j].setText("");
                }
                names[i][j].setFill(Color.BLACK);
                names[i][j].setFont(Font.font(null, FontWeight.SEMI_BOLD, 33 / 1.7));
                //N[i][j].setVisible(false);
                //names[i][j].setX(50 / 2.6);
                names[i][j].setTranslateX(33 / 2 - 33 / 8);
                //names[i][j].setTranslateY(50 / -26);
            }
        }
        G.checkAvailableMoves();
        if (G.checkForSol()) {
            confirmWin.display();
            window.close();
        }
    }

    void setOnSomething() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                final int h = i, hh = j;
                rect[i][j].setOnMouseClicked(e -> {
                    int ID = G.sells.get(h)[hh];
                    if (ID != -1) {
                        //if (G.blocks.get(ID).BT == BlockType.Bridge || G.blocks.get(ID).BT == BlockType.Grass) {
                        G.pressedBlockID = ID;
                        update();
                        //System.out.println("the pressed Block " + ID);
                        //}
                    }
                    if (G.checkForSol()) {
                        confirmWin.display();
                        window.close();
                    }
                });
                names[i][j].setOnMouseClicked(e -> {
                    int ID = G.sells.get(h)[hh];
                    if (ID != -1) {
                        //if (G.blocks.get(ID).BT == BlockType.Bridge || G.blocks.get(ID).BT == BlockType.Grass) {
                        G.pressedBlockID = ID;
                        update();
                        //System.out.println("the pressed Block " + ID);
                        //}
                    }
                    if (G.checkForSol()) {
                        confirmWin.display();
                        window.close();
                    }
                });
                rect[i][j].setOnMouseEntered(e -> {
                    rect[h][hh].setFill(Color.LIGHTSLATEGREY);
                });
                rect[i][j].setOnMouseExited(e -> {
                    if (G.sells.get(h)[hh] == -1) {
                        rect[h][hh].setFill(Color.WHITE);
                    } else {
                        Color temp = G.blocks.get(G.sells.get(h)[hh]).color;
                        if (G.blocks.get(G.sells.get(h)[hh]).blockID == G.pressedBlockID) {
                            temp = temp.darker();
                        }
                        rect[h][hh].setFill(temp);
                    }
                });
                names[i][j].setOnMouseEntered(e -> {
                    rect[h][hh].setFill(Color.LIGHTSLATEGREY);
                });
                names[i][j].setOnMouseExited(e -> {
                    if (G.sells.get(h)[hh] == -1) {
                        rect[h][hh].setFill(Color.WHITE);
                    } else {
                        Color temp = G.blocks.get(G.sells.get(h)[hh]).color;
                        rect[h][hh].setFill(temp);
                    }
                });
            }
        }
    }

}
