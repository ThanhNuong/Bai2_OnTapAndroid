package vn.edu.ntu.thanhnuong.bai2.Controller;

import android.app.Application;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.thanhnuong.bai2.Model.Friend;

public class Controller  extends Application implements IController {
    List<Friend> list= new ArrayList<>();

    public Controller() {
        list.add(new Friend("01", "Thanh Nương", "20/05/1999", "090909090", "Quảng Nam"));
        list.add(new Friend("02", "Mộng Ngân", "20/05/1999", "090909090", "Phú Yên"));
        list.add(new Friend("03", "Mộng Ngân", "20/05/1999", "090909090", "Phú Yên"));
    }

    @Override
    public List<Friend> getAllFriend() {
        return list;
    }

    @Override
    public boolean addFriend(Friend f) {
        if(list.contains(f)){
            return false;
        }
        else{
            list.add(f);
        }
        return true;
    }

    @Override
    public Friend getFriendById(String id) {
        for(int i = 0; i < list.size(); i++){
            if(id == list.get(i).getId()){
                return list.get(i);
            }
        }return null;
    }

    @Override
    public void suaFriend(Friend f) {
        String id = f.getId();
        for(int i = 0 ; i < list.size(); i++){
            if (id.equals(list.get(i).getId())){
                list.set(i,f);
                Toast.makeText(getApplicationContext(),f.getId(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
