package project;

import java.util.ArrayList;

public class ZoomOneReport extends Report{


	public ZoomOneReport(ArrayList<Task> tasks) {
		super(tasks);
	}
	
	public String getReport(Task selected) {
		String ret="";
		
		for(int i=0; i<tasks.size(); i++){
			ret  = ret+tasks.get(i).getReport()+"\n";
			if(tasks.get(i)!=selected) continue;
			
			ArrayList<Task> subs = tasks.get(i).getSubtasks();
			for(int j=0; j<subs.size() ; j++){
				ret = ret + subs.get(j).getReport()+"\n";
			}
		}
		return ret;
	}
}
