import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Course implements java.io.Serializable {
	String courseN;
	String courseID;
	int maxStudents;
	int currentStudents;
	String instructorName;
	int courseSection;
	String courseLocation;
	static ArrayList<Course> courseList = new ArrayList<Course>();

	Course() {

	}

	Course(String courseN, String courseID, int maxStudents, int currentStudents, String instructorName,
			int courseSection, String courseLocation) {
		this.courseN = courseN;
		this.courseID = courseID;
		this.maxStudents = maxStudents;
		this.currentStudents = currentStudents;
		this.instructorName = instructorName;
		this.courseSection = courseSection;
		this.courseLocation = courseLocation;
	}

	public String print(ArrayList<Student> studentList) {
		String names = "";

		if (studentList != null) {
			for (int i = 0; i < studentList.size(); i++) {
				if(studentList.get(i).UcourseList.contains(this)) {
				String addFirst = studentList.get(i).getFirstName();
				String addLast = studentList.get(i).getLastName();
				names = names + addFirst + " " + addLast + ", ";
				}
			}
			System.out.println("Course: " + courseN + "\n" 
					+ "Course ID: " + courseID + "\n"
					+ "Maximum # of Students: " + maxStudents + "\n"
					+ "Current # of Students: " + currentStudents + "\n" 
					+ "Registered Students: " + names + "\n"
					+ "Instructor: " + instructorName + "\n"
					+ "Section: " + courseSection + "\n" 
					+ "Location: " + courseLocation);
			System.out.println("==========");
			String text1 = "Course: " + courseN + "\n" 
					+ "Course ID: " + courseID + "\n" 
					+ "Maximum # of Students: " + maxStudents + "\n" 
					+ "Current # of Students: " + currentStudents + "\n" 
					+ "Registered Students: " + names + "\n" 
					+ "Instructor: " + instructorName + "\n" 
					+ "Section: " + courseSection + "\n"
					+ "Location: " + courseLocation;
			return (text1);
		} else {
			System.out.println("Course: " + courseN + "\n" 
					+ "Course ID: " + courseID + "\n"
					+ "Maximum # of Students: " + maxStudents + "\n" 
					+ "Current # of Students: " + currentStudents + "\n" 
					+ "Registered Students: " + studentList + "\n" 
					+ "Instructor: " + instructorName + "\n"
					+ "Section: " + courseSection + "\n" 
					+ "Location: " + courseLocation);
			System.out.println("==========");
			String text2 = "Course: " + courseN + "\n" 
					+ "Course ID: " + courseID + "\n" 
					+ "Maximum # of Students: " + maxStudents + "\n"
					+ "Current # of Students: " + currentStudents + "\n"
					+ "Registered Students: " + studentList + "\n"
					+ "Instructor: " + instructorName + "\n" 
					+ "Section: " + courseSection + "\n"
					+ "Location: " + courseLocation;
			return (text2);
		}
	}

	public String studentPrint() {
		String text = "Course: " + courseN + "\n" 
				+ "Course ID: " + courseID + "\n" 
				+ "Maximum # of Students: " + maxStudents + "\n"
				+ "Current # of Students: " + currentStudents + "\n"
				+ "Registered Students: " + "\n"
				+ "Instructor: " + instructorName + "\n" 
				+ "Section: " + courseSection + "\n" 
				+ "Location: " + courseLocation;
		System.out.println(text);
		System.out.println("-----------");
		
		return (text);
	}


	public String getcourseN() {
		return courseN;
	}

	public void setcourseN(String courseN) {
		this.courseN = courseN;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public int getMaxStudents() {
		return maxStudents;
	}

	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}

	public int getCurrentStudents() {
		return currentStudents;
	}

	public void setCurrentStudents(int currentStudents) {
		this.currentStudents = currentStudents;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public int getCourseSection() {
		return courseSection;
	}

	public void setCourseSection() {
		this.courseSection = courseSection;
	}

	public String getCourseLocation() {
		return courseLocation;
	}

	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}

	public static void deSerialization() {
		try {
			FileInputStream fis = new FileInputStream("CRSData.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);

			courseList = (ArrayList<Course>) ois.readObject();
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

	public static void serialization() {
		try {
			FileOutputStream fos = new FileOutputStream("CRSData.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(courseList);
			oos.close();
			fos.close();
			System.out.println("Serialization complete");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Admin admin = new Admin();
		File fileName = new File("CRSData.ser");
		if (!fileName.exists()) {
			fileName = new File("MyUniversityCourses.csv");

			String line = null;

			try {
			
				FileReader fileReader = new FileReader(fileName);

				BufferedReader bufferedReader = new BufferedReader(fileReader);

				bufferedReader.close();
			}
			catch (FileNotFoundException ex) {
				System.out.println("Unable to open file '" + fileName + "'");

				ex.printStackTrace();
			}

			catch (IOException ex) {
				System.out.println("Error reading file '" + fileName + "'");
				ex.printStackTrace();
			}
			
			String input = new Scanner(fileName).useDelimiter("\\A").next();
			StringTokenizer strTokens = new StringTokenizer(input, ",\n");

			int count = 0;
			while (strTokens.hasMoreTokens()) {
				if (count > 7) {
					String courseN = strTokens.nextToken();
					String courseID = strTokens.nextToken();
					String test = strTokens.nextToken();
					String trimmedTest = test.replace(" ", "");
					int maxStudents = Integer.parseInt(trimmedTest);
					String test1 = strTokens.nextToken();
					String trimmedTest1 = test1.replace(" ", "");
					int currentStudents = Integer.parseInt(trimmedTest1);
					strTokens.nextToken();
					String instructorName = strTokens.nextToken();
					String test2 = strTokens.nextToken();
					String trimmedTest2 = test2.replace(" ", "");
					int courseSection = Integer.parseInt(trimmedTest2);
					String courseLocation = strTokens.nextToken();

					Course c = new Course(courseN, courseID, maxStudents, currentStudents, instructorName,
							courseSection, courseLocation);
					courseList.add(c);
					count++;
				} else {
					strTokens.nextToken();
					count++;
				}
			}
		} else {
			deSerialization();
			admin.deSerializationA();

		}

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome!");
		System.out.println(" '1' to login as Admin");
		System.out.println(" '2' to login as Student");
		System.out.println(" '3' to Exit");
		String option = in.readLine();

		while (!option.contentEquals("1") && !option.contentEquals("2") && !option.contentEquals("3")) {
			System.out.println("Sorry, your input is not valid! Try again.");
			System.out.println(" '1' to login as Admin");
			System.out.println(" '2' to login as Student");
			System.out.println(" '3' to Exit");
			option = in.readLine();
		}

		if (option.contentEquals("1")) {

			System.out.println("Admin username:");
			String userInput = in.readLine();
			System.out.println("Admin password:");
			String passInput = in.readLine();

			while (!userInput.contentEquals("admin") || !passInput.contentEquals("admin001")) {
				if (!userInput.contentEquals("admin")) {
					System.out.println("The username is incorrect! Try again.");
					System.out.println("Enter the Admin username:");
					userInput = in.readLine();
					System.out.println("Enter the Admin password:");
					passInput = in.readLine();
				} else if (!passInput.contentEquals("admin001")) {
					System.out.println("Sorry the password is incorrect! Try again.");
					System.out.println("Enter the Admin username:");
					userInput = in.readLine();
					System.out.println("Enter the Admin password:");
					passInput = in.readLine();
				}
			}

			System.out.println("Admin Console");
			boolean logout = false;
			while (!logout) {
				System.out.println("Menu");
				System.out.println(" '1' to Manage Courses");
				System.out.println(" '2' to View Reports");
				System.out.println(" '3' to Exit");
				String option2 = in.readLine();
				if (option2.contentEquals("1")) {

					System.out.println("Course Management");
					System.out.println(" '1' to Create a New Course");
					System.out.println(" '2' to Delete a Course");
					System.out.println(" '3' to Edit a Course");
					System.out.println(" '4' to Display Information for a Given Course");
					System.out.println(" '5' to Register a Student");
					System.out.println(" '6' to Exit");
					String option3 = in.readLine();

					if (option3.contentEquals("1")) {
						admin.createCourse();
					} else if (option3.contentEquals("2")) {
						admin.deleteCourse();
					} else if (option3.contentEquals("3")) {
						admin.editCourse();
					} else if (option3.contentEquals("4")) {
						admin.displayACourse();
					} else if (option3.contentEquals("5")) {
						admin.registerStudent();
					} else {
						System.out.println("END");
						logout = true;
						serialization();
						admin.serializationA();
					}

				} else if (option2.contentEquals("2")) {

					System.out.println("View Reports");
					System.out.println(" '1' to View All Courses");
					System.out.println(" '2' to View All Full Courses");
					System.out.println(" '3' to Write to File All Full Courses");
					System.out.println(" '4' to View Registered Students of Specific Course");
					System.out.println(" '5' to View All Registered Courses of Specific Student");
					System.out.println(" '6' to Sort Courses");
					System.out.println(" '7' to Exit");
					String option3 = in.readLine();

					if (option3.contentEquals("1")) {
						admin.adminViewAllCourses();
					} else if (option3.contentEquals("2")) {
						admin.viewFullCourses();
					} else if (option3.contentEquals("3")) {
						admin.writeToFileFullCourses();
					} else if (option3.contentEquals("4")) {
						admin.viewRegisteredStudents();
					} else if (option3.contentEquals("5")) {
						admin.viewAllStudentCourses();
					} else if (option3.contentEquals("6")) {
						admin.sortCourses();
					} else {
						System.out.println("END");
						logout = true;
						serialization();
						admin.serializationA();
					}

				} else {
					System.out.println("END");
					logout = true;
					serialization();
					admin.serializationA();

					
				}
			}

		} else if (option.contentEquals("2")) {
			

			System.out.println("Enter your first name:");
			String firstName = in.readLine();
			System.out.println("Enter your last name:");
			String lastName = in.readLine();

			Student searchStudent = new Student(firstName, lastName);
			
			boolean notf = true;
			for (int i = 0; i < Admin.masterRegistry.size() && notf; i++) {
				Student sti = Admin.masterRegistry.get(i);
				if ((Admin.masterRegistry.get(i).getFirstName().equals(firstName)) && (Admin.masterRegistry.get(i).getLastName().equals(lastName))){
					notf = false;
					searchStudent = Admin.masterRegistry.get(i);
				}
			}
			if (notf) {
				System.out.println("This name is not in the Student List. An admin has to register you!");
				System.exit(0);
			}

			if ((searchStudent.studentPass.equals("")) || (searchStudent.studentUser.equals(""))) {
				System.out.println("Username and Password set-up ");
				System.out.println("Please enter the desired username:");
				String setUser = in.readLine();
				System.out.println("Please enter the desired password:");
				String setPass = in.readLine();
				for (int i = 0; i < Admin.masterRegistry.size(); i++) {
					if ((Admin.masterRegistry.get(i).getFirstName().equals(firstName))
							&& (Admin.masterRegistry.get(i).getLastName().equals(lastName))) {
						Admin.masterRegistry.get(i).studentPass = setPass;
						Admin.masterRegistry.get(i).studentUser = setUser;
						System.out.println("Username and password succesfully created. ");
					}
				}
			}

			System.out.println("Enter your Student username:");
			String userInput = in.readLine();
			System.out.println("Enter your Student password:");
			String passInput = in.readLine();
			Student st = new Student(firstName,lastName);

			boolean valid = true;
			while (valid) {
				for (int i = 0; i < Admin.masterRegistry.size() && valid; i++) {
					Student sti = Admin.masterRegistry.get(i);
					if ((sti.getFirstName().equals(firstName))
							&& (sti.getLastName().equals(lastName))
							&& (sti.studentUser.equals(userInput) )
							&& (sti.studentPass.equals(passInput))) {
						valid = false;
						st = sti;
					}
				}
					
				if (valid) {
					System.out.println("Enter your Student username:");
					userInput = in.readLine();
					System.out.println("Enter your Student password:");
					passInput = in.readLine();
				}
			}
			String option3 = "";
			while(!option3.equals("6")) {
			
				System.out.println("Menu");
				System.out.println(" '1' to View All Courses");
				System.out.println(" '2' to View All Available Courses");
				System.out.println(" '3' to Register to a Course");
				System.out.println(" '4' to Withdraw from a Course");
				System.out.println(" '5' to View All Registered Courses");
				System.out.println(" '6' to Exit");
				option3 = in.readLine();
	
				if (option3.contentEquals("1")) {
					st.studentViewAllCourses();
				} else if (option3.contentEquals("2")) {
					st.viewAvailableCourses();
				} else if (option3.contentEquals("3")) {
					st.registerToCourse();
				} else if (option3.contentEquals("4")) {
					st.withdrawFromCourse();
				} else if (option3.contentEquals("5")) {
					st.viewAllRegisteredCourses();
				} else {
					System.out.println("END");
					serialization();
					admin.serializationA();
			
				}
			}

		} else {
			System.out.println("END");
			serialization();
			admin.serializationA();

			
		}

	}

}