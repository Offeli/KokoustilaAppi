package com.varausjarjestelma.sähköposti;
import javax.mail.MessagingException;

public class EmailMain {

	public static void main(String[] args) throws MessagingException {
		
		//Parametrinä osoite johon viesti lähetetään
		SimppeliMaili.sendMail("eemeli.niemi@gmail.com");
        
	}

}


