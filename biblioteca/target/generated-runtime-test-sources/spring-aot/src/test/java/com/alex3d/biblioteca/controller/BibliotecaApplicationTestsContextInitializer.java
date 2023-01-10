package com.alex3d.biblioteca.controller;

import com.alex3d.biblioteca.services.BibliotecaService;
import java.lang.reflect.Field;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ReflectionUtils;

public final class BibliotecaApplicationTestsContextInitializer {
  public static void registerBibliotecaController(DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("bibliotecaController", BibliotecaController.class)
        .instanceSupplier((instanceContext) -> {
          BibliotecaController bean = new BibliotecaController();
          instanceContext.field("bibliotecaServise", BibliotecaService.class)
              .invoke(beanFactory, (attributes) -> {
                Field bibliotecaServiseField = ReflectionUtils.findField(BibliotecaController.class, "bibliotecaServise", BibliotecaService.class);
                ReflectionUtils.makeAccessible(bibliotecaServiseField);
                ReflectionUtils.setField(bibliotecaServiseField, bean, attributes.get(0));
              });
                  return bean;
                }).register(beanFactory);
          }
        }
