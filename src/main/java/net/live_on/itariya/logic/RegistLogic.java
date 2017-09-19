package net.live_on.itariya.logic;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import net.live_on.itariya.dao.UserDao;
import net.live_on.itariya.entity.User;
import net.live_on.itariya.form.RegistForm;
import net.live_on.itariya.util.ApDateUtil;
import net.live_on.itariya.util.CipherUtil;

@Stateless
public class RegistLogic {

	@Inject
    RegistForm form;

	@EJB
	UserDao userDao;

	public void registNewUser() {
		Date now = ApDateUtil.getSystemDate();

		// 新規ユーザID採番
		String userId = userDao.nextUserId();

		// 新規ユーザ登録
    	User user = new User();
		user.setUserId(userId);
		user.setMail(form.getMail());
		user.setPassword(CipherUtil.encrypt(form.getPassword()));
		user.setRegistDate(now);
		user.setRegistUserId(userId);
		user.setUpdateDate(now);
		user.setUpdateUserId(userId);

		userDao.create(user);
	}

	public boolean isRegisteredMail() {
		boolean ret = userDao.isRegisteredMail(form.getMail());
		return ret;
	}
}
