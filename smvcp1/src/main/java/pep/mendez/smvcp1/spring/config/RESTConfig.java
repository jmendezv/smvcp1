package pep.mendez.smvcp1.spring.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
public class RESTConfig
{
//    @Bean
//    public View jsonTemplate() {
//        MappingJackson2JsonView view = new MappingJackson2JsonView();
//        view.setPrettyPrint(true);
//        return view;
//    }
//     
//    @Bean
//    public ViewResolver viewResolver() {
//        return new BeanNameViewResolver();
//    }
    
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
    	RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
    	List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
    	list.add(jsonMessageConverter());
    	requestMappingHandlerAdapter.setMessageConverters(list);
    	return requestMappingHandlerAdapter;
    }
    
    @Bean
    public MappingJackson2HttpMessageConverter jsonMessageConverter() {
    	return new  MappingJackson2HttpMessageConverter();
    }
}