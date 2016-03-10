package tlacouque.uc.admin.ref.customer.fragment;

/**
 * Created by tlacouque on 23/01/2016.
 */
/* public class CreateHCFragmentTest {

    @Test
    public void testCreateHCWithValidHTTPResponse() throws Exception {

        ResponseRestCustomer responseRestCustomer = new ResponseRestCustomer();
        responseRestCustomer.setIsInserted(true);
        Response<ResponseRestCustomer> response = Response.success(responseRestCustomer);

        // actual attribute used by the class
        boolean         createCalledisOk;
        boolean         createCalledisNOk;
        boolean         errorServRest;
        HealthCenter independant = mock(HealthCenter.class);

        createCalledisNOk = false;
        createCalledisOk = false;
        errorServRest = false;

        // actual method after rest response
        if (response.errorBody() != null) {
            createCalledisNOk = true;
        } else {
            if (response.body().getIsInserted()) {
                independant.save();
                createCalledisOk = true;
            } else {
                errorServRest = true;
            }
        }

        assertTrue(createCalledisOk);
        assertFalse(createCalledisNOk);
        assertFalse(errorServRest);
    }


} */
