package net.live_on.itariya.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import net.live_on.itariya.entity.User;

@Stateless
public class UserDao extends AbstractFacade<User> {

	/** エンティティマネージャ */
    @PersistenceContext(unitName = "entrecUnit")
	protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserDao() {
        super(User.class);
    }

    /**
     * ユーザ情報を取得する
     * @param mail メールアドレス
     * @param password 暗号化済パスワード
     * @return ユーザ情報
     */
    public User searchUser(String mail, String password) {
    	User ret = null;
    	TypedQuery<User> query = em.createQuery(
    			"select u from User u where u.mail = :mail and u.password = :password", User.class);
    	query.setParameter("mail", mail);
    	query.setParameter("password", password);

    	try {
    		ret = query.getSingleResult();
    	} catch (NoResultException e) {
    		return null;
    	}

    	em.detach(ret);
        return ret;
    }
}
