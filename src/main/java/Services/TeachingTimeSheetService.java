package Services;

import Dao.Impl.SubjectDaoImpl;
import Dao.Impl.TeacherDaoImpl;
import Dao.Impl.TeacherTimeSheetDaoImpl;
import Entity.Subject;
import Entity.Teacher;
import Entity.TeacherTimeSheet;

import java.util.ArrayList;
import java.util.Scanner;

public class TeachingTimeSheetService {
	TeacherDaoImpl teacherDao = new TeacherDaoImpl();
	SubjectDaoImpl subjectDao = new SubjectDaoImpl();
	TeacherTimeSheetDaoImpl teacherTimeSheetDao = new TeacherTimeSheetDaoImpl();

	ArrayList<TeacherTimeSheet> teacherTimeSheets = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	public void keKhai(){
		System.out.println("Nhập số bản kê khai cần lập: ");
		int n = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < n; i++) {
			System.out.println("Nhập id giáo viên cần kê khai: ");
			long teacherId = sc.nextLong();
			System.out.println("Nhập id môn học cần kê khai: ");
			long subject_id = sc.nextLong();
			System.out.println("Nhập số lớp cần dạy: ");
			long nummber_class = sc.nextLong();

			Teacher teacher = teacherDao.findById(teacherId);
			Subject subject = subjectDao.findById(subject_id);

			teacherTimeSheetDao.create(new TeacherTimeSheet(nummber_class,teacher,subject));
		}
	}

	public static void main(String[] args) {
		TeachingTimeSheetService sv = new TeachingTimeSheetService();
		sv.keKhai();
//		sv.teacherTimeSheetDao.getAll().forEach(System.out::println);
	}
}
