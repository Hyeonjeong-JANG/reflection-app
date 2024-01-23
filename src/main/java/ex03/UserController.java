package ex03;

@Controller
public class UserController {


    @RequestMapping(uri = "/login")
    public void login() {
        System.out.println("로그인 호출됨");
    }

    @RequestMapping(uri = "/join")
    public void join() {
        System.out.println("회원가입 호출됨");
    }

    // 새로 이런 메서드를 만들었는데 서로 전화통화해서 이거 만들었어요! 하기 전에는 알 수 있는 방법이 없다.
    @RequestMapping(uri = "/userInfo")
    public void userInfo() {
        System.out.println("유저 정보 보기");
    }

    @RequestMapping(uri = "/update-password")
    public void updatePassword(){
        System.out.println("패스워드 수정하기");
    }
    
}
