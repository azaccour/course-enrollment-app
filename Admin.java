import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

class Admin extends User implements AdminInterface, java.io.Serializable {
	static ArrayList<Student> masterRegistry = new ArrayList<Student>();

	public Admin() {
		super();
		this.user = "admin";
		this.pass = "admin001";
	}

	public ArrayList<Student> getMasterRegistry() {
		return masterRegistry;
	}

	public void setUsername(ArrayList<Student> masterRegistry) {
		this.masterRegistry = masterRegistry;
	}
	
	public void deSerializationA() {
		try {
			FileInputStream fis = new FileInputStream("StudentList.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);

			Admin.masterRegistry = (ArrayList<Student>) ois.readObject();
			for(int i = 0; Admin.masterRegistry.size() > i; i++) {
				Student sti = Admin.masterRegistry.get(i);

			}
			ois.close();
			fis.close();
			System.out.println("Deserialization complete");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
		}
	}

	public void serializationA() {
		try {
			FileOutputStream fos = new FileOutputStream("StudentList.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(Admin.masterRegistry);
			oos.close();
			fos.close();
			System.out.println("Serialization complete");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	@Override 
	public void createCourse() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter course name: ");
		this.courseN = in.readLine();

		System.out.println("Enter course ID: ");
		this.courseID = in.readLine();

		System.out.println("Enter the maximum number of students that can register: ");
		String maxS = in.readLine();
		int maxStudents = Integer.parseInt(maxS);

		System.out.println("Enter the current number of students registered: ");
		String currS = in.readLine();
		int currentStudents = Integer.parseInt(currS);

		System.out.println("Enter instructor's name: ");
		this.instructorName = in.readLine();

		System.out.println("Enter the section number: ");
		String courSec = in.readLine();
		int courseSection = Integer.parseInt(courSec);

		System.out.println("Enter course location: ");
		this.courseLocation = in.readLine();

		Course x = new Course(this.courseN, this.courseID, maxStudents, currentStudents, this.instructorName, courseSection, this.courseLocation);
		courseList.add(x);

		System.out.println(courseN + " has been successfully added. ");
	}

	@Override
	public void deleteCourse() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the course name you want to delete: ");
		String courseN = in.readLine();

		for (int i = 0; i < courseList.size(); i++) {
			String j = courseList.get(i).getcourseN();
			System.out.println(courseList.get(i).getcourseN());
			if (j.contentEquals(courseN)) {
				courseList.remove(i);
				System.out.println("The course " + courseN + " has been removed!");
				System.out.println(" ");
				break;
			} else if ((!j.contentEquals(courseN)) && (i == courseList.size() - 1)) {
				System.out.println("Unable to find that course. ");
				Admin admin = new Admin();
				admin.deleteCourse();
			}
		}
	}

	@Override
	public void editCourse() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println(" '1' to change a course's instructor.");
		System.out.println(" '2' to change a course's location.");
		String option = in.readLine();
		System.out.println("Enter the name of the course you would like to edit: ");
		String courseN = in.readLine();

		if (option.contentEquals("1")) {
			System.out.println("Enter the new professors name: ");
			String instructorName = in.readLine();
			for (int i = 0; i < courseList.size(); i++) {
				String j = courseList.get(i).getcourseN();
				if (j.contentEquals(courseN)) {
					courseList.get(i).setInstructorName(instructorName);
					System.out.println("Course instructor name has been edited to: " + instructorName);
					System.out.println("");
				}
			}
		} else if (option.contentEquals("2")) {
			System.out.println("Enter the course's new location: ");
			String courseLocation = in.readLine();
			for (int i = 0; i < courseList.size(); i++) {
				String j = courseList.get(i).getcourseN();
				if (j.contentEquals(courseN)) {
					courseList.get(i).setCourseLocation(courseLocation);
					System.out.println("Course location has been edited to: " + courseLocation);
					System.out.println(" ");
				}
			}
		}
	}

	@Override
	public void displayACourse() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the course ID: ");
		String courseID = in.readLine();
		for (int i = 0; i < courseList.size(); i++) {
			String j = courseList.get(i).getCourseID();
			if (j.contentEquals(courseID)) {
				courseList.get(i).print(masterRegistry);
			}
		}
	}

	@Override
	public void registerStudent() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the new student's first name: ");
		String firstName = in.readLine();
		System.out.println("Enter the new student's last name: ");
		String lastName = in.readLine();

		Student newStudent = new Student(firstName, lastName);
		Admin.masterRegistry.add(newStudent);
		System.out.println(Admin.masterRegistry.get(Admin.masterRegistry.size() - 1).getFirstName());
		System.out.println(Admin.masterRegistry.size());
		System.out.println("New student has been added!");
		System.out.println(" ");
	}

	@Override
	public void adminViewAllCourses() {
		for (int i = 0; i < courseList.size(); i++) {
			courseList.get(i).print(masterRegistry);
		}
	}

	@Override
	public ArrayList<Course> viewFullCourses() {
		ArrayList<Course> returnCourses = new ArrayList<Course>();
		for (int i = 0; i < courseList.size(); i++) {
			if (courseList.get(i).getCurrentStudents() == courseList.get(i).getMaxStudents()) {
				courseList.get(i).print(masterRegistry);
				returnCourses.add(courseList.get(i));
			}
		}
		return returnCourses;
	}

	@Override
	public void viewRegisteredStudents() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the course's name: ");
		String courseN = in.readLine();
		
		System.out.println("Enter the course's ID: ");
		String courseID = in.readLine();
		System.out.println("Registered Students for " + courseN);
		for (int i = 0; i < masterRegistry.size(); i++) {
			Student sti = masterRegistry.get(i);
			for (int j = 0; j < sti.UcourseList.size(); j++) {
				if ((sti.UcourseList.get(j).courseN.equals(courseN)) && (sti.UcourseList.get(j).courseID.equals(courseID))){	
					System.out.print("* " + sti.firstName + " " + sti.lastName);
			
				}
			}
		}
		
	}

	@Override
	public void viewAllStudentCourses() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the new student's first name: ");
		String firstName = in.readLine();
		System.out.println("Enter the new student's last name: ");
		String lastName = in.readLine();

		System.out.println(firstName + " " + lastName + "'s Classes:");
		for (int i = 0; i < masterRegistry.size(); i++) {
			Student sti = masterRegistry.get(i);
			if(sti.firstName.equals(firstName) && sti.lastName.equals(lastName)) {
				for (int j = 0; j < sti.UcourseList.size(); j++) {
					System.out.print("* " + sti.UcourseList.get(j).courseN + " " + sti.UcourseList.get(j).courseID);
				}
			}
		}
			
	}

	@Override
	public void sortCourses() {
		for (int i = 0; i < courseList.size(); i++) {
			for (int j = courseList.size() - 1; j > i; j--) {
				if (courseList.get(i).getCurrentStudents() > courseList.get(j).getCurrentStudents()) {
					Course tmp = courseList.get(i);
					courseList.set(i, courseList.get(j));
					courseList.set(j, tmp);
				}
			}
		}
		
		for (int i = 0; i < courseList.size(); i++) {
			courseList.get(i).print(masterRegistry);
		}
	}

	@Override
	public void writeToFileFullCourses() {
		String fileName = "text.txt";
		Scanner scan = new Scanner(System.in);

		try {
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for (int i = 0; i < viewFullCourses().size(); i++) {
				String text = viewFullCourses().get(i).print(masterRegistry);
				bufferedWriter.write(text);
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
		}
		catch (IOException exk) {
			System.out.println("Error writing file '" + fileName + "'");
			exk.printStackTrace();
		}
	}
}
