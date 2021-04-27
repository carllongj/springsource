package debug.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author longjie
 * 2021/4/27
 */
@Component
public class DatabaseConnectionInformation {

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Override
	public String toString() {
		return "DatabaseConnectionInformation{" +
				"url='" + url + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
