# CVApp

1 minute youtube video demo:
[https://youtu.be/l4EAQR3y0qQ](https://youtu.be/l4EAQR3y0qQ)

### Architecture

It's very straightforward and common, but some notes:

We have 2 data sources for our CV: Local and Remote. 
Local can load CV from Assets and File. For Remote we are using json file hosted at Dropbox.

On every app start we fetch remote version and compare it with local, if needed we 
store new version in our files directory as a file (later we will use this file, not assets).

We compare 2 CV by "last_update" value provided in json.

This gives us an ability to display CV all the time, with or without an internet connection.
Even if we won't have an internet connection on the first run, we can still display CV.

### UI

Once we receive our CV model, we will cut it into sections to be able to display inside RecyclerView.
This gives us an ability to display CV of any size and in a very performant way.
