package up5.mli630.tutore1314.if08058;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import up5.mli630.tutore1314.Enseignement;
import up5.mli630.tutore1314.EnseignementExterne;
import up5.mli630.tutore1314.EnseignementNormal;
import up5.mli630.tutore1314.csv.OdeforException;
import up5.mli630.tutore1314.maquette.Formation;

@SuppressWarnings("serial")
public class VueJTable extends JPanel {
	
	private JScrollPane scroll;
	private static ModeleJTable modele;
    private static JTable tableau;
    private JPanel boutons;
 
	public VueJTable(Formation formation) {
        modele = new ModeleJTable(formation);
        tableau = new  JTable(modele);
        scroll = new JScrollPane(tableau);
        resizeColumnWidth (tableau );
        centerColumn(tableau);
        scroll.setPreferredSize(new Dimension(950, 140));

        boutons = new JPanel();
        boutons.add(new JButton(new AddAction()));
        boutons.add(new JButton(new RemoveAction()));
        
        
        Box boxVertical = Box.createVerticalBox();
        boxVertical.add(Box.createGlue());
        boxVertical.add(scroll);
        boxVertical.add(boutons);
        
        add(boxVertical);
    }
	
	
    public static void main(String[] args) throws OdeforException {
    	JFrame window = new JFrame();
    	
    	window.setTitle("Universit� Paris Descartes : Odefor Editor");
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setBounds(100, 100, 1000,230);
    	
    	Formation formation = up5.mli630.tutore1314.Main.getFormationTest();
    	
    	VueJTable content = new VueJTable(formation);
    	window.add(content);
    	
    	window.setVisible(true);
    }

    private class AddAction extends AbstractAction {
        private AddAction() {
            super("Ajouter Enseigenement");
        }

        @SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed(ActionEvent e) {
        	String[] options = {"Ok", "Annuler"};
        	String[] tab = {"Enseignement Normal", "Enseignement Externe"};
			JComboBox combo = new JComboBox(tab);
        	 combo.setSelectedIndex(0);
        	 int result = JOptionPane.showOptionDialog(null, combo, "Choisir le type d'enseignement "
             		, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,0);
        	 /**
        	  * 0 : Enseignement Normal
        	  * 1 : Enseignement Externe
        	  */
        	/* if (result == 0) {
        	 modele.addEns((combo.getSelectedIndex() != 0)? new EnseignementExterne(modele.incrementerID(), "Externe", "Externe", 0, 0, 0, 0, 0) 
        	 : new EnseignementNormal(modele.incrementerID(), "Normal", "Normal", 0, 0, 0, 0, null));
        	 }*/
        	 /**
        	  * Or
        	  **/
        	 if(combo.getSelectedIndex() == 0){
        		 EnseignementNormal ensNor = new EnseignementNormal(modele.incrementerID(), "Normal", "Normal", 0, 0, 0, 0, null);
        		 modele.addEns(ensNor);
        		 selectEnseignement(ensNor);
        	 }else{
        		 EnseignementExterne ensExt = new EnseignementExterne(modele.incrementerID(), "Externe", "Externe", 0, 0, 0, 0, 0);
        		 modele.addEns(ensExt);
        		 selectEnseignement(ensExt);
        	 }
        	
        	 tableau.setModel(modele);
        }
    }
 
    private class RemoveAction extends AbstractAction {
        private RemoveAction() {
            super("Supprimer Enseigenement");
        }
 
        public void actionPerformed(ActionEvent e) {
            int[] selection = tableau.getSelectedRows();
 
            for(int i = selection.length - 1; i >= 0; i--){
                modele.removeEns(selection[i]);
            }
        }
    }

    /**
     * @param JTable
     * @But redimenssionner les colonnes
     */
    public  void resizeColumnWidth ( JTable table )  { 
        final  TableColumnModel columnModel = table . getColumnModel (); 
        for  ( int column =  0 ; column < table . getColumnCount (); column ++)  { 
            int width =  50 ;  // Min width 
            for  ( int row =  0 ; row < table . getRowCount (); row ++)  { 
                TableCellRenderer renderer = table . getCellRenderer ( row , column ); 
                Component comp = table . prepareRenderer ( renderer , row , column ); 
                width =  Math . max ( comp . getPreferredSize (). width , width ); 
            } 
            columnModel . getColumn ( column ). setPreferredWidth ( width ); 
        } 
    }
    
    /** But centrer le contenu des cellules
     * @param JTable
     */
    public void centerColumn(JTable tableau){
    	 for(int i=0 ; i< tableau.getColumnCount() ; i++)
         {
 	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
 	        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
 	        tableau.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
         }
    }
    
    /** But selectionner/visionner un enseignement dans la tableau afin de le modifier ou supprimer
     * @param ens
     */
    public static void selectEnseignement(Enseignement ens){
        int i = modele.getIndexEnseignementToSelect(ens)-1;
        tableau.getSelectionModel().setSelectionInterval(i, i);
        tableau.scrollRectToVisible(tableau.getCellRect(i, 0, true));
    }
    
  

}