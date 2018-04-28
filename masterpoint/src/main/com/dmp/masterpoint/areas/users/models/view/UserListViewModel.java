package com.dmp.masterpoint.areas.users.models.view;

public class UserListViewModel {

    private String id;

    private String username;

    private boolean isAdmin;
    private boolean isModerator;
    private boolean isWorkman;
    private boolean isClient;


    public UserListViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean getIsModerator() {
        return isModerator;
    }

    public void setIsModerator(boolean moderator) {
        isModerator = moderator;
    }

    public boolean getIsWorkman() {
        return isWorkman;
    }

    public void setIsWorkman(boolean workman) {
        isWorkman = workman;
    }

    public boolean getIsClient() {
        return isClient;
    }

    public void setIsClient(boolean client) {
        isClient = client;
    }
}
