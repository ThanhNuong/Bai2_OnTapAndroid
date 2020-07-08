package vn.edu.ntu.thanhnuong.bai2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.ntu.thanhnuong.bai2.Controller.Controller;
import vn.edu.ntu.thanhnuong.bai2.Controller.IController;
import vn.edu.ntu.thanhnuong.bai2.Model.Friend;

public class ListAllFriendFragment extends Fragment {

    NavController navController;
    RecyclerView rvAllFriend;
    Controller controller;
    List<Friend> list;
    FriendAdapter friendAdapter;
    Button btnThem;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_all_friend, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = NavHostFragment.findNavController(ListAllFriendFragment.this);
        ((MainActivity)getActivity()).navController = navController;

        rvAllFriend = view.findViewById(R.id.rvAllFriend);
        btnThem = view.findViewById(R.id.btnThem);

        addEvents();

        controller = (Controller) getActivity().getApplication();//dùng chung
        list = controller.getAllFriend();
        friendAdapter = new FriendAdapter(list);

        rvAllFriend.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAllFriend.setAdapter(friendAdapter);
    }

    private  class  FriendViewHolder extends RecyclerView.ViewHolder{
        TextView txtTen, txtNgaySinh, txtSDT;
        ImageView imvEdit;
        Friend f;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtNgaySinh = itemView.findViewById(R.id.txtNgaySinh);
            txtSDT = itemView.findViewById(R.id.txtSDT);
            imvEdit = itemView.findViewById(R.id.imvEdit);

            imvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();

                    bundle.putString("id",f.getId());
                    bundle.putBoolean("sua",true);
                    navController.navigate(R.id.action_listAllFriendFragment_to_detailFragment,bundle);

                }
            });

        }

        public void bind(Friend f ){
            this.f = f;
            txtTen.setText(f.getTen());
            txtNgaySinh.setText(f.getNgaySinh());
            txtSDT.setText(f.getSDT());
        }

    }

    private class FriendAdapter extends RecyclerView.Adapter<FriendViewHolder>{

        List<Friend> list;

        public FriendAdapter(List<Friend> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.friend, parent, false);
            return new FriendViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
            holder.bind(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private void addEvents(){
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();

                bundle.putString("id","");
                bundle.putBoolean("sua",false);
                navController.navigate(R.id.action_listAllFriendFragment_to_detailFragment, bundle);

            }
        });
    }

}
