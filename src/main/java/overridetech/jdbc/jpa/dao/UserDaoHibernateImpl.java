package overridetech.jdbc.jpa.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.util.Util;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getHibernateConnection();
    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "create table if not exists users (id SERIAL PRIMARY KEY, first_name varchar(200), last_name varchar(200), age smallint)";
            session.createNativeQuery(hql).executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "drop table if exists users";
            session.createNativeQuery(hql).executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession();) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
            session.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<User> list = session.createQuery("FROM User", User.class).list();
            transaction.commit();
            session.close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            String hql = "truncate table users";
            session.createNativeQuery(hql).executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
