package upr.uas.pedro.object;

public class User {
  private int id;
  private String name;
  private String username;
  private String password;
  private int isLogin;

  public User(int id, String name, String username, String password) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.password = password;
  }

  public User(String name, String username, String password) {
    this.name = name;
    this.username = username;
    this.password = password;
  }

  public User() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername(Boolean query) {
    if (query) {
      username = '\'' + username + '\'';
    }
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword(Boolean query) {
    if (query) {
      password = '\'' + password + '\'';
    }
    return password;
  }

  public void setPassword(String Password) {
    password = Password;
  }

  public int getIsLogin() {
    return isLogin;
  }

  public void setIsLogin(int isLogin) {
    this.isLogin = isLogin;
  }
}
