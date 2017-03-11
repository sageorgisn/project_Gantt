package project;
import java.util.ArrayList;
import java.util.Iterator;
public class TaskManager {
	private ArrayList<Task> allTasks;
	private String name;
	
	public TaskManager(String name) {
		this.name=name;
		this.allTasks = new ArrayList<Task>();
	}
	
	public String getName(){
		return name;
	}
	
	public void addTask(Task task){
		getAllTasks().add(task);
	}
	
	public Task getTask(int i){
		return getAllTasks().get(i);
	}
	
	public int getNumTasks(){
		return getAllTasks().size();
	}
	
	public void reportAllTasks(){
		
		
	}
	public void report(){
		Iterator <Task> ii=getAllTasks().iterator();
		while (ii.hasNext()){
			Task i=ii.next();
			i.showDetails();
		}
	}

	public ArrayList<Task> getAllTasks() {
		return allTasks;
	}

	public Task findTask(int id) {
		for(int i=0; i<this.allTasks.size(); i++){
			Task t=allTasks.get(i);
			if(t.getId()==id){
				return t;
			}
			else {
				Task sub = t.findSubTask(id);
				if(sub!=null){
					return sub;
				}
			}
		}
		return null;
	}
}
