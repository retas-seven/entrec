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

import net.live_on.itariya.entity.UserCookie;

@Stateless
public class CookieDao extends AbstractFacade<UserCookie> {

	/** エンティティマネージャ */
    @PersistenceContext(unitName = "entrecUnit")
	protected EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CookieDao() {
        super(UserCookie.class);
    }

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
