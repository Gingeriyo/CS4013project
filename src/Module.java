
import java.util.ArrayList;

public class Module {
    private String name;
    private int credit;
    private String classCode;

    public Module (String name,int credit,String classCode) {
        this.name = name;
        this.credit = credit;
        this.classCode = classCode;
    }
    public int getCredit() {
        return credit;
    }
    public String getName() {
        return name;
    }
    public String getClassCode() {
        return classCode;
    }


}