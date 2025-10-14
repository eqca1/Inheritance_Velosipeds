package nhk;

public class KalnuRitenis extends Velosipeds {
	// atr
	private int iestatAtrums;
	private boolean atsperes;
	
	
	// konstr
	public KalnuRitenis(int iestatAtrums, boolean atsperes,
			int ritenaD, int sedeklPoz, double cena, String razotajs) {
		
		super(ritenaD, sedeklPoz, cena, razotajs);
		this.iestatAtrums = iestatAtrums;
		this.atsperes = atsperes;
		
		
	}
	
	// metodes
	public int noteiktIestatAtr() {
		return iestatAtrums;
	}
	public void parslegtAtr(int atr) {
		iestatAtrums = atr;
		
	}
	
	public String izvadit() {
		return "Riteņa ražotājs ~ "+noteiktRaz()+"\nRiteņa cena ~ "+noteiktCenu()+" EUR\nRiteņa lielums collās ~ "+noteiktRitenaD()
		+"\nIestatītais sēdekļa augstums ~ "+noteiktSedeklPoz()+"\nĀtrums ~ "+noteiktAtr()+" m/s"
		+"\nIestatītais ātrums ~ "+noteiktIestatAtr()+
		"\nPapildus amortizācija ~ "+((atsperes)? "IR" : "NAV");
		
	}
}
