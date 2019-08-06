package com.business.core.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 反射工具类
 */
public class ReflectUtil {

    /**
     * 深度扫描指定包，并且注解为指定类的所有类集合
     *
     * @param basePackage     包地址。比如，com.eku
     * @param annotationClass 注解！
     * @return 类集合
     */
    public static Set<Class<?>> scanClass(String basePackage, Class<?> annotationClass) {
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                ClassUtils.convertClassNameToResourcePath(basePackage) + "/" + "**/*.class";
        Set<Class<?>> sets = new HashSet<Class<?>>();
        try {
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
            MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                if (metadataReader.getAnnotationMetadata().isAnnotated(annotationClass.getName())) {
                    sets.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sets;
    }

}