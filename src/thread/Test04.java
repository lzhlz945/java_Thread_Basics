package thread;

public class Test04 {
    private Test04(){}
    private static Test04 test04=null;

    public Test04 getTest04(){
        if(test04 ==  null){
            synchronized (Test04.class){
                if(test04 == null){
                    test04=new Test04();
                }
            }
        }
        return test04;
    }
}
class Teee{
    public static void main(String[] args) {

    }

}