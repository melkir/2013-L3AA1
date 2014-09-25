package up5.mli630.tutore1314.test;

import up5.mli630.tutore1314.Element;
import up5.mli630.tutore1314.maquette.*;

import javax.swing.*;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Cette class est une sous-class de JTree qui affiche l'arbre d'un component AWT ou SWING
 * qui compose un Graphical User Interface(GUI)
 */
public class ComponentTree extends JTree {
    /**
     * Toute cette méthode du constructeur doit charger 
     * TreeModel et les objets TreeCellRenderer pour l'arbre.
     * C'est ces classes (défini ci-dessous) qui font tout le vrai travail
     */
    public ComponentTree(Formation c) {
        super(new ComponentTreeModel(c));
        setCellRenderer(new ComponentCellRenderer(getCellRenderer()));
    }

    /**
     * cette méthode main montre l'usage du ComponentTree,
     * le met dans une frame et affiche son arborescence
     */
    public static void main(String[] args) {
        // Création d'une frame, and handle window close requests
        JFrame frame = new JFrame("ComponentTree Demo");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Create a scroll pane and a "message line" and add them to the
        // center and bottom of the frame.
        JScrollPane scrollpane = new JScrollPane();
        final JLabel msgline = new JLabel(" ");
        frame.getContentPane().add(scrollpane, BorderLayout.CENTER);
        frame.getContentPane().add(msgline, BorderLayout.SOUTH);

        // Now create the ComponentTree object, specifying the frame as the
        // component whose tree is to be displayed. Also set the tree's font.
        Formation formationLMI = TestFormation.createFormationLMI1();
        //formationLMI.addChild(TestFormation.createAnneeLMI1());

        JTree tree = new ComponentTree(formationLMI);
        tree.setFont(new Font("SansSerif", Font.BOLD, 12));

        // Permet a un seul élément seulement d'être selectionné
        tree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);



        // installation de l'arbre et l'ajout au scrollpane
        scrollpane.setViewportView(tree);

        // Dimension de la taille de la frame
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    public static JPanel createJTreeLMI1() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        // creation de la scrollpane
        JScrollPane scrollpane = new JScrollPane();
        final JLabel msgline = new JLabel(" ");
        panel.add(scrollpane, BorderLayout.CENTER);
        panel.add(msgline, BorderLayout.SOUTH);

        // creation de l'objet a afficher dans la JTree
        Formation formationLMI = new Formation("LMI", "LMI", "Licence Math Informatique");
        formationLMI.addChild(TestFormation.createAnneeLMI1());

        JTree tree = new ComponentTree(formationLMI);
        tree.setFont(new Font("SansSerif", Font.BOLD, 12));

        // Permet a un seul élément seulement d'être selectionné
        tree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);


        //ajout de l'arbre a la scrollpane
        scrollpane.setViewportView(tree);

        return panel;
    }

    /**
     * La class TreeModel met des données hiérarchisées sous une certaine forme de sorte 
     * que l'arbre puisse s'afficher. N'importe quel sorte d'objet peut être un noeud 
     * tant que le TreeModel puisse le manipuler
     */
    static class ComponentTreeModel implements TreeModel {
        Formation root; // L'objet racine de l'arbre

        // Constructeur : se souvient juste de l'objet racine
        public ComponentTreeModel(Formation c) {
            this.root = c;
        }

        // retourne la racine de l'arbre
        public Object getRoot() {
            return root;
        }

        /**
         * @return Retourne si l'objet est une feuille , c'est a dire si il n'a pas de fils
         * @param node object à traiter
         */
        public boolean isLeaf(Object node) {
            if (node instanceof Formation) {
                Oblig<?> c = (Oblig<?>) node;
                return c.getNbElement() == 0;
            } else if (node instanceof AnneeDeFormation) {
                Choix<?> c = (Choix<?>) node;
                return c.getNbChoix() == 0;
            } else if (node instanceof Parcours) {
                Oblig<?> c = (Oblig<?>) node;
                return c.getNbElement() == 0;
            } else if (node instanceof Periode) {
                Bloc<?> c = (Bloc<?>) node;
                return (c.getNbChoix() + c.getNbObligatoires()) == 0;
            } else if (node instanceof UE) {
                Bloc<?> c = (Bloc<?>) node;
                return (c.getNbChoix() + c.getNbObligatoires()) == 0;
            } else if (node instanceof Choix) {
                Choix<?> c = (Choix<?>) node;
                return c.getNbChoix() == 0;
            } else if (node instanceof ECUE) {
                return true;
            } else {
                return true;
            }
        }

        /**
         * @return Determine combien d'enfant a l'objet
         * @param node Objet à traiter
         */
        public int getChildCount(Object node) {
            if (node instanceof Formation) {
                Oblig<?> c = (Oblig<?>) node;
                return c.getNbElement();
            }
            if (node instanceof AnneeDeFormation) {
                Choix<?> c = (Choix<?>) node;
                return c.getNbChoix();
            }
            if (node instanceof Parcours) {
                Oblig<?> c = (Oblig<?>) node;
                return c.getNbElement();
            }
            if (node instanceof Periode) {
                Bloc<?> c = (Bloc<?>) node;

                int k = 0;
                int cumul1 = 0;
                int cumul2 = 0;

                cumul1 = c.getNbObligatoires();
                cumul2 = c.getNbChoix();
                return cumul1 + cumul2;

            }
            if (node instanceof UE) {
                Bloc<?> c = (Bloc<?>) node;
                int k = 0;
                int cumul1 = 0;
                int cumul2 = 0;

                cumul1 = c.getNbObligatoires();
                cumul2 = c.getNbChoix();
                return cumul1 + cumul2;
            }
            if (node instanceof Oblig) {
                Oblig<?> c = (Oblig<?>) node;
                return c.getNbElement();
            }
            if (node instanceof Choix) {
                Choix<?> c = (Choix<?>) node;
                return c.getNbChoix();
            }
            if (node instanceof ECUE) {
                return 0;
            }
            return 0;
        }

        /**
         * @return Retourne l'enfant spécifique à l'index definie de l'objet parent
         * @param parent Objet parent
         * @param index Index du noeud fils
         */
        public Object getChild(Object parent, int index) {
            if (parent instanceof Formation) {
                Oblig<?> c = (Oblig<?>) parent;
                return c.getElementNumero(index);
            }
            if (parent instanceof AnneeDeFormation) {
                Choix<?> c = (Choix<?>) parent;
                return c.getChoixNumero(index);
            }
            if (parent instanceof Parcours) {
                Oblig<?> c = (Oblig<?>) parent;
                return c.getElementNumero(index);
            }
            if (parent instanceof Periode) {
                Bloc<?> c = (Bloc<?>) parent;
                if (0 <= index && index < c.getNbObligatoires()) {
                    return c.getObligatoireNumero(index);
                } else if (c.getNbObligatoires() <= index && index < (c.getNbChoix() + c.getNbObligatoires())) {
                    return c.getChoixNumero(index - c.getNbObligatoires());
                }
            }

            if (parent instanceof UE) {
                Bloc<?> c = (Bloc<?>) parent;
                if (0 <= index && index < c.getNbObligatoires()) {
                    return c.getObligatoireNumero(index);
                } else /*if(c.getNbObligatoires()<=index && index<(c.getNbChoix()+c.getNbObligatoires()-1))*/ {
                    return c.getChoixNumero(index - c.getNbObligatoires());
                }
            }
            if (parent instanceof Oblig) {
                Oblig<?> c = (Oblig<?>) parent;
                return c.getElementNumero(index);
            }
            if (parent instanceof Choix) {
                Choix<?> c = (Choix<?>) parent;
                return c.getChoixNumero(index);
            }
            if (parent instanceof ECUE) {
                return null;
            }
            return null;
        }

        /**
         * @return retourne l'index du noeud fils dans le noeud parent
         * @param parent objet parent
         * @param child objet enfant
         */
        public int getIndexOfChild(Object parent, Object child) {
            if (parent instanceof ECUE) {
                return 0;
            } else if (parent instanceof UE) {
                Bloc<?> c1 = (Bloc<?>) parent;
                for (int k = 0; k <= c1.getNbChoix(); k++) {
                    //if (c1.getChoixNumero(k).getLongName() == child.toString() || c1.getChoixNumero(k).getShortName() == child.toString())
                        return k;
                }
                Bloc<?> c2 = (Bloc<?>) parent;
                for (int k = 0; k <= c2.getNbObligatoires(); k++) {
                    if (c2.getObligatoireNumero(k).getLongName() == child.toString() || c2.getObligatoireNumero(k).getShortName() == child.toString())
                        return k;
                }
            } else if (parent instanceof Oblig) {
                Oblig<?> c = (Oblig<?>) parent;
                for (int k = 0; k <= c.getNbElement(); k++) {
                    //if (c.getElementNumero(k).getLongName() == child.toString() || c.getElementNumero(k).getShortName() == child.toString())
                        return k;
                }
            } else if (parent instanceof Periode) {
                Bloc<?> c2 = (Bloc<?>) parent;
                for (int k = 0; k <= c2.getNbObligatoires(); k++) {
                    //if (c2.getObligatoireNumero(k).getLongName() == child.toString() || c2.getObligatoireNumero(k).getShortName() == child.toString())
                        return k;
                }
                Bloc<?> c1 = (Bloc<?>) parent;
                for (int k = 0; k <= c1.getNbChoixBloc(); k++) {
                    //if (c1.getChoixBlocNumero(k).getLongName() == child.toString() || c1.getChoixBlocNumero(k).getShortName() == child.toString())
                        return k;
                }
            } else if (parent instanceof Parcours) {
                Oblig<?> c = (Oblig<?>) parent;
                for (int k = 0; k <= c.getNbElement(); k++) {
                    //if (c.getElementNumero(k).getLongName() == child.toString() || c.getElementNumero(k).getShortName() == child.toString())
                        return k;
                }
            } else if (parent instanceof AnneeDeFormation) {
                Choix<?> c = (Choix<?>) parent;
                for (int k = 0; k <= c.getNbChoix(); k++) {
                    //if (c.getChoixNumero(k).getLongName() == child.toString() || c.getChoixNumero(k).getShortName() == child.toString())
                        return k;
                }
            } else {
                Oblig<?> c = (Oblig<?>) parent;
                for (int k = 0; k <= c.getNbElement(); k++) {
                    //if (c.getElementNumero(k).getLongName() == child.toString() || c.getElementNumero(k).getShortName() == child.toString())
                        return k;
                }
            }
            return -1;
        }

        //Cette methode require un arbre editable donc il n'est pas implementé
        public void valueForPathChanged(TreePath path, Object newvalue) {
        }

        //idem, l'arbre doit être editable
        public void addTreeModelListener(TreeModelListener l) {
        }

        public void removeTreeModelListener(TreeModelListener l) {
        }


        public class ThreadUEObl extends Thread {
            private Object parent;
            private int index;

            public ThreadUEObl(Object parent, int index) {
                this.parent = parent;
                this.index = index;
            }

            public void run() {
                Bloc<?> c = (Bloc<?>) parent;
                c.getObligatoireNumero(index);
            }
        }

        public class ThreadUEChoix extends Thread {
            private Object parent;
            private int index;

            public ThreadUEChoix(Object parent, int index) {
                this.parent = parent;
                this.index = index;
            }

            public void run() {
                Bloc<?> c = (Bloc<?>) parent;
                c.getObligatoireNumero(index);
            }
        }
    }

    /**
     *TreeCellRenderer affiche chaque noeud de l'arbre
     */
    static class ComponentCellRenderer implements TreeCellRenderer {
        TreeCellRenderer renderer; // Le rendu que nous allons prendre


        // Constructeur: se souvient juste du rendu
        public ComponentCellRenderer(TreeCellRenderer renderer) {
            this.renderer = renderer;
        }

        // Calcul la chaine de caractére à afficher et le passe au rendu a mettre a la ligne
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                      boolean selected, boolean expanded, boolean leaf, int row,
                                                      boolean hasFocus) {

            String newvalue = ((Element) value).getLongName();
            String name = ((Element) value).getLongName();
            if (name != null)
                newvalue += " (" + name + ")"; // unless null
            // Utilise l'objet rendu passer a la ligne pour faire l'opération 
            return renderer.getTreeCellRendererComponent(tree, newvalue,
                    selected, expanded, leaf, row, hasFocus);
        }
    }
}