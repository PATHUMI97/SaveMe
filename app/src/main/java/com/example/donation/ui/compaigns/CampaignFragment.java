package com.example.donation.ui.compaigns;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.donation.Home;
import com.example.donation.R;
import com.example.donation.adapter.CampaignAdapter;
import com.example.donation.adapter.RequestAdapter;
import com.example.donation.model.Campaign;
import com.example.donation.model.Request;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CampaignFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;
    private List<Campaign> campaignList;

    public CampaignFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        campaignList = new ArrayList<>();

//        insertSampleData();

    }

    private void insertSampleData() {

        CollectionReference reference = firebaseFirestore.collection("campaigns");
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(new Campaign(1,"", "A blood donation campaign held in Colombo today to mark the 83rd anniversary of the Bank of Ceylon (BOC). BOC Chairman Kanchana Ratwatte and CEO K. E. D. Sumanasiri were present at the campaign."));
        campaigns.add(new Campaign(2,"https://www.redcross.lk/wp-content/uploads/2014/03/Mul-blood-2.jpg", "Sri Lanka Red Cross Society Mullaitivu Branch organized a Blood Donation Camp at Ehatugaswewa in Welioya on the 20th of February 2014 with the support of the Blood bank of Mullaitivu.\n" +
                "\n" +
                "The blood donation drive held at Ehathugaswewa Pansala premises with a targeted collectiing of 50 pints of Bloods, many people walked in on the day. It was indeed a meaningful day for all of us; we would like to thanks all the staffs of Blood Bank unit Mullaitivu."));
        campaigns.add(new Campaign(3,"https://www.kdu.ac.lk/wp-content/uploads/2021/007/blooddonation/KDUM5276.jpg", "National Rover Scouts of KDU together with University Hospital of Kotelawala Defence University organized a Blood Donation Campaign under the guidance of the Vice Chancellor of KDU and The Chief Commissioner of Scout Association of Sri Lanka, Major General MP Peiris RWP RSP VSV USP ndc psc MPhil to aid the National Blood Bank during this time of need due to the depletion of blood bags, with the current COVID -19 pandemic situation."));
        campaigns.add(new Campaign(4,"http://island.lk/wp-content/uploads/2022/04/donation.jpg", "\n" +
                "A blood donation campaign was held by the North Central Naval Command at the Command Hospital on 28 April 2022."));
        campaigns.add(new Campaign(5,"https://www.kotte.mc.gov.lk/images/news/39/3.jpg", "A blood donation campaign organized by Nugegoda Public Library was held on 28th April 2022 at the Library premises. This was conducted by the Librarian Mr. M.S.N. Perera. The collected blood were donated to Apeksha Hospital Maharagama."));
        campaigns.add(new Campaign(6,"", "A blood donation campaign held in Colombo today to mark the 83rd anniversary of the Bank of Ceylon (BOC). BOC Chairman Kanchana Ratwatte and CEO K. E. D. Sumanasiri were present at the campaign."));
        campaigns.add(new Campaign(7,"https://www.redcross.lk/wp-content/uploads/2014/03/Mul-blood-2.jpg", "Sri Lanka Red Cross Society Mullaitivu Branch organized a Blood Donation Camp at Ehatugaswewa in Welioya on the 20th of February 2014 with the support of the Blood bank of Mullaitivu.\n" +
                "\n" +
                "The blood donation drive held at Ehathugaswewa Pansala premises with a targeted collectiing of 50 pints of Bloods, many people walked in on the day. It was indeed a meaningful day for all of us; we would like to thanks all the staffs of Blood Bank unit Mullaitivu."));
        campaigns.add(new Campaign(8,"https://www.kdu.ac.lk/wp-content/uploads/2021/007/blooddonation/KDUM5276.jpg", "National Rover Scouts of KDU together with University Hospital of Kotelawala Defence University organized a Blood Donation Campaign under the guidance of the Vice Chancellor of KDU and The Chief Commissioner of Scout Association of Sri Lanka, Major General MP Peiris RWP RSP VSV USP ndc psc MPhil to aid the National Blood Bank during this time of need due to the depletion of blood bags, with the current COVID -19 pandemic situation."));
        campaigns.add(new Campaign(9,"http://island.lk/wp-content/uploads/2022/04/donation.jpg", "\n" +
                "A blood donation campaign was held by the North Central Naval Command at the Command Hospital on 28 April 2022."));
        campaigns.add(new Campaign(10,"https://www.kotte.mc.gov.lk/images/news/39/3.jpg", "A blood donation campaign organized by Nugegoda Public Library was held on 28th April 2022 at the Library premises. This was conducted by the Librarian Mr. M.S.N. Perera. The collected blood were donated to Apeksha Hospital Maharagama."));

        for (Campaign c : campaigns){
            reference.add(c);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Home home = (Home) getActivity();
        home.showBottomNavigationView(true);
        return inflater.inflate(R.layout.fragment_campaign, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.fragment_campaign_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        CampaignAdapter adapter = new CampaignAdapter(campaignList, view.getContext());
        recyclerView.setAdapter(adapter);

        firebaseFirestore.collection("campaigns").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    campaignList.clear();
                    for (QueryDocumentSnapshot snapshot : task.getResult()){
                        Campaign campaign = snapshot.toObject(Campaign.class);
                        campaignList.add(campaign);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}