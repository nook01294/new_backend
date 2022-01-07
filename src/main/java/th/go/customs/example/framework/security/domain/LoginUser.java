package th.go.customs.example.framework.security.domain;

public class LoginUser {
    private String username;
    private String password;
    private String notiToken;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getNotiToken() {
		return notiToken;
	}

	public void setNotiToken(String notiToken) {
		this.notiToken = notiToken;
	}
}
