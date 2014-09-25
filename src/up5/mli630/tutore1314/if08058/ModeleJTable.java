package up5.mli630.tutore1314.if08058;


import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import up5.mli630.tutore1314.Activite;
import up5.mli630.tutore1314.Enseignement;
import up5.mli630.tutore1314.EnseignementExterne;
import up5.mli630.tutore1314.EnseignementNormal;
import up5.mli630.tutore1314.maquette.Formation;



public class ModeleJTable  extends AbstractTableModel{

	private static final long serialVersionUID = 1L;

	private final String[] entete = {"Identifiant", "Nom court", "Nom long",
			"Nombre d'ECTS","Heures de Cours","Heures de TDs","Heures de TPs"};
	Formation formationLMI;
	private int nbrElement = 0;
	private String idEns  = " ID ";
	private final ArrayList<Enseignement> EnsList = new ArrayList<Enseignement>();
	
	
	  public ModeleJTable(Formation formationLMI){
		  this.formationLMI = formationLMI;
	    	    
	      nbrElement = formationLMI.getEnseignementSet().getNbEnseignement();
	      System.out.println("Nombre d'enseignement trouvé dans la Classe Formation : "+nbrElement);
	    
	      for(int i = 0; i< nbrElement; i++){
	    	 EnsList.add(formationLMI.getEnseignementSet().getEnseignementNumero(i));
		  }
	    
	  }

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return EnsList.size();
		}
		@Override
		 public String getColumnName(int columnIndex) {
		        return entete[columnIndex];
		    }
		 
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return entete.length;
		}


		@Override
		public Object getValueAt(int rowIndex, int arg1) {
			// TODO Auto-generated method stub
				switch(arg1)
				{
				case 0 :
					return  EnsList.get(rowIndex).getId();
		        case 1:
		            return  EnsList.get(rowIndex).getShortName();
		        case 2:
		            return  EnsList.get(rowIndex).getLongName();
		        case 3:
		            return  EnsList.get(rowIndex).getEcts();
		        case 4: 
		        	if(EnsList.get(rowIndex).getClass().getSimpleName().toString().equals("EnseignementNormal")){
		        		EnseignementNormal ensN = (EnseignementNormal) EnsList.get(rowIndex);
				    	Activite act = ensN.getNbActiviteNumero(0);
				    	return act.getNbHC();
		        	}else{
		        		EnseignementExterne ensE = (EnseignementExterne) EnsList.get(rowIndex);
		        		return ensE.getNbHC();
		        	}
		        case 5: 
		        	if(EnsList.get(rowIndex).getClass().getSimpleName().toString().equals("EnseignementNormal")){
		        		EnseignementNormal ensN = (EnseignementNormal) EnsList.get(rowIndex);
					    	Activite act = ensN.getNbActiviteNumero(0);
					    	return act.getNbHTD();
		        	}else{
		        		EnseignementExterne ensE = (EnseignementExterne) EnsList.get(rowIndex);
		        		return ensE.getNbHTD();
		        	}
		        case 6: 
		        	if(EnsList.get(rowIndex).getClass().getSimpleName().toString().equals("EnseignementNormal")){
		        		EnseignementNormal ensN = (EnseignementNormal) EnsList.get(rowIndex);
					    	Activite act = ensN.getNbActiviteNumero(0);
					    	return act.getNbHTP();
		        	}else{
		        		EnseignementExterne ensE = (EnseignementExterne) EnsList.get(rowIndex);
		        		return ensE.getNbHTP();
		        	}
		        default:
		            return null; //Ne devrait jamais arriver
				}
			}
		/** add an Enseiegnement to the Table
		 * @param ens : Enseigenement
		 */
		public void addEns(Enseignement ens) {
   				formationLMI.addEnseignement(ens);
				 int nbrElement = formationLMI.getEnseignementSet().getNbEnseignement();
				    System.out.println("Le nouveau nombre d'enseignement dans la Liste apres la mise a jour "+nbrElement);
				    EnsList.add(ens);
		        fireTableRowsInserted(EnsList.size() -1, EnsList.size() -1);
			
	    }
		/** 
		 * @param rowIndex : the row index of Enseignement to remove
		 */
	    public void removeEns(int rowIndex) {
	    	
	    	formationLMI.removeEnseignement(EnsList.get(rowIndex));
	    	EnsList.remove(rowIndex);
	        fireTableRowsDeleted(rowIndex, rowIndex);
	        int nbrElement = formationLMI.getEnseignementSet().getNbEnseignement();
	        System.out.println("Le nouveau nombre d'enseignement dans la Liste apres la mise a jour "+nbrElement);
	    }
	    /** Remove enseiement from the Table
	     * @param 
	     * @But toutes les cellules doivent etre modifiables sauf ID
	     */
	    @Override
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	    	 return columnIndex==0 ? false : true;
	    	
	    }
	     
	   @Override
	    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    	
	        if(aValue != null){
	            Enseignement ens = EnsList.get(rowIndex);
	     
	            switch(columnIndex){
	                case 1:
	                	System.out.println("Mise a jour de "+getColumnName(columnIndex)+"..."+aValue);
	                	ens.setShortName((String)aValue);

	                    break;
	                case 2:
	                	System.out.println("Mise a jour de "+getColumnName(columnIndex)+"..."+aValue);
	                	ens.setLongName((String)aValue);
	                    break;
	                case 3:
	                	System.out.println("Mise a jour de "+getColumnName(columnIndex)+"..."+aValue);
	                	ens.setEcts(Float.parseFloat((String) aValue));	                	
	                    break;
	                case 4:
	                	if(EnsList.get(rowIndex).getClass().getSimpleName().toString().equals("EnseignementNormal")){
			        		EnseignementNormal ensN = (EnseignementNormal) EnsList.get(rowIndex);
					    	Activite act = ensN.getNbActiviteNumero(0);
					    	System.out.println("Mise a jour de "+getColumnName(columnIndex)+"..."+aValue);
					    	act.setNbHC(Float.parseFloat((String) aValue));
			        	}else{
			        		EnseignementExterne ensE = (EnseignementExterne) EnsList.get(rowIndex);
			        		System.out.println("Mise a jour de "+getColumnName(columnIndex)+"..."+aValue);
			        		ensE.setNbHC(Float.parseFloat((String) aValue));
			        	}
	                    break;
	                case 5:
	                	System.out.println("Type :::: "+aValue.getClass().toString());
	                	if(EnsList.get(rowIndex).getClass().getSimpleName().toString().equals("EnseignementNormal")){
			        		EnseignementNormal ensN = (EnseignementNormal) EnsList.get(rowIndex);
					    	Activite act = ensN.getNbActiviteNumero(0);
					    	System.out.println("Mise a jour de "+getColumnName(columnIndex)+"..."+aValue);
					    	act.setNbHTD(Float.parseFloat((String) aValue));
					    	
			        	}else{
			        		EnseignementExterne ensE = (EnseignementExterne) EnsList.get(rowIndex);
			        		System.out.println("Mise a jour de "+getColumnName(columnIndex)+"..."+aValue);
			        		ensE.setNbHTD(Float.parseFloat((String) aValue));
			        		
			        	}
	                    break;
	                case 6:
	                	if(EnsList.get(rowIndex).getClass().getSimpleName().toString().equals("EnseignementNormal")){
			        		EnseignementNormal ensN = (EnseignementNormal) EnsList.get(rowIndex);
					    	Activite act = ensN.getNbActiviteNumero(0);
					    	System.out.println("Mise a jour de "+getColumnName(columnIndex)+"..."+aValue);
					    	act.setNbHTP(Float.parseFloat((String) aValue));
			        	}else{
			        		EnseignementExterne ensE = (EnseignementExterne) EnsList.get(rowIndex);
			        		System.out.println("Mise a jour de "+getColumnName(columnIndex)+"..."+aValue);
			        		ensE.setNbHTP(Float.parseFloat((String) aValue));
			        	}
	                    break;
            }
        }
    }
	  /**
	   * @param ensToSelect
	   * @return index : index of the Enseignement to Select in the List
	   */
	   public int getIndexEnseignementToSelect(Enseignement ensToSelect){
		   int index=0;
			boolean reponse = false;
			 try{
				while(reponse == false){
					reponse = formationLMI.getEnseignementSet().getEnseignementNumero(index).equals(ensToSelect);
					index++;
				}
			 }catch(Exception e){System.out.println(" Cet enseignement n'existe pas dans notre la JTable");}
		 
		
		   return index;
	   }
	   
	   /** Add a new ID to the each new "Enseiegnement"
	    * @return new id
	    */
	   public String incrementerID(){
		   String newID = idEns.concat(String.valueOf(nbrElement));
		   nbrElement++;
		   return newID;
	   }
}
