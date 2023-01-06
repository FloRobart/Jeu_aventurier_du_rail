package ihm.customComponent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import controleur.Controleur;


public class TextFieldPseudo extends TextFieldWithHint
{
    private static final int MAX_SIZE_PSEUDO = 16;

    private Controleur ctrl;


    public TextFieldPseudo(Controleur ctrl)
    {
        super(ctrl);
        this.ctrl = ctrl;
        this.setMaximumSize();
    }
  
    public TextFieldPseudo(final String hint, Controleur ctrl)
    {
        super(hint, ctrl);
        this.ctrl = ctrl;
        this.setMaximumSize();
    }


    public void setMaximumSize()
    {
        TextFieldPseudo txt = this;
        this.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (txt.getText().length() < TextFieldPseudo.MAX_SIZE_PSEUDO || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == KeyEvent.VK_DELETE)
                    txt.setEditable(true);
                else
                    txt.setEditable(false);


                /* Il est important que cette méthode sois en avant dernier */
                if (ke.getKeyChar() != KeyEvent.VK_ENTER      &&
                    ke.getKeyChar() != KeyEvent.VK_BACK_SPACE &&
                    ke.getKeyChar() != KeyEvent.VK_DELETE     && 
                    ke.getKeyChar() != 65535                  &&
                   (ke.getKeyChar() >= 'A' || ke.getKeyChar() <= 'Z' || ke.getKeyChar() >= '0' || ke.getKeyChar() <= '9' ))
                {
                    if (txt.getBorder() != null)
                        txt.setBorder(null);
                }

                /* Il est important que cette méthode sois en dernier */
                if (txt.getText().isEmpty() && txt.getBorder() == null)
                    txt.setPlaceholderColorOnly(txt.getControleur().getTheme().get("saisies").get(2));
            }
        });
    }


    public Controleur getControleur()
    {
        return this.ctrl;
    }

    public static int getMaxSizePseudo()
    {
        return TextFieldPseudo.MAX_SIZE_PSEUDO;
    }
}
