package com.majianglearn.majiangcommunity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitHubUserDTO {
    private String login;
    private Long id;
    private String bio;
}
