/*
 * Copyright 2021 carllongj
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package debug.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author carllongj
 * 2021/5/2 22:33
 */
@Component
public class JdbcUrlParams {

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "JdbcUrlParams{" +
				"url='" + url + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
