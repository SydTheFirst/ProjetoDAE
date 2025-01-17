package com.example.projetodae.security;

import com.example.projetodae.ejbs.UserBean;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Priority;
import jakarta.ejb.EJB;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.spec.SecretKeySpec;
import java.security.Principal;
import java.util.logging.Logger;


@Provider
@Authenticated
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class.getName());

    @EJB
    private UserBean userBean;

    @Context
    private UriInfo uriInfo;

    @Override
    public void filter(ContainerRequestContext requestContext){

        var header = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if(header == null || !header.startsWith("Bearer ")){
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        String token = header.substring("Bearer".length()).trim();

        String username = getUsername(token);


        try {

            LOGGER.info("Extracted username: " + username);
        } catch (NotAuthorizedException e) {
            LOGGER.log(Level.SEVERE, "Invalid token: " + token, e);
            throw e; // Re-throw the exception after logging
        }

        var user = userBean.findOrFail(getUsername(token));
        System.out.println(user.getTipouser());

        requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return user::getUsername;
            }

            @Override
            public boolean isUserInRole(String role) {
                org.hibernate.Hibernate.initialize(user);
                System.out.println(user.getTipouser().valueOf(role).name().equals(role));
                return user.getTipouser().valueOf(role).name().equals(role);
            }

            @Override
            public boolean isSecure() {
                return uriInfo.getAbsolutePath().toString().startsWith("https");
            }

            @Override
            public String getAuthenticationScheme() {
                return "Bearer";
            }
        });

    }


    private String getUsername(String token){
        var key = new SecretKeySpec(TokenIssuer.SECRET_KEY, TokenIssuer.ALGORITHM);
        System.out.println(key);
        try{
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        }catch (Exception e){
            throw new NotAuthorizedException("Invalid JWT");
        }

    }


}
