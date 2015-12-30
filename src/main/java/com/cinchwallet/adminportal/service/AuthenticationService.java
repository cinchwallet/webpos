
package com.cinchwallet.adminportal.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import com.cinchwallet.adminportal.model.Merchant;
import com.cinchwallet.adminportal.security.Role;
import com.cinchwallet.adminportal.security.User;


public class AuthenticationService implements AuthenticationProvider{

	POSService posService = new POSService();
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		Merchant merchant = posService.getStoreDetail(username, password);
		
		if (merchant == null) {
			throw new BadCredentialsException("Wrong password.");
		}

		User user = new User();
		user.setUsername(username);
		user.setMerchantId(merchant.getMerchantId());
		user.setStoreId(merchant.getStoreId());
		user.setMerchantName(merchant.getMerchantName());
		user.setStoreName(merchant.getStoreName());
		
		Role r = new Role();
		r.setName("ROLE_USER");
		List<Role> roles = new ArrayList<Role>();
		roles.add(r);
		user.setAuthorities(roles);


		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}