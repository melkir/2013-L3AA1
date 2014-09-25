package up5.mli630.tutore1314.mvc.model;

import up5.mli630.tutore1314.Element;
import up5.mli630.tutore1314.maquette.*;
import up5.mli630.tutore1314.mvc.view.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;


public class TreeListener implements TreeSelectionListener {

    PanelInformation panelInfo;
    PanelContent panelContent;
    JTree tree;
    CommandListener commandListener;

    public TreeListener(JTree tree, PanelInformation panelInfo) {
        this.tree = tree;
        this.panelInfo = panelInfo;
        this.panelContent = panelInfo.getPanelContent();
        this.commandListener = panelInfo.getPanelCommand().getCommandListener();
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = tree.getSelectionPath();
        Object selected = path.getLastPathComponent();

        String classname = selected.getClass().getSimpleName();
        panelInfo.setText(classname + " " + ((Element) selected).getId());
        // On défini les éléments sur lesquels vont agir les boutons sauvegarder / supprimer
        commandListener.setPath(path);
        commandListener.setTree(tree);

        PanelElement panel;
        if (classname.equals("Formation")) {
            panel = new PanelFormation((Formation) selected);
        } else if (classname.equals("AnneeDeFormation")) {
            panel = new PanelAnneeDeFormation((AnneeDeFormation) selected);
        } else if (classname.equals("Parcours")) {
            panel = new PanelParcours((Parcours) selected);
        } else if (classname.equals("Periode")) {
            panel = new PanelPeriode((Periode) selected);
        } else if (classname.equals("Choix")) {
            panel = new PanelChoix((Choix<?>) selected);
        } else if (classname.equals("UE")) {
            panel = new PanelUE((UE) selected);
        } else if (classname.equals("ECUE")) {
            panel = new PanelECUE((ECUE) selected);
        } else {
            panel = null;
            System.out.println("type non reconnu");
        }

        commandListener.setFormulaire(panel.getForm());

        panelContent.add(panel, "panel");
        panelContent.show("panel");
    }
}
