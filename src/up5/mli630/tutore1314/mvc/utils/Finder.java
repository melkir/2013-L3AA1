package up5.mli630.tutore1314.mvc.utils;

import up5.mli630.tutore1314.Activite;
import up5.mli630.tutore1314.Element;
import up5.mli630.tutore1314.Enseignement;
import up5.mli630.tutore1314.EnseignementNormal;
import up5.mli630.tutore1314.maquette.*;
import up5.mli630.tutore1314.test.TestFormation;

import java.util.ArrayList;

public class Finder {

    /*
    Chercher_feuilles :
        Si il y a des enfants,
        pour chaque enfant,
        appeller la fonction Chercher_feuilles
        Sinon
        récupérer la feuille.
        Fin du si
    */
    private static final ArrayList<Enseignement> liste_ens = new ArrayList<Enseignement>();

    private static void chercherEnseignements(Element elm) {
        Element child;
        if (elm instanceof Oblig<?>) {
            Oblig<?> c = (Oblig<?>) elm;
            int size = c.getNbElement();
            for (int i = 0; i < size; i++) {
                child = c.getElementNumero(i);
                chercherEnseignements(child);
            }
        } else if (elm instanceof Choix<?>) {
            Choix<?> c = (Choix<?>) elm;
            int size = c.getNbChoix();
            for (int i = 0; i < size; i++) {
                child = c.getChoixNumero(i);
                chercherEnseignements(child);
            }
        } else if (elm instanceof Bloc<?>) {
            Bloc<?> c = (Bloc<?>) elm;
            int nbOblig = c.getNbObligatoires(), nbChoix = c.getNbChoixBloc();
            int size = nbOblig + nbChoix;
            for (int i = 0; i < size; i++) {
                if (i < nbOblig) child = c.getObligatoireNumero(i);
                else child = c.getChoixNumero(i);
                chercherEnseignements(child);
            }
        } else {
            ECUE ecue = (ECUE) elm;
            Enseignement ens = ecue.getEnseignement();
            System.out.println(ens.toString());
            liste_ens.add(ens);
        }
    }

    public static ArrayList<Activite> getListActivites(EnseignementNormal ens) {
        ArrayList<Activite> list_act = new ArrayList<Activite>();
        for (int i = 0; i < ens.getNbActivites(); i++) {
            list_act.add(ens.getNbActiviteNumero(i));
            System.out.println(i + " " + ens.getNbActiviteNumero(i).toString());
        }
        return list_act;
    }

    private static ArrayList<Enseignement> getListEnseignements(Element elm) {
        chercherEnseignements(elm);
        return liste_ens;
    }

    public static void main(String[] args) {
        Formation form = new Formation("", "", "");
        form.addChild(TestFormation.createAnneeLMI1());
        ArrayList<Enseignement> lens = getListEnseignements(form);
        Activite act = ((EnseignementNormal) liste_ens.get(0)).getNbActiviteNumero(0);
        System.out.println(act.toString());
    }
}
