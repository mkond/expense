package com.expense.objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/*@Service("sendMail")*/
public class SendMail {
	
		@Autowired
		private MailSender mailSender;
		
		public void setMailSender(MailSender mailSender) {
			this.mailSender = mailSender;
		}
		
		public void sendMail(String from, String to, String subject, String msg) {
	 
			final String Ffrom = from;
			final String Fto = to;
			final String Fsubject = subject;
			final String Fmsg = msg;
			
			Thread thread = new Thread(){
					public void run(){
						SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
						simpleMailMessage.setFrom(Ffrom);
						simpleMailMessage.setTo(Fto);
						simpleMailMessage.setSubject(Fsubject);
						simpleMailMessage.setText(Fmsg);
						mailSender.send(simpleMailMessage);
					}
			};
			thread.start();
	
		}
	
}
