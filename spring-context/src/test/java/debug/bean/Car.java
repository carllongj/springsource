package debug.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author longjie
 * 2021/4/20
 */
@Service
public class Car {
	private Integer id;

	@Value("${name}")
	private String brand;

	@Value("${information}")
	private String logo;

	public void setId(Integer id) {
		this.id = id;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public String getLogo() {
		return logo;
	}

	@Override
	public String toString() {
		return "Car{" +
				"id=" + id +
				", brand='" + brand + '\'' +
				", logo='" + logo + '\'' +
				'}';
	}
}
