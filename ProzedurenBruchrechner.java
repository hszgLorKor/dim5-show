class ProzedurenBruchrechner
{

	public static void main(String[] args)
	{
		String rechenart = args[0];
		String add = "add";
		String sub = "sub";
		String mult = "mult";
		String div = "div";
		int[] input = new int[(args.length - 1)];
		int[] zaehler = new int[(args.length / 2)];
		int[] nenner = new int[(args.length / 2)];
		int[] ergebnis = new int[2];
		
		

		if ((args.length % 2) == 0)
		{
			System.out.println("please input an even amount of numbers");
			System.exit(0);
		}
		else
		{
			for (int i = 1; i < args.length; i++)
			{
				input[(i - 1)] = Integer.parseInt(args[i]);
			}
			System.out.print("input: ");
			int y = 0;
			int x = 0;
			while (x < (input.length / 2)){
				zaehler[x] = input[y];
				nenner[x] = input[y+1];
				System.out.print(zaehler[x] + "/" + nenner[x] + "; ");
				y++;
				y++;
				x++;
			}
			System.out.println();
			
			for (int i = 0; i < nenner.length; i++){
				if (nenner[i] == 0)
				{
					System.out.println("nenner kann nicht null sein");
					System.exit(0);
				}
			}
			
			if (rechenart.equals(add) || rechenart.equals(sub) || rechenart.equals(mult) || rechenart.equals(div))
			{
				if (rechenart.equals(add)){
					ergebnis = Addition(zaehler, nenner);
					Ausgabe(ergebnis);	
				}
				if (rechenart.equals(sub)){
					ergebnis = Subtraktion(zaehler, nenner);
					Ausgabe(ergebnis);
				}
				if (rechenart.equals(mult)){
					System.out.println("Rechenart Multiplikation");
					ergebnis = Multiplikation(zaehler, nenner);
					Ausgabe(ergebnis);
				}
				if (rechenart.equals(div)){
					ergebnis = Division(zaehler, nenner);
					Ausgabe(ergebnis);
				}
			}
			else {
				System.out.println("please input a valid method. (add/sub/mult/div)");
			}
		}
	}
	
	static int ggT(int ergZaehler, int ergNenner) {
		int r;
    		while (ergNenner!=0){
        	r=ergZaehler%ergNenner;
        	ergZaehler=ergNenner;
        	ergNenner=r;
    		}
    		return ergZaehler;
	}
	static void Ausgabe(int ergebnis[]) {
		System.out.println(ergebnis[0] + "/" + ergebnis[1]);
	}
	static int[] Division(int zaehler[], int nenner[]) {
		System.out.println("Rechenart Division");
		for (int i = 0; i < zaehler.length; i++) {
			if (zaehler[i] == 0) {
				System.out.println("zaehler kann bei division nicht null sein");
				System.exit(0);
			}
		}
		int ergZaehler = zaehler[0];
		int ergNenner = nenner[0];
		for (int p = 1; p < zaehler.length; p++) {
			ergZaehler = ergZaehler * nenner[p];
			ergNenner = ergNenner * zaehler[p];
		}
		int gg = ggT(ergZaehler, ergNenner);
		int[] divErgebnis = new int[2];
		divErgebnis[0] = (ergZaehler / gg);
		divErgebnis[1] = (ergNenner / gg);
		return divErgebnis;
	}
	static int[] Multiplikation(int zaehler[], int nenner[]){
		int ergNenner = 1;
		int ergZaehler = 1;
		for (int i = 0; i < zaehler.length; i++)
		{
			ergZaehler = ergZaehler * zaehler[i];
			ergNenner = ergNenner * nenner[i];
		}
		int gg = ggT(ergZaehler, ergNenner);
		int[] multErgebnis = new int[2];
		multErgebnis[0] = (ergZaehler / gg);
		multErgebnis[1] = (ergNenner / gg);
		return multErgebnis;
	}
	static int[] Addition(int zaehler[], int nenner[]){
		int ergZaehler = 0;
		int ergNenner = 1;
		for (int i = 0; i < zaehler.length; i++){
			ergNenner = nenner[i] * ergNenner;
			}
			for (int i = 0; i < zaehler.length; i++){
				ergZaehler = ergZaehler + (zaehler[i] * ergNenner / nenner[i]);
			}
		
		int gg = ggT(ergZaehler, ergNenner);
		int[] addErgebnis = new int[2];
		addErgebnis[0] = (ergZaehler / gg);
		addErgebnis[1] = (ergNenner / gg);
		return addErgebnis;
	}
	static int[] Subtraktion(int zaehler[], int nenner[]){
		int ergZaehler = 0;
		int ergNenner = 1;
		for (int i = 0; i < zaehler.length; i++){
			ergNenner = nenner[i] * ergNenner;
			}
			for (int i = 0; i < zaehler.length; i++){
				ergZaehler = ergZaehler - (zaehler[i] * ergNenner / nenner[i]);
			}
		
		int gg = ggT(ergZaehler, ergNenner);
		int[] subErgebnis = new int[2];
		subErgebnis[0] = (ergZaehler / gg);
		subErgebnis[1] = (ergNenner / gg);
		return subErgebnis;
	}
	
}