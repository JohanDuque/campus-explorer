package it.polimi.iol.duque.bean;
    import java.util.Arrays;


public class Impiccato {
	
	int numTentativi;
	int maxTentativi=0;
	char [] parolaSegreta;
	char [] trattini;
	char [] lettereInserite;
	boolean successo;

    public Impiccato() {
        reset();              
    }
  
    public void reset() {                
        successo = false;
        numTentativi = 1;
        maxTentativi = 7;
        lettereInserite = new char[26];
        for (int i=0; i < lettereInserite.length; i++){
            lettereInserite[i]=' ';
        }
        parolaSegreta = estraiParola();
        trattini = new char[parolaSegreta.length];
        for (int i=0; i < parolaSegreta.length; i++){
            trattini[i]='-';
        }
    }  
   
  	public char[] estraiParola() {

  		/* QUI, SE DESIDERA, LO STUDENTE PUo INCLUDERE UN DATABSE PER RECUPERARE DINAMICAMENTE LE PAROLE */
  			
//    	try {          
//            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            ResultSet rs1 = null, rs2 = null;
//            // Conta quante parole sono nel db
//            int numParole = 0;
//            rs1 = statement.executeQuery("SELECT COUNT(*) FROM parola");
//            rs1.next();
//            numParole = rs1.getInt(1);
//            
//            // Estrai una parola casuale
//            String parolaEstratta="";
//            int indice = Math.abs(new Random().nextInt(numParole));
//            rs2 = statement.executeQuery("SELECT nome FROM parola");
//            rs2.absolute(indice+1);
//            parolaEstratta=rs2.getString("nome").toUpperCase();
//            return parolaEstratta.toCharArray();
//            
//        } catch(Exception e){ e.printStackTrace(); }
       	
  		return "LAUREAONLINE".toCharArray();
   	}

    public void setParolaUtente(String parolaUtente) {      
    	                         
        char[] arrayUtente = parolaUtente.toUpperCase().toCharArray();
        
		if (arrayUtente.length == 1)   {
				
			//Array dei caratteri totali inseriti 
			boolean nuovaLettera = true;
			int x=0;
			for (; x < lettereInserite.length && lettereInserite[x] != ' '; x++){ 
				if (lettereInserite[x] == arrayUtente[0]){
					nuovaLettera = false;
				}
			}
			if (nuovaLettera == true) lettereInserite[x]=arrayUtente[0];
			
			//Array dei caratteri indovinati misti ai trattini
			boolean nuovaScoperta = false;
			for (int i=0; i < parolaSegreta.length; i++){   
			   	if (parolaSegreta[i] == arrayUtente[0] && trattini[i]=='-') {
					trattini[i] = arrayUtente[0];      
					nuovaScoperta=true;
			   	}
			}
			
			if (nuovaLettera == true && nuovaScoperta==false) {
				numTentativi++;
			}
			
			if (Arrays.equals(parolaSegreta, trattini)){
			   	successo = true;
			}        
		}
		
		//Nel caso di inserimento di parola intera
		else{   
			if (Arrays.equals(parolaSegreta, arrayUtente)){
			   successo = true;
			} else {
			   numTentativi++;
			}
		}
	}
  
    public int getMaxTentativi() {
        return maxTentativi;
    }

    public String getLettereInserite() {
        return String.valueOf(lettereInserite);
    }

    public String getParolaSegreta() {
       	return String.valueOf(parolaSegreta);
    }

    public boolean getSuccesso() {
        return successo;
    }

    public String getTrattini() {
        return String.valueOf(trattini);
    }

    public int getNumTentativi() {
        return numTentativi;
    }
}
