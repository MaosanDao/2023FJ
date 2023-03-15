package com.vangelis.service.aidl;

import android.os.RemoteException;

/**
 * Function：AIDL实现
 * Created on 2023/3/15.
 * Comment：
 *  继承目标，需要在AIDL文件生成后，且需要BUILD项目后才能看到
 *
 * @author Wangpei
 */
public class IProcessInfoImpl extends IProcessInfo.Stub{
    @Override
    public String getProcessMsg() throws RemoteException {
        return "这个是远程service给的值";
    }
}