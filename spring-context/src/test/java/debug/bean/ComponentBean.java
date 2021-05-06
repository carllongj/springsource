package debug.bean;

/**
 * @author longjie
 * 2021/4/30
 */
public class ComponentBean {

	private InnerBean innerBean;

	public ComponentBean(InnerBean innerBean) {
		this.innerBean = innerBean;
	}

	public InnerBean getInnerBean() {
		return innerBean;
	}
}
