package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RockPaperScissorApplication {
    public static void main(String [] args){

        //MatchEngineService matchEngineService = new MatchEngineService();
        ApplicationContext context =new ClassPathXmlApplicationContext("Bean.xml");
        GameEngine obj=(GameEngine)context.getBean("game");
        obj.startGame();
    }
}
