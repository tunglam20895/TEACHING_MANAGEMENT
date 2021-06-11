package controller;

import dao.Impl.TeacherDaoImpl;
import entity.Teacher;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Teacher")
public class TeacherController {

	TeacherDaoImpl teacherDao = new TeacherDaoImpl();

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Teacher> getListTeacher() {
		return teacherDao.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Teacher getTeacher(@PathParam("id") long id) {
		return teacherDao.findById(id);
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addNewTeacher(Teacher teacher) {
		return teacherDao.create(teacher) ? "Thêm mới thành công" : "Thêm mới thất bại";
	}

//	@DELETE
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public String removeTeacher(@PathParam("id") long id) {
//		return teacherDao.delete(id) ? "Xóa thành công" : "Xóa thất bại";
//	}
}
