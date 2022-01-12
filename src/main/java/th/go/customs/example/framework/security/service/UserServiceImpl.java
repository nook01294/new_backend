package th.go.customs.example.framework.security.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import th.go.customs.example.framework.security.domain.UserInfo;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Autowired
	private JdbcTemplate jdbcTemplate;

//  ########################### Load User by JPA ########################### 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = getUserInfo(username);
		// GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
		UserDetails userDetails = (UserDetails) new User(userInfo.getUsername(), userInfo.getPassword(),
				getAuthorizes(username));
		return userDetails;
	}

	public UserInfo getUserInfo(String username) {
		String sql = "SELECT u.user_name username, u.password password from fw_users u   WHERE  "
				+ " u.user_name = '" + username + "' ";
		System.out.println(" Login SQL:" + sql);
		UserInfo userInfo = (UserInfo) jdbcTemplate.queryForObject(sql, new RowMapper<UserInfo>() {
			public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserInfo user = new UserInfo();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));

				return user;
			}
		});
		return userInfo;
	}

	public Set<SimpleGrantedAuthority> getAuthorizes(String username) {
		String sql = "SELECT  ur.role_code role FROM "
				+ "fw_users u INNER JOIN fw_users_role ur on u.user_name=ur.username WHERE " + "  u.user_name = '"
				+ username + "' ";
		System.out.println(" Login SQL Roles:" + sql);

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		List<String> roleList = jdbcTemplate.query(sql, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
		// List<GrantedAuthority> athoList = new ArrayList<GrantedAuthority>();
		for (String roletmp : roleList) {
			// GrantedAuthority authority = new SimpleGrantedAuthority(roletmp);
			authorities.add(new SimpleGrantedAuthority(roletmp));
		}

		return authorities;
	}

}
