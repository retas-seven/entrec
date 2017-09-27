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

import net.live_on.itariya.entrec.entity.UserCookie;

@Stateless
public class CookieDao extends AbstractFacade<UserCookie> {

	/** エンティティマネージャ */
	//@PersistenceContext(unitName = "entrecUnit")
    @Inject
	protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CookieDao() {
        super(UserCookie.class);
    }

    /**
     * クッキーの値をもとにユーザ情報を検索する
     * @param cookieValue クッキーの値
     * @return 検索結果
     */
    public UserCookie findByCookieValue(String cookieValue) {
    	UserCookie ret = null;
    	TypedQuery<UserCookie> query = em.createNamedQuery("UserCookie.findByCookieValue", UserCookie.class);
    	query.setParameter("cookieValue", cookieValue);

    	try {
    		ret = query.getSingleResult();
    	} catch (NoResultException e) {
    		return null;
    	}

    	em.detach(ret);
        return ret;
    }
}
