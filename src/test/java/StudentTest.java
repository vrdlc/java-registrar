import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class StudentTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Student.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Student firstStudent = new Student("Sally", "1900/01/01");
    Student secondStudent = new Student("Sally", "1900/01/01");
    assertTrue(firstStudent.equals(secondStudent));
  }

  @Test
  public void save_addsInstanceOfStudentToDatabase() {
    Student newStudent = new Student("Sally", "1900/01/01");
    newStudent.save();
    Student savedStudent = Student.all().get(0);
    assertTrue(newStudent.equals(savedStudent));
  }

  @Test
  public void save_assignsIdToObject() {
    Student newStudent = new Student("Sally", "1900/01/01");
    newStudent.save();
    Student savedStudent = Student.all().get(0);
    assertEquals(newStudent.getId(), savedStudent.getId());
  }

  @Test
  public void find_locatesAllInstancesOfClassInDatabaseUsingId() {
    Student newStudent = new Student("Sally" , "1900/01/01");
    newStudent.save();
    Student savedStudent = Student.find(newStudent.getId());
    assertTrue(newStudent.equals(savedStudent));
  }

  @Test
  public void updateName_updatesNameOfObject() {
    Student newStudent = new Student("Sally", "1900/01/01");
    newStudent.save();
    newStudent.updateName("Susan");
    assertEquals(Student.all().get(0).getName(), ("Susan"));
  }

  @Test
  public void updateDate_updatesDateOfObject() {
    Student newStudent = new Student("Sally", "1900/01/01");
    newStudent.save();
    newStudent.updateDate("1901/01/01");
    assertEquals(Student.all().get(0).getDate(), ("1901/01/01"));
  }

  @Test
  public void deleteStudent_deleteStudentObject() {
    Student newStudent = new Student("Sally", "1900/01/01");
    newStudent.save();
    newStudent.delete();
    assertEquals(Student.all().size(), 0);
  }

  @Test
  public void addCourse_addsCourseToStudent() {
    Student newStudent = new Student("Sally", "1900/01/01");
    newStudent.save();

    Course newCourse = new Course("History", "101");
    newCourse.save();

    newStudent.addCourse(newCourse);
    Course savedCourse = newStudent.getCourses().get(0);
    assertTrue(newCourse.equals(savedCourse));
  }

  @Test
  public void getCourses_getsStudentCoursesByStudentID() {
    Student newStudent = new Student("Sally", "1900/01/01");
    newStudent.save();

    Course newCourse = new Course("History", "101");
    newCourse.save();

    newStudent.addCourse(newCourse);
    List savedCourses = newStudent.getCourses();
    assertEquals(savedCourses.size(), 1);
  }
}
