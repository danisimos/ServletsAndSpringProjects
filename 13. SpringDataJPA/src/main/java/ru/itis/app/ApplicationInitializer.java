package ru.itis.app;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.itis.config.ApplicationConfig;
import ru.itis.filters.EncodingFilter;
import ru.itis.repositories.ProfilesRepositoryJpa;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.register(ApplicationConfig.class);

        //EncodingFilter encodingFilter = new EncodingFilter();
        //servletContext.addFilter("encodingFilter", encodingFilter).addMappingForUrlPatterns(null, false, "/*");

        ContextLoaderListener listener = new ContextLoaderListener(springContext);
        servletContext.addListener(listener);

        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcher",
                new DispatcherServlet(springContext));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");

        //springContext.getBean(ProfilesRepositoryJpa)

    }
}
