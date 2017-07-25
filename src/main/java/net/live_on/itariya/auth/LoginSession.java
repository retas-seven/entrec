package net.live_on.itariya.auth;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lombok.Data;

@Named
@SessionScoped
@Data
public class LoginSession implements Serializable {
	private static final long serialVersionUID = 1;

    private LoginUser loginUser;
}
