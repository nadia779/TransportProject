package models;

public class UserSession {
	 
    private static UserSession instance;
 
    private static String role;
 
    private UserSession(String role) {
        UserSession.role = role;
    }

    public static UserSession getInstance(String role) {
        if(instance == null) {
            instance = new UserSession(role);
        }
        return instance;
    }
 
    public static String getRole() {
        return role;
    }

    public static void cleanUserSession() {
        role = "";// or null
    }
 
    @Override
    public String toString() {
        return "UserSession{" +
                "role='" + role + "}";
    }
 
    static class cleanUserSession {
 
        public cleanUserSession() {
            role = "";
        }
    }
}
