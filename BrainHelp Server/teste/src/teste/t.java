package teste;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class t {

	public static void main(String[] args) {
		HtmlEmail email = new HtmlEmail();
        email.setDebug(true);  
        email.setHostName("smtp.gmail.com");
        email.setAuthentication("appbrainhelp@gmail.com","senhasegura");  
        email.setSSL(true);  
        try {
        	email.addTo("parragafernandes@gmail.com");
			email.setFrom("appbrainhelp@gmail.com", "Brain Help");
			email.setSubject("Solicitação de refinição de senha");
			   
			String pathHtml = new File(".").getCanonicalPath()+"/src/teste/index.html";			
			String pathLogo = new File(".").getCanonicalPath()+"/src/teste/sem-nome.png";
			String pathIdosos = new File(".").getCanonicalPath()+"/src/teste/idosos.jpg";
			
			String cid = email.embed(new File(pathLogo));
			String cidIdosos = email.embed(new File(pathIdosos));
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathHtml)));
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
			    sb.append(line).append("\n");
			} 
			   
			String html = sb.toString().replace("#LOGO", "cid:"+cid).replace("#IDOSOS", "cid:"+cidIdosos).replace("#LINKBOTAO", "naofunfa");
			
			email.setHtmlMsg(html);
			email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 
	}

}
