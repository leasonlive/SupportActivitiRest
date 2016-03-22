package com.zxhy.support.workflow;

import java.io.File;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ApplicationContext.xml" })
public class ProcessEngineTest {
	@Autowired
	ProcessEngine processEngine;

	@Test
	public void getDeployments() {
		String deployments = processEngine.getDeployments(0);
		System.out.println("deployments" + ":" + deployments);
	}
	@Test
	public void getDeployment(){
		String deployment = processEngine.getDeployment("4408");
		System.out.println("deployment:" + deployment);
	}
	@Test
	public void getProcessDefinitions(){
		String processDefinitions00 = this.processEngine.getProcessDefinitions(0);
		System.out.println("definitions00:" + processDefinitions00);
		String processDefinitions11 = this.processEngine.getProcessDefinitions(10);
		System.out.println("definitions11:" + processDefinitions11);
	}
	@Test
	public void getProcessDefinition(){
		String processDefinition = this.processEngine.getProcessDefinitionById("yingyezhizhao:1:35008");
		JSONObject json = new JSONObject(processDefinition);
		Assert.assertEquals("营业执照", json.getString("name"));
	}
	@Test
	public void getProcessDefinitionImage(){
		byte[] image = this.processEngine.getProcessDefinitionImage("yingyezhizhao:1:35008");
		 try {
				FileUtils.writeByteArrayToFile(new File("d:/FixSystemFailureProcess.png"), image);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}
	@Test
	public void startProcess(){
		this.processEngine.startProcess("yingyezhizhao:1:35008", "testLTD2");
	}
	@Test
	public void getMyTask(){
		//this.processEngine.getMyTask("gonzo");
		this.processEngine.getMyTask("kermit");
	}
	@Test
	public void getCandidatTask(){
		this.processEngine.getCandidaTask("gonzo");
	}
	@Test
	public void getGroupTask(){
		this.processEngine.getGroupTask("gonzo");
	}
	@Test
	public void completeTask(){
		this.processEngine.completeTask("37508");
	}
}
