package tester.models;

import java.util.LinkedList;

public class Headmate{
	private String name, pronouns, subsystem, gender, age, desc;
	private final LinkedList<String> jobs;

	public Headmate(String name){
		this.name=name;
		jobs=new LinkedList<String>();
	}

	// GETTERS \\
	public String getName(){ return name; }
	public String getPronouns(){ return pronouns; }
	public String getSubsystem(){ return subsystem; }
	public String getGender(){ return gender; }
	public String getAge(){ return age; }
	public String getDesc(){ return desc; }
	public String[] getJobs(){ return jobs.toArray(new String[jobs.size()]); }

	// SETTERS \\
	public void setName(String name){ this.name=name; }
	public void setPronouns(String pronouns){ this.pronouns=pronouns; }
	public void setSubsystem(String subsystem){ this.subsystem=subsystem; }
	public void setGender(String gender){ this.gender=gender; }
	public void setAge(String age){ this.age=age; }
	public void setDesc(String desc){ this.desc=desc; }
	public void setJobs(String... jobs){
		clearJobs();
		addJobs(jobs);
	}
	public void addJobs(String... jobs){ for(String job : jobs) addJob(job); }
	public void addJob(String job){ this.jobs.add(job); }

	// CLEAR \\
	public void clearName(){ this.name=null; }
	public void clearPronouns(){ this.pronouns=null; }
	public void clearSubsystem(){ this.subsystem=null; }
	public void clearGender(){ this.gender=null; }
	public void clearAge(){ this.age=null; }
	public void clearDesc(){ this.desc=null; }
	public void clearJobs(){ this.jobs.clear(); }

	// other
	public String toString(){
		StringBuilder sb=new StringBuilder();

		sb.append("Name: "+ (name!=null? getName(): ""));
		sb.append("\nPronouns: "+ (pronouns!=null? getPronouns(): ""));
		sb.append("\nSubsystem: "+ (subsystem!=null? getSubsystem(): ""));
		sb.append("\nGender: "+ (gender!=null? getGender(): ""));
		sb.append("\nAge: "+ (age!=null? getAge(): ""));
		sb.append("\nBio: "+ (desc!=null? getDesc(): ""));

		return sb.toString();
	}
}
