public class Student {
	
	private String studentId;
	private String firstname;
	private String lastname;
	private Grade[] grades;
	private int totalCredits;
	private int currentGPA;
	
	public String getId() {
		return studentId;
	}
	public void setId(String sId) {
		studentId = sId;
	}
	public String getFirst() {
		return firstname;
	}
	public void setFirst(String f) {
		firstname = f;
	}
	public String getLast() {
		return lastname;
	}
	public void setLast(String l) {
		lastname = l;
	}
	
	public Grade[] getGrades() {
		return grades;
	}
	
	public void setGrades(Grade[] g) {
		grades = g;
		setTotalCredits();
		setCurrentGPA();
	}
	
	public void addGrade(Grade g) {
		Grade[] grades = new Grade[this.grades.length + 1];
		grades[grades.length - 1] = g;
		this.grades = grades;
	}
	
	public void removeGrade(Grade g) {
		Grade[] grades = new Grade[this.grades.length - 1];
		int c = 0;
		for(Grade p : this.grades) {
			if(!g.equals(p)) {
				grades[c++] = p;
			}
		}
		this.grades = grades;
	}	
	
	public double getTotalCredits() {
		setTotalCredits();
		return totalCredits;
	}
	
	private void setTotalCredits() {
		int credits = 0;
		for(Grade p : grades ) {
			credits += p.getCredit();
		}
		
		this.totalCredits = credits;
	}
	
	public double getCurrentGPA() {
		setCurrentGPA();
		return this.currentGPA;
	}
	
	public void setCurrentGPA() {
		int currentGpa = 0;
		for (Grade g : grades) {
			int sum = 0;
			for (int grade : g.getGrades()) {
				sum += grade;
			}
			currentGpa += sum / g.getCredit();
		}
		this.currentGPA = currentGpa;
	}
	
	
	@Override
	public boolean equals(Object m) {
		return (this.studentId.equals(((Student)m).studentId) 
				&& this.lastname.equalsIgnoreCase(((Student)m).lastname)
				&& this.firstname.equalsIgnoreCase(((Student)m).firstname)
			);
	}
	
	@Override
	public String toString() {
		return "Student studentId=" +studentId + "\n"+"firstname=" + firstname + "\n"+"lastname=" + lastname + ", totalCredits=" + totalCredits + "\n"+" currentGPA=" + currentGPA ;
	}
	
}
