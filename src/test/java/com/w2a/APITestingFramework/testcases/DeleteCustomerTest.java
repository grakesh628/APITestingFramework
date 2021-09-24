package com.w2a.APITestingFramework.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.json.JSONObject;

import com.w2a.APITestingFramework.APIs.DeleteCustomerAPI;
import com.w2a.APITestingFramework.setup.BaseTest;
import com.w2a.APITestingFramework.utilities.DataUtil;
import com.w2a.APITestingFramework.utilities.TestUtil;

import io.restassured.response.Response;

public class DeleteCustomerTest extends BaseTest{

	@Test(dataProviderClass=DataUtil.class, dataProvider = "data")
	public void deleteCustomer(Hashtable<String, String> data) {

		Response response = 
				DeleteCustomerAPI.sendDeleteRequestToDeleteCustomerAPIWithValidID(data);

//		ExtentListeners.testReport.get().info(data.toString());
		response.prettyPrint();
		//Inside delete --- stat
		System.out.println(response.statusCode());
//		String actual_id = response.jsonPath().get("id").toString();
//		System.out.println("ID from response Json : "+actual_id);
//		
//		Assert.assertEquals(actual_id, data.get("id"),"ID not matching");
		
		/*
		 * JSONObject jsonObject = new JSONObject(response.asString());
		 * jsonObject.has("id");
		 */
		System.out.println("Check for the presence of Object key :"+TestUtil.jsonHasKey(response.asString(),"object"));
		System.out.println("Check for the presence of Deleted key :"+TestUtil.jsonHasKey(response.asString(),"deleted"));
		
		Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "id"),"ID key is not present in response");
		/*
		 * String actual_id = jsonObject.getString("id");
		 * System.out.println("ID from response Json : "+actual_id);
		 */	
		Assert.assertEquals(TestUtil.getJsonKeyValue(response.asString(),"id"), data.get("id"),"ID not matching");
		
		System.out.println("Object key value is : "+TestUtil.getJsonKeyValue(response.asString(),"object"));
		System.out.println("Deleted key value is : "+TestUtil.getJsonKeyValue(response.asString(),"deleted"));
		
	}
}
