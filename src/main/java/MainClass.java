
import dao.Impl.SubjectDaoImpl;
import dao.Impl.TeacherDaoImpl;
import org.apache.log4j.Logger;

public class MainClass {
	static Logger logger = Logger.getLogger(MainClass.class);
	static SubjectDaoImpl subjectDao = new SubjectDaoImpl();
	static TeacherDaoImpl teacherDao = new TeacherDaoImpl();
	public static void main(String[] args) {
//		teacherDao.createTeacher(new Teacher("Lâm","Hà Nội","0977823111","Th.S"));
//		subjectDao.createSubject(new Subject("Toán",1,1,1));
//		subjectDao.getSubject().forEach(System.out::println);
//		teacherDao.getAllTeacher().forEach(System.out::println);

//		teacherDao.findById(1);
	}
}
