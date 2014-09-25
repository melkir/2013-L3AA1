package up5.mli630.tutore1314.test;

import up5.mli630.tutore1314.Element;
import up5.mli630.tutore1314.EnseignementExterne;
import up5.mli630.tutore1314.EnseignementNormal;
import up5.mli630.tutore1314.maquette.*;

import java.util.Scanner;


public class TestFormation {

    /**
     * Créer une formation avec l'année LMI1
     */
    public static Formation createFormationLMI1() {
        Formation formation = new Formation("LMI1", "LM1", "Licence Math Info 1ère année");
        formation.addChild(createAnneeLMI1());

        // Création des enseignements
        EnseignementNormal ens1, ens2, ens3, ens4, ens5, ens6;
        ens1 = new EnseignementNormal("LMI1_122", "Intro A Prog", "Introduction à la programmation", 5, 12, 36, 8, null);
        ens2 = new EnseignementNormal("LMI1_221", "Prog Fonct", "Programmation fonctionnelle", 5, 14, 34, 0, null);
        ens3 = new EnseignementNormal("LMI1_221", "Prog Fonct", "Programmation fonctionnelle", 5, 40, 32, 2, null);
        ens4 = new EnseignementNormal("LMI1_222", "Num&Log", "Numération et logique", 5, 16, 38, 6, null);
        ens5 = new EnseignementNormal("LMI1_241", "Anglais", "Anglais 1", 2.5f, 18, 24, 10, null);
        ens6 = new EnseignementNormal("LMI1_242", "C2i", "C2i", 2.5f,12, 22, 0, null);
        EnseignementExterne ens7 = new EnseignementExterne("ENS Externe", "test", "test", 5, 12, 24, 6, 0);
        // Ajout des enseignements
        formation.addEnseignement(ens1);
        formation.addEnseignement(ens2);
        formation.addEnseignement(ens3);
        formation.addEnseignement(ens4);
        formation.addEnseignement(ens5);
        formation.addEnseignement(ens6);
        formation.addEnseignement(ens7);

        return formation;
    }

    /**
     * Créer l'année de formation LMI1
     *
     * @return L'année de formation LMI1
     */
    public static AnneeDeFormation createAnneeLMI1() {
        // Création de l'année de formation
        AnneeDeFormation annee = new AnneeDeFormation("Licence math info 1ère année", 1);

        // Création d'un parcours par défaut
        Parcours parcours = new Parcours("MLICP", "Parcours par défaut", "Parcours par défaut");

        // Création des périodes
        Periode periodeS1 = new Periode("LMIS1", "S1", "Semestre 1", 1);
        Periode periodeS2 = new Periode("LMIS2", "S2", "Semestre 2", 2);

        // Création des UE
        UE ue1 = new UE("LMI1_11", "Mathématiques² et calcul 1",
                "Mathématiques²et calcul 1", 10, 10);
        UE ue2 = new UE("LMI1_131", "Biologie 1", "Biologie 1", 4, 4);
        UE ue3 = new UE("LMI1_132", "Economie 1", "Economie 1", 4, 4);
        UE ue4 = new UE("LMI1_133", "Physique 1", "Physique 1", 4, 4);
        UE ue5 = new UE("LMI1_134", "Sciences humaines 1",
                "Sciences humaines 1", 4, 4);
        UE ue6 = new UE("LMI1_14", "Méthodologie du travail universitaire",
                "Méthodologie du travail universitaire", 2, 2);
        UE ue7 = new UE("LMI1_1EX-M",
                "Arithmétique, Groupes, Groupes quotient",
                "Arithmétique, Groupes, Groupes quotient", 5, 5);
        UE ue8 = new UE("LMI1_1EX-I", "Projet ( C )", "Projet ( C )", 5, 5);
        UE ue9 = new UE("LMI1_21", "Mathématiques et calcul 2",
                "Mathématiques et calcul 2", 10, 10);
        UE ue10 = new UE("LMI1_231", "Biologie 2", "Biologie 2", 5, 5);
        UE ue11 = new UE("LMI1_232", "Economie 2", "Economie 2", 5, 5);
        UE ue12 = new UE("LMI1_233", "Physique 2", "Physique 2", 5, 5);
        UE ue13 = new UE("LMI1_234", "Sciences humaines 2",
                "Sciences humaines 2", 5, 5);
        UE ue14 = new UE("LMI1_2EX-M",
                "Projet : approximation numérique d'intégrales",
                "Projet : approximation numérique d'intégrales", 5, 5);
        UE ue15 = new UE("LMI1_2EX-I", "Projet (Caml)", "Projet (Caml)", 5, 5);

        // Création des UE sans ID
        UE ue1NoIdS1 = new UE(null, "UE", "UE");
        UE ue1NoIdS2 = new UE(null, "UE", "UE");
        UE ue2NoIdS2 = new UE(null, "Culture générale", "Culture générale");

        // Création des enseignements
        EnseignementNormal ens1, ens2, ens3, ens4, ens5, ens6;
        ens1 = new EnseignementNormal("LMI1_122", "Intro A Prog", "Introduction à la programmation", 5);
        ens2 = new EnseignementNormal("LMI1_221", "Prog Fonct", "Programmation fonctionnelle", 5);
        ens3 = new EnseignementNormal("LMI1_221", "Prog Fonct", "Programmation fonctionnelle", 5);
        ens4 = new EnseignementNormal("LMI1_222", "Num&Log", "Numération et logique", 5);
        ens5 = new EnseignementNormal("LMI1_241", "Anglais", "Anglais 1", 2.5f);
        ens6 = new EnseignementNormal("LMI1_242", "C2i", "C2i", 2.5f);
        EnseignementExterne ens7 = new EnseignementExterne("LOL", "test", "test", 0, 0, 0, 0, 0);

        // Création des ECUE
        ECUE ecue1 = new ECUE(ens1, 5, 5);
        ECUE ecue2 = new ECUE(ens2, 5, 5);
        ECUE ecue3 = new ECUE(ens3, 5, 5);
        ECUE ecue4 = new ECUE(ens4, 5, 5);
        ECUE ecue5 = new ECUE(ens5, 2.5f, 2.5f);
        ECUE ecue6 = new ECUE(ens6, 2.5f, 2.5f);
        ECUE ecue7 = new ECUE(ens7, 2.5f, 2.5f); // test pour EnsExterne

        // Création des Choix UE avec 4 choix
        Choix<UE> choixUES1 = new Choix<UE>(null, "Découverte", "Découverte", 4);
        Choix<UE> choixUES2 = new Choix<UE>(null, "UE", null, 4);

        // Création des Choix UE falcultatifs avec 2 choix
        Choix<UE> choixUES1Facultatifs = new Choix<UE>(null, "Excellence",
                "Excellence", 2);
        Choix<UE> choixUES2Facultatifs = new Choix<UE>(null, "Excellence",
                "Excellence", 2);

		/* Ajout d'un parcours à l'année de formation */
        annee.ajouterChoix(parcours, 0);
        // LMI Première année -> Parcours par défaut

		/* Ajout de périodes au parcours par défaut */
        parcours.addChild(periodeS1);
        parcours.addChild(periodeS2);
        // Parcours par défaut -> S1, S2

		/* Ajout d'un choix d'UE au Semestre 1 */
        periodeS1.addChoix(choixUES1);

		/* Ajout d'un choix d'UE falcultatifs aux périodes */
        periodeS1.addChoix(choixUES1Facultatifs);
        periodeS2.addChoix(choixUES2Facultatifs);

		/* Assignation des UE (1-8) au parcours S1 */
        // 1 obligatoire
        periodeS1.addObligatoire(ue1);
        // 4 choix
        choixUES1.ajouterChoix(ue2, 0);
        choixUES1.ajouterChoix(ue3, 0);
        choixUES1.ajouterChoix(ue4, 0);
        choixUES1.ajouterChoix(ue5, 0);
        // 1 obligatoire
        periodeS1.addObligatoire(ue6);
        // 2 facultatifs
        choixUES1Facultatifs.ajouterChoix(ue7, 0);
        choixUES1Facultatifs.ajouterChoix(ue8, 0);

		/* Assignation des UE (9-15) au parcours S2 */
        // 1 obligatoire
        periodeS2.addObligatoire(ue9);
        // 4 choix
        choixUES2.ajouterChoix(ue10, 0);
        choixUES2.ajouterChoix(ue11, 0);
        choixUES2.ajouterChoix(ue12, 0);
        choixUES2.ajouterChoix(ue13, 0);
        // 2 facultatifs
        choixUES2Facultatifs.ajouterChoix(ue14, 0);
        choixUES2Facultatifs.ajouterChoix(ue15, 0);

		/* Assignation des UE sans ID aux parcours */
        periodeS1.addObligatoire(ue1NoIdS1);
        periodeS2.addObligatoire(ue1NoIdS2);
        periodeS2.addObligatoire(ue2NoIdS2);

		/* Assignation des ECUE aux UE */
        // 2 ECUE UE 1 Semestre 1
        ue1NoIdS1.addObligatoire(ecue1);
        ue1NoIdS1.addObligatoire(ecue2);
        // 2 ECUE UE 1 Semestre 2
        ue1NoIdS2.addObligatoire(ecue3);
        ue1NoIdS2.addObligatoire(ecue4);
        // 2 ECUE UE 2 Semestre 2
        ue2NoIdS2.addObligatoire(ecue5);
        ue2NoIdS2.addObligatoire(ecue6);
        ue2NoIdS2.addObligatoire(ecue7); // test pour EnsExterne

        return annee;
    }

    /**
     * Explorer une formation avec la console
     *
     * @param form La formation à parcourir
     */
    public static void explore(Formation form) {
        Scanner sc = new Scanner(System.in);
        int choix;
        String marge = "";

		/*
         * Une liste d'année est affiché L'utilisateur choisi une année On
		 * affiche les parcours de l'année choisie
		 */
        showChild(form, marge);
        choix = select(sc, "Choisir une année", form.getNbElement());
        AnneeDeFormation annee = form.getElementNumero(choix);

        showChild(annee, marge += " ");
        choix = select(sc, "Choisir un parcours", annee.getNbChoix());
        Parcours parcours = annee.getChoixNumero(choix);

        showChild(parcours, marge + " ");
        choix = select(sc, "Choisir un semestre", parcours.getNbElement());
        Periode periode = parcours.getElementNumero(choix);

        showChild(periode, marge += " ");
        // Partie non fonctionnelle, 0 bloc UE dans formation LMI1
        //TODO Compléter les bloc UE de formation
        //TODO Comprendre la différence entre Choix Bloc UE et Bloc UE

//		choix = select(sc, "???", periode.getNbChoixBloc());
//		Choix<Bloc<UE>> choixBlocUE = periode.getChoixBlocNumero(choix);
//		choix = select(sc, "Choisir un bloc d'UE", periode.getNbChoix() + periode.getNbObligatoires());
//		Bloc<UE> blocUE = choixBlocUE.getChoixNumero(choix);

    }

    /**
     * Selectionne un nombre entre 0 et max
     *
     * @param sc  Scanner
     * @param max Nombre maximun
     * @return Renvoi le nombre choisi
     */
    public static int select(Scanner sc, String message, int max) {
        // Si il n'y qu'un seul élément, le sélectionner sans afficher de message
        if (0 >= --max) return 0;
        int i;
        // Boucle de test du nombre choisi
        do {
            System.out.print(message + " (0-" + max + ") : ");
            i = sc.nextInt();
        } while (i > max);

        return i;
    }

    private static void showChild(Oblig<? extends Element> elm, String marge) {
        if (1 == elm.getNbElement()) {
            System.out.println(marge + elm.getType() + " " + elm.getLongName());
            return;
        }
        for (int i = 0, size = elm.getNbElement(); i < size; ++i) {
            System.out.println(marge + i + "." + elm.getType() + " "
                    + elm.getElementNumero(i).getLongName());
        }
    }

    private static void showChild(Choix<? extends Element> elm, String marge) {
        if (1 == elm.getNbChoix()) {
            System.out.println(marge + elm.getType() + " " + elm.getLongName());
            return;
        }
        for (int i = 0, size = elm.getNbChoix(); i < size; ++i)
            System.out.println(marge + i + "." + elm.getType() + " "
                    + elm.getChoixNumero(i).getLongName());
    }

    private static void showChild(Bloc<? extends Element> elm, String marge) {
        // TODO A compléter, améliorer
        System.out.println(marge + "0.Bloc UE Obligatoires");
        for (int i = 0, size = elm.getNbObligatoires(); i < size; ++i)
            System.out.println(" " + marge + i + "."
                    + elm.getObligatoireNumero(i).getLongName());

        System.out.println(marge + "1.Bloc UE Choix");
        for (int i = 0, size = elm.getNbChoix(); i < size; ++i)
            System.out.println(" " + marge + i + "."
                    + elm.getChoixNumero(i).getLongName());
    }

    /**
     * Affiche le contenu de la formation
     */
    public static void show(Formation form) {
        System.out.println(form.getType() + " " + form.getShortName() + " " + form.getLongName());
        for (int i = 0; i < form.getNbElement(); ++i) {
            System.out.println(form.getElementNumero(i).getString(" "));
        }
    }

    public static void main(String[] args) {
        Formation formationLMI = new Formation("LMI", "LMI", "Licence Math Informatique");
        formationLMI.addChild(createAnneeLMI1());
        show(formationLMI);
//		explore(formationLMI);
    }
}