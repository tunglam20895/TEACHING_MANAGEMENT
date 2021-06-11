package services;

import dao.Impl.TeacherDaoImpl;
import entity.Teacher;

import java.util.ArrayList;
import java.util.Scanner;

public class TeacherService {
	TeacherDaoImpl teacherDao = new TeacherDaoImpl();
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		TeacherService sv = new TeacherService();
		sv.serviceTeacher();

	}

	public void menuTeacher() {
		System.out.println("----------------------------");
		System.out.println("1. Thêm giáo viên mới");
		System.out.println("2. Update thông tin giáo viên");
		System.out.println("3. Xóa giáo viên");
		System.out.println("4. Xuất danh sách giáo viên");
		System.out.println("5. Thoát menu");
		System.out.println("----------------------------");
	}

	public void serviceTeacher() {
		int choose;
		do {
			menuTeacher();
			System.out.println("Chọn chức năng");
			choose = sc.nextInt();
			switch (choose) {
				case 1:
					addTeacher();
					break;
				case 2:
					updateTeacher();
					break;
				case 3:
					deleteTeacher();
					break;
				case 4:
					getInfoTeacher();
					break;
				case 5:
					System.exit(0);
					break;
			}
		} while (choose != 5);
	}

	public void addTeacher() {
		System.out.println("Nhập số giáo viên muốn thêm: ");
		int n = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < n; i++) {
			System.out.println("Giáo viên thứ " + (i + 1) + ":");
			System.out.println("Nhập tên GV:");
			String name = sc.nextLine();
			System.out.println("Nhập địa chỉ giáo viên: ");
			String address = sc.nextLine();
			System.out.println("Nhập số điện thoại: ");
			String phoneNumber = sc.nextLine();
			System.out.println("-----------------");
			System.out.println("1. TS");
			System.out.println("2. Th.S");
			System.out.println("3. Giáo viên chính");
			System.out.println("-----------------");
			System.out.println("Chọn level giáo viên: ");
			String level = null;
			while (true) {
				int chooseLevel = sc.nextInt();
				if (chooseLevel < 4) {
					switch (chooseLevel) {
						case 1:
							level = "TS";
							break;
						case 2:
							level = "Th.S";
							break;
						case 3:
							level = "Giáo viên chính";
							break;
					}
					break;
				}
				System.err.println("Nhập sai vui lòng nhập lại: ");
			}
			sc.nextLine();
			teacherDao.create(new Teacher(name, address, phoneNumber, level));
		}
		teacherDao.getAll();
	}

	public void getInfoTeacher() {
		teacherDao.getAll().forEach(System.out::println);
	}

	public void deleteTeacher() {
		ArrayList<Teacher> listTeacher;
		listTeacher = (ArrayList<Teacher>) teacherDao.getAll();
		System.out.println("--------------------------------------------------------");
		System.out.println("STT      ID      NAME");
		for (int i = 0; i < listTeacher.size(); i++) {
			System.out.println((i + 1) + ".      " + listTeacher.get(i).getId() + "      " + listTeacher.get(i).getName());
		}
		System.out.println("--------------------------------------------------------");
		System.out.println("Nhập ID giáo viên bạn muốn xóa: ");
		long id = sc.nextLong();
		teacherDao.delete(id);
	}

	public void updateTeacher() {
		ArrayList<Teacher> listTeacher;
		listTeacher = (ArrayList<Teacher>) teacherDao.getAll();
		System.out.println("--------------------------------------------------------");
		System.out.println("STT      ID      NAME");
		for (int i = 0; i < listTeacher.size(); i++) {
			System.out.println((i + 1) + ".      " + listTeacher.get(i).getId() + "      " + listTeacher.get(i).getName());
		}
		System.out.println("--------------------------------------------------------");
		System.out.println("Chọn giáo viên muốn update thông tin");
		long id = sc.nextLong();
		sc.nextLine();
		System.out.println("nhập tên mới: ");
		String name = sc.nextLine();
		System.out.println("Nhập địa chỉ mới: ");
		String address = sc.nextLine();
		System.out.println("Nhập sđt mới: ");
		String phoneNumber = sc.nextLine();

		Teacher teacherUp = new Teacher();
		teacherUp.setId(id);
		teacherUp.setName(name);
		teacherUp.setPhoneNumber(phoneNumber);
		teacherUp.setAddress(address);
		teacherUp.setTeacherLevel(teacherDao.findById(id).getTeacherLevel());

		teacherDao.update(teacherUp);

	}

}
