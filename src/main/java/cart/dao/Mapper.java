package cart.dao;

import cart.domain.Member;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class Mapper {


    public Member toMember(ResultSet rs) {
        //return new Member(rs., email, null);
        return null;
    }
}
