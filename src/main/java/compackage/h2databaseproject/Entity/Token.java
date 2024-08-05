package compackage.h2databaseproject.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public class Token {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
	 @Column(name="token_id")
	private Long tokenid;
	
	
	@Column(name="token_string",nullable=false,unique=true) 
	private String tokenstring;
	
	@Column(name="token_username") 
	private String tokenusername ;
	
	@Column(name="is_loggedout") 
	private boolean isloggedout;

	public Token() {
		super();
	}

	public Token(Long tokenid, String tokenstring, String tokenusername, boolean isloggedout) {
		super();
		this.tokenid = tokenid;
		this.tokenstring = tokenstring;
		this.tokenusername = tokenusername;
		this.isloggedout = isloggedout;
	}

	@Override
	public String toString() {
		return "Token [tokenid=" + tokenid + ", tokenstring=" + tokenstring + ", tokenusername=" + tokenusername
				+ ", isloggedout=" + isloggedout + "]";
	}

	public Long getTokenid() {
		return tokenid;
	}

	public void setTokenid(Long tokenid) {
		this.tokenid = tokenid;
	}

	public String getTokenstring() {
		return tokenstring;
	}

	public void setTokenstring(String tokenstring) {
		this.tokenstring = tokenstring;
	}

	public String getTokenusername() {
		return tokenusername;
	}

	public void setTokenusername(String tokenusername) {
		this.tokenusername = tokenusername;
	}

	public boolean isIsloggedout() {
		return isloggedout;
	}

	public void setIsloggedout(boolean isloggedout) {
		this.isloggedout = isloggedout;
	}
	
}
