import bo.DoctorManager;
import entity.Doctor;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorManagerTest {

    private DoctorManager manager;
    private Doctor doctor1;
    private Doctor doctor2;

    @BeforeEach
    public void setUp() {
        manager = new DoctorManager();
        doctor1 = new Doctor("D001", "John Doe", "Cardiology", 5);
        doctor2 = new Doctor("D002", "Jane Smith", "Neurology", 3);
    }

    @Test
    public void testAddDoctorSuccess() throws Exception {
        assertTrue(manager.addDoctor(doctor1));
        assertEquals(1, manager.getDoctorMap().size());
    }

    @Test
    public void testAddDoctorDuplicateCode() throws Exception {
        manager.addDoctor(doctor1);
        Exception exception = assertThrows(Exception.class, () -> {
            manager.addDoctor(new Doctor("D001", "Duplicate", "Surgery", 2));
        });
        assertEquals("Doctor code [D001] is duplicate.", exception.getMessage());
    }

    @Test
    public void testUpdateDoctorSuccess() throws Exception {
        manager.addDoctor(doctor1);
        Doctor updated = new Doctor("D001", "John Updated", "Oncology", 10);
        assertTrue(manager.updateDoctor(updated));
        assertEquals("John Updated", manager.getDoctorMap().get("D001").getName());
    }

    @Test
    public void testUpdateDoctorNotExist() {
        Exception exception = assertThrows(Exception.class, () -> {
            manager.updateDoctor(doctor1);
        });
        assertEquals("Doctor code doesn’t exist.", exception.getMessage());
    }

    @Test
    public void testDeleteDoctorSuccess() throws Exception {
        manager.addDoctor(doctor1);
        assertTrue(manager.deleteDoctor("D001"));
        assertEquals(0, manager.getDoctorMap().size());
    }

    @Test
    public void testDeleteDoctorNotExist() {
        Exception exception = assertThrows(Exception.class, () -> {
            manager.deleteDoctor("D999");
        });
        assertEquals("Doctor code doesn’t exist.", exception.getMessage());
    }

    @Test
    public void testSearchDoctorFound() throws Exception {
        manager.addDoctor(doctor1);
        manager.addDoctor(doctor2);

        Map<String, Doctor> result = manager.searchDoctor("Jane");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("D002", result.keySet().iterator().next());
    }

    @Test
    public void testSearchDoctorNotFound() throws Exception {
        manager.addDoctor(doctor1);
        assertNull(manager.searchDoctor("XYZ"));
    }

    @Test
    public void testToStringNotEmpty() throws Exception {
        manager.addDoctor(doctor1);
        manager.addDoctor(doctor2);
        String output = manager.toString();
        assertNotNull(output);
        assertTrue(output.contains("Code"));
        assertTrue(output.contains("John Doe"));
        assertTrue(output.contains("Jane Smith"));
    }

    @Test
    public void testToStringEmpty() {
        assertNull(manager.toString());
    }
}
