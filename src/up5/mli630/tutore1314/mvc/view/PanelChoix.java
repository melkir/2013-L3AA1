package up5.mli630.tutore1314.mvc.view;

import up5.mli630.tutore1314.maquette.Choix;

import javax.swing.*;


public class PanelChoix extends PanelElement {
    public PanelChoix(Choix<?> choix) {
        super(choix);
        this.add(new JLabel("Somme des importances " + choix.getSommeImportances()));
    }
}
