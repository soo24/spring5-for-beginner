package io.namoosori.travelclub.spring;

import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.service.ClubService;
import io.namoosori.travelclub.spring.service.logic.ClubServiceLogic;
import io.namoosori.travelclub.spring.service.sdo.TravelClubCdo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;

public class TravelClubApp {

    // Test를 위해 만든 main method를 가지고 있는 class

    // 프로그램이 처음 시작될 때 applicationContext.xml에 대한 정보를 spring에게 알려줘야함
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); //문자열로 우리가 만든 xml파일로 설정 정보를 읽어오겠다
        // = Spring에게 설정정보는 classpath(같은 context path 안에 'applicationContext.xml')에 있다라고 알려준다

        String[] beanNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanNames));

        TravelClubCdo clubCdo = new TravelClubCdo("TravelCLub 이름", "Test TravelCLub");
        ClubService clubService = context.getBean("clubService", ClubService.class);

        String clubId = clubService.registerClub(clubCdo);
        System.out.println("ID: "+ clubId);

        TravelClub foundedClub = clubService.findClubById(clubId);
        System.out.println("클럽 이름: "+ foundedClub.getName());
        System.out.println("클럽 intro: "+ foundedClub.getIntro());
        System.out.println("클럽 foundationtime: "+ new Date(foundedClub.getFoundationTime()) );

    }
}
