/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.dao.jpa.AbstractFacade;
import com.mycompany.dao.jpa.TermDTOFacade;
import com.mycompany.interfaces.TermDTOFacadeLocal;
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
public class TermDTOFacadeLocalIT
{

    @EJB
    private TermDTOFacadeLocal termDTOFacade;

    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(TermDTOFacadeLocal.class)
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

    public TermDTOFacadeLocalIT()
    {
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void createTest()
    {
        TermDTO t1 = new TermDTO();
        java.util.Date data = new Date();
        java.sql.Date date = new java.sql.Date(data.getTime());
        t1.setDate(date);
        t1.setTime("12:00");

        termDTOFacade.create(t1);
        termDTOFacade.flush();
        
        Integer a = t1.getId();
        assert a != null;

        TermDTO t2 = termDTOFacade.find(t1.getId());

        assert t2 != null;
    }
    
    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void quantityTest()
    {
        TermDTO t1 = new TermDTO();
        TermDTO t2 = new TermDTO();
        TermDTO t3 = new TermDTO();
        TermDTO t4 = new TermDTO();
        java.util.Date data = new Date();
        java.sql.Date date = new java.sql.Date(data.getTime());
        t1.setDate(date);
        t2.setDate(date);
        t3.setDate(date);
        t4.setDate(date);
        t1.setTime("12:00");
        t2.setTime("13:00");
        t3.setTime("14:00");
        t4.setTime("15:00");

        termDTOFacade.create(t1);
        termDTOFacade.create(t2);
        termDTOFacade.create(t3);
        termDTOFacade.create(t4);
        termDTOFacade.flush();

        Integer a = termDTOFacade.count()-3;
        
        assert a == 4;
        
        List<TermDTO> list = termDTOFacade.findAll();
        Integer b = list.size()-3;
        
        assert b == 4;

    }
    
    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void removeTest()
    {
        TermDTO t1 = new TermDTO();
        TermDTO t2 = new TermDTO();
        TermDTO t3 = new TermDTO();
        TermDTO t4 = new TermDTO();
        java.util.Date data = new Date();
        java.sql.Date date = new java.sql.Date(data.getTime());
        t1.setDate(date);
        t2.setDate(date);
        t3.setDate(date);
        t4.setDate(date);
        t1.setTime("12:00");
        t2.setTime("13:00");
        t3.setTime("14:00");
        t4.setTime("15:00");

        termDTOFacade.create(t1);
        termDTOFacade.create(t2);
        termDTOFacade.create(t3);
        termDTOFacade.create(t4);
        termDTOFacade.flush();
        
        termDTOFacade.remove(t3);
        termDTOFacade.remove(t4);
        termDTOFacade.flush();

        Integer a = termDTOFacade.count()-3;
        
        assert a == 2;
        
        List<TermDTO> list = termDTOFacade.findAll();
        Integer b = list.size()-3;
        
        assert b == 2;

    }
    
    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void editTest()
    {
        TermDTO t1 = new TermDTO();
        java.util.Date data = new Date();
        java.sql.Date date = new java.sql.Date(data.getTime());
        t1.setDate(date);
        t1.setTime("12:00");

        termDTOFacade.create(t1);
        termDTOFacade.flush();
        
        t1.setTime("15:00");
        
        termDTOFacade.edit(t1);
        
        assert termDTOFacade.find(t1.getId()).getTime() == "15:00";

    }

}
