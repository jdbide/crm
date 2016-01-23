package fr.pds.isintheair.crmtab.common.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.pds.isintheair.crmtab.R;
import fr.pds.isintheair.crmtab.common.controller.adapter.ContactAdapter;
import fr.pds.isintheair.crmtab.common.model.database.dao.ContactDAO;
import fr.pds.isintheair.crmtab.common.model.database.entity.Contact;

public class ContactListFragment extends Fragment {
    @Bind(R.id.contact_list)
    RecyclerView contactList;

    public ContactListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        ButterKnife.bind(this, view);

        List<Contact>  contacts       = ContactDAO.getAll();
        ContactAdapter contactAdapter = new ContactAdapter(getContext(), contacts);

        contactList.setAdapter(contactAdapter);
        contactList.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
