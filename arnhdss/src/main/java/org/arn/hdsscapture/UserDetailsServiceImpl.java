package org.arn.hdsscapture;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.arn.hdsscapture.entity.GroupTable;
import org.arn.hdsscapture.entity.UserTable;
import org.arn.hdsscapture.repository.UserTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserTableRepository api;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	 String lowercaseUsername = username.toLowerCase();
    	 
         Optional<UserTable> userOptional = api.findByUsernameIgnoreCase(lowercaseUsername);
         
         if (!userOptional.isPresent()) {
             throw new UsernameNotFoundException(username);
         }
//
//        if (!api.findById(username).isPresent()) {
//            throw new UsernameNotFoundException(username);
//        }

//        final UserTable json = api.findById(username).get();
        final UserTable json = userOptional.get();
        
        if(!json.isUser_enabled()) {
            throw new UsernameNotFoundException(username);        	
        }
        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (GroupTable elem : json.getGroups()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(elem.getGroup_role()));

        }
        

        return new User(lowercaseUsername, json.getUser_password(), json.isUser_enabled(),true,true,true, grantedAuthorities);
        //return new User(json.getUsername(), json.getUser_password(), json.isUser_enabled(),true,true,true, grantedAuthorities);

    }

}