package com.saintsapp.saintsserver;

public class View {
    //Enclosing type to define User views
     public static interface UserView {
          //External View for User 
          public static interface User {
          }
          //Intenal View for User, will inherit all filds in External
          public static interface Admin extends User {
          }
       
    }
}