package up5.mli630.tutore1314.mvc.utils;

import up5.mli630.tutore1314.Element;
import up5.mli630.tutore1314.maquette.AnneeDeFormation;
import up5.mli630.tutore1314.maquette.Choix;
import up5.mli630.tutore1314.maquette.ECUE;

import javax.swing.*;
import java.util.ArrayList;


public class Formulaire extends JPanel {

    ArrayList<JLabel> listJLabel;
    ArrayList<FieldTyped> listFieldTyped;

    public Formulaire() {
        this.listJLabel = new ArrayList<JLabel>();
        this.listFieldTyped = new ArrayList<FieldTyped>();
    }

    public void addConstraints() {
        this.setLayout(new SpringLayout());
        int numPairs = this.listJLabel.size();
        for (int i = 0; i < numPairs; i++) {
            JLabel label = listJLabel.get(i);
            FieldTyped field = listFieldTyped.get(i);
            label.setHorizontalAlignment(JLabel.TRAILING);
            field.setColumns(30);
            label.setLabelFor(field);
            this.add(label);
            this.add(field);
        }
        //Lay out the panel.
        SpringUtilities.makeCompactGrid(this,
                numPairs, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
    }

    public void add(String label, String text, String type) {
        this.listJLabel.add(new JLabel(label));
        this.listFieldTyped.add(new FieldTyped(text, type));
    }

    public void add(String label, Integer value, String type) {
        this.listJLabel.add(new JLabel(label));
        this.listFieldTyped.add(new FieldTyped(String.valueOf(value), type));
    }

    public void save(Element element) {
        for (FieldTyped fieldTyped : listFieldTyped) {
            if (fieldTyped.isEdited()) {
                String type = fieldTyped.getType();

                if (type.equals("shortName")) {
                    element.setShortName(fieldTyped.getText());
                } else if (type.equals("longName")) {
                    element.setLongName(fieldTyped.getText());
                }

                if (element instanceof AnneeDeFormation) {
                    if (type.equals("numeroAnnee")) {
                        ((AnneeDeFormation) element).setNumeroAnnee(Integer.parseInt(fieldTyped.getText()));
                    } else if (type.equals("typeFormation")) {
                        ((AnneeDeFormation) element).setTypeFormation(fieldTyped.getText());
                    } else if (type.equals("effectifReference")) {
                        ((AnneeDeFormation) element).setEffectifReference(Integer.parseInt(fieldTyped.getText()));
                    }
                }

                if (element instanceof Choix) {
                    if (type.equals("importances")) {
                        ((Choix) element).setImportances((java.util.Map) fieldTyped);
                    }
                }

                if (element instanceof ECUE) {
                    if (type.equals("ects")) {
                        ((ECUE) element).setEcts(Float.parseFloat(fieldTyped.getText()));
                    } else if (type.equals("coeff")) {
                        ((ECUE) element).setCoeff(Float.parseFloat(fieldTyped.getText()));
                    }
                }
            }
        }
    }
}
