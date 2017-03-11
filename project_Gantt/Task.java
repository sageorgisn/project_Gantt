package project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//gia to date xrhsimopoihthike to:
//http://www.tutorialspoint.com/java/java_date_time.htm
public class Task {
	private	int id;
	private Date startDate;
	private Date endDate;
	private ArrayList<Task> predecessors;
	private ArrayList<Task> subtasks;
	private Task parentTask;
	private String info;
	private String taskname;
	private String status;
	private int cost;
	private static int idcounter=1;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ArrayList<Task> getPredecessors() {
		return predecessors;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Task(String taskname, Date startDate, Date endDate) 
	{
		this.id = idcounter;
		idcounter++;
		this.startDate = startDate;
		this.endDate = endDate;
		this.taskname = taskname;
		//this.info = info;
		status = "Inactive";
		this.predecessors = new ArrayList<Task>();
		this.parentTask=null;
		this.subtasks = new ArrayList<Task>();
	}
	
	public Task getParentTask() {
		return parentTask;
	}

	public void setParentTask(Task parentTask) {
		this.parentTask = parentTask;
	}

	public ArrayList<Task> getSubtasks() {
		return subtasks;
	}

	public void addSubTask(Task t){
		subtasks.add(t);
	}
	
	public void addPredecessor(Task t){
		predecessors.add(t);
	}
	
	public boolean isComplex()
	{
		if(this.parentTask==null){
			return true;
		}
		return false;
	}
	
	public void changeParameter(int id,String task,String info,int duration,String status){
		
	}
	public void changeParameter(int id,String task,int predecessors,String info,int duration,String status){
		
	}
	public void showDetails(){
		
	}

	public String getReport() {
		String ret="";
		Date diff = new Date(endDate.getTime() - startDate.getTime());
		String pred = "";
		for(int i=0; i<this.predecessors.size(); i++){
			pred= pred+predecessors.get(i).getId()+";";
		}
		if(this.parentTask==null){
			ret = String.format("%3d. %-30s\t%-10s\t%10d\t%10s", id,taskname,pred,diff.getDate(),status);
		}
		else{
			ret = String.format("%3d. %30s\t%-10s\t%10d\t%10s", id,taskname,pred,diff.getDate(),status);
		}
		return ret;
	}

	public Task findSubTask(int id) {
		for(int i=0; i<this.subtasks.size(); i++){
			Task t=subtasks.get(i);
			if(t.getId()==id){
				return t;
			}
		}
		return null;
	}

	public String getSaveLine() {
		String ret="";
		if(this.parentTask==null){
			ret="SIMPLE";
		}
		else{
			ret="SUB";
		}
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd");
		ret+="|"+this.taskname;
		ret+="|"+ft.format(this.startDate);
		ret+="|"+ft.format(this.endDate);
		ret+="|"+this.status;
		if(predecessors.size()>0){
			ret = ret +"|" + predecessors.get(0).getId();
		}
		for(int i=1; i<this.predecessors.size(); i++){
			ret = ret +","+predecessors.get(i).getId();
		}
		
		return ret;
	}
	
}
