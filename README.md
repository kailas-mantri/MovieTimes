# MovieTimes


MovieTimes is a mobile application that allows users to discover and explore movies and TV shows easily. It provides a user-friendly interface to browse through various genres, view movie details, watch trailers, and add movies to the wishlist.


### Features

- Add Movies, Series to Wishlist
- Browse Media Shows by Trending, Popular, etc.
- Filter Movies and TV Shows by Genres
- View Movie, Series Details and Synopsis
- Watch Trailers of Movies
- Architecture MVC model.


### Tech Stack

- Java with Android SDK for development.
- Firebase Authentication for secure user login.
- Material Design components for an intuitive user interface.
- Library implementation - Retrofit, Glide, ImageSlideshow.
- RESTful APIs implementation - to Get movie, TV details.
- SQLite database for local watchLists and searchQuery management.
/* - YouTube API for trailer integration.*/

## Screenshots

<img src="img.png" width="20%" height="20%">  <img src="img_1.png" width="20%" height="20%">  <img src="img_2.png" width="20%" height="20%">  <img src="img_3.png" width="20%" height="20%"> <img src="img_4.png" width="20%" height="20%"> <img src="img_5.png" width="20%" height="20%"> <img src="img_6.png" width="20%" height="20%">    


### Development Phases.

#### Phase 1: *SplashScreen, LoginActivity, BottomSheetDialog*.

* Firebase Authentication 
  * 1.0. Mobile number authentication using OTP.
  * 1.1. Authentication by GoogleSign In.


#### Phase 2: *Customise BottomNavigation, Navigation Drawer, View fragments*.

* 2.0. Bottom Navigation
  * Implementation of view's using menu item.

* 2.1. Navigation Drawer 
  * Implementation of Navigation Drawer to switch different fragment and community options.


#### Phase 3: *Layout Design and Data Handling*


Customization of Layouts, Fragments, Drawables, Theme, Design, Activities.


#### Phase 4: *RESTful API Implementation*.


Implementation of Model class, Adapters. 

* 3.0. Search:
  * Search History, Search Result.
  * On Search - query added to recent search history & provided query related results. 


#### Phase 5: *Activity - brief details*.

  * On click of item  show details of it (Movie/TV shows).
  * Implementation - RESTful API, Adapter, Implicit & Explicit Intent, Collapsing Toolbar, Fetching Season and episode.


#### Phase 6: *Local Database*.


SQLite database

  * WishList and Search management - handling's wrt UI.


#### Phase 7: *New Features Add on word's*

* New Features 
  * LiveData, ViewModel, database modification, Complete change in programming language
