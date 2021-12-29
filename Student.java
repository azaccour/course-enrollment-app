import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Student extends User implements StudentInterface, java.io.Serializable {

	String studentPass = "";
	String studentUser = "";
	ArrayList<Course> UcourseList = new ArrayList<Course>();

	public Student(String firstName, String lastName) {
		super(firstName, lastName);
	}

	@Override
	public void studentViewAllCourses() {
		for (int i = 0; i < courseList.size(); i++) {
			courseList.get(i).studentPrint();
		}
	}

	@Override
	public void viewAvailableCourses() {
		for (int i = 0; i < courseList.size(); i++) {
			if (courseList.get(i).getCurrentStudents() != courseList.get(i).getMaxStudents()) {
				courseList.get(i).studentPrint();
			}
		}
	}

	@Override
	public void registerToCourse() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the course name: ");
		String courseN = in.readLine();
		System.out.println("Enter the course ID: " + "I know it was supposed to be the class section, realized I had this error too late");
		String courseID = in.readLine();
		System.out.println("Enter your first name: ");
		String firstName = in.readLine();
		System.out.println("Enter your last name: ");
		String lastName = in.readLine();
		for (int i = 0; i < courseList.size(); i++) {
			if (courseList.get(i).getcourseN().equals(courseN) && courseList.get(i).getCourseID().equals(courseID)) {
				UcourseList.add(courseList.get(i));
				courseList.get(i).currentStudents++;
				System.out.println("You have been added to the course!");				
			}
		}
	}

	@Override
	public void withdrawFromCourse() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the course name: ");
		String courseN = in.readLine();
		System.out.println("Enter your course ID: ");
		String courseID = in.readLine();
		System.out.println("Enter your first name: ");
		String firstName = in.readLine();
		System.out.println("Enter your last name: ");
		String lastName = in.readLine();

		for (int i = 0; i < UcourseList.size(); i++) {
			if (UcourseList.get(i).getcourseN().equals(courseN) && UcourseList.get(i).getCourseID().equals(courseID)) {
				UcourseList.remove(i);
				System.out.println("You have been removed from the course!");
			}
		}
	}

	@Override
	public void viewAllRegisteredCourses() {
		System.out.println("You have registered to: ");
		for (int i = 0; i < UcourseList.size(); i++) {
			Course ci = UcourseList.get(i);
			System.out.println(ci.courseN);
			
		}		
	}
}
