package Project.DAO.impl;

import Project.DAO.UserDAO;
import Project.Model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(User user) {
        return (Long) sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getById(long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public User getByMail(String mail) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User u WHERE u.email =:mail");
        query.setParameter("mail", mail);
        return (User) query.getSingleResult();
    }

    @Override
    public Long getCount() {
        return (Long) sessionFactory.getCurrentSession().createQuery("select count(*) from User").getSingleResult();
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().remove(user);
    }
}
