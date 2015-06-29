package helper;

import java.io.*;

public class PythonCaller {
 
	public static void CallSummarizer(String file, int SummarizerSelection) throws IOException {
	// set up the command and parameter
		
		String pythonScriptPath = null;
		//System.out.println(SummarizerSelection);
		switch (SummarizerSelection){
		
		case 1: pythonScriptPath = "./glowing.py";
				System.out.println("Selected Glowing Summarizer");
				System.out.println("===========================");
				break;
				
		case 2: pythonScriptPath = "./simple.py";
				System.out.println("Selected Simple Summarizer");
				System.out.println("==========================");
				break;
				
		case 3: pythonScriptPath = "./sum.py";
				System.out.println("Selected Sum Summarizer");
				System.out.println("=======================");
				break;
				
		default: System.out.println("Please Select a Summarizer and try again!");
					System.exit(1);
					break;
		
		}
	String[] cmd = new String[3];
	cmd[0] = "python";
	cmd[1] = pythonScriptPath;
	cmd[2] = file;
	 
	// create runtime to execute external command
	Runtime rt = Runtime.getRuntime();
	Process pr = rt.exec(cmd);
	 
	// retrieve output from python script
	BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
	String line = "";
		while((line = bfr.readLine()) != null) {
			// display each output line form python script
			System.out.println(line);
		}
	}
}