package up5.mli630.tutore1314.mvc.view;

import up5.mli630.tutore1314.Element;
import up5.mli630.tutore1314.mvc.utils.Formulaire;

import javax.swing.*;


public class PanelElement extends JPanel {

    private Formulaire form;

    public PanelElement(Element element) {
        // Création du formulaire
        this.form = new Formulaire();
        // Ajout des champs
        form.add("Id: ", element.getId(), "id");
        form.add("Nom court: ", element.getShortName(), "shortName");
        form.add("Nom long: ", element.getLongName(), "longName");
        try {
            form.add("ECTS: ", Float.toString(element.getEcts()), "ects");
            form.add("Coefficient: ", Float.toString(element.getCoeff()), "coeff");
        } catch (RuntimeException re) {
        }
        // Ajout du formulaire au panel
        form.addConstraints();
        this.add(form);
        try {
            this.add(new JLabel("Nombres d'heures des etudiants: " + element.getNbHeuresEtudiants()));
        } catch (RuntimeException re) {
        }
    }

    public Formulaire getForm() {
        return form;
    }
}
