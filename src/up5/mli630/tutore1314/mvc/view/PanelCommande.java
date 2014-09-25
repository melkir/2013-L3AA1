package up5.mli630.tutore1314.mvc.view;

import up5.mli630.tutore1314.mvc.model.CommandListener;

import javax.swing.*;


public class PanelCommande extends JPanel {

    JButton bSave = new JButton("Sauvegarder");
    JButton bDelete = new JButton("Supprimer");
    CommandListener commandListener = new CommandListener();

    public PanelCommande() {
        add(bDelete);
        add(bSave);
        // Ajout des listeners
        bDelete.addActionListener(commandListener);
        bSave.addActionListener(commandListener);
    }

    public CommandListener getCommandListener() {
        return commandListener;
    }

}
