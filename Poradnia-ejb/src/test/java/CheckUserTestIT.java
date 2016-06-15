/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.dao.jpa.AbstractFacade;
import com.mycompany.dao.jpa.AdminDTOFacade;
import com.mycompany.dao.jpa.DoctorDTOFacade;
import com.mycompany.dao.jpa.PatientDTOFacade;
import com.mycompany.dao.jpa.TermDTOFacade;
import com.mycompany.interfaces.AdminDTOFacadeLocal;
import com.mycompany.interfaces.DoctorDTOFacadeLocal;
import com.mycompany.interfaces.PatientDTOFacadeLocal;
import com.mycompany.model.AdminDTO;
import com.mycompany.model.DoctorDTO;
import com.mycompany.model.PatientDTO;
import com.mycompany.model.PlaceDTO;
import com.mycompany.model.TermDTO;
import com.mycompany.model.VisitDTO;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Marcin Kaczorowski, Karol Nowicki
 */
@RunWith(Arquillian.class)
@Transactional
public class CheckUserTestIT
{

    @EJB
    private DoctorDTOFacadeLocal doctorDTOFacade;
    @EJB
    private PatientDTOFacadeLocal patientDTOFacade;
    @EJB
    private AdminDTOFacadeLocal adminDTOFacade;

    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DoctorDTOFacadeLocal.class)
                .addClass(PatientDTOFacadeLocal.class)
                .addClass(AdminDTOFacadeLocal.class)
                .addClass(DoctorDTOFacade.class)
                .addClass(PatientDTOFacade.class)
                .addClass(AdminDTOFacade.class)
                .addClass(TermDTO.class)
                .addClass(TermDTOFacade.class)
                .addClass(AbstractFacade.class)
                .addClass(AdminDTO.class)
                .addClass(DoctorDTO.class)
                .addClass(PatientDTO.class)
                .addClass(PlaceDTO.class)
                .addClass(VisitDTO.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    public CheckUserTestIT()
    {

    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void doctorTest()
    {
        DoctorDTO doctor = new DoctorDTO();
        doctor.setAddress("aaaa");
        doctor.setEmail("bbb@gmail.com");
        doctor.setFirstName("Jan");
        doctor.setLastName("Nowak");
        doctor.setPassword("passwd");
        doctor.setPhoneNumber(478512369);
        doctor.setSpecialization("neurolog");
        doctor.setUserName("doctor1");

        doctorDTOFacade.create(doctor);
        doctorDTOFacade.flush();

        DoctorDTO doctor2 = doctorDTOFacade.checkUser(doctor.getUserName(), doctor.getPassword());

        assert doctor2 != null;
        assert doctor2.equals(doctor);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void patientTest()
    {
        PatientDTO patient = new PatientDTO();
        patient.setAddress("aaaa");
        patient.setEmail("bbb@gmail.com");
        patient.setFirstName("Jan");
        patient.setLastName("Nowak");
        patient.setPassword("passwd");
        patient.setPhoneNumber(478512369);
        patient.setUserName("patient1");

        patientDTOFacade.create(patient);
        patientDTOFacade.flush();

        PatientDTO patient2 = patientDTOFacade.checkUser(patient.getUserName(), patient.getPassword());

        assert patient2 != null;
        assert patient2.equals(patient);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void adminTest()
    {
        AdminDTO admin = new AdminDTO();
        admin.setPassword("passwd");
        admin.setUserName("admin1");

        adminDTOFacade.create(admin);
        adminDTOFacade.flush();

        AdminDTO admin2 = adminDTOFacade.checkUser(admin.getUserName(), admin.getPassword());

        assert admin2 != null;
        assert admin2.equals(admin);
    }

}
