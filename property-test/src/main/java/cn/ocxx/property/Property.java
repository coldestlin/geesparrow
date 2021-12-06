package cn.ocxx.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:cu.properties", ignoreResourceNotFound = true)
public class Property {

    @Value("${portal.csrf.urls:/api/whoami}") // 在返回中设置csrf token的url,默认是whoami接口
    private String[] csrfUrls;

    @Value("${portal.csrf.header.urls:/api/whoami}") // 在返回中设置csrf token的url,默认是whoami接口
    private String testU;

    @Value("${portal.csrf.header.urls:/api/whoami}") // 在返回中设置csrf token的url,默认是whoami接口
    private String[] csrfHeaderUrls2;

    @Value("${portal.csrf:abc}") // 在返回中设置csrf token的url,默认是whoami接口
    private String abc;

    public void showProperty() {
        System.out.println(testU);
        System.out.println(csrfUrls);
        System.out.println(csrfHeaderUrls2);
        System.out.println(abc);
    }
}