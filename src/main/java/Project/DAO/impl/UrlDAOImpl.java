package Project.DAO.impl;

import Project.DAO.UrlDAO;
import Project.Model.Url;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class UrlDAOImpl implements UrlDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Url url) {
        Serializable id = sessionFactory.getCurrentSession().save(url);
        return (Long) id;
    }

    @Override
    public Url getById(long id) {
        return sessionFactory.getCurrentSession().get(Url.class, id);
    }
}
