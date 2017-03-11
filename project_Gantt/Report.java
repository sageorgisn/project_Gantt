package project;

import java.util.ArrayList;

public class Report  {
	protected ArrayList<Task> tasks;
	
	public Report(ArrayList<Task> tasks){
		this.tasks = tasks;
	}

	public String getReport() {
		String ret="";
		
		for(int i=0; i<tasks.size(); i++){
			ret  = ret+tasks.get(i).getReport()+"\n";
		}
		return ret;
	}
	
	
}
