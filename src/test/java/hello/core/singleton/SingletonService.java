package hello.core.singleton;

public class SingletonService {

    // instance 변수명은 관례
    // 자기 자신을 내부에 private하게, static으로 가지고 있음.
    // static 영역, 클래스 레벨에 올라가므로 딱 하나만 존재하게 된다.
    private static final SingletonService instance = new SingletonService();

    // 조회하기 위해
    public static SingletonService getInstance() {
        // 인스턴스의 참조를 꺼낼 수 있는 유일한 방법
        return instance;
    }
    // JVM이 뜰 때, new SingletonService();를 실행해서 객체를 생성한 후에,
    // SingletonService의 static 영역의 instance에 참조를 걸어놓음
    // 나중에 조회할 때는 getInstance를 실행하면 된다.

    // 생성자를 private 으로 선언해서, 외부에서 new SingletonService(); 를 호출하는 것을 막는다.
    // 외부에서 new 키워드로 객체를 생성할 수 없으므로, 객체를 생성할 수 있는 방법이 없다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
