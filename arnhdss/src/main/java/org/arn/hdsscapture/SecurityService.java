package org.arn.hdsscapture;

public interface SecurityService {
    
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}