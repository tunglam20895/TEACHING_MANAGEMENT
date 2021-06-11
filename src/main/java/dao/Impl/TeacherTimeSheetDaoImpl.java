package dao.Impl;

import dao.ObjectDao;
import entity.TeacherTimeSheet;
import utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class TeacherTimeSheetDaoImpl implements ObjectDao<TeacherTimeSheet> {

	Logger logger = Logger.getLogger(TeacherTimeSheetDaoImpl.class);

	Transaction transaction;

	@Override
	public List<TeacherTimeSheet> getAll() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			String hql = "from TeacherTimeSheet";
			Query query = session.createQuery(hql);
			List<TeacherTimeSheet> teacherTimeSheets = query.list();
		}catch (HibernateException e){
			logger.error(e);
		}
		return null;
	}

	@Override
	public boolean create(TeacherTimeSheet obj) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			session.save(obj);
			return true;
		}catch (HibernateException e){
			logger.error(e);
		}
		return false;
	}

	@Override
	public boolean update(TeacherTimeSheet obj) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.getTransaction();
			transaction.begin();
			session.update(obj);
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
		TeacherTimeSheet teacherTimeSheet = findById(id);
		if(teacherTimeSheet == null){
			System.out.println("Không tìm thấy bản kê khai");
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.getTransaction();
			transaction.begin();
			session.delete(teacherTimeSheet);
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
	public TeacherTimeSheet findById(long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			return session.get(TeacherTimeSheet.class,id);
		}catch (HibernateException e){
			logger.error(e);
		}
		return null;
	}
}
