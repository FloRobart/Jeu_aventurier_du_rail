package ihm.customComponent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import controleur.Controleur;


public class TextFieldMdp extends TextFieldOnlyInteger
{
    private static final int MAX_SIZE_MDP = 4;


    public TextFieldMdp(Controleur ctrl)
    {
        super(ctrl);
        this.setOnlyInteger();
    }


    public TextFieldMdp(String hint, Controleur ctrl)
    {
        super(hint, ctrl);
        this.setOnlyInteger();
    }


    @Override
    public void setOnlyInteger()
    {
        JTextField txt = this;
        this.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent ke)
            {
                if (txt.getText().length() < TextFieldMdp.MAX_SIZE_MDP && ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == KeyEvent.VK_DELETE || ke.getKeyChar() == KeyEvent.VK_ENTER)
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


    public static int getMaxSizeMdp()
    {
        return TextFieldMdp.MAX_SIZE_MDP;
    }
}
