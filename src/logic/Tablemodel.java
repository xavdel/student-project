/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ad31
 */
public class Tablemodel extends AbstractTableModel {

    final int ColumnCount = 2;
    @Override
    public int getRowCount() {
        if (Sommet.table==null) return 0;
    return Sommet.table.size();
    }

    @Override
    public int getColumnCount() {
        return ColumnCount;
    }
    static String temp0;
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (Sommet.table==null) return "";
        switch (columnIndex){
            case 0:  return Sommet.table.get(rowIndex).id;
            case 1:  return Sommet.table.get(rowIndex).chx;
            default: return "Erreur";
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col >= 1) {
            return true;
        } else {
            return false;
        }
    }

        public void setValueAt(Object value, int row, int col) {
            
            switch (col){
            case 0:  return ;
            case 1: Sommet.table.get(row).chx=(Choix) value;
                /*
                if ( (Choix.FALSE.toString().equals(value)) 
                    || ("f".equals(value)) 
                    || ("F".equals(value))){
                Sommet.table.get(row).chx=Choix.FALSE;
            }
            else
                if ( (Choix.TRUE.toString().equals(value)) 
                    || ("t".equals(value)) 
                    || ("T".equals(value))){
                Sommet.table.get(row).chx=Choix.TRUE;
            }
            else
                {
                    Sommet.table.get(row).chx=Choix.UNDETERMINED;
                }*/
                
                
                
            fireTableCellUpdated(row, col);
                return ;
            default: return;
        }
        
    }

    @Override
    public String getColumnName(int column) {
            switch (column){
            case 0:     return "Variables";
            case 1:     return "Valeur";
            default:    return "Erreur";
        }
    }
        
        
        
    public Tablemodel() {
    }
    
    
}
