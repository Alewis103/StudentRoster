import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		dialogue1();
		
		//get the user choice from menu
		int choice = scan.nextInt();
		
		Roster bst = null;
		switch(choice) {
			case 1: 
				bst = getStudentData(1);
				enterStudents1(bst);
				break;
				
			case 2:
				bst = getStudentData(2);
				enterStudent2(bst);
		}
		
		scan.close();
	}
	
	private static void dialogue1() {
		System.out.println("enter corresponding number:");
		System.out.println("1. Student Admission period not over");
		System.out.println("2. Student Admission period over");
	}

	private static void dialogue2() {
		System.out.println("enter a corresponding number:");
		System.out.println("1. Add Student");
		System.out.println("2. Delete Student");
		System.out.println("3. Add course for student");
		System.out.println("4. Drop course for student");
		System.out.println("5. Search for student");
		System.out.println("6. Exit");
	}
	
	private static void dialogue3() {
		System.out.println("enter corresponding number: ");
		System.out.println("1. See a sorted listing of the students");
		System.out.println("2. Save the sorted listing of students into a text file, StudentDataSorted.txt");
	}
	
	private static void dialogue4() {
		System.out.println("Admission Period is over, enter corresponding number: ");
		System.out.println("1. search for student");
		System.out.println("2. Exit");
	}

	private static void enterStudents1(Roster bst) throws FileNotFoundException {
		//show students choices when admission period is not over
		dialogue2();
		
		Scanner scanner = new Scanner(System.in);
		//get user choice
		int choice = Integer.valueOf(scanner.nextLine());
		while (choice != 6) {
			switch (choice) {
			case 1:
				Student s = getNewStudent();
				bst.insert(s);
				System.out.println("New student added");
				break;
				
			case 2:
				System.out.println("Enter student id#: ");
				scanner.nextLine();
				String studentId = scanner.nextLine();
				RosterNode node = bst.get(studentId);
				if(node != null) {
					bst.delete(node.data);
					System.out.println("Selceted student deleted ");
				} else {
					System.out.println("Student id: "+ studentId+"  not found");
				}
				break;
			
			case 3: 
				System.out.println("Enter student id#: ");
				studentId = scanner.nextLine();
				RosterNode node2 = bst.get(studentId);
				if(node2 != null) {
					Grade g = getGrade();
					node2.data.addGrade(g);
					System.out.println("Course added to selected student ");
				} else {
					System.out.println("Student id: "+ studentId+"  not found");
				}
				break;
			
			case 4:
				System.out.println("Enter student id#: ");
				studentId = scanner.nextLine();
				RosterNode node3 = bst.get(studentId);
				if(node3 != null) {
					Grade g = getGrade();
					node3.data.removeGrade(g);
					System.out.println("Course added to student");
				} else {
					System.out.println("Student id: "+ studentId+"  not found");
				}
				break;
			
			case 5:
				System.out.println("Enter student id#: ");
				studentId = scanner.nextLine();
				RosterNode node4 = bst.get(studentId);
				if(node4 != null) {
					System.out.println(node4.data);
				} else {
					System.out.println("Student id: "+ studentId+"  not found");
				}
				break;

			case 6: 
				break;
				
			default:
				System.out.println("Choice not vaild");
				break;
			}
			dialogue2();
			choice = scanner.nextInt();
		}
		
		dialogue3();
		choice = scanner.nextInt();
		
		switch(choice) {
			case 1: 
				bst.inorder();
				break;
				
			case 2: 
				PrintWriter out = new PrintWriter("StudentDataSorted.txt");
				bst.saveData(out);
				out.close();
				break;
			
			default: 
				System.out.println("Choice not valid");
		}
		scanner.close();
	}
	
	private static void enterStudent2(Roster bst) {
		Scanner s = new Scanner(System.in);
		dialogue4();
		int choice = Integer.valueOf(s.nextLine());
		switch(choice) {
		case 1:
			System.out.println("Enter student id#: ");
			String studentId = s.nextLine();
			RosterNode node4 = bst.get(studentId);
			if(node4 != null) {
				System.out.println(node4.data);
			} else {
				System.out.println("Student id: "+ studentId+"  not found");
			}
			break;

		case 2:
			System.out.println("Exiting...");
		
		default: 
			System.out.println("Choice not valid");
		}
		s.close();
		
	}
	
	private static Student getNewStudent() {
		Scanner s = new Scanner(System.in);
		
		//get student information
		System.out.println("Enter Student ID# (6 Digit): ");
		String id = s.nextLine();
		System.out.println("Enter Student's First Name: ");
		String firstname = s.nextLine();
		System.out.println("Enter Student's Last Name: ");
		String lastname = s.nextLine();
		
		//get grade information
		System.out.println("Enter number of courses student is enrolled to: ");
		int n = s.nextInt();
		Grade[] grades = new Grade[n];
		for(int i=0; i<n; i++) {
			grades[i] = getGrade();
		}
		
		//create new student's instance
		Student d = new Student();
		d.setId(id);
		d.setFirst(firstname);
		d.setLast(lastname);
		d.setGrades(grades);
		
		return d;
	}

	private static Grade getGrade() {
		Scanner s = new Scanner(System.in);
		Grade g = new Grade();
		
		//get coursename from user
		System.out.println("Enter course name: ");
		String courename = s.nextLine();
		
		//get course credit from user
		System.out.println("Enter course credit: ");
		int coursecredit = Integer.valueOf(s.nextLine());
		
		//get course grades from user
		System.out.println("Enter grades received (comma separated): ");
		String[] gradesReceivedStr = s.nextLine().split(",");
		int[] gradesReceived = new int[gradesReceivedStr.length];
		int c = 0;
		for(String grades : gradesReceivedStr) {
			gradesReceived[c++] = Integer.valueOf(grades);
		}
		
		g.setCourse(courename);
		g.setCredit(coursecredit);
		g.setGrades(gradesReceived);
		return g;
	}

	private static Roster getStudentData(int choice) throws FileNotFoundException {
		Roster bst = new Roster();
		Scanner studentScanner = null;
		if(choice == 1) {
			File file;
			file = new File("C:/Users/alester/Desktop/StudentData.txt.txt");
			System.out.println(file = new File("StudentData.txt").getAbsoluteFile());
			                                                                                                                                                                                                           
			studentScanner = new Scanner(file);
		} else {
			studentScanner = new Scanner(new FileReader("StudentDataSorted.txt"));
		}
		String line;
		while(studentScanner.hasNextLine()) {
			line = studentScanner.nextLine();
			Student student = new Student();
			
			String[] details = line.split(";");
			
			student.setId(details[0]);
			student.setFirst(details[1].split(" ")[0]);
			student.setLast(details[1].split(" ")[1]);
			
			Grade[] grades = new Grade[details.length - 2];
			for(int i=2; i<details.length; i++) {
				String gradeWord = details[i];
				String[] gradeDetails = gradeWord.split(":");
				
				Grade grade = new Grade();
				grade.setCourse(gradeDetails[0]);
				grade.setCredit(Integer.valueOf(gradeDetails[1]));
				
				String gradeWord2 = gradeDetails[2];
				String[] gradeReceived = gradeWord2.split(",");
				int[] gradesInt = new int[gradeReceived.length];
				int c = 0;
				for(String g : gradeReceived) {
					gradesInt[c++] = Integer.valueOf(g);
				}
				grade.setGrades(gradesInt);
				grades[i-2] = grade;
			}
			student.setGrades(grades);
			bst.insert(student);
		}
		studentScanner.close();
		return bst;
	}

	
}
