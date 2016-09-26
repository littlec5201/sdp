package com.development.software.finance.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of users. This is used for saving the
 * list of persons to XML.
 *
 */
@XmlRootElement(name = "Users")
public class UserListWrapper {

	private List<User> users;

	@XmlElement(name = "user")
    public List<User> getUsers() {
        return users;
    }

	 public void setUsers(List<User> users) {
	        this.users = users;
	    }
}
