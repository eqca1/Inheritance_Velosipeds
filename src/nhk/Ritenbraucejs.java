package nhk;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

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
				iestatAtrums = Metodes.skPar("Ievadiet iestatīto ātrumu!", 1, 500);
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
			
			}
		}while(izvID != 5);
		
	}
	
	
}
