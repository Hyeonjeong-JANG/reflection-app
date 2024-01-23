package ex04;

public class DogApp {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String className = "ex04.Dog";
        Class cls = Class.forName(className);
        System.out.println(cls);
        Object ob = cls.newInstance(); // 뉴됨, 문자열로도 뉴를 할 수 있다 이거다.

        Dog d = (Dog) ob;
        System.out.println(d.name);
    }
}
