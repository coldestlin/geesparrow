package cn.ocxx.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


@Service
public class Another {
    
    @Autowired
    Property p;

    public void show() {
        System.out.println("try");
        p.showProperty();
    }

    public static void main(String[] args) {
        
        
    }

}
