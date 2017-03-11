package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

public class LoadingTxt {

	public LoadingTxt() {
	}
	
	public static TaskManager readFile(String filename)
	{
		Scanner scan;
		TaskManager taskman =null;
		String line,name;
		String tokens[];
		String type, taskname;
		Date startdate, enddate;
		String pred;
		String status;
		String predids[];
		Task newtask;
		Task lastsimple=null;
		try {
			scan = new Scanner(new FileInputStream(filename));
			name = scan.nextLine(); //Prwth grammh exei to onoma
			taskman = new TaskManager(name);
			
			while(scan.hasNextLine()){
				line = scan.nextLine();
				tokens = line.split("\\|");
				type = tokens[0];
				taskname = tokens[1];
				startdate = new Date(tokens[2]);
				enddate = new Date(tokens[3]);
				newtask = new Task(taskname,startdate,enddate);
				status = tokens[4];
				newtask.setStatus(status);
				if(type.equals("SIMPLE")){
					taskman.addTask(newtask);
					lastsimple=newtask;
				}
				else{
					newtask.setParentTask(lastsimple);
					lastsimple.addSubTask(newtask);
				}
				if(tokens.length>5){
					predids = tokens[5].split(",");
					for(int j =0; j<predids.length; j++){ //gia kathe prokatwxo prepei na ton prosthesw sto task
						int id = Integer.parseInt(predids[j]);
						Task findtask = taskman.findTask(id);
						if(findtask!=null)
							newtask.addPredecessor(findtask);
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("error reading file");
		}
		
		
		
		return taskman;
	}

}
