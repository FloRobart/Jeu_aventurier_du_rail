package ihm.customComponent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import controleur.Controleur;


public class TextFieldMdp extends TextFieldOnlyInteger
{
    public TextFieldMdp(Controleur ctrl)
    {
        super(ctrl);
    }


    public TextFieldMdp(String hint, Controleur ctrl)
    {
        super(hint, ctrl);
    }


    @Override
    public void setOnlyInteger()
    {
        JTextField txt = this;
        addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent ke)
            {
                if (txt.getText().length() < 4 && ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == KeyEvent.VK_DELETE )
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
