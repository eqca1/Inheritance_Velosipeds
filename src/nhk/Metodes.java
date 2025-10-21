package nhk;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Metodes {

    static int skPar(String teks, int min, int max) {
        int z;
        String iev;

        while (true) {
            iev = JOptionPane.showInputDialog(null, teks);
            if (iev == null) {
                return min;
            }

            if (iev.trim().isEmpty()) {
                continue;
            }

            try {
                z = Integer.parseInt(iev.trim());
                if (z < min || z > max) {
                    JOptionPane.showMessageDialog(null, teks);
                } else {
                    return z;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Klūda!");
            }
        }
    }
	
    static JSlider bidjosla(JOptionPane jop, int max) {
    	
    	JSlider js = new JSlider(JSlider.HORIZONTAL, 1, max, 1);
    	js.setMajorTickSpacing(1);
    	js.setPaintTicks(true);
    	js.setPaintLabels(true);
    	jop.setInputValue(js.getValue());
    	
    	ChangeListener cl = new ChangeListener() {
    		public void stateChanged(ChangeEvent ce) {
    			JSlider sl = (JSlider) ce.getSource();
    			
    			if(!sl.getValueIsAdjusting()) {
    				jop.setInputValue(Integer.valueOf(sl.getValue()));
    			}
    		}
    	};
    	
    	js.addChangeListener(cl);
		return js;
    	
    }
    
    static int iestatitSedekli() {
    	
    	JFrame jf = new JFrame();
    	JOptionPane jop = new JOptionPane();
    	JSlider js = bidjosla(jop, 6);
    	jop.setMessage(new Object[] {"Kurā pozīcijā iestatīt sēdekļi?", js});
    	jop.setMessageType(JOptionPane.QUESTION_MESSAGE);
    	jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
    	JDialog jd = jop.createDialog(jf, "Sēdekļa pozīcija iestatīšana");
    	jd.setVisible(true);
    	return (int) jop.getInputValue();
    	
    }
    static int iestatitAtrumu() {
    	
    	JFrame jf = new JFrame();
    	JOptionPane jop = new JOptionPane();
    	JSlider js = bidjosla(jop, 12);
    	jop.setMessage(new Object[] {"Kuru ātrumu iestatīt?", js});
    	jop.setMessageType(JOptionPane.QUESTION_MESSAGE);
    	jop.setOptionType(JOptionPane.OK_CANCEL_OPTION);
    	JDialog jd = jop.createDialog(jf, "Ātruma iestatīšana");
    	jd.setVisible(true);
    	return (int) jop.getInputValue();
    	
    }
	static int ritenaIzvele(ArrayList<Object> riteni) {

		String[] riten = new String[riteni.size()];
		for(int i=0; i<riten.length; i++) {
			
			riten[i] = (((Velosipeds)riteni.get(i)).noteiktRaz())+" "+(((Velosipeds)riteni.get(i)).noteiktCenu())+" EUR";
		}
		
		String x = (String) JOptionPane.showInputDialog(null, "Izvēlēties riteņu", "Izvēle", JOptionPane.QUESTION_MESSAGE, null,
				riten, riten[0]);
		
		int izveID = Arrays.asList(riten).indexOf(x);
		
		return izveID;
	}    	

    	
    	
    
    
}
