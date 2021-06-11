package Dao.Impl;

import Dao.ObjectDao;
import Utils.HibernateUtil;
import Entity.Subject;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectDaoImpl implements ObjectDao<Subject> {

	Logger logger = Logger.getLogger(SubjectDaoImpl.class);

	Transaction transaction = null;

	@Override
	public List<Subject> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			String HQL = "from Subject";
			Query query = session.createQuery(HQL);
			List<Subject> list = query.list();
			return list;
		}catch (HibernateException e){
			logger.error(e);
		}
		return null;
	}

	@Override
	public boolean create(Subject obj) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			session.save(obj);
			return true;
		}catch (HibernateException e){
			logger.error(e);
		}
		return false;
	}

	@Override
	public boolean update(Subject obj) {
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
	public boolean delete(Subject obj) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.getTransaction();
			transaction.begin();
			session.delete(obj);
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
	public Subject findById(long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			return session.get(Subject.class,id);
		}catch (HibernateException e){
			logger.error(e);
		}
		return null;
	}
}
