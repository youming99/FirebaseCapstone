package halla.icsw.capstone_hym;

public class MemberInfo {
    private String nickName;

    public MemberInfo(String nickName){
        this.nickName =nickName;
    }

    public String getNickName(){
        return this.nickName;
    }
    public void setNickName(String nickName){
        this.nickName =nickName;
    }
}
