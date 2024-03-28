package me.guayabita.user;

public interface IUser {

	User getUser(String name);

	void saveUser(User user, boolean forceSave);

	void loadUser(User user, boolean byName);

	void reset();
}
