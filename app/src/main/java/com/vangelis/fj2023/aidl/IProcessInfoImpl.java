package com.vangelis.fj2023.aidl;

import android.os.RemoteException;

import com.vangelis.fj2023.IProcessInfo;
import com.vangelis.fj2023.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Function：AIDL实现
 * Created on 2023/3/15.
 * Comment：
 *  继承目标，需要在AIDL文件生成后，且需要BUILD项目后才能看到
 *  这个实现可以放在OnBind接口中进行
 * @author Wangpei
 */
public class IProcessInfoImpl extends IProcessInfo.Stub {
    @Override
    public String getProcessMsg() throws RemoteException {
        return "我是APP，这个是我给你的信息：这个是远程service给的值";
    }

    @Override
    public List<User> getUsers() throws RemoteException {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setName("wangpei");
        User user2 = new User();
        user2.setId(2);
        user2.setName("xianli");
        users.add(user1);
        users.add(user2);
        return users;
    }
}