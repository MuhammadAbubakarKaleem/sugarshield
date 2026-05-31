package com.sugarshield;

import javax.swing.*;

public class App {
    public static void main(String[] args) {

//        ConsoleController consoleController = new ConsoleController();
//        consoleController.consoleApp();

        SwingUtilities.invokeLater(() -> {
                    new GuiController();
                }
        );


    }


}