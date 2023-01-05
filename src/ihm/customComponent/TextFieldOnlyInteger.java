package ihm.customComponent;

import javax.swing.JTextField;

import controleur.Controleur;

import java.awt.event.*;


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
        addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == KeyEvent.VK_DELETE )
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
