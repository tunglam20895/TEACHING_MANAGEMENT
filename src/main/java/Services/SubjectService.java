package Services;

import Dao.Impl.SubjectDaoImpl;
import Entity.Subject;
import Entity.Teacher;

import java.util.ArrayList;
import java.util.Scanner;

public class SubjectService {

	public static void main(String[] args) {
		SubjectService sj = new SubjectService();
		sj.serviceSubject();
	}

	Scanner sc = new Scanner(System.in);

	SubjectDaoImpl subjectDao = new SubjectDaoImpl();

	public void menuSubject(){
		System.out.println("--------------------");
		System.out.println("1. Thêm môn học");
		System.out.println("2. Update môn học");
		System.out.println("3. Xóa môn học");
		System.out.println("4. Xuất thông tin môn học");
		System.out.println("5. Thoát!!");
		System.out.println("--------------------");
	}

	public void serviceSubject(){
		int choose;

		do {
			menuSubject();
			System.out.println("Chọn chức năng: ");
			choose = sc.nextInt();
			switch (choose){
				case 1:
					addSubject();
					break;
				case 2:
					updateSubject();
					break;
				case 3:
					deleteSubject();
					break;
				case 4:
					getInfo();
					break;
				case 5:
					System.out.println("Hẹn gặp lại!");
					break;
			}
		}while (choose!=5);

	}

	public void addSubject(){
		System.out.println("Nhập số môn cần thêm: ");
		int n = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < n; i++) {
			System.out.println("Môn thứ "+(n+1));
			sc.nextLine();
			System.out.println("Nhập tên môn học: ");
			String name = sc.nextLine();
			System.out.println("Nhập tổng số tiết: ");
			long totalLesson = sc.nextLong();
			System.out.println("Nhập số tiết lý thuyết: ");
			long theoryLesson = sc.nextLong();
			System.out.println("Nhập số tiền cho mỗi tiết giảng: ");
			long theoryExpense = sc.nextLong();
			sc.nextLine();

			subjectDao.create(new Subject(name,totalLesson,theoryLesson,theoryExpense));
		}

	}

	public void updateSubject(){
		ArrayList<Subject> listSubject;
		listSubject = (ArrayList<Subject>) subjectDao.getAll();
		System.out.println("--------------------------------------------------------");
		System.out.println("STT      ID      NAME");
		for (int i = 0; i < listSubject.size(); i++) {
			System.out.println((i + 1) + ".      " + listSubject.get(i).getId() + "      " + listSubject.get(i).getName());
		}
		System.out.println("--------------------------------------------------------");
		System.out.println("Chọn id môn học bạn muốn update thông tin");
		long id = sc.nextLong();
		sc.nextLine();
		System.out.println("nhập tên mới: ");
		String name = sc.nextLine();
		System.out.println("Nhập tổng số tiết mới: ");
		long totalLesson = sc.nextLong();
		System.out.println("Nhập số tiết lý thuyết mới: ");
		long theoryLesson = sc.nextLong();
		System.out.println("Nhập số tiền cho mỗi tiết mới: ");
		long theoryExpense = sc.nextLong();

		Subject subjectUp = new Subject();
		subjectUp.setId(id);
		subjectUp.setName(name);
		subjectUp.setTotalLesson(totalLesson);
		subjectUp.setTheoryLesson(theoryLesson);
		subjectUp.setTheoryExpense(theoryExpense);

		subjectDao.update(subjectUp);

	}

	public void deleteSubject(){
			ArrayList<Subject> listSubject;
			listSubject = (ArrayList<Subject>) subjectDao.getAll();
			System.out.println("--------------------------------------------------------");
			System.out.println("STT      ID      NAME");
			for (int i = 0; i < listSubject.size(); i++) {
				System.out.println((i + 1) + ".      " + listSubject.get(i).getId() + "      " + listSubject.get(i).getName());
			}
			System.out.println("--------------------------------------------------------");
			System.out.println("Nhập ID môn học bạn muốn xóa: ");
			long id = sc.nextLong();
			Subject deleteSubject = subjectDao.findById(id);
			subjectDao.delete(deleteSubject);
		}

		public void getInfo(){
		subjectDao.getAll().forEach(System.out::println);
	}

}
