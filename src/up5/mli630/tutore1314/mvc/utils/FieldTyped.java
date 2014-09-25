package up5.mli630.tutore1314.mvc.utils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class FieldTyped extends JTextField implements DocumentListener {
    String type;
    Boolean edited;

    public FieldTyped(String text, String type) {
        super();
        this.setText(text);
        this.type = type;
        this.edited = false;
        this.getDocument().addDocumentListener(this);
    }

    public Boolean isEdited() {
        return edited;
    }

    public String getType() {
        return type;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        this.edited = true;
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        this.edited = true;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
