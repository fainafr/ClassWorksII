package model;

public interface IAccount {
	boolean addAccount(String login, String password);
	boolean addRole(String login, String role);
	String getPassword(String login);
	String[] getRoles(String login);
	String[] getAccounts(String roleString);
}
