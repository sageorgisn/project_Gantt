package project;

import java.util.Date;
import java.util.Scanner;

public class GanttEngine {

	private static TaskManager project = null;
	private static  Scanner input=null;
	
	public static void showOptions()
	{
		System.out.println("1.Create Project");
		System.out.println("2.Add Task");
		System.out.println("3.Change Task");
		System.out.println("4.Simple Report");
		System.out.println("5.Zoom in ONE Report");
		System.out.println("6.Zoom in ALL Report");
		System.out.println("7.Save Txt");
		System.out.println("8.Save Html");
		System.out.println("9.Load from Txt");
		System.out.println("0.Exit");
	}
	
	
	public static void UseCases(int choice)
	{
		if(choice==1){
			createProject();
		}
		else if(choice==2){
			if(project!=null){
				addTask();
			}
			else{
				System.out.println("No project Opened");
			}
		}
		else if(choice==3){
			if(project!=null){
				changeTask();
			}
			else{
				System.out.println("No project Opened");
			}
		}
		else if(choice==4){
			if(project!=null){
				simpleReport();
			}
			else{
				System.out.println("No project Opened");
			}
		}
		else if(choice==5){
			if(project!=null){
				zoominOneReport();
			}
			else{
				System.out.println("No project Opened");
			}
		}
		else if(choice==6){
			if(project!=null){
				zoominAllReport();
			}
			else{
				System.out.println("No project Opened");
			}
		}
		else if(choice==7){
			if(project!=null){
				saveTxt();
			}
			else{
				System.out.println("No project Opened");
			}
		}
		else if(choice==8){
			System.out.println("Not Implemented yet! :)");
		}
		else if(choice==9){
			readFromTxt();
		}
		else if(choice==0){
			System.out.println("Bye Bye");
		}
		
	}
	
	
	private static void saveTxt(){
		System.out.println("Dwse Onoma arxeiou");
		String filename= input.next();
		IVisualizer ivis = new SimpleTxt(filename);
		ivis.visualize(project);
		
	}
	
	private static void changeTask(){
		Task findtask=null;
		do{
			System.out.println("Dwse Taskid: ");
			int id = input.nextInt();
			findtask =project.findTask(id);
			if(findtask==null){
				System.out.println("Lathos Task id");
			}
		}while(findtask==null);
		
		System.out.println("1. Change Status");
		System.out.println("2. Change Start Date");
		System.out.println("3. Change End Date");
		if(findtask.isComplex()){
			System.out.println("4. Add SubTask");
		}
		int choice = input.nextInt();
		if(choice==1){
			System.out.println("Dwse neo status:");
			String newstat = input.next();
			findtask.setStatus(newstat);
		}
		else if(choice==2){
			System.out.println("Dwse neo StartDate:");
			String strdate = input.next();
			Date date = new Date(strdate);
			findtask.setStartDate(date);
		}
		else if(choice==3){
			System.out.println("Dwse neo EndDate:");
			String strdate = input.next();
			Date date = new Date(strdate);
			findtask.setEndDate(date);;
		}
		else if(choice==4 && findtask.isComplex()){
			Task t = readTaskFromUser();
			findtask.addSubTask(t);
			t.setParentTask(findtask);
		}
	}
	
	private static void readFromTxt(){
		System.out.println("Dwse Onoma arxeiou");
		String filename = input.next();
		project = LoadingTxt.readFile(filename);
	}
	
	private static void simpleReport() {
		Report rep  = new Report(project.getAllTasks());
		String firstrow = String.format("%3s. %-30s\t%-10s\t%10s\t%10s", "id","TaskName","Pred","Duration","Status");
		String dots = "----------------------------------------------------------------------------------";
		System.out.println(firstrow);
		System.out.println(dots);
		System.out.println(rep.getReport());
	}
	
	private static void zoominOneReport(){
		System.out.println("Dwse selected task id:");
		int id = input.nextInt();
		Task findtask = project.findTask(id);
		ZoomOneReport rep  = new ZoomOneReport(project.getAllTasks());
		String firstrow = String.format("%3s. %-30s\t%-10s\t%10s\t%10s", "id","TaskName","Pred","Duration","Status");
		String dots = "----------------------------------------------------------------------------------";
		System.out.println(firstrow);
		System.out.println(dots);
		System.out.println(rep.getReport(findtask));
	}
	
	private static void zoominAllReport(){
		Report rep  = new ZoomAllReport(project.getAllTasks());
		String firstrow = String.format("%3s. %-30s\t%-10s\t%10s\t%10s", "id","TaskName","Pred","Duration","Status");
		String dots = "----------------------------------------------------------------------------------";
		System.out.println(firstrow);
		System.out.println(dots);
		System.out.println(rep.getReport());
	}

	private static Task readTaskFromUser(){
		System.out.println("Dwse Onoma:");
		String name = input.next();
		//System.out.println("Dwse cost:");
		//int cost= input.nextInt();
		System.out.println("Dwse Start Date(year/month/day):");
		String startDate = input.next();
		Date date1 =new Date(startDate);
		System.out.println("Dwse End Date(year/month/day):");
		String endDate = input.next();
		Date date2 =new Date(endDate);
		Task t = new Task(name,date1, date2);
		System.out.println("Dwse Plithos Predecessors: ");
		int count = input.nextInt();
		Task findtask=null;
		for(int i=0; i<count; i++){
			do{
				System.out.println("Dwse id "+(i+1)+"-osto Predecessor: ");
				int findid = input.nextInt();
				findtask = project.findTask(findid);
				if(findtask==null){
					System.out.println("Den yparxei ayto to id");
				}
			}while(findtask==null);
			t.addPredecessor(findtask);
		}
		return t;
	}
	

	private static void addTask() {
		Task t = readTaskFromUser();
		project.addTask(t);
		System.out.println("Poso subTasks exei to task: ");
		int numsub = input.nextInt();
		for(int i=0; i<numsub; i++){
			Task subt = readTaskFromUser();
			t.addSubTask(subt);
			subt.setParentTask(t);
		}
	}


	
	public static void createProject()
	{
		System.out.println("Dwse Onoma");
		String name = input.next();
		project = new TaskManager(name);
	}
	
	public static void main(String[] args) {
		int choice=0;
		input = new Scanner(System.in);
		do{
			showOptions();
			choice = input.nextInt();
			UseCases(choice);
		}while(choice!=0);
	}

}
