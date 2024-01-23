package ex03;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // 클래스 위에 붙일 때는 TYPE으로 한다.
public @interface Controller {
    // Controller는 클라이언트의 요청을 처리하고, 응답을 생성하는 역할을 담당한다.
}
