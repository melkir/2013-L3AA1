package up5.mli630.tutore1314.mvc.view;

import up5.mli630.tutore1314.csv.OdeforException;
import up5.mli630.tutore1314.mvc.controller.*;
import up5.mli630.tutore1314.maquette.Formation;
import up5.mli630.tutore1314.mvc.model.TreeListener;
import up5.mli630.tutore1314.test.ComponentTree;

import javax.swing.*;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;


public class PanelComposite extends JPanel {

    public PanelComposite() throws OdeforException {
        super();
        this.setLayout(new BorderLayout());

        final PanelInformation panelInfo = new PanelInformation();
        
       // Formation formation = TestFormation.createFormationLMI1();
        
        Formation formation = up5.mli630.tutore1314.Main.getFormationTest();
        
        final JTree tree = new ComponentTree(formation);

        // Configuration de l'arbre
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        // Ajout de l'auditeur tree
        tree.addTreeSelectionListener(new TreeListener(tree, panelInfo));
        // Ajout du menu sur l'arbre
        new Ecouteur(tree, panelInfo);

        // Creation du scroll pane Tree
        JScrollPane scrollPaneTree = new JScrollPane(tree);
        scrollPaneTree.setPreferredSize(new Dimension(500, panelInfo.getHeight()));

        // Création du scroll pane Table
        JScrollPane scrollPaneTable = new JScrollPane(new VueJTable(formation));
        scrollPaneTable.setPreferredSize(new Dimension(0, 200));

        add(panelInfo, BorderLayout.CENTER);
        add(scrollPaneTree, BorderLayout.WEST);
        add(scrollPaneTable, BorderLayout.SOUTH);

    }

    public static void main(String[] args) throws OdeforException {
        JFrame frame = new JFrame("Editeur Odefor");
        JPanel panel = new PanelComposite();
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
    }
}
