
public class Grade {
	private String course;
	private int credit;
	private int[] grade;

	
	public int[] getGrades() {
		return grade;
	}
	public void setGrades(int[] grade) {
		this.grade = grade;
	}
	
	public int getCredit() {
		return credit;
	}
	public void setCredit(int n) {
		credit = n;
	}
	
	public String getCourse() {
		return course;
	}
	public void setCourse(String c) {
		course = c;
	}

	@Override
	public boolean equals(Object obj) {
		return this.course.equalsIgnoreCase(((Grade)obj).course);
	}
}
