package vn.edu.ntu.thanhnuong.bai2.Controller;

import java.util.List;

import vn.edu.ntu.thanhnuong.bai2.Model.Friend;

public interface IController {
    public List<Friend> getAllFriend();
    public boolean addFriend(Friend f);
    public Friend getFriendById(String id);
    public void suaFriend(Friend f);
}
