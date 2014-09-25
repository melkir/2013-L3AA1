package up5.mli630.tutore1314.mvc.view;

import up5.mli630.tutore1314.maquette.Periode;
import up5.mli630.tutore1314.mvc.utils.Formulaire;


public class PanelPeriode extends PanelElement {

    public PanelPeriode(Periode periode) {
        super(periode);
        Formulaire form = new Formulaire();
        form.add("N°Periode: ", periode.getNumber(), "number");
        form.addConstraints();
        add(form);
    }
}
