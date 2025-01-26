package com.mycompany.encryption;

import com.mycompany.controller.AlgorithmController;
import com.mycompany.controller.CipherController;
import com.mycompany.view.UserInterface;

public class Encryption {

    static int state = 0;

    public static void main(String[] args) {

        UserInterface ui = new UserInterface();

        ui.printMenu(state);
        int action = ui.getUserChoice(state);
        ui.handleChoice(action, state);

        if (action == 0) {
            state = 1;
            ui.printMenu(state);
            int cipher = ui.getUserChoice(state);
            ui.handleChoice(cipher, state);

            CipherController cipherControl = new CipherController();
            cipherControl.handleCypherCall(cipher, action);
        }

        state = 2;
        ui.printMenu(state);
        int algorithm = ui.getUserChoice(state);
        ui.handleChoice(algorithm, state);

        AlgorithmController algControl = new AlgorithmController();
        algControl.handleAlgCall(algorithm, action);
        
        if (action == 1) {
            state = 1;
            ui.printMenu(state);
            int cipher = ui.getUserChoice(state);
            ui.handleChoice(cipher, state);

            CipherController cipherControl = new CipherController();
            cipherControl.handleCypherCall(cipher, action);
        }

    }

}
