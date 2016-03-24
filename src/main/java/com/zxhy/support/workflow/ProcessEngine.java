package com.zxhy.support.workflow;

import java.util.List;

import org.activiti.engine.impl.util.json.JSONArray;

import com.zxhy.support.workflow.impl.TaskInfo;

/**
 * 流程引擎
 * 在使用activiti的时候需要1、加入oracle驱动、2修改db.property文件、3在activiti-custom-context.xml的engine配置中增加<property name="databaseSchema" value="XJLFLOW"/><br>
 * 流程图中看到的字体是乱码，修改了流程发布字符集，参考activiti自建工程包org.activiti.editor.ui.EditorProcessDefinitionDetailPanel
 * @author leasonlive
 *
 */
public interface ProcessEngine {
	/**
	 * 得到所有的发布流程
	 * @param start 从第几个开始算
	 * @return
	 */
	public String getDeployments(int start);
	/**
	 * 得到一个发布流程的信息
	 * @param deployId
	 * @return
	 */
	public String getDeployment(String deployId);
	/**
	 * 得到所有的流程定义
	 * @param start 从第几个开始计算
	 * @return
	 */
	public String getProcessDefinitions(int start);
	/**
	 * 得到流程定义
	 * @param processDefinitionId 流程id
	 * @return
	 */
	public String getProcessDefinitionById(String processDefinitionId);
	/**
	 * 得到流程定义
	 * @param processDefinitionKey 流程Key
	 * @return
	 */
	public String getProcessDefinitionByKey(String processDefinitionKey);
	
	/**
	 * 得到一个流程图
	 * @param processDefinitionId
	 * @return
	 */
	public byte[] getProcessDefinitionImage(String processDefinitionId);
	/**
	 * 启动一个业务流程
	 * 
	 * @param processDefinitionId  流程定义ID
	 * @param businessKey 业务Key
	 * @param variables 启动流程实例变量
	 * @return 流程实例ID
	 */
	public String startProcessById(String processDefinitionId, String businessKey,JSONArray variables);
	/**
	 * 启动一个业务流程
	 * @param processDefinitionKey 流程定义Key
	 * @param businessKey
	 * @param variables 启动流程实例变量
	 * 	@return 流程实例ID
	 */
	public String startProcessByKey(String processDefinitionKey, String businessKey,JSONArray variables);
	/**
	 * 得到所有待办任务
	 * @param userId 用户id
	 * @return
	 */
	public List<TaskInfo> getMyTask(String userId);
	/**
	 * 得到待办任务列表
	 * @param userId 用户id
	 * @param processDefinitionKey 流程key
	 * @return
	 */
	public List<TaskInfo> getMyTask(String userId, String processDefinitionKey);
	/**
	 * 可以认领的任务
	 * @param userId
	 * @return
	 */
public List getCandidaTask(String userId);
	/**
	 * 得到所有组的任务，还没有分配给我，但是我可以认领的
	 * 
	 * @return
	 */
	public List getGroupTask(String userId);
	/**
	 * 认领任务
	 */
	public void claimTask(String taskId, String userId);

	/**
	 * 执行任务
	 * 
	 * @param taskId
	 */
	public void completeTask(String taskId, JSONArray variables);
	/**
	 * 得到我参与过的任务
	 * @param userId
	 * @return
	 */
	public List getHistoryTask(String userId);
	/**
	 * 增加一个用户到realNameGroup
	 * 
	 */
	public void addUser(String id, String firstName, String lastName, String email,String password);
	public void addUserToGroup(String userId, String groupId);
}
