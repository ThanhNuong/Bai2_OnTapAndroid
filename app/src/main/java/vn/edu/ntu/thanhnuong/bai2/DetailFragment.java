package vn.edu.ntu.thanhnuong.bai2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import vn.edu.ntu.thanhnuong.bai2.Controller.Controller;
import vn.edu.ntu.thanhnuong.bai2.Model.Friend;

public class DetailFragment extends Fragment {
    EditText edtId, edtTen, edtNgaySinh, edtSDT, edtDiaChi;
    Button btnLuu;
    Controller controller;
    NavController navController;
    String id;
    boolean sua;
    Friend f;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = NavHostFragment.findNavController(DetailFragment.this);
        ((MainActivity)getActivity()).navController = navController;

        controller = (Controller) getActivity().getApplication();

        Bundle bundle = getArguments();
        id = bundle.getString("id");
        sua = bundle.getBoolean("sua");

        addViews(view);
        addEvents();
    }

    private void addViews(View view){
        edtId = view.findViewById(R.id.edtId);
        edtTen = view.findViewById(R.id.edtTen);
        edtNgaySinh = view.findViewById(R.id.edtNgaySinh);
        edtSDT = view.findViewById(R.id.edtSDT);
        edtDiaChi = view.findViewById(R.id.edtDiaChi);
        btnLuu = view.findViewById(R.id.btnLuu);

        if(sua == true){
            f = controller.getFriendById(id);
            edtId.setText(f.getId());
            edtTen.setText(f.getTen());
            edtNgaySinh.setText(f.getNgaySinh());
            edtSDT.setText(f.getSDT());
            edtDiaChi.setText(f.getDiaChi());
            btnLuu.setText("Sửa");
        }

    }

    private void addEvents(){
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = edtId.getText().toString();
                String ten = edtTen.getText().toString();
                String ngaySinh = edtNgaySinh.getText().toString();
                String sdt = edtSDT.getText().toString();
                String diaChi = edtDiaChi.getText().toString();
                Friend f = new Friend(id, ten, ngaySinh, sdt, diaChi);

                if(sua == true){
//                    Toast.makeText(getActivity(),f.getId(),Toast.LENGTH_SHORT).show();
                    controller.suaFriend(f);

                }else  {
                    if(controller.addFriend(f)){
                        Toast.makeText(getActivity(), "Thành công", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(getActivity(), "Thất bại", Toast.LENGTH_SHORT).show();
                }

                navController.navigateUp();
            }

        });
    }
}
