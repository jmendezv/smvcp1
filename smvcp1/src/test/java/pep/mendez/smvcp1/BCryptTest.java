package pep.mendez.smvcp1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * insert into users values (null, 1, '$2a$10$k/u79QN2iQzzK4I88MgrK..NAj7N2u5pbo942Dz5cAZIy3TnpuyOG', 'pep@smvcp.com');
 * insert into authorities values (null, 'ROLE_USER', 'pep@smvcp.com', 1);
 * insert into authorities values (null, 'ROLE_ADMIN', 'pep@smvcp.com', 1);
 */

public class BCryptTest {

	@Test
	public void test() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String s = encoder.encode("123456");
		System.out.println(s);
	}

}
