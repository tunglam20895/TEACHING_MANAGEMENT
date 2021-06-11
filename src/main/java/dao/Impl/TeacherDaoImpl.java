package dao.Impl;

import dao.ObjectDao;
import entity.Teacher;
import utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

//@Transactional
public class TeacherDaoImpl implements ObjectDao<Teacher> {

	Logger logger = Logger.getLogger(TeacherDaoImpl.class);
	Transaction transaction = null;

	@Override
	public List<Teacher> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			String HQL = "from Teacher";
			Query query = session.createQuery(HQL);
			List<Teacher> list = query.list();
			return list;
		}catch (HibernateException e){
			logger.error(e);
		}
		return null;
	}

	@Override
	public boolean create(Teacher teacher) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			session.save(teacher);
			System.out.println("alo" + teacher);
			return true;
		}catch (HibernateException e){
			logger.error(e);
		}
		return false;
	}

	@Override
	public boolean update(Teacher teacher) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.getTransaction();
			transaction.begin();
			session.update(teacher);
			transaction.commit();
			return true;
		}catch (HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			logger.error(e);
		}
		return false;
	}

	@Override
	public boolean delete(long id) {
		Teacher teacher = findById(id);
		if(teacher == null){
			System.out.println("Không tìm thấy giáo viên");
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.getTransaction();
			transaction.begin();
			session.delete(teacher);
			transaction.commit();
			System.out.println("xóa thành công");
			return true;
		}catch (HibernateException e){
			if(transaction != null){
				transaction.rollback();
			}
			logger.error(e);
		}
		return false;
	}

	@Override
	public Teacher findById(long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			return session.get(Teacher.class,id);
		}catch (HibernateException e){
			logger.error(e);
		}
		return null;
	}
}
