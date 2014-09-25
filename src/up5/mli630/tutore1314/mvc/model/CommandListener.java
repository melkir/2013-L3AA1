package up5.mli630.tutore1314.mvc.model;

import up5.mli630.tutore1314.Element;
import up5.mli630.tutore1314.maquette.*;
import up5.mli630.tutore1314.mvc.utils.Formulaire;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CommandListener implements ActionListener {
    Element element;
    Element parent;
    TreePath path;
    Formulaire formulaire;
    JTree tree;

    public CommandListener() {
    }

    public void setFormulaire(Formulaire formulaire) {
        this.formulaire = formulaire;
    }

    public void setPath(TreePath path) {
        this.path = path;
        this.element = (Element) path.getLastPathComponent();
        if( element instanceof Formation){}
        else this.parent = (Element) path.getParentPath().getLastPathComponent();
    }

    public void setTree(JTree tree) {
        this.tree = tree;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Sauvegarder")) {

            formulaire.save(element);

        } else if (e.getActionCommand().equals("Supprimer")) {
            if(null == parent || null == element) {
                System.err.println("Erreur: Aucun element selectionne");
                return;
            }
            // TODO A compléter avec les autres cas
            if (parent instanceof Formation) {
                ((Formation) parent).removeChild((AnneeDeFormation) element);
            } else if (parent instanceof AnneeDeFormation) {
                ((AnneeDeFormation) parent).supprimerChoix((Parcours) element);
            } else if (parent instanceof Parcours) {
                ((Parcours) parent).removeChild((Periode) element);
            } else if (parent instanceof Periode) {
                if (element instanceof UE) {
                    ((Periode) parent).removeObligatoire((UE) element);
                } else if (element instanceof Choix) {
                    try {
                        ((Periode) parent).removeChoix((Choix<UE>) element);
                    } catch (Exception ex) {
                        ((Periode) parent).removeChoixBloc((Choix<Bloc<UE>>) element);
                    }
                } else if (element instanceof Facultatif) {
                    ((Periode) parent).removeFacultative((Facultatif<UE>) element);
                } else if (element instanceof Bloc) {
                    ((Periode) parent).removeBloc((Bloc<UE>) element);
                }
            } else if (parent instanceof UE) {
                ((UE) parent).removeObligatoire((ECUE) element);
            }

            try {
                tree.updateUI();
                tree.collapsePath(path);
            } catch (Exception ex) {
            }
        }
    }
}