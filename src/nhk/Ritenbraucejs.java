package nhk;

import java.awt.Dimension;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Ritenbraucejs {


	
	public static void main(String[] args) throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		
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
				
			case 4:
				if(riteni.size()>0) {
					
					int NizveID = Metodes.ritenaIzvele(riteni);
					String[] metodes = {"Noteikt riteņa izmēru", "Iestatīt sēdekli", "Noteikt kustības ātrumu",
							"Mīties", "Bremzēt", "Noteikt iestatīto ātrumu", "Pārslēgt ātrumu", "Palīgriteņi", "Zvaniņš"};

					String izvele1 = (String) JOptionPane.showInputDialog(null, "Izvēlies metodi", "Metodes izvēle", JOptionPane.QUESTION_MESSAGE,
							null, metodes, metodes[0]);
					if(izvele1 == null) {
						break;
					}
					int izvelesID1 = Arrays.asList(metodes).indexOf(izvele1);	
					switch(izvelesID1) {
					case 0:
						JOptionPane.showMessageDialog(null, "Riteņa izmērs ir "+(((Velosipeds) riteni.get(NizveID)).noteiktRitenaD())+" cm, "
								+ "un sedekļa pozīcija ir "+(((Velosipeds) riteni.get(NizveID)).noteiktSedeklPoz())+".", "Noteikt riteņa izmēru", 
								JOptionPane.INFORMATION_MESSAGE);
					break;
					case 1:
						
						((Velosipeds) riteni.get(NizveID)).iestatitSedeklaPoz(Metodes.iestatitSedekli());
						JOptionPane.showMessageDialog(null, "Sedekļa pozīcija veiksmīgi iestatīta",
								"Veiksme", JOptionPane.INFORMATION_MESSAGE);
					break;
					case 2:
						JOptionPane.showMessageDialog(null, "Riteņa kustība ātrums ir "+(((Velosipeds) riteni.get(NizveID)).noteiktAtr())+" m/s.", "Noteikt kustības ātrumu", 
								JOptionPane.INFORMATION_MESSAGE);
					break;
					case 3:
						((Velosipeds) riteni.get(NizveID)).mities(Integer.parseInt(JOptionPane.showInputDialog(null, "Cik mītienu jūs grībāt uztaisīt?", "Mities", JOptionPane.QUESTION_MESSAGE)));
					break;
					case 4: 
						((Velosipeds) riteni.get(NizveID)).bremzet(Integer.parseInt(JOptionPane.showInputDialog(null, "Cik jūs grībāt uzbremzēt?", "Bremzēt", JOptionPane.QUESTION_MESSAGE)));
					break;
					case 5:
						if(riteni.get(NizveID) instanceof KalnuRitenis) {
							JOptionPane.showMessageDialog(null, "Iestatītais ātrums ir "+((KalnuRitenis)riteni.get(NizveID)).noteiktIestatAtr()+".", 
									"Noteikt iestatīto ātrumu", JOptionPane.INFORMATION_MESSAGE);
						}else JOptionPane.showMessageDialog(null, "Šim riteņa veidam nav iestatītie ātrumi~", "Kļūda", JOptionPane.ERROR_MESSAGE);
						
					break;
					case 6:
						if(riteni.get(NizveID) instanceof KalnuRitenis) {
							((KalnuRitenis) riteni.get(NizveID)).parslegtAtr(Metodes.iestatitAtrumu());
						}else JOptionPane.showMessageDialog(null, "Šim riteņa veidam nav iestatītie ātrumi~", "Kļūda", JOptionPane.ERROR_MESSAGE);
					break;
					case 7:
						if(riteni.get(NizveID) instanceof BernuRitenis) {
							boolean darbiba;
							String izv2 = (String) JOptionPane.showInputDialog(null, "Veikt darbību ar palīgrīteņiem?", "Izvēle", JOptionPane.QUESTION_MESSAGE,
									null, atbilde, atbilde[0]);
							if(izv2 == null || izv2 == "Nē") darbiba = false; 
							else darbiba = true;
							
							((BernuRitenis)riteni.get(NizveID)).darbArPaligrit(darbiba);
						}else JOptionPane.showMessageDialog(null, "Šim riteņa veidam nav palīgriteņi~", "Kļūda", JOptionPane.ERROR_MESSAGE);
			
					break;
						case 8:
						if(riteni.get(NizveID) instanceof BernuRitenis) {
							((BernuRitenis)riteni.get(NizveID)).zvanitZvaninu();
						}else JOptionPane.showMessageDialog(null, "Šim riteņa veidam nav zvaniņš~", "Kļūda", JOptionPane.ERROR_MESSAGE);
						
					break;
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Sarakstā nav neviens ritenis!", "Brīdinājums", JOptionPane.WARNING_MESSAGE);
					break;
				}
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "Programma apturēta~", "Brīdinājums", JOptionPane.WARNING_MESSAGE);
				break;
			}
		}while(izvID != 5);
		
	}
	
	
}
