package exemplodaogenerico;

import javax.persistence.EntityManager;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        //UIManager.put("OptionPane.yesButtonText", "Sim");
        //UIManager.put("OptionPane.noButtonText", "Não");
           
        EntityManager em = Banco.getInstancia().getEm();
        
        CadastroCidades cadCidades = new CadastroCidades();
        cadCidades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cadCidades.setVisible(true);

    }
}
