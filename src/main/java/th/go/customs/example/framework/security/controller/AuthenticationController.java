package th.go.customs.example.framework.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import th.go.customs.example.framework.security.config.TokenProvider;
import th.go.customs.example.framework.security.domain.LoginUser;
import th.go.customs.example.framework.security.model.AuthToken;

 
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws Exception {
    	
    	authenticate(loginUser.getUsername(), loginUser.getPassword());
    	
    	final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                         loginUser.getPassword() 
                )
        );
    	
        SecurityContextHolder.getContext().setAuthentication(authentication);
       // final String token = jwtTokenUtil.generateToken(authentication);
        AuthToken returnToken = jwtTokenUtil.generateToken2(authentication);
        return ResponseEntity.ok(returnToken);
    }
    
    private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
