package up5.mli630.tutore1314.mvc.view;

import up5.mli630.tutore1314.Enseignement;
import up5.mli630.tutore1314.maquette.ECUE;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelECUE extends PanelElement {
    public PanelECUE(final ECUE ecue) {
        super(ecue);
        final Enseignement ens = ecue.getEnseignement();
        JButton bEns = new JButton("Enseignement " + ens.toString());
        bEns.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VueJTable.selectEnseignement(ens);
            }
        });
        this.add(bEns);
    }
}
