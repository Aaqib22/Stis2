package com.example.bluecodic.stis.aqibchat.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bluecodic.stis.R;
import com.example.bluecodic.stis.aqibchat.core.users.get.all.GetUsersContract;
import com.example.bluecodic.stis.aqibchat.core.users.get.all.GetUsersPresenter;
import com.example.bluecodic.stis.aqibchat.models.User;
import com.example.bluecodic.stis.aqibchat.ui.activities.ChatActivity;
import com.example.bluecodic.stis.aqibchat.ui.adapters.UserListingRecyclerAdapter;
import com.example.bluecodic.stis.aqibchat.utils.ItemClickSupport;
import com.example.bluecodic.stis.mysharedpref;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: Kartik Sharma
 * Created on: 8/28/2016 , 10:36 AM
 * Project: FirebaseChat
 */

public class UsersFragment extends Fragment implements GetUsersContract.View, ItemClickSupport.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String ARG_TYPE = "type";
    public static final String TYPE_CHATS = "type_chats";
    public static final String TYPE_ALL = "type_all";

    DatabaseReference mDatabaseReference;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerViewAllUserListing;

    private UserListingRecyclerAdapter mUserListingRecyclerAdapter;

    private GetUsersPresenter mGetUsersPresenter;

    public static UsersFragment newInstance(String type) {
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        UsersFragment fragment = new UsersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    List<User> users;
    mysharedpref prefobj;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_users, container, false);
        bindViews(fragmentView);

        users=new ArrayList<>();


        updateuserlist();

        prefobj=new mysharedpref(getContext());

        return fragmentView;
    }

    private void updateuserlist() {

        /*for(int i=1;i<=5;i++)
        {
            users.add(new User(i+"","aqib123","1122"));

        }*/


      //  mDatabaseReference = FirebaseDatabase.getInstance().getReference("Upload");
        mDatabaseReference=FirebaseDatabase.getInstance().getReference("student/users");
        //retrieving upload data from firebase database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            int i=0;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    DataSnapshot mysnap=postSnapshot.child("userInfo");
                    Map<String, String> userinfo=prefobj.getshared();
                    String myemail = mysnap.child("email").getValue().toString();
                    String mysharedemail=userinfo.get("email");

                    mysharedemail = mysharedemail.replace("@", "");
                    mysharedemail = mysharedemail.replace(".", "");

                    myemail = myemail.replace("@", "");
                    myemail = myemail.replace(".", "");
                    if(!myemail.equals(mysharedemail)) {
                        users.add(new User(myemail, myemail, "1122"));
                        i++;
                    }

                }

                mUserListingRecyclerAdapter = new UserListingRecyclerAdapter(users);
                mRecyclerViewAllUserListing.setAdapter(mUserListingRecyclerAdapter);
                mUserListingRecyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabaseReference=FirebaseDatabase.getInstance().getReference("teacher/users");
        //retrieving upload data from firebase database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            int i=0;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    DataSnapshot mysnap=postSnapshot.child("userInfo");
                    Map<String, String> userinfo=prefobj.getshared();
                    String myemail = mysnap.child("email").getValue().toString();
                    String mysharedemail=userinfo.get("email");

                    mysharedemail = mysharedemail.replace("@", "");
                    mysharedemail = mysharedemail.replace(".", "");

                    myemail = myemail.replace("@", "");
                    myemail = myemail.replace(".", "");
                    if(!myemail.equals(mysharedemail)) {
                        users.add(new User(myemail, myemail, "1122"));
                        i++;
                    }

                }

                mUserListingRecyclerAdapter = new UserListingRecyclerAdapter(users);
                mRecyclerViewAllUserListing.setAdapter(mUserListingRecyclerAdapter);
                mUserListingRecyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });









    }

    private void bindViews(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mRecyclerViewAllUserListing = (RecyclerView) view.findViewById(R.id.recycler_view_all_user_listing);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        mGetUsersPresenter = new GetUsersPresenter(this);
        getUsers();
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        ItemClickSupport.addTo(mRecyclerViewAllUserListing)
                .setOnItemClickListener(this);

        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        getUsers();
    }

    private void getUsers() {
        if (TextUtils.equals(getArguments().getString(ARG_TYPE), TYPE_CHATS)) {

        } else if (TextUtils.equals(getArguments().getString(ARG_TYPE), TYPE_ALL)) {
            mGetUsersPresenter.getAllUsers();
        }
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        ChatActivity.startActivity(getActivity(),
                mUserListingRecyclerAdapter.getUser(position).email,
                mUserListingRecyclerAdapter.getUser(position).uid,
                mUserListingRecyclerAdapter.getUser(position).firebaseToken);
    }

    @Override
    public void onGetAllUsersSuccess(List<User> users) {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onGetAllUsersFailure(String message) {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        Toast.makeText(getActivity(), "Error: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetChatUsersSuccess(List<User> users) {

    }

    @Override
    public void onGetChatUsersFailure(String message) {

    }
}
