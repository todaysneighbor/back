package com.todaysneighbor.auth.service;

import com.todaysneighbor.auth.dto.UserDTO;
import com.todaysneighbor.auth.entity.User;
import com.todaysneighbor.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 카카오에서 반환된 사용자 정보 추출
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Long providerId = (Long) attributes.get("id");
        String nickname = (String) ((Map<String, Object>) attributes.get("properties")).get("nickname");
        String profileImageUrl = (String) ((Map<String, Object>) attributes.get("properties")).get("profile_image");
        log.info("providerId : {}", providerId);
        log.info("nickname : {}", nickname);
        log.info("profileImageUrl : {}", profileImageUrl);

        // UserDTO 생성 및 필드 설정
        UserDTO userDTO = new UserDTO();
        userDTO.setProviderId(providerId);
        userDTO.setNickname(nickname);
        userDTO.setFilename(profileImageUrl);

        // DB에 사용자가 있는지 확인 후 없다면 DB에 저장
        Optional<User> userOptional = userRepository.findByProviderId(providerId);
        User user = userOptional.orElseGet(() -> createUserDTO(userDTO));


        // 권한 설정
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");

        // nameAttributeKey
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();


        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), userNameAttributeName);
    }

    private User createUserDTO(UserDTO userDTO) {
        User newUser = new User();
        newUser.setProviderId(userDTO.getProviderId());
        newUser.setNickname(userDTO.getNickname());
        newUser.setFilename(userDTO.getFilename());
        return userRepository.save(newUser);
    }
}
