/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author ASUS
 */
public class KeyPressing extends KeyAdapter {

    char keyCode = ' ';

    @Override
    public void keyPressed(KeyEvent event) {

        char ch = event.getKeyChar();

        if (ch == 'w' || ch == 's' || ch == 'a' || ch == 'd') {

            keyCode = ch;
            System.out.println(event.getKeyChar());

        }

        if (event.getKeyCode() == KeyEvent.VK_HOME) {

            System.out.println("Key codes: " + event.getKeyCode());

        }
    }
}
