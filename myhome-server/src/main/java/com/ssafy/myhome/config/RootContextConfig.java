package com.ssafy.myhome.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import com.ssafy.myhome.model.board.repo.BoardRepo;
import com.ssafy.myhome.model.house.repo.DongCodeRepo;
import com.ssafy.myhome.model.user.repo.UserRepo;


@Configuration
@MapperScan(basePackageClasses = {UserRepo.class, DongCodeRepo.class, BoardRepo.class} )
public class RootContextConfig {

}
