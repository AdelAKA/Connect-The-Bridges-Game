/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class confirmWin {

    public static void display() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Wait");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText("You won the game !!!");

        //Create two buttons
        Button okButton = new Button("OK");

        okButton.setOnAction(e -> {
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, okButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.centerOnScreen();
        window.showAndWait();
        
    }
}
