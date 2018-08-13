package nexters.com.dear.model;

import android.media.session.MediaSession;

/**
 * Created by Jiwon on 2018-08-13.
 *  * Define. 회원 객체
 *
 *   로그인 성공한 회원 정보 객체
 *
 */

public class Member {

    private String email;
    private String nickname;

    public void setEmail(String email) { this.email = email; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public String getEmail() { return email; }
    public String getNickname() { return nickname; }

}
