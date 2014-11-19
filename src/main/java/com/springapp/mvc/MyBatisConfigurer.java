package com.springapp.mvc;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.*;

/**
 * Created by papadimos on 23/10/2014.
 */
public class MyBatisConfigurer {
    //private static final Logger log = Logger.getLogger(MyBatisConfigurer.class);

    public static String generateTypeAliasesPackages(String pathPattern) throws IOException {
        if (Utilities.isEmpty(pathPattern)) {
            throw new BeanInitializationException("The pathPattern is required");
        }
        String[] pathPatterns = pathPattern.split(",");

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Set<String> paths = new HashSet();
        for (int j = 0; j < pathPatterns.length; j++) {
            String pattern = pathPatterns[j];
            if (!pattern.startsWith("velti/tech/pms") && !pattern.startsWith("/velti/tech/pms")) {
                throw new BeanInitializationException("Each pathPattern should start with the \"velti\\tech\\pms\" package.");
            }
            Resource[] resources = resolver.getResources("classpath*:" + pattern);
            //log.debug("Discovered " + resources.length + " resources");

            for (int i = 0; i < resources.length; i++) {
                Resource resource = resources[i];
                //log.debug("Processing resource: " + resource);
                String fileName = resource.getURL().toString();
                String packageName = fileName.substring(fileName.indexOf("/velti/tech/pms"));
                if (packageName.startsWith("/")) {
                    packageName = packageName.substring(1);
                }
                if (packageName.endsWith("/")) {
                    packageName = packageName.substring(0, packageName.length() - 1);
                }
                packageName = packageName.replace('/', '.');

                //log.debug("Adding packageName: " + packageName);
                paths.add(packageName);
            }

        }

        String ret = "";
        Iterator<String> iterator = paths.iterator();
        while (iterator.hasNext()) {
            String typeAliasPackage = iterator.next();
            ret += typeAliasPackage;
            //log.info("Discovered type alias package: " + typeAliasPackage);
            if (iterator.hasNext()) {
                ret += ",";
            }
        }


        return ret;
    }

    public static Resource[] generateMapperLocations(String pathPattern) throws IOException {
        if (Utilities.isEmpty(pathPattern)) {
            throw new BeanInitializationException("The pathPattern is required");
        }
        String[] pathPatterns = pathPattern.split(",");

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Set<String> paths = new HashSet();
        List<Resource> resourcesList = new ArrayList<Resource>();
        for (int j = 0; j < pathPatterns.length; j++) {
            String pattern = pathPatterns[j];

            Resource[] resources = resolver.getResources("classpath*:" + pattern);
            //log.debug("Discovered " + resources.length + " resources");
            resourcesList.addAll(Arrays.asList(resources));

        }


        //exclude customer-extension if plugin customer-extension exists

        boolean pluginCustomerExtendMapperExists = false;

        for (Resource resource : resourcesList) {
            if(resource.getURL().getPath().endsWith("extend-customer-extra-attributes-mapper.xml")){
                pluginCustomerExtendMapperExists = true;
                break;
            }
        }

        if(pluginCustomerExtendMapperExists){
            for (int i=0; i< resourcesList.size(); i++) {
                if(resourcesList.get(i).getURL().getPath().endsWith("extend-customer-mapper.xml")){
                    resourcesList.remove(i);
                    break;
                }
            }
        }

        return resourcesList.toArray(new Resource[resourcesList.size()]);
    }


}

