// ServiceIProcessInfo.aidl
package com.vangelis.fj2023;
// Declare any non-default types here with import statements
//注意：这里要手动引入：：：在实体的aidl中需要手动将实体的路径用import引入
import com.vangelis.fj2023.User;
/**
* 这个直接从AS中新建即可
*
*/
interface IProcessInfo {
    //定义传输的方法以及内容
    String getProcessMsg();
    List<User> getUsers();
}