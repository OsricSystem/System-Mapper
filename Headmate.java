public class Headmate{
    String name, pronouns, subsystem, gender, age, bio;
    String[] jobs;
    
    public Headmate(String name, String pronouns, String subsystem, String gender, String age, String bio, String... jobs){
        this.name = name;
        this.pronouns = pronouns;
        this.subsystem = subsystem;
        this.gender = gender;
        this.age = age;
        this.bio = bio;
        this.jobs = jobs;
    }
}
