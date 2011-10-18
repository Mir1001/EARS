package org.um.feri.ears.export.data;

public class EDAuthor {
	public String firstName; 
	public String lastName;
	public String nickName; //mandatory
	public String email; //mandatory used as id
	public String info; //data of original code author (if from other)
    @Override
    public String toString() {
        return "EDAuthor [firstName=" + firstName + ", lastName=" + lastName + ", nickName=" + nickName + ", email=" + email + ", info=" + info + "]";
    }
}
