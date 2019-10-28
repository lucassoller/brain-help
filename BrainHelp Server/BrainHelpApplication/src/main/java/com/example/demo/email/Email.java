package com.example.demo.email;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
//import org.apache.commons.mail.EmailException;
//import org.apache.commons.mail.HtmlEmail;

public class Email {
	public void enviarEmail(String emailRedefinicao, String url){
//		HtmlEmail email = new HtmlEmail();
//        email.setDebug(true);  
//        email.setHostName("smtp.gmail.com");
//        email.setAuthentication("appbrainhelp@gmail.com","senhasegura");  
//        email.setSSL(true);  
//        try {
//        	email.addTo(emailRedefinicao);
//			email.setFrom("appbrainhelp@gmail.com", "Brain Help");
//			email.setSubject("Solicitação de redefinição de senha");
//			   
//			String pathHtml = new File(".").getCanonicalPath()+"/src/main/java/com/example/demo/email/index.html";			
//			String pathLogo = new File(".").getCanonicalPath()+"/src/main/java/com/example/demo/email/sem-nome.png";
//			String pathIdosos = new File(".").getCanonicalPath()+"/src/main/java/com/example/demo/email/idosos.jpg";
//			
//			String cid = email.embed(new File(pathLogo));
//			String cidIdosos = email.embed(new File(pathIdosos));
//			
//			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathHtml)));
//			String line;
//			StringBuilder sb = new StringBuilder();
//			while ((line = br.readLine()) != null) {
//			    sb.append(line).append("\n");
//			} 
//			   
//			String html = sb.toString().replace("#LOGO", "cid:"+cid).replace("#IDOSOS", "cid:"+cidIdosos).replace("#LINKBOTAO", url);
//			
//			email.setHtmlMsg(html);
//			email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");
//			email.send();
//		} catch (EmailException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}      
	}
}
