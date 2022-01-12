package th.go.customs.example.framework.security.config;

import static th.go.customs.example.framework.security.domain.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static th.go.customs.example.framework.security.domain.Constants.AUTHORITIES_KEY;
import static th.go.customs.example.framework.security.domain.Constants.OGANIZATION_KEY;
import static th.go.customs.example.framework.security.domain.Constants.SIGNING_KEY;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import th.go.customs.example.app.model.FwUserModel;
import th.go.customs.example.app.repo.jpa.FwUserRepo;
import th.go.customs.example.app.service.RoleService;
import th.go.customs.example.app.vo.res.GetRoleRes;
import th.go.customs.example.framework.security.model.AuthToken;
import th.go.customs.example.framework.security.model.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenProvider implements Serializable {

	@Autowired
	private JdbcTemplate  jdbcTemplate;
	
	@Autowired
	private FwUserRepo fwUserRepo;
	
	@Autowired
	private RoleService roleService;
	
	
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority) .collect(Collectors.joining(","));
        
        System.out.println(" In  TokenProvider.generateToken  authorities:"+authorities);
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .claim(OGANIZATION_KEY, "Chaingmai")
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .compact();
    }
    
    public AuthToken generateToken2(Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority) .collect(Collectors.joining(","));
        AuthToken returnToken = new AuthToken();
        
        UserInfo userInfo = getUserInfo(authentication.getName());
        
     //   System.out.println(" In  TokenProvider.generateToken2  authorities:"+authorities);
        String tokenStr= Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .claim(OGANIZATION_KEY, "Chaingmai")
//                .claim(USERNAME, authentication.getName())
//                .claim(FULLNAME, userInfo.getFullName())
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .compact();
        
        returnToken.setToken(tokenStr);
        returnToken.setUsername(authentication.getName());
        returnToken.setFirstName(userInfo.getFirstName());
        returnToken.setLastName(userInfo.getLastName());
        returnToken.setDateOfBirth(userInfo.getDateOfBirth());
        returnToken.setMobile(userInfo.getMobile());
        returnToken.setEmail(userInfo.getEmail());
        
        FwUserModel user = fwUserRepo.findByUsername(authentication.getName());
        GetRoleRes role = roleService.getRoleByRoleCode(user.getRoleCode());
        returnToken.setRole(role);
        		
        return returnToken;
    }

 
    public UserInfo getUserInfo(String username){
    	String sql = "SELECT * from fw_users u   WHERE u.user_name = '"+username+"' ";
    	//System.out.println("   SQL get Organize:"+sql);
    	UserInfo userInfo = (UserInfo)jdbcTemplate.queryForObject(sql,
    		new RowMapper<UserInfo>() {
	            public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
	                UserInfo user = new UserInfo();
	                user.setUsername(rs.getString("user_name"));
	                user.setPassword(rs.getString("password"));
	                user.setFirstName(rs.getString("first_name"));
	                user.setLastName(rs.getString("last_name"));
	                user.setDateOfBirth(rs.getDate("date_of_birth"));
	                user.setMobile(rs.getString("mobile"));
	                user.setEmail(rs.getString("email"));
	                return user;
	            }
        });
    	return userInfo;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
              username.equals(userDetails.getUsername())
                    && !isTokenExpired(token));
    }

    UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {

        final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);

        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        final Claims claims = claimsJws.getBody();

        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

}
