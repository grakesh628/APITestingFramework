package com.w2a.APITestingFramework.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.APITestingFramework.APIs.CreateCustomerAPI;
import com.w2a.APITestingFramework.listeners.ExtentListeners;
import com.w2a.APITestingFramework.setup.BaseTest;
import com.w2a.APITestingFramework.utilities.DataUtil;

import io.restassured.response.Response;

import java.util.Hashtable;

public class CreateCustomerTest extends BaseTest {

	@Test(dataProviderClass=DataUtil.class, dataProvider = "data")
	public void ValidateCreateCustomerAPIWithValidSecretKey(Hashtable<String, String> data) {

		Response response = 
				CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithValidAuth(data);

		ExtentListeners.testReport.get().info(data.toString());
		response.prettyPrint();
		
		System.out.println(response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);
	}

	
	  @Test(dataProviderClass=DataUtil.class, dataProvider = "data")
	  public void ValidateCreateCustomerAPIWithInValidSecretKey(Hashtable<String, String> data) {
	  	  
	  Response response = 
			  CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithInValidAuth(data);
	  ExtentListeners.testReport.get().info(data.toString());
	  response.prettyPrint();
	  
	  System.out.println(response.statusCode());
	  Assert.assertEquals(response.statusCode(), 200);
	  
	  }
	 

	
}
