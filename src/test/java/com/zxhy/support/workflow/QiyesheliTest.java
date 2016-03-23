package com.zxhy.support.workflow;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:ApplicationContext*.xml" })
public class QiyesheliTest {
	@Autowired
	ProcessEngine processEngine;
	@Test
	public void startProcess(){
		JSONArray form = new JSONArray();
		form.put(this.createjson("qiyemingcheng", "大学生创业公司0"));
		form.put(this.createjson("farenshenfenzheng", "0"));
		form.put(this.createjson("shifoudaxuesheng", true));
		String processId = this.processEngine.startProcessByKey("qiyesheli", "大学生创业0",form);
		Assert.assertNotNull(processId);
	}
	@Test
	public void yushenList(){
		//查询队列中的任务
		this.processEngine.getCandidaTask("yushen.a");
	}
	@Test
	public void yushenAClaimTask(){
		//没有认领之前是在队列中的，认领之后是在待办列表中的
		this.processEngine.claimTask("37558", "yushen.a");
	}
	@Test
	public void yushenComple(){
		JSONArray form = new JSONArray();
		this.processEngine.completeTask("37558", form);
	}
	@Test
	public void shouliAList(){
		//查询队列中的任务
		this.processEngine.getCandidaTask("shichang.shouli.A");
	}
	@Test
	public void shouliAClaimTask(){
		//没有认领之前是在队列中的，认领之后是在待办列表中的
		this.processEngine.claimTask("35039", "shichang.shouli.A");
	}
	@Test
	public void shouliAComple(){
		JSONArray form = new JSONArray();
		this.processEngine.completeTask("35039", form);
	}
	JSONObject createjson(String name, Object value){
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("value", value);
		return json;
	}
}
