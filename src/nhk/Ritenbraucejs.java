package nhk;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Ritenbraucejs {


	
	public static void main(String[] args) {
		
		String izv;
		int izvID;
		String[] darbibas = {"Jauns ritenis", "Noņemt riteni", "Riteņu saraksts", "Kārtot pēc cenas",
							 "Izsaukt metodi", "Aizvērt programmu"};
		int ritenaD, sedeklPoz, iestatAtrums;
		double cena;
		String razotajs;
		boolean atsperes, paligriteni, zvanins;
		String[] veidi = {"Velosipēds", "Kalnu velosipēds", "Bērnu velosipēds"};
		String[] atbilde = {"Jā", "Nē"};
		ArrayList<Object> riteni = new ArrayList<>();
		
		do {
			izv = (String)JOptionPane.showInputDialog(null, "Izvēlies darbību", "Izvēlne", JOptionPane.QUESTION_MESSAGE,
					null, darbibas, darbibas[0]);
			if(izv == null) break;
			izvID = Arrays.asList(darbibas).indexOf(izv);
			
			switch(izvID) {
			case 0: //Jauns ritenis
				String izve = (String)JOptionPane.showInputDialog(null, "Izvēlies velosipēda veidi!", "Izvēlne", JOptionPane.QUESTION_MESSAGE,
						null, veidi, veidi[0]);
				if(izve == null) break;
				int izveID = Arrays.asList(veidi).indexOf(izve);
			
				switch(izveID) {
				case 0: //velosipēds
				ritenaD = Metodes.skPar("Ievadiet riteņa lielumu collās!", 1, 100);
				sedeklPoz =  Metodes.iestatitSedekli();
				cena = Metodes.skPar("Ievadiet cenu!", 1, 10000);
				razotajs = (String)(JOptionPane.showInputDialog("Ievadiet ražotāja nosaukumu!", "Rockmachine"));
				
				riteni.add(new Velosipeds(ritenaD, sedeklPoz, cena, razotajs));
				JOptionPane.showMessageDialog(null, "Velosipēds veiksmīgi tika izveidots~", "Veiksme!", JOptionPane.PLAIN_MESSAGE);
				break;
				case 1: //Kalnu velosipēds
				ritenaD = Metodes.skPar("Ievadiet riteņa lielumu collās!", 1, 100);
				sedeklPoz = Metodes.iestatitSedekli();
				cena = Metodes.skPar("Ievadiet cenu!", 1, 10000);
				razotajs = (String)(JOptionPane.showInputDialog("Ievadiet ražotāja nosaukumu!", "Rockmachine"));
				iestatAtrums = Metodes.iestatitAtrumu();
				String atsp = (String) JOptionPane.showInputDialog(null, "Vai ir papildu amortizācija(atsperes)?", "Izvēle", JOptionPane.QUESTION_MESSAGE,
						null, atbilde, atbilde[0]);
				if(atsp == null || atsp == "Nē") atsperes = false; 
				else atsperes = true;
				riteni.add(new KalnuRitenis(iestatAtrums, atsperes, ritenaD, sedeklPoz, cena, razotajs));
				JOptionPane.showMessageDialog(null, "Kalnu velosipēds veiksmīgi tika izveidots~", "Veiksme!", JOptionPane.PLAIN_MESSAGE);

				break;
				case 2: //Bērnu velosipēds
				ritenaD = Metodes.skPar("Ievadiet riteņa lielumu collās!", 1, 100);
				sedeklPoz = Metodes.iestatitSedekli();
				cena = Metodes.skPar("Ievadiet cenu!", 1, 10000);
				razotajs = (String)(JOptionPane.showInputDialog("Ievadiet ražotāja nosaukumu!", "Rockmachine"));
				String papil = (String) JOptionPane.showInputDialog(null, "Vai ir papildriteņi?", "Izvēle", JOptionPane.QUESTION_MESSAGE,
						null, atbilde, atbilde[0]);
				if(papil == null || papil == "Nē") paligriteni = false; 
				else paligriteni = true;
				String zva = (String) JOptionPane.showInputDialog(null, "Vai ir zvāniņš?", "Izvēle", JOptionPane.QUESTION_MESSAGE,
						null, atbilde, atbilde[0]);
				if(zva == null || zva == "Nē") zvanins = false; 
				else zvanins = true;
				riteni.add(new BernuRitenis(ritenaD, sedeklPoz, cena, razotajs, paligriteni, zvanins));
				JOptionPane.showMessageDialog(null, "Bērnu velosipēds veiksmīgi tika izveidots~", "Veiksme!", JOptionPane.PLAIN_MESSAGE);

				break;
				}
				break;
			case 1: // noņemšana
				if(riteni.size() > 0) {
				
					int NizvID = Metodes.ritenaIzvele(riteni);
					riteni.remove(NizvID);
					JOptionPane.showMessageDialog(null, "Velosipēds tika noņemts!", "Veiksme", JOptionPane.PLAIN_MESSAGE);
				}else {
					
					JOptionPane.showMessageDialog(null, "Sarakstā nav neviens ritenis!", "Brīdinājums", JOptionPane.WARNING_MESSAGE);
				}
			break;
			case 2: //saraksts
				if(riteni.size()>0) {
					String str = "Riteņu skaits~ "+riteni.size()+
							"\n___________________\n";
					for(int i=0; i<riteni.size(); i++) {
						
						str += ((Velosipeds)riteni.get(i)).izvadit()+
								"\n___________________\n";
						}
					JTextArea textArea = new JTextArea(str);
					textArea.setEditable(false);
					textArea.setLineWrap(true);
					textArea.setWrapStyleWord(true);

					JScrollPane scrollPane = new JScrollPane(textArea);
					scrollPane.setPreferredSize(new Dimension(500, 500));

					JOptionPane.showMessageDialog(null, scrollPane, "Velosipēdu saraksts", JOptionPane.INFORMATION_MESSAGE);

					
		}else{
				JOptionPane.showMessageDialog(null, "Sarakstā nav neviens ritenis!", "Brīdinājums", JOptionPane.WARNING_MESSAGE);}
				break;
			case 3: 
				if(riteni.size()>0) {
					String atb = (String) JOptionPane.showInputDialog(null, "Kārtot riteņus pēc cenas augoši?", "Izvēle", JOptionPane.QUESTION_MESSAGE,
							null, atbilde, atbilde[0]);
					if(atb != null) {
						if(atb.equals("Jā")) {
							riteni.sort(null);
							JOptionPane.showMessageDialog(null, "Riteņi sakārtoti augoši", "Kārtošana", JOptionPane.INFORMATION_MESSAGE);
						} else {
							riteni.sort(Collections.reverseOrder());
							JOptionPane.showMessageDialog(null, "Riteņi sakārtoti dilstoši", "Kārtošana", JOptionPane.INFORMATION_MESSAGE);

						}
					}
					
					
				}else {
					JOptionPane.showMessageDialog(null, "Sarakstā nav neviens ritenis!", "Brīdinājums", JOptionPane.WARNING_MESSAGE);
					break;
					
				}
				break;
			}
		}while(izvID != 5);
		
	}
	
	
}
