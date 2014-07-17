package unq.tpi.persistencia.performanceEj.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import org.hibernate.criterion.Projections;
import unq.tpi.persistencia.performanceEj.model.Department;
import unq.tpi.persistencia.performanceEj.model.Employee;
import unq.tpi.persistencia.util.SessionManager;

public class DepartmentDAO {

	public Department getByName(final String name) {
		Session session = SessionManager.getSession();
		return (Department) session
				.createQuery("from Department where name = :name")
				.setParameter("name", name).uniqueResult();
	}

	public Department getByCode(String num) {
		Session session = SessionManager.getSession();
		return (Department) session.get(Department.class, num);
	}

	@SuppressWarnings("unchecked")
	public List<Department> getAll() {
		Session session = SessionManager.getSession();
		return session.createCriteria(Department.class).list();
	}

    @SuppressWarnings("unchecked")
    public Employee getManager(Department department) {
        Session session = SessionManager.getSession();
        Query criteria = session.createQuery("select e.id from Department this join this.managers e where this.id = :id").setMaxResults(1).setParameter("id", department.getNumber());
        return (Employee) session.get(Employee.class, (Integer) criteria.uniqueResult());
    }



}
