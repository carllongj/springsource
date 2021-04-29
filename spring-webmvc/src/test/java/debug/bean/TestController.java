package debug.bean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author longjie
 * 2021/4/28
 */
@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping(value = "fun", method = RequestMethod.GET)
	@ResponseBody
	public String doPrint(HttpSession session, HttpServletRequest request) {
		return "{\"hello\":\"world\"}";
	}
}
