package cn.ocxx.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PropertyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyApplication.class, args);

		// Property p = new Property();
		// p.showProperty();

		// Another a = new Another();
		// a.show();

		PropertyUtil u = new PropertyUtil();
		String r = u.getCustomPropertyValue("abc", "WTF");
		System.out.println("check read property" + r);
		System.out.println(r);
		String re = u.getCustomPropertyValue("abcd", "WTF");
		System.out.println("re" + re);

	}

}
