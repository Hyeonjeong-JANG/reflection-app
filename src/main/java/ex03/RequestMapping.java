package ex03;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 어노테이션은 원래 컴파일시점에 발동되는데 런타임으로 해놓으면 런타임 시점에 발동됨
@Target(ElementType.METHOD)
public @interface RequestMapping {
    // RequestMapping은 컨트롤러의 메서드에 적용되어 해당 메서드가 특정 URL 요청과 매핑(연결)되도록 설정한다. 즉, 클라이언트의 특정 URL 요청이 들어왔을 때, 해당 요청을 처리하기 위한 메서드를 지정하는 역할을 한다.
    String uri(); // identify /account/1 의 1 이런 것!

}
