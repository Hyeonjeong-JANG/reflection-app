package ex03;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// ex02 리팩토링
public class App {

    public static void findUri(List<Object> instances, String path){

        for (Object instance : instances){
            Method[] methods = instance.getClass().getDeclaredMethods();

            for(Method method : methods){
                RequestMapping rm = method.getDeclaredAnnotation(RequestMapping.class); // 이 메서드에 어떤 메서드가 있지? 이런 말!!

                if(rm == null) continue;

                if(rm.uri().equals(path)){
                    try {
                        method.invoke(instance); // con.login();
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static List<Object> componentScan(String pkg) throws URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();  // 클래스로더: 패키지를 분석할 수 있다.
        URL packageUrl = classLoader.getResource(pkg); // ex03에 있는 어노테이션을 다 분석한다.

        File ex03 = new File(packageUrl.toURI());

        List<Object> instances = new ArrayList<>(); // 뭐가 들어갈지 모르기 때문에 오브젝트에 담는다.

        for (File file : ex03.listFiles()){ // 오에스의 관점에서는 디렉터리도 뭐도 다 파일로 보인다. 그래서 디렉터리를 읽을 때 그냥 파일로 읽는다. // ex03안에 4개의 파일이 있기 때문에 이 포문은 내 바퀴 돈다. 네 바퀴를 돌면서 안에 @컨트롤러가 있나 확인하고 셋 자료 형에 담아놓는다.
            //System.out.println(file.getName());
            if(file.getName().endsWith(".class")){ // 패키지는 탐색할 필요가 없으니 .class가 뒤에 붙는 애만 가지고 하겠다.
                String className = pkg + "." + file.getName().replace(".class", ""); // 이렇게하면 .class가 공백으로 처리됨 // 이렇게 이름을 바꾼 이유!!! 이렇게 해야 이 바뀐 이름으로 뉴를 할 수 있음.
                //System.out.println(className);

                Class cls = Class.forName(className); // 없는 클래스 이름이 나오면 오류날 수 있으니까 예외처리를 해둔다.
                if(cls.isAnnotationPresent(Controller.class)){
                    Object instance = cls.newInstance(); // 여기 들어올 수 있는 것은 유저컨트롤러뿐이야 지금은. @컨트롤러가 거기에만 붙어서.
                    instances.add(instance); // UserController, BoardController
                }

            }
        }
        return instances;
    }

    public static void main(String[] args) throws URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 패키지를 분석해서 골뱅이 컨트롤러라는 것이 붙은 애들을 다 뉴해서 셋 자료형에 담음. 그러면 중복이 없는 싱글톤으로 담김. 어레이리스트에 담아도 되지만 셋에 담는 것이 안전함
        // 클래스로더는 아웃 폴더로 가는데 그냥 메인.자바로 간다고 봐도 무방
        List<Object> instances = componentScan("ex03");
        findUri(instances, "/login"); // UserController 외에 다른 컨트롤러에도 사용될 수 있게 만들자!!!
    }
}