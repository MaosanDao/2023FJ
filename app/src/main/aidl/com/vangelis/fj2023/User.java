package com.vangelis.fj2023;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * Function：IPC通信的自定义数据
 * Created on 2023/3/17.
 * Comment：
 *      注意：
 *          1.Parcel in和write的顺序一定要一致
 *          2.同时定义的实体也需要在这个目录中
 *          3.在实体的aidl中需要手动将实体的路径用import引入
 *          4.另外需要aidl的包路径必须要和服务端的一致
 *
 * @author Wangpei
 */
public class User implements Parcelable {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected User(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        /**
         * 这里的顺序一定要和Parcel in这个的顺序一直，否则会有问题！！！
         */
        parcel.writeInt(id);
        parcel.writeString(name);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}