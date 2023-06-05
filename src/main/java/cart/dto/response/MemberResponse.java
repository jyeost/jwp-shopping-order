package cart.dto.response;

import cart.domain.Member.Member;


public class MemberResponse {

    private String email;
    private String password;

    public MemberResponse() {
    }

    public MemberResponse(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    public static MemberResponse of(final Member member) {
        return new MemberResponse(
                member.getEmail().email(),
                member.getPassword().password()
        );
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}