package Models;

/**
 * Created by DZ on 2015/12/22.
 */
public class User//注意这里的所有命名为user的是管理员，到时候如果是真的用户使用名字client,但是他们都将使用这个类
{
    private String userName;
    private String passWord;

    public User() {
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean equals(User o)
    {
        if( userName.equals(o.getUserName()) && passWord.equals(o.getPassWord()) )
        {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (passWord != null ? passWord.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
