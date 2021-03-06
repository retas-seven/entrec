package net.live_on.itariya.entrec.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import net.live_on.itariya.entrec.entity.User;

@Stateless
public class UserDao extends AbstractFacade<User> {

	/** エンティティマネージャ */
    //@PersistenceContext(unitName = "entrecUnit")
	@Inject
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

    /**
     * 指定されたメールアドレス登録済みであるか確認する
     * @param mail メールアドレス
     * @return 確認結果
     */
    public boolean isRegisteredMail(String mail) {
    	TypedQuery<Long> query = em.createQuery(
    			"select count(u) from User u where u.mail = :mail", Long.class);
    	query.setParameter("mail", mail);

    	Long count = query.getSingleResult();

    	if(count == 0) {
    		// 登録されていない場合
    		return false;
    	}

    	return true;
    }

    /**
     * 新規ユーザIDを採番する
     * @return 新規ユーザID
     */
    public String nextUserId() {
    	Long count;
    	String ret;
    	TypedQuery<Long> query = em.createQuery(
    			"select count(u) from User u", Long.class);

    	count = query.getSingleResult();
    	ret = StringUtils.leftPad(String.valueOf(count.intValue() + 1), 12, '0');

        return ret;
    }
}
