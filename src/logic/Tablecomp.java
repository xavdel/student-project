/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ad31
 */
public class Tablecomp extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table,
                                      Object value,
                                      boolean isSelected,
                                      boolean hasFocus,
                                      int row,
                                      int column){
        JComboBox comboBox = new JComboBox();
        comboBox.addItem(Choix.UNDETERMINED);
        comboBox.addItem(Choix.TRUE);
        comboBox.addItem(Choix.FALSE);
        comboBox.setSelectedItem(table.getValueAt(row, column));
        if (isSelected){
            return comboBox;
        }
        else {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
            


    }
}
