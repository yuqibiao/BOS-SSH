package com.yyyu.ssh.templete;

import com.yyyu.ssh.templete.inter.IBaseDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 功能：BaseDao实现
 *
 * @author yu
 * @date 2017/7/27.
 */

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

    private Class clazz;//用于接收运行期泛型类型

    private DetachedCriteria criteria;


    public BaseDaoImpl() {
        //获得当前类型的带有泛型类型的父类
        ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获得运行期的泛型类型
        clazz = (Class) ptClass.getActualTypeArguments()[0];
    }

    @Resource
    public void setSessionFactory0(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }


    @Override
    public void saveOrUpdate(T t) {
        getHibernateTemplate().saveOrUpdate(t);
    }

    @Override
    public void save(T t) {
        getHibernateTemplate().save(t);
    }

    @Override
    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    @Override
    public void delete(Serializable id) {
        T t = getById(id);
        if (t == null) {
            throw new UnsupportedOperationException("不存在该id的数据，无法删除");
        } else {
            getHibernateTemplate().delete(t);
        }
    }

    @Override
    public void update(T t) {
        getHibernateTemplate().update(t);
    }

    @Override
    public T getById(Serializable id) {
        return (T) getHibernateTemplate().get(clazz, id);
    }


    @Override
    public Integer getTotalCount(DetachedCriteria dc) {
        //设置查询的聚合函数,总记录数
        dc.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);
        //清空之前设置的聚合函数
        dc.setProjection(null);
        if (list != null && list.size() > 0) {
            Long count = list.get(0);
            return count.intValue();
        } else {
            return null;
        }
    }

    @Override
    public List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize) {
        List<T> list = (List<T>) getHibernateTemplate().findByCriteria(dc, start, pageSize);
        return list;
    }

    @Override
    public List<T> getAllList(DetachedCriteria dc) {
        return (List<T>) getHibernateTemplate().findByCriteria(dc);
    }


    public <E> List<E> getPageListByHql(final String hql, final Object[] values, final Integer start, final Integer pageSize) {
        List<E> result = getHibernateTemplate().execute(new HibernateCallback<List<E>>() {
            @Override
            public List<E> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        query.setParameter(i, values[i]);
                    }
                }
                if (start>0){
                    query.setFirstResult(start);
                }
                if (pageSize>0){
                    query.setMaxResults(pageSize);
                }
                return query.list();
            }
        });
        return result;
    }

    @Override
    public <E> List<E> getAllListByHql(String hql, Object[] values) {
        return getPageListByHql(hql , values , -1 , -1);
    }


    @Override
    public <E> List<E> getPageListBySql(final String sql, final Object[] values, final Integer start, final Integer pageSize) {
        List<E> result = getHibernateTemplate().execute(new HibernateCallback<List<E>>() {
            @Override
            public List<E> doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery(sql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        query.setParameter(i, values[i]);
                    }
                }
                if (pageSize>0){
                    query.setMaxResults(pageSize);
                }
                if (start>0){
                    query.setFirstResult(start);
                }
                return query.list();
            }
        });
        return result;
    }

    @Override
    public <E> List<E> getAllListBySql(String sql, Object[] values) {
        return getPageListBySql(sql , values , -1 , -1);
    }

    public void evict(T t){
        getHibernateTemplate().evict(t);
    }

    public DetachedCriteria getCriteria() {
        return DetachedCriteria.forClass(clazz);
    }

}
