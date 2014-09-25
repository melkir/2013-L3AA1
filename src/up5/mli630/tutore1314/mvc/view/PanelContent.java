package up5.mli630.tutore1314.mvc.view;

import javax.swing.*;
import java.awt.*;


public class PanelContent extends JPanel {

    final CardLayout cardLayout;

    public PanelContent() {
        this.cardLayout = new CardLayout();
        this.setLayout(cardLayout);
    }

    public void show(String name) {
        cardLayout.show(this, name);
    }
}
