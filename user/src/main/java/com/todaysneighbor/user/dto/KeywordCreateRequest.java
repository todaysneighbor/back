package com.todaysneighbor.user.dto;

import com.todaysneighbor.user.domain.entity.Keyword;
import com.todaysneighbor.user.domain.entity.User;
import lombok.*;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeywordCreateRequest {
    @NonNull
    private String keyword;

    public Keyword toEntity(User user) {
        Keyword keyword = new Keyword();
        keyword.setUser(user);
        keyword.setKeyword(this.keyword);
        return keyword;
    }
}