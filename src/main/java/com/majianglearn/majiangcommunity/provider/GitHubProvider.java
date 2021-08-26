package com.majianglearn.majiangcommunity.provider;

import com.alibaba.fastjson.JSON;
import com.majianglearn.majiangcommunity.dto.AccessTokenDTO;
import com.majianglearn.majiangcommunity.dto.GitHubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
//            String access_token = response.body().string();
//            System.out.println(access_token);
//            return access_token;
                String string = response.body().string();
                String[] split = string.split("&");
                String token = split[0].split("=")[1];
                return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public GitHubUserDTO getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization", "token " + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string =  response.body().string();
            GitHubUserDTO userDTO = JSON.parseObject(string, GitHubUserDTO.class);
            return userDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
