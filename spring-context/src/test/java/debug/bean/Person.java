package debug.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author longjie
 * 2021/4/20
 */
public class Person {

	private Integer id;

	@Value("${test}")
	private String name;

	private Integer age;

	private Car car;

	public Person(Car car){
		this.car = car;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", car=" + car +
				'}';
	}
}
