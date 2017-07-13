package net.live_on.itariya.logic;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import net.live_on.itariya.dao.UserDao;
import net.live_on.itariya.entity.User;
import net.live_on.itariya.form.RegistForm;
import net.live_on.itariya.util.CipherUtil;

@Stateless
public class RegistLogic {

	@Inject
    RegistForm form;

	@EJB
	UserDao UserDao;

	public void registNewUser() {
		String userId = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
		Date now = new Date();

		// 新規ユーザ登録
    	User user = new User();
		user.setUserId(userId);
		user.setMail(form.getMail());
		user.setPassword(CipherUtil.encrypt(form.getPassword()));
		user.setRegistDate(now);
		user.setRegistUserId(userId);
		user.setUpdateDate(now);
		user.setUpdateUserId(userId);

		UserDao.create(user);
	}
}
