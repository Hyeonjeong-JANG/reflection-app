package ex01;

public class UserController {

    // /login이 들어오면 호출
    public void login() {
        System.out.println("로그인 호출됨");
    }

    // /join이 들어오면 호출
    public void join() {
        System.out.println("회원가입 호출됨");
    }

    // 새로 이런 메서드를 만들었는데 서로 전화통화해서 이거 만들었어요! 하기 전에는 알 수 있는 방법이 없다.
    public void userInfo() {
        System.out.println("유저 정보 보기");
    }
}
