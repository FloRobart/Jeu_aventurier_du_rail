package ihm.customComponent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import controleur.Controleur;


public class TextFieldOnlyInteger extends TextFieldWithHint 
{
    public TextFieldOnlyInteger(Controleur ctrl)
    {
        super(ctrl);
        this.setOnlyInteger();
    }


    public TextFieldOnlyInteger(String hint, Controleur ctrl)
    {
        super(hint, ctrl);
        this.setOnlyInteger();
    }


    public void setOnlyInteger()
    {
        JTextField txt = this;
        this.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == KeyEvent.VK_DELETE || ke.getKeyChar() == KeyEvent.VK_ENTER)
                    txt.setEditable(true);
                else
                    txt.setEditable(false);
            }
        });  
    }
}
