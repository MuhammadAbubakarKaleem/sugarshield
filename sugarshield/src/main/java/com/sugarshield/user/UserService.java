package com.sugarshield.user;

public class UserService {

    private final UserDao userDao;

    public UserService(){
        userDao = new UserDao();
    }

    public User loginUser(String email, String password){
        User user = userDao.getUserByEmail(email);
        if(user!=null){
            if(user.getPassword().equals(password)){
               return user;
            }
        }
        return null;
    }

    public void getAllUser (){

        for (int i = 0; i < userDao.getAllUsers().size() ; i++) {
            System.out.println(userDao.getAllUsers().get(i).toString());
        }
    }

}
