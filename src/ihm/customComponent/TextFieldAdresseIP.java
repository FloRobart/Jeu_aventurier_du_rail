package ihm.customComponent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import controleur.Controleur;


public class TextFieldAdresseIP extends TextFieldWithHint
{

    public TextFieldAdresseIP(Controleur ctrl)
    {
        super(ctrl);
    }
  
    public TextFieldAdresseIP(final String hint, Controleur ctrl)
    {
        super(hint, ctrl);
    }


    public void setOnlyInteger()
    {
        JTextField txt = this;
        this.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'  && ke.getKeyChar() == '.' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == KeyEvent.VK_DELETE )
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
