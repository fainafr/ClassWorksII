package application.model;

public interface IAdmin {
	
	boolean addAccount(String login, String password);
	boolean addRole(String login, String role);
	String getPassword(String login);
	String[] getRoles(String login);
	String[] getAccounts(String roleString);
	
}
