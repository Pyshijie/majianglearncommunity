package com.majianglearn.majiangcommunity.controller;

import com.majianglearn.majiangcommunity.dto.AccessTokenDTO;
import com.majianglearn.majiangcommunity.dto.GitHubUserDTO;
import com.majianglearn.majiangcommunity.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("f4812b549bbb61035abb");
        accessTokenDTO.setClient_secret("4a2936da75415e6655e0a5d42683bc4ad185439c");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        GitHubUserDTO user = gitHubProvider.getUser(accessToken);
        System.out.println(user.getLogin());
        return "index";
    }


}
