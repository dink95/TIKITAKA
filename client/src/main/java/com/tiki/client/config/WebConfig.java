package com.tiki.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.PathResourceResolver;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final Locale DEFAULT_LOCALE = new Locale("en");

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor())
//    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("mbrId");
        return localeChangeInterceptor;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieName("mbrId");
        localeResolver.setCookieMaxAge(60*60*24*7);
        // 쿠키 유지 시간 (초)
        localeResolver.setDefaultLocale(DEFAULT_LOCALE);
        // 기본 로케일
        return localeResolver;
    }


    @Value("/istatic/images/")
    private String imgStatic;

    @Value("${server.file.upload.folder}")
    private String imgPath;


    /*로그인 여부 인터셉터*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ProcessInterceptor())
                .addPathPatterns("/auction/create")
                .addPathPatterns("/auction/detail")
                .addPathPatterns("/product/create")
                .addPathPatterns("/member/myinfo")
                .addPathPatterns("/complain/index")
                .excludePathPatterns("/main/**", "/webjars/**", "/dist/**", "/plugins/**", "/css/**")
                .excludePathPatterns("/images/**", "/js/**", "/fonts", "/webfonts/**", "/scss/**");
        registry.addInterceptor(new ProcessInterceptor_manager())
                .addPathPatterns("/admin/complain/detail")
                .addPathPatterns("/admin/complain/list")
                .excludePathPatterns("/main/**", "/webjars/**", "/dist/**", "/plugins/**", "/css/**")
                .excludePathPatterns("/images/**", "/js/**", "/fonts", "/webfonts/**", "/scss/**");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(imgStatic + "**").addResourceLocations("file:///" + imgPath)
                .setCachePeriod(0)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }


    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;

    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.of(1024, DataUnit.MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(1024, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

}
