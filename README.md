# figma

The Customised Android Movie Application with Firebase Authentication, Realtime Database as well as 
TMDB database implementation of API's. continues to future plans for payment gateway implementation
for Subcriber to watch LATEST movies.  

Development Phase 1: **Completed*
    ~ Splash Screen, Login Page
    
    1.1. Splash Screen:
         The screen with Application logo while launching the application.
            
    1.2. Login Page : 
         followed by Phone Authentication and GoogleSign In Authentication using Firebase Database.

    1.3. Phone Authentication:
         Contain the mobile Numver with a Constant CC (+91) IND, follows to OTP verification.
           
    1.4. GoogleSign In:
         Google Account Sign In.

Development Phase 2: **In Process*
    ~ **Navigation Drawer** customization for fragment Layout in Navigation drawer using fragment 
    instead of menu items for navigation, then **BottomNavigation** using menu items with help of
    supportive fragments.

    2.1. Bottom Navigation:
         The Bottom Navigation View is created using menu items and frameLayout to make movements 
         of fragments with respect to menu items.

    2.2. Navigation Drawer:
         **In Progress**


Development Phase 3:
    ~ *Implementation of API* for HOME Fragment, Movie Fragment, Series Fragment, Search Fragment.

    3.1. HomeFragment:
         This page Contain Movie Carousel and Various Adapter classes ("Need to minimize it by 
         single Adapter)", As well as movie and series types.

    3.2. MovieFragment:
         This page contain Movie Carousel and RecyclerView to get movies.

    3.3. SeriesFragment:
         This page contain Series Carousel and RecyclerView to get Series.

    3.4. SearchFragment:
         This Page contain Mix search bar to serach movie or series, accross the application.

Development Phase 4:
    ~ **Implement onClick Functionality** on each item of each fragment **w.r.t. position*.
