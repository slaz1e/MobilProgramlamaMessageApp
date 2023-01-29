package com.example.chatapp.ui.mesajOlustur;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chatapp.MessageClass;
import com.example.chatapp.MesssageAdapter;
import com.example.chatapp.R;
import com.example.chatapp.databinding.FragmentMesajOlusturBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class mesajOlustur extends Fragment {
    private FragmentMesajOlusturBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public mesajOlustur() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static mesajOlustur newInstance(String param1, String param2) {
        mesajOlustur fragment = new mesajOlustur();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mesajOlusturViewModel mesajOlusturViewModel=new ViewModelProvider(this).get(mesajOlusturViewModel.class);
        binding=FragmentMesajOlusturBinding.inflate(inflater,container,false);
        View root=binding.getRoot();
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference messageDatabaseRef=firebaseDatabase.getReference("Users").child(firebaseAuth.getUid()).child("Messages");

        ArrayList<MessageClass>messageClassArrayList=new ArrayList<>();
        RecyclerView messagesRecyclerView=binding.recyclerView;
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        messagesRecyclerView.setLayoutManager(layoutManager);
        MesssageAdapter messsageAdapter=new MesssageAdapter(root.getContext().getApplicationContext(),messageClassArrayList);
        messagesRecyclerView.setAdapter(messsageAdapter);

        messageDatabaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                messageClassArrayList.add(new MessageClass(snapshot.getKey(),snapshot.getValue(String.class)));
                messsageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                int index=-1;
                for(int i=0;i<messageClassArrayList.size();++i)
                    if(messageClassArrayList.get(i).getName().equals(snapshot.getKey())){
                        index =i;
                        break;
                    }
                if(-1!=index){
                    messageClassArrayList.set(index,new MessageClass(snapshot.getKey(),snapshot.getValue(String.class)));
                    messsageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                int index=-1;
                for(int i=0;i<messageClassArrayList.size();++i)
                    if(messageClassArrayList.get(i).getName().equals(snapshot.getKey())){
                        index =i;
                        break;
                    }
                if(-1!=index){
                    messageClassArrayList.remove(index);
                    messsageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                int index=messageClassArrayList.indexOf(new MessageClass(snapshot.getKey(),snapshot.getValue(String.class)));
                if(-1!=index){
                    MessageClass messageClass=messageClassArrayList.get(index);
                    messageClassArrayList.remove(index);
                    messageClassArrayList.add(1+index,messageClass);
                    messsageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(root.getContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        Button messageButton=binding.olusturButton;
        EditText
                messageEditText=binding.fragmentMesaj,
                messageNameEditText=binding.fragmentMesajAd;
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String
                        message=messageEditText.getText().toString(),
                        messageName=messageNameEditText.getText().toString();
            if(!messageName.isEmpty())
                messageDatabaseRef.child(messageName).setValue(message);
                messageEditText.setText(null);
                messageNameEditText.setText(null);
            }
        });
        return root;
    }
}