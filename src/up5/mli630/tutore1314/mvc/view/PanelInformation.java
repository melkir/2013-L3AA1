package up5.mli630.tutore1314.mvc.view;

import javax.swing.*;
import java.awt.*;

public class PanelInformation extends JPanel {

    final JLabel labelTitle = new JLabel("Aucun element selectionne");
    final PanelContent panelContent = new PanelContent();
    final PanelCommande panelCommand = new PanelCommande();

    public PanelInformation() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 450));
        add(labelTitle, BorderLayout.NORTH);
        add(panelContent, BorderLayout.CENTER);
        add(panelCommand, BorderLayout.SOUTH);
    }

    public PanelCommande getPanelCommand() {
        return panelCommand;
    }

    public void setText(String title) {
        this.labelTitle.setText(title);
    }

    public PanelContent getPanelContent() {
        return panelContent;
    }

}
