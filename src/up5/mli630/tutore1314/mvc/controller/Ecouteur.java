package up5.mli630.tutore1314.mvc.controller;

import up5.mli630.tutore1314.Element;
import up5.mli630.tutore1314.EnseignementExterne;
import up5.mli630.tutore1314.EnseignementNormal;
import up5.mli630.tutore1314.maquette.*;
import up5.mli630.tutore1314.mvc.model.CommandListener;
import up5.mli630.tutore1314.mvc.view.*;

import javax.swing.*;
import javax.swing.tree.TreePath;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Ecouteur implements ActionListener {
    PanelInformation panelInformation;
    private Point loc;
    private JTree montree;
    private JPopupMenu popupFormation;
    private JPopupMenu popupAForm;
    private JPopupMenu popupParcours;
    private JPopupMenu popupPeriode;
    private JPopupMenu popupUE;
    private JPopupMenu popupBlocUE;
    private JPopupMenu popupECUE;
    private Object selectedNode;
    private Element parent ;
    
   

    public Ecouteur(final JTree tree, PanelInformation panelInfo) {
    	
        this.panelInformation = panelInfo;
        this.montree = tree;
        this.popupFormation = createPopupFormation();
        this.popupAForm = createPopupAnneeDeFormation();
        this.popupParcours = createPopupParcours();
        this.popupPeriode = createPopupPeriode();
        this.popupUE = createPopupUE();
        this.popupBlocUE = createPopupBlocEU();
        this.popupECUE = createPopupECUE();

        tree.addMouseListener(  new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    //System.out.println("click Right");

                    TreePath selPath = montree.getPathForLocation(e.getX(), e.getY());
                    if (selPath != null) {
                        selectedNode = selPath.getLastPathComponent();
                        // Affichage du popup ad�quat
                        if (selectedNode instanceof Formation) {
                            popupFormation.show((JComponent) e.getSource(), e.getX(), e.getY());
                        } else if (selectedNode instanceof AnneeDeFormation) {
                            popupAForm.show((JComponent) e.getSource(), e.getX(), e.getY());
                        } else if (selectedNode instanceof Parcours) {
                            popupParcours.show((JComponent) e.getSource(), e.getX(), e.getY());
                        } else if (selectedNode instanceof Periode) {
                            popupPeriode.show((JComponent) e.getSource(), e.getX(), e.getY());
                        } else if (selectedNode instanceof UE) {
                            popupUE.show((JComponent) e.getSource(), e.getX(), e.getY());
                        } else if (selectedNode instanceof Choix) {
                            popupBlocUE.show((JComponent) e.getSource(), e.getX(), e.getY());
                        } else if (selectedNode instanceof ECUE) {
                            popupECUE.show((JComponent) e.getSource(), e.getX(), e.getY());
                        }
                    }

                }

            }
        //tree.addMouseListener(ml);

    });

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    	TreePath path = montree.getSelectionPath();
    	 selectedNode  = path.getLastPathComponent();
    	 if( selectedNode instanceof Formation){}
         else this.parent = (Element) path.getParentPath().getLastPathComponent();
    	 
    	 System.out.println(selectedNode.toString());
        String ac = e.getActionCommand();

        if (ac.equals("Ajouter une annee de formation")) {
            System.out.println("ok");
            PanelContent panelContent = panelInformation.getPanelContent();
            AnneeDeFormation annee = new AnneeDeFormation("modifier", 0);
            if(selectedNode instanceof Formation){((Formation) selectedNode).addChild(annee);	}
            panelContent.add(new PanelAnneeDeFormation(annee), "panel");
            panelContent.show("panel");
        }

        if (ac.equals("Ajouter un parcours")) {
        	PanelContent panelContent = panelInformation.getPanelContent();
            Parcours par = new Parcours("newPar", "", "");
            if(selectedNode instanceof AnneeDeFormation){ ((AnneeDeFormation) selectedNode).ajouterChoix(par,3); }
            panelContent.add(new PanelParcours(par), "panel");
            panelContent.show("panel");
        }
        if (ac.equals("Ajouter une periode")) {
        	PanelContent panelContent = panelInformation.getPanelContent();
            Periode perio = new Periode("newPer", "", "", 0);
            if(selectedNode instanceof Parcours){((Parcours) selectedNode).addChild(perio); }
            panelContent.add(new PanelPeriode(perio), "panel");
            panelContent.show("panel");
        }
        if (ac.equals("Ajouter  UE")) {
        	PanelContent panelContent = panelInformation.getPanelContent();
            UE eu = new UE("newU", "", "");
            if(selectedNode instanceof Periode){((Periode) selectedNode).addObligatoire(eu);  }
            panelContent.add(new PanelUE(eu), "panel");
            panelContent.show("panel");
        }
        if (ac.equals("Ajouter  Bloc<?>")) {
        	PanelContent panelContent = panelInformation.getPanelContent();
            Choix<UE> eu = new Choix<UE>("newchoix", "", "");
            if(selectedNode instanceof Periode){((Periode) selectedNode).addChoix(eu); }
            panelContent.add(new PanelChoix(eu), "panel");
            panelContent.show("panel");
        }
        if (ac.equals("Ajouter  Bloc<?>facutative")) {
        	PanelContent panelContent = panelInformation.getPanelContent();
            Choix<UE> eu = new Choix<UE>("newchoix", "", "");
            if(selectedNode instanceof Periode){((Periode) selectedNode).addChoix(eu); }
            panelContent.add(new PanelChoix(eu), "panel");
            panelContent.show("panel");
        }
        if (ac.equals("Ajouter UE au choix ")) {
        	PanelContent panelContent = panelInformation.getPanelContent();
            UE ue = new UE("new", "", "");
            if(selectedNode instanceof Choix){((Choix) selectedNode).ajouterChoix(ue, 0);
            }
            panelContent.add(new PanelUE(ue), "panel");
            panelContent.show("panel");}
        if (ac.equals("Ajouter ECUE(enseigmentNormal)")) {
        	PanelContent panelContent = panelInformation.getPanelContent();
        	EnseignementNormal ensN = new EnseignementNormal("newEN", "", "", 0, 0, 0, 0, null);
        	ECUE ecue = new ECUE(ensN, 0, 0);
        	if(selectedNode instanceof UE){((UE) selectedNode).addObligatoire(ecue);}
            panelContent.add(new PanelECUE(ecue), "panel");
            panelContent.show("panel");
            ;
            
        }
        if (ac.equals("Ajouter ECUE(enseigmentExterne)")) {
        	PanelContent panelContent = panelInformation.getPanelContent();
        	EnseignementExterne en = new EnseignementExterne("newEns","", "",0, 0, 0, 0, 0);
            ECUE ecue = new ECUE(en, 0, 0);
            if(selectedNode instanceof UE){((UE) selectedNode).addObligatoire(ecue);}
            panelContent.add(new PanelECUE(ecue), "panel");
            panelContent.show("panel");
           
        }
         
        if (ac.equals("Supprimer")) {
                
                // TODO A compléter avec les autres cas
                if (parent instanceof Formation) {
                    ((Formation) parent).removeChild((AnneeDeFormation) selectedNode);
                } else if (parent instanceof AnneeDeFormation) {
                    ((AnneeDeFormation) parent).supprimerChoix((Parcours) selectedNode);
                } else if (parent instanceof Parcours) {
                    ((Parcours) parent).removeChild((Periode) selectedNode);
                } else if (parent instanceof Periode) {
                    if (selectedNode instanceof UE) {
                        ((Periode) parent).removeObligatoire((UE) selectedNode);
                    } else if (selectedNode instanceof Choix) {
                        try {
                            ((Periode) parent).removeChoix((Choix<UE>) selectedNode);
                        } catch (Exception ex) {
                            ((Periode) parent).removeChoixBloc((Choix<Bloc<UE>>) selectedNode);
                        }
                    } else if (selectedNode instanceof Facultatif) {
                        ((Periode) parent).removeFacultative((Facultatif<UE>) selectedNode);
                    } else if (selectedNode instanceof Bloc) {
                        ((Periode) parent).removeBloc((Bloc<UE>) selectedNode);
                    }
                } else if (parent instanceof UE) {
                    ((UE) parent).removeObligatoire((ECUE) selectedNode);
                }

                
            
    			                                       
        	
        	   
        }
        
        try {
            montree.updateUI();
            montree.collapsePath(path);
            montree.expandPath(path);
        } catch (Exception ex) {
        }
    }

    // crertion des popup

    //menu popup formation
    public JPopupMenu createPopupFormation() {

        JPopupMenu popup = new JPopupMenu();

        popup.add(getMenuItem("Ajouter une annee de formation", this));
        /*popup.add(getMenuItem("Supprimer", this));*/

        return popup;
    }


    // menu popup annee de formation
    public JPopupMenu createPopupAnneeDeFormation() {
        JPopupMenu popup = new JPopupMenu();
        popup.add(getMenuItem("Ajouter un parcours", this));
        popup.add(getMenuItem("Supprimer", this));


        return popup;
    }

    //menu popup parcours
    public JPopupMenu createPopupParcours() {
        JPopupMenu popup = new JPopupMenu();
        popup.add(getMenuItem("Ajouter une periode", this));
        
		popup.add(getMenuItem("Supprimer", this));
        return popup;
    }

    //menu popup periode
    public JPopupMenu createPopupPeriode() {
        JPopupMenu popup = new JPopupMenu();
        popup.add(getMenuItem("Ajouter  UE", this));
        popup.add(getMenuItem("Ajouter  Bloc<?>", this));  
        popup.add(getMenuItem("Ajouter  Bloc<?>facutative", this));
        popup.add(getMenuItem("Supprimer", this));
        return popup;


    }

    // menu popup EU
    public JPopupMenu createPopupUE() {
        JPopupMenu popup = new JPopupMenu();
        popup.add(getMenuItem("Ajouter ECUE(enseigmentNormal)", this));
        popup.add(getMenuItem("Ajouter ECUE(enseigmentExterne)", this));
        popup.add(getMenuItem("Supprimer", this));
        return popup;

    }


    public JPopupMenu createPopupBlocEU() {
        JPopupMenu popup = new JPopupMenu();
        popup.add(getMenuItem("Ajouter UE au choix", this));
        popup.add(getMenuItem("Supprimer", this));
        return popup;

    }

    public JPopupMenu createPopupECUE() {
        JPopupMenu popup = new JPopupMenu();
        popup.add(getMenuItem("Supprimer", this));
        return popup;

    }


    // la classe qui ajouter l'  ecouteur  � un JMenuItem
    private JMenuItem getMenuItem(String s, ActionListener al) {
        JMenuItem menuItem = new JMenuItem(s);
        menuItem.addActionListener(al);
        return menuItem;
    }
    
}