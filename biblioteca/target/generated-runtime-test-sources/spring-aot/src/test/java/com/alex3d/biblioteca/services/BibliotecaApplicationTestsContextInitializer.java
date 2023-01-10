package com.alex3d.biblioteca.services;

import com.alex3d.biblioteca.model.Biblioteca;
import java.lang.reflect.Field;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ReflectionUtils;

public final class BibliotecaApplicationTestsContextInitializer {
  public static void registerBibliotecaService(DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("bibliotecaService", BibliotecaService.class)
        .instanceSupplier((instanceContext) -> {
          BibliotecaService bean = new BibliotecaService();
          instanceContext.field("bibliotecaModel", Biblioteca.class)
              .invoke(beanFactory, (attributes) -> {
                Field bibliotecaModelField = ReflectionUtils.findField(BibliotecaService.class, "bibliotecaModel", Biblioteca.class);
                ReflectionUtils.makeAccessible(bibliotecaModelField);
                ReflectionUtils.setField(bibliotecaModelField, bean, attributes.get(0));
              });
                  return bean;
                }).register(beanFactory);
          }
        }
