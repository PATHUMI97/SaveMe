package com.example.donation.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.donation.Home;
import com.example.donation.R;
import com.example.donation.adapter.BloodAdapter;
import com.example.donation.adapter.SliderAdapter;
import com.example.donation.model.Blood;
import com.example.donation.model.SliderItem;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;
    private CardView C1,c2,c3,c4,c5,c6,c7,c8;


    LinearLayoutManager linearLayoutManager;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseFirestore = FirebaseFirestore.getInstance();
        Home activity =(Home) getActivity();
        activity.showBottomNavigationView(true);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SliderView sliderView = view.findViewById(R.id.imageSlider);
        SliderAdapter sliderAdapter = new SliderAdapter(view.getContext());
        sliderAdapter.addItem(new SliderItem("", "https://www.bagmo.in/wp-content/uploads/2022/03/volunteers-woman-man-donating-blood-blood-donor-charity_262189-61.webp"));
        sliderAdapter.addItem(new SliderItem("", "https://t4.ftcdn.net/jpg/02/79/69/63/360_F_279696311_ipDrKpQKGub4oOG7ogyqiTQpMJXhnl1M.jpg"));
        sliderAdapter.addItem(new SliderItem("", "https://image.shutterstock.com/image-vector/blood-donor-tiny-people-character-260nw-1141569941.jpg"));
        sliderAdapter.addItem(new SliderItem("", "https://media.istockphoto.com/vectors/world-blood-donor-day-design-blood-donation-vector-concept-vector-id1225493905?k=20&m=1225493905&s=612x612&w=0&h=TMh7Yepch03wLETAnAukleWZzR8cse4X0GuEkz3Kg2Y="));
        sliderAdapter.addItem(new SliderItem("", "https://static.vecteezy.com/system/resources/previews/001/978/288/original/volunteer-collecting-blood-donation-free-vector.jpg"));

        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setScrollTimeInSec(5);
        sliderView.startAutoCycle();

    }
}