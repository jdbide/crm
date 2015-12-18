package pds.isintheair.fr.crm_tab.isin.truong.api;

import com.google.gson.*;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import retrofit.*;


/**
 * Created by Truong on 12/14/2015.
 */
public class RestHandle {
    private static RestHandle mInstant;
    private final ProspectAPI prospectAPI;

    public RestHandle() {
        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getDeclaredClass().equals(ModelAdapter.class);
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();

        Retrofit retrofit   = new Retrofit.Builder().baseUrl(ProspectAPI.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        prospectAPI         = retrofit.create(ProspectAPI.class);
    }

    public static synchronized RestHandle getmInstant() {
        if (mInstant == null) {
            mInstant = new RestHandle();
        }
        return mInstant;
    }

    public ProspectAPI getProspectAPI() {
        return prospectAPI;
    }
}
