package up5.mli630.tutore1314.mvc.view;

import up5.mli630.tutore1314.maquette.AnneeDeFormation;
import up5.mli630.tutore1314.mvc.utils.Formulaire;

import javax.swing.*;


public class PanelAnneeDeFormation extends PanelElement {

    public PanelAnneeDeFormation(AnneeDeFormation anneeDeFormation) {
        super(anneeDeFormation);
        Formulaire form = new Formulaire();
        form.add("  N°Annee: ", anneeDeFormation.getNumeroAnnee(), "numeroAnnee");
        form.add("Type formation: ", anneeDeFormation.getTypeFormation(), "typeFormation");
        form.add("Effectif reference: ", anneeDeFormation.getEffectifReference(), "effectifReferrence");
        form.addConstraints();
        add(form);
        this.add(new JLabel(anneeDeFormation.getNbAChoisir() + " choix sur " + anneeDeFormation.getNbChoix() + " a choisir"));
    }

}
