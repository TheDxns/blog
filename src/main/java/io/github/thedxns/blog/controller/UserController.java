package io.github.thedxns.blog.controller;

import java.net.URI;
import java.util.List;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

	private String keycloakServerUrl;
    private String keycloakRealm;

	public UserController(@Value("${keycloak.auth-server-url}") String keycloakServerUrl, @Value("${keycloak.realm}") String keycloakRealm) {
		this.keycloakServerUrl = keycloakServerUrl;
        this.keycloakRealm = keycloakRealm;
	}

	@GetMapping("/api/users")
	public Object getAllUsers() {
		Keycloak keycloak = KeycloakBuilder.builder()
            .serverUrl(keycloakServerUrl)
            .grantType(OAuth2Constants.PASSWORD)
            .realm(keycloakRealm)
            .clientId("blog-client")
            .username("serviceaccount")
            .password("serviceaccount")
            .resteasyClient(
                new ResteasyClientBuilder()
                    .connectionPoolSize(10).build()
            ).build();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + keycloak.tokenManager().getAccessToken().getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        return restTemplate.exchange(URI.create(keycloakServerUrl + "/admin/realms/Blog/users"), HttpMethod.GET, request, List.class).getBody();			
	}

    @GetMapping("/api/users/{id}")
	public Object getUser(@PathVariable String id) {
	Keycloak keycloak = KeycloakBuilder.builder()
        .serverUrl("http://localhost:8180/auth")
        .grantType(OAuth2Constants.PASSWORD)
        .realm("Blog")
        .clientId("blog-client")
        .username("serviceaccount")
        .password("serviceaccount")
        .resteasyClient(
            new ResteasyClientBuilder()
                .connectionPoolSize(10).build()
        ).build();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + keycloak.tokenManager().getAccessToken().getToken());
        HttpEntity<String> request = new HttpEntity<String>(headers);
        return restTemplate.exchange(URI.create(keycloakServerUrl + "/admin/realms/Blog/users/" + id), HttpMethod.GET, request, Object.class).getBody();			
	}

}