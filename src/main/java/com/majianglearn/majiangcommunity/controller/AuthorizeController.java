package com.majianglearn.majiangcommunity.controller;

import com.majianglearn.majiangcommunity.dto.AccessTokenDTO;
import com.majianglearn.majiangcommunity.dto.GitHubUserDTO;
import com.majianglearn.majiangcommunity.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        GitHubUserDTO user = gitHubProvider.getUser(accessToken);
        System.out.println(user.getLogin());
        return "index";
    }


}
