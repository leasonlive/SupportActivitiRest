package com.zxhy.support.workflow;

import java.io.File;
import java.util.List;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zxhy.support.workflow.impl.TaskInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ApplicationContextWorkflow.xml" })
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
	public void startProcessById(){
		this.processEngine.startProcessById("yingyezhizhao:1:35008", "testLTD2", null);
	}
	@Test
	public void startProcessByKey(){
		this.processEngine.startProcessByKey("yingyezhizhao", "start by key test", null);
	}
	@Test
	public void getMyTask(){
		//this.processEngine.getMyTask("gonzo");
		this.processEngine.getMyTask("kermit");
	}
	@Test
	public void getMyTaskWithKey(){
		//this.processEngine.getMyTask("gonzo");
		this.processEngine.getMyTask("135", "realNameAuth");
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
		this.processEngine.completeTask("37508", null);
		//完成任务
	}
	@Test
	public void addUser(){
		this.processEngine.addUser("leasonlive", "leason", "live", "jarden@126.com", "123456");
	}
	@Test
	public void addUserToGroup(){
		this.processEngine.addUserToGroup("leasonlive", "realName");
	}
	@Test
	public void startRealName(){
		JSONArray json = new JSONArray();
		
		JSONObject businessKey = new JSONObject();
		businessKey.put("name", "businessKey");
		businessKey.put("value", "130");
		json.put(businessKey);
		this.processEngine.startProcessByKey("realNameAuth", "130", json);
	}
	@Test
	public void registerFinish(){
		List<TaskInfo> taskList = this.processEngine.getMyTask("130");
		for (TaskInfo taskInfo : taskList) {
			this.processEngine.completeTask(taskInfo.getTaskId(), null);
		}
	}
}
