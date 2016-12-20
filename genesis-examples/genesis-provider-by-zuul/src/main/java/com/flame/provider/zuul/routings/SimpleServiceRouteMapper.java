package com.flame.provider.zuul.routings;

import org.springframework.cloud.netflix.zuul.filters.discovery.ServiceRouteMapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sungang on 2016/12/20.
 */
public class SimpleServiceRouteMapper /*implements ServiceRouteMapper */{

    /**
     * A default api version
//     */
//    private static final String DEFAULT_VERSION = "v1";
//
//    private Pattern servicePattern;
//
//    /**
//     * A RegExp that refer to named groups define in servicePattern. Ex :
//     * "${version}/${name}"
//     */
//    private String routePattern;
//
//    public SimpleServiceRouteMapper(String servicePattern, String routePattern) {
//        this.servicePattern = Pattern.compile(servicePattern);
//        this.routePattern = routePattern;
//    }
//
//
//    @Override
//    public String apply(String serviceId) {
//        return mapped(serviceId);
//    }
//
//
//    public String mapped(String serviceId){
//        Matcher matcher = this.servicePattern.matcher(serviceId);
//        String service = serviceId;
//        String version = DEFAULT_VERSION;
//
//
//
//
//        return serviceId;
//    }
//
//    /**
//     * Route with regex and replace can be a bit messy when used with conditional named
//     * group. We clean here first and trailing '/' and remove multiple consecutive '/'
//     * @param route
//     * @return
//     */
//    private String cleanRoute(final String route) {
//        String routeToClean = route.replaceAll("/{2,}", "/");
//        if (routeToClean.startsWith("/")) {
//            routeToClean = routeToClean.substring(1);
//        }
//        if (routeToClean.endsWith("/")) {
//            routeToClean = routeToClean.substring(0, routeToClean.length() - 1);
//        }
//        return routeToClean;
//    }
}
