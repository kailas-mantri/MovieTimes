# figma

MovieTimes is a mobile application that allows users to discover and explore movies and TV shows easily. It provides a user-friendly interface to browse through various genres, view movie details, watch trailers, and add movies to the wishlist.

# Features
Browse Trending Movies and TV Shows
Filter Movies and TV Shows by Genres
View Movie Details and Synopsis
Watch Trailers of Movies
Add Movies to Wishlist

# Screenshots

# Tech Stack
Java
SQLite Database
Retrofit for API calls
Glide for Image Loading
Material Design Components
RecyclerView for List Display

- Upcoming Updates:
    Kotlin, LiveData, ViewModel


#Development Phases.
Phase 1: *Splash Screen, Login Page*
    
    1.1. Splash Screen:
         The launch screen of application.
            
    1.2. Login Page : 
         followed by 'Phone & GoogleSignIn' Authentication using Firebase Database.
         
         1.2.1. Phone Authentication:
             Authorization with mobile Number with OTP verification.
           
    1.4. GoogleSign In:
         Google Account Sign In.

Phase 2: Customise **BottomNavigation** using menu items with supportive fragments.

    Upcoming Feature: *Navigation Drawer* for Navigation using fragment instead of menu items for navigation.

    2.1. Bottom Navigation:
         The BottomNavigation View using menu items and frameLayout Manager to switch fragments with respect to menu Id.

    2.2. Navigation Drawer:
         **In Progress**

Phase 3: *API Implementation* - (HOME, Movie, Series, Search) Fragment.
    ~ POJO class creation, Adapter creation. 

    3.1. HomeFragment:
         Home has movie and series types in RecyclerView.

    3.2. MovieFragment:
         This page has movies Carousel and RecyclerView.

    3.3. SerisFragment:
         This page has Series Carousel and RecyclerView.

    3.4. SearchFragment:
         This Page has (MIX - API call) to SearchBar for movie/series search and recent search.

Phase 4: *Handling of SearchFunctionality and MovieDetailsActivity*
    ~ Activation of OnItemClicked Function with respect to id, API Implementation, Adapter Creation, Inplicite Intent, Collapsing Toolbar.


Phase 5: _*SeriesDetailsActivity Design and implementations*_
