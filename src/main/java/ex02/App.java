package ex02;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 리플렉션의 한계점: 각 테이블마다 컨트롤러를 만들어야 한다. UserController, TableController, ... 이것을 해결해 보자! ex03에서
 */
// A 개발자: 컨트롤러 만든 사람한테 @리퀘스트 맵핑 써야해요. 라고 말해주면 됨.(어떻게 호출하는지도 알려줘야 해.)
public class App {
    public static void main(String[] args) {
        String path = "/update-password";

        UserController con = new UserController();

        Method[] methods = con.getClass().getDeclaredMethods(); // 컨의 클래스를 가져와서 그 안에 선언된 메서드를 다 가져와
//        System.out.println(methods.length);

        for (Method method : methods) {
//            System.out.println(method.getName()); // join, userInfo, login
            RequestMapping rm = method.getDeclaredAnnotation(RequestMapping.class);// 메서드 중 어노테이션 선언된 메서드를 겟하고, 그 어노테이션된 객체를 rm에 담는다.

            if (rm == null) {
                continue;
            }
            if (rm.uri().equals(path)) {
                try {
                    method.invoke(con); // 메서드를 이름이 아니라 리플렉션으로 바로 호출할 수 있다. con.login();과 같은 코드임. 괄호 안에는 오브젝트.
                    // 유저 컨트롤러는 힙을 세 번 하면 세 개 생기는데 그게 너무 많아지면? 그 많은 힙을 구부하기 위해서는 con을 적어줘야 함.
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
