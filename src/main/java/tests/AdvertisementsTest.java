package tests;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.example.demo.dao.AdvertisementDao;
import com.example.demo.models.Advertisement;



public class AdvertisementsTest {




		@Test
		void testAddKoza() throws SQLException {
			AdvertisementDao dao = new AdvertisementDao();
			int oldBroiAdvertisements = dao.getAllAdvertisements().size();

			dao.addKoza(new Advertisement());

			int novBroiAdvertisements = dao.getAllAdvertisements().size();

			assertNotSame(oldBroiAdvertisements, novBroiAdvertisements);
		}

		
	}


