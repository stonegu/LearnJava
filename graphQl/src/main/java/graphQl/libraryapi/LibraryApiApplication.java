package graphQl.libraryapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/*
 * *****************************************************************************************************************************
 * OAuth 2 allows arbitrary 'clients' to access user's ('resource owner') resources on 'resource servers' 
 * via 'authorization servers' in a secure, reliable, and efficient manner
 * 
 * client: the client is the application that wants to access the user's account
 * resource owner: the resource owner is the user who authorizes an application to access their account
 * resource server: the resource server hosts the protected user accounts
 * authorization server: the authorization server verifies the identity of the user then issues access tokens to the application
 * *****************************************************************************************************************************
*/
@SpringBootApplication
/*
 * The @EnableResourceServer annotation enables our application to behave as a Resource Server by configuring an 
 * OAuth2AuthenticationProcessingFilter and other equally important components.
 * 
 * Check out the ResourceServerSecurityConfigurer class to get a better idea on what's being configured behind the scenes.
 * 
*/
@EnableResourceServer
/*
 * the @EnableOAuth2Sso annotation transforms our application into an OAuth2 client. It instructs Spring to configure an 
 * OAuth2ClientAuthenticationProcessingFilter, along with other components that our application needs to be capable of 
 * obtaining access tokens from an authorization server.
 * 
 * Take a look at the SsoSecurityConfigurer class for further details on what Spring configures for us.
*/
@EnableOAuth2Sso
public class LibraryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
