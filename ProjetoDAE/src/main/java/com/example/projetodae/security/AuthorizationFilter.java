package com.example.projetodae.security;

import jakarta.annotation.Priority;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.core.ResourceMethodInvoker;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;

@Provider
@Authenticated
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    public static final Response ACCESS_DENIED = Response.status(401)
            .entity("Access denied for this resource").build();

    public static final Response ACCESS_FORBIDDEN = Response.status(403)
            .entity("Access forbidden for this resource").build();


    @Context
    private SecurityContext securityContext;

    @Override
    public void filter(ContainerRequestContext containerRequestContext){
        var methodInvoker = (ResourceMethodInvoker) containerRequestContext
                .getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");

        Method method = methodInvoker.getMethod();
        var resource = method.getDeclaringClass();


        //IF authenticated, access granted for all roles
        if (resource.isAnnotationPresent(PermitAll.class)){
            if (method.isAnnotationPresent(DenyAll.class)){
                containerRequestContext.abortWith(ACCESS_DENIED);
                return;
            }

            //Verify User Access
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                var roles = new HashSet<>(Arrays.asList(rolesAnnotation.value()));

                //User valido
                if (roles.stream().anyMatch(securityContext::isUserInRole)) {
                    return;
                }
            }

            containerRequestContext.abortWith(ACCESS_FORBIDDEN);
            return;
        }

        //Access denied for all
        if(resource.isAnnotationPresent(DenyAll.class)){
            if (method.isAnnotationPresent(PermitAll.class)){
                return;
            }

            //Verify User Access
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                var roles = new HashSet<>(Arrays.asList(rolesAnnotation.value()));

                //User valido
                if (roles.stream().anyMatch(securityContext::isUserInRole)) {
                    return;
                }
            }

            containerRequestContext.abortWith(ACCESS_DENIED);
            return;
        }

        if (resource.isAnnotationPresent(RolesAllowed.class)){
            if (method.isAnnotationPresent(DenyAll.class)){
                containerRequestContext.abortWith(ACCESS_DENIED);
                return;
            }

            if (method.isAnnotationPresent(PermitAll.class)){
                return;
            }

            RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
            var roles = new HashSet<>(Arrays.asList(rolesAnnotation.value()));

            if (method.isAnnotationPresent(RolesAllowed.class)){
                rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                roles.addAll(Arrays.asList(rolesAnnotation.value()));
            }

            //User valido
            if (roles.stream().anyMatch(securityContext::isUserInRole)) {
                return;
            }

            containerRequestContext.abortWith(ACCESS_FORBIDDEN);
            return;
        }

        if (method.isAnnotationPresent(DenyAll.class)){
            containerRequestContext.abortWith(ACCESS_DENIED);
            return;
        }

        if(method.isAnnotationPresent(PermitAll.class)){
            return;
        }

        if (method.isAnnotationPresent(RolesAllowed.class)){

            RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
            var roles = new HashSet<>(Arrays.asList(rolesAnnotation.value()));

            //User valido
            if (roles.stream().anyMatch(securityContext::isUserInRole)) {
                return;
            }
            containerRequestContext.abortWith(ACCESS_FORBIDDEN);
        }


    }



}
