package org.superbiz.struts;

import com.opensymphony.module.sitemesh.filter.PageFilter;
import org.apache.struts2.dispatcher.ActionContextCleanUp;
import org.apache.struts2.dispatcher.FilterDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EntityScan("org.superbiz.struts")
public class Application {
    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public FilterRegistrationBean struts2(){
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        Map<String,String> initParams = new HashMap<String, String>();

        initParams.put("actionPackages","com.lq");
        FilterDispatcher filterDispatcher = new FilterDispatcher();

        filterRegBean.setFilter(filterDispatcher);
        filterRegBean.setInitParameters(initParams);
        filterRegBean.addUrlPatterns("/*");
        filterRegBean.setOrder(0);
        return filterRegBean;
    }
    @Bean
    public FilterRegistrationBean strutsCleanup(){
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();

        ActionContextCleanUp actionContextCleanUp = new ActionContextCleanUp();

        filterRegBean.setFilter(actionContextCleanUp);
        filterRegBean.addUrlPatterns("/*");
        filterRegBean.setOrder(1);
        return filterRegBean;
    }
    @Bean
    public FilterRegistrationBean sitemesh(){
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();

        PageFilter pageFilter = new PageFilter();

        filterRegBean.setFilter(pageFilter);
        filterRegBean.addUrlPatterns("/*");
        filterRegBean.setOrder(2);
        return filterRegBean;
    }
}
