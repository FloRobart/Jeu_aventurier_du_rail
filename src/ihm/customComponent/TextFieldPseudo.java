package ihm.customComponent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import controleur.Controleur;


public class TextFieldPseudo extends TextFieldWithHint
{
    public TextFieldPseudo(Controleur ctrl)
    {
        super(ctrl);
        this.setMaximumSize();
    }
  
    public TextFieldPseudo(final String hint, Controleur ctrl)
    {
        super(hint, ctrl);
        this.setMaximumSize();
    }

    public void setMaximumSize()
    {
        JTextField txt = this;
        addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent ke)
            {
                if (txt.getText().length() < 20)
                {
                    txt.setEditable(true);
                }
                else
                {
                    txt.setEditable(false);
                }               
            }
        });  
    }
}
