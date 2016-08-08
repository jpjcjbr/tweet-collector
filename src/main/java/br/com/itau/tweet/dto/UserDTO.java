package br.com.itau.tweet.dto;

public class UserDTO {

	private String username;
	private Integer followersCount = 0;
	
	public UserDTO(String username, Integer followersCount) {
		this.username = username;
		this.followersCount = followersCount;
	}
	
	public UserDTO() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}

}
