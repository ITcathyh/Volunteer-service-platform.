package hyh.listner;

import hyh.global.Variable;
import hyh.util.GetRandomString;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements ApplicationContextAware {
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Variable.brerurl = GetRandomString.getRandomString(10);
        Variable.excelpath = "pair/excel/";
        Variable.authpath = "pair/auth/";
    }
}
