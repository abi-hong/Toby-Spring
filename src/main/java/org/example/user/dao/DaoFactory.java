package org.example.user.dao;

import org.example.user.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//DI 컨테이너
@Configuration//애플리케이션 컨텍스트가 사용할 정보 표시
public class DaoFactory {//팩토리를 통해 객체를 생성해서 반환함으로 Main에서 ConnectionMaker을 결정하는 것을 막음

    @Bean//Ioc용 오브젝트 생성 메소드
    public UserDao userDao(){

        /* 팩토리의 메소드는 UserDao 타입의 오브젝트를 어떻게 만들고 어떻게 준비시킬지를 결정한다.
        return new UserDao(new DConnectionDao());
        */
        return new UserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker(){ //분리해서 중복을 제거함
        return new DConnectionMaker();
    }
}
