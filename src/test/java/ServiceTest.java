import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.daos.IReimbDAO;
import com.revature.daos.IUserDAO;
import com.revature.daos.ReimbDAO;
import com.revature.daos.UserDAO;
import com.revature.models.ReimbDTO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.LoginServicee;
import com.revature.services.ReimbursementService;

public class ServiceTest {

	public static LoginServicee ls;
	public static ReimbursementService rs;
	public static IUserDAO uDao = new UserDAO();
	public static IReimbDAO rDao = new ReimbDAO();

	public static User u1;
	public static User u2;
	public static ReimbDTO testReimbDTO;
	
	
	
	public ServiceTest() {
		super();
	}

	@BeforeClass
	public static void set() {
		System.out.println("In Before Class");
		ls = new LoginServicee();
		rs = new ReimbursementService();
	}	
	
	@Before 
	public void startup() {

		u1= new User("wp", "44C00D86F24494FA710CF657C9518808");
		u2 = new User("wp", "ilikehoney");
		
		testReimbDTO = new ReimbDTO(58.00, "testDescription", 1, 2);
		
	}
	
	@AfterClass
	public static void shutdown() {
		u1= null;
		ls = null;
		rs = null;
	}

	@Test
	public void testLogin() throws NoSuchAlgorithmException {

		assertTrue(ls.login(u2) == uDao.findById(2));
		assertNull(ls.login(u1));
	}


	
	@Test
	public void addReimbTest() {
		testReimbDTO.submitted = new Timestamp(System.currentTimeMillis());
		boolean reimbAdd= rs.addReimbursement(testReimbDTO);
		assertTrue(reimbAdd);
	}
	

	@Test 
	public void findReimbById() {
		assertEquals(rDao.findById(1), rs.findById(1));
	}

	
	@Test
	public void findReimbByAuthor() {
		assertEquals(rs.findByAuthor(2),rDao.findByAuthor(2));
	}
	
	
	@Test
	public void findReimbByStatus() {
		assertEquals(rs.findByStatus(1), rDao.findByStatus(1));
	}
	

	@Test 
	public void findAllReimb() {
		List<Reimbursement> rList= rs.findAll();
		assertTrue(rList!=null);
	}

	@Test
	public void updateReimb() {
		testReimbDTO.id = 1;
		testReimbDTO.status = 2;
		testReimbDTO.resolver = 1;
		
		assertTrue(rs.updateReimbursement(testReimbDTO));
	}

}