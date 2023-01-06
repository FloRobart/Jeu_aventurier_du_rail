package ihm.customComponent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import controleur.Controleur;


public class TextFieldPseudo extends TextFieldWithHint
{
    private static final int MAX_SIZE_PSEUDO = 16;


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
        this.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (txt.getText().length() < TextFieldPseudo.MAX_SIZE_PSEUDO || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == KeyEvent.VK_DELETE)
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


    public static int getMaxSizePseudo()
    {
        return TextFieldPseudo.MAX_SIZE_PSEUDO;
    }
}
