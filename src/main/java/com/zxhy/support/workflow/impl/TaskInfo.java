package com.zxhy.support.workflow.impl;

import org.activiti.engine.impl.util.json.JSONObject;

public class TaskInfo {
	private String taskId;
	private String name;
	private String processDefinitionId;
	private String processDefinitionName;
	private String startDate;
	private String dualDate;
	public TaskInfo() {
		// TODO Auto-generated constructor stub
	}
	public TaskInfo(JSONObject json){
		this.taskId = json.getString("id");
		this.name = json.getString("name");
		this.processDefinitionId = json.getString("processDefinitionId");
		this.startDate = json.getString("createTime");
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getProcessDefinitionName() {
		return processDefinitionName;
	}
	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getDualDate() {
		return dualDate;
	}
	public void setDualDate(String dualDate) {
		this.dualDate = dualDate;
	}
	
}
