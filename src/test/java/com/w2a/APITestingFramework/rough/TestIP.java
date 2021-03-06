package com.w2a.APITestingFramework.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.w2a.APITestingFramework.utilities.MonitoringMail;
import com.w2a.APITestingFramework.utilities.TestConfig;

public class TestIP {

	public static void main(String[] args) throws AddressException, MessagingException {
		
		try {
			System.out.println("http://"+InetAddress.getLocalHost()+":8080/job/APITestingFramework/HTML_20Reports/");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MonitoringMail mail = new MonitoringMail();
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody);
	}
}
