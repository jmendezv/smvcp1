package pep.mendez.smvcp1.spring.model.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class AdminService2Impl implements AdminService2 {

	@Override
	@Secured({ "ROLE_ADMIN", "ROLE_ROOT" })
	public void performNewService() {
		System.out.println("in performNewService");
	}

}
