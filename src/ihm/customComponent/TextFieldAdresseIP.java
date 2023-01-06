package ihm.customComponent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import controleur.Controleur;


public class TextFieldAdresseIP extends TextFieldWithHint
{
    private static final int MAX_SIZE_IP = 15; /* Une adresse IP V4 ne peux pas dépasser 15 caractères (Exemple : 123.567.901.345) */


    public TextFieldAdresseIP(Controleur ctrl)
    {
        this("", ctrl);
        this.setOnlyIntegerAndPoint();
    }
  
    public TextFieldAdresseIP(final String hint, Controleur ctrl)
    {
        super(hint, ctrl);
        this.setOnlyIntegerAndPoint();
    }


    private void setOnlyIntegerAndPoint()
    {
        JTextField txt = this;
        this.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (txt.getText().length() < TextFieldAdresseIP.MAX_SIZE_IP && ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == '.' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == KeyEvent.VK_DELETE || ke.getKeyChar() == KeyEvent.VK_ENTER)
                    txt.setEditable(true);
                else
                    txt.setEditable(false);
            }
        });  
    }
}
