package ex02;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 어노테이션은 원래 컴파일시점에 발동되는데 런타임으로 해놓으면 런타임 시점에 발동됨
@Target(ElementType.METHOD)
public @interface RequestMapping {
    String uri(); // identify /account/1 의 1 이런 것!

}
