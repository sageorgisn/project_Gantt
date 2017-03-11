package project;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;

//import commons.*;

public class SimpleTxt implements IVisualizer{
	private String filename;
	public SimpleTxt(String filename) {
		this.filename = filename;
	}

	@Override
	public void visualize(TaskManager  m) {
		ArrayList<Task> tasks = m.getAllTasks();
		try {
			PrintWriter printer = new PrintWriter(new File(filename));
			printer.println(m.getName());
			for(int i=0; i<tasks.size(); i++){
				printer.println(tasks.get(i).getSaveLine());
				ArrayList <Task> subs = tasks.get(i).getSubtasks();
				for(int j=0; j<subs.size(); j++){
					printer.println(subs.get(j).getSaveLine());
				}
			}
			printer.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error Opening file " + filename);
		}
	}
}
